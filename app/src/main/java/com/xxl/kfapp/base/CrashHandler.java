package com.xxl.kfapp.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import talex.zsw.baselibrary.util.CalendarUtil;
import talex.zsw.baselibrary.util.klog.KLog;

/**
 * 作者:xnn
 * 描述:程序异常捕捉
 */
public class CrashHandler implements UncaughtExceptionHandler {

    public static final String TAG = CrashHandler.class.getSimpleName(); //tag
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");  // 用于格式化日期,作为日志文件名的一部分
    private Map<String, String> infos = new HashMap<>(); // 用来存储设备信息和异常信息
    private Context mContext; //程序的上下文环境
    private BaseApplication baseApplication; //Application

    /**
     * 系统默认的UncaughtException处理类
     */
    private UncaughtExceptionHandler mDefaultHandler;
    /**
     * CrashHandler实例
     */
    private static CrashHandler INSTANCE;

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;
    }

    /**
     * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
     */
    public void init(Context ctx) {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = ctx;
    }

    public void setApplication(BaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && null != mDefaultHandler) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                String excString = sw.toString();
                KLog.e(excString);
                save(excString);
//                new AlertDialog.Builder(mContext).setTitle("error")
//                        .setMessage(excString).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // 如果自己处理了异常，则不会弹出错误对话框，则需要手动退出app
            baseApplication.exit();//退出程序
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @return true代表处理该异常，不再向上抛异常，
     * false代表不处理该异常(可以将该log信息存储起来)然后交给上层(这里就到了系统的异常处理)去处理，
     * 简单来说就是true不会弹出那个错误提示框，false就会弹出
     */
    private boolean handleException(final Throwable ex) {
        if (ex != null) {
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                String excString = sw.toString();//打印错误
                KLog.e(excString);
//                collectDeviceInfo(mContext);//收集设备参数信息
//                saveCrashInfo2File(ex);//保存错误信息到文件中
                save(excString);
                return true;
            } catch (Exception e) {
                return false;

            }
        }
        return false;
    }

    //弹出toast提示信息
    private void toastPrompt(final String prompt) {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext.getApplicationContext(), prompt,
                        Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
    }

    /**
     * 收集设备参数信息
     */
    private void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info",
                        e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {
        KLog.d("保存错误信息到文件");
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                String path = "/sdcard/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path
                        + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }


    public void save(String text) {
        FileOutputStream fop = null;
        File file;
        try {
            String strPath = getInnerSDCardPath() + "/zxy/";
            isFile(strPath);

            String path = strPath + CalendarUtil.getDateTimeNow(CalendarUtil.STR_FOMATER_DATA_TIME) + ".txt";

            KLog.d(path);
            file = new File(path);
            fop = new FileOutputStream(file);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] contentInBytes = text.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void isFile(String s) {
        File file = new File(s);
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("//不存在");
            file.mkdir();
        } else {
            System.out.println("//目录存在");
        }
    }

    /**
     * 获取内置SD卡路径
     */
    public static String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

}

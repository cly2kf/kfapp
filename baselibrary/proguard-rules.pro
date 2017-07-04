# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/tale/work/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# 混淆时不使用大小写混合，混淆后的类名为小写
# windows下的同学还是加入这个选项吧(windows大小写不敏感)
-dontusemixedcaseclassnames

# 指定不去忽略非公共的库的类
# 默认跳过，有些情况下编写的代码与类库中的类在同一个包下，并且持有包中内容的引用，此时就需要加入此条声明
-dontskipnonpubliclibraryclasses

# 指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers

# 不做预检验，preverify是proguard的四个步骤之一
# Android不需要preverify，去掉这一步可以加快混淆速度
-dontpreverify

# 有了verbose这句话，混淆后就会生成映射文件
# 包含有类名->混淆后类名的映射关系
# 然后使用printmapping指定映射文件的名称
-verbose
-printmapping priguardMapping.txt

# 指定混淆时采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不改变
-optimizations !code/simplification/artithmetic,!field/*,!class/merging/*

# 保护代码中的Annotation不被混淆
# 这在JSON实体映射时非常重要，比如fastJson
-keepattributes *Annotation*

# 避免混淆泛型
# 这在JSON实体映射时非常重要，比如fastJson
-keepattributes Signature

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

# 保留所有的本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留了继承自Activity、Application这些类的子类
# 因为这些子类有可能被外部调用
# 比如第一行就保证了所有Activity的子类不要被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.annotation.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.**

# 如果有引用android-support-v4.jar包，可以添加下面这行
-keep public class com.null.test.ui.fragment.** {*;}

# 保留Activity中的方法参数是view的方法，
# 从而我们在layout里面编写onClick就不会影响
-keepclassmembers class * extends android.app.Activity {
    public void * (android.view.View);
}

# 枚举类不能被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留自定义控件(继承自View)不能被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(***);
    *** get* ();
}

# 保留Parcelable序列化的类不能被混淆
-keep class * implements android.os.Parcelable{
    public static final android.os.Parcelable$Creator *;
}

# 保留Serializable 序列化的类不被混淆
-keepclassmembers class * implements java.io.Serializable {
   static final long serialVersionUID;
   private static final java.io.ObjectStreamField[] serialPersistentFields;
   !static !transient <fields>;
   private void writeObject(java.io.ObjectOutputStream);
   private void readObject(java.io.ObjectInputStream);
   java.lang.Object writeReplace();
   java.lang.Object readResolve();
}

# 对R文件下的所有类及其方法，都不能被混淆
-keepclassmembers class **.R$* {
    *;
}

# 对于带有回调函数onEvent的，不能混淆
-keepclassmembers class ** {
    public void onEvent*(**);
}
-keepclassmembers class ** {
public void onEvent(**);
}

# 保留实体类和成员不被混淆
-keep class com.null.test.entities.** {
    public void set*(***);
    public *** get*();
    public *** is*();
}
# 对WebView的处理
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String);
}
# 对JavaScript的处理
-keepclassmembers class com.null.test.MainActivity$JSInterfacel {
    <methods>;
}

####################baseLibrary#####################
-keep class talex.zsw.baselibrary.** {*;}
-dontwarn talex.zsw.baselibrary.**
-keep class talex.zsw.baselibrary.view.** {*;}
-dontwarn talex.zsw.baselibrary.view.**
-keep class com.sendinfo.travelconverge.database.** {*;}
-dontwarn com.sendinfo.travelconverge.database.**
-keep class com.sendinfo.travelconverge.entitys.** {*;}
-dontwarn com.sendinfo.travelconverge.entitys.**
-keep class com.sendinfo.travelconverge.mpush.** {*;}
-dontwarn com.sendinfo.travelconverge.mpush.**
-keep class com.sendinfo.travelconverge.test.** {*;}
-dontwarn com.sendinfo.travelconverge.test.**
-keep class com.sendinfo.travelconverge.widget.** {*;}
-dontwarn com.sendinfo.travelconverge.widget.**
-keep class com.sendinfo.travelconverge.update.** {*;}
-dontwarn com.sendinfo.travelconverge.update.**
-keep class com.sendinfo.travelconverge.util.** {*;}
-dontwarn com.sendinfo.travelconverge.util.**
-keep class com.sendinfo.travelconverge.zxing.** {*;}
-dontwarn com.sendinfo.travelconverge.zxing.**

####################友盟#####################
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
   }
-keep public class com.sendinfo.travelconverge.R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

####################微博#####################
-keep class com.sina.** {*;}
-dontwarn com.sina.**

####################QQ&微信#####################
-keep class com.sendinfo.travelconverge.wxapi.** {*;}
-dontwarn com.sendinfo.travelconverge.wxapi.**
-keep class com.tencent.** {*;}
-dontwarn com.tencent.**

####################阿里,AndFix#####################
-keep class com.alipay.** {*;}
-dontwarn com.alipay.**
-keep class com.ta.utdid2.** {*;}
-dontwarn com.ta.utdid2.**
-keep class com.ut.device.** {*;}
-dontwarn com.ut.device.**
-keep class org.json.alipay.** {*;}
-dontwarn org.json.alipay.**
-keep class * extends java.lang.annotation.Annotation
-keepclasseswithmembernames class * {
    native <methods>;
}

################gson##################
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.** {
    <fields>;
    <methods>;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-dontwarn com.google.gson.**
-dontwarn com.google.**

####################universal-image-loader########
-keep class com.nostra13.universalimageloader.** {*;}
-dontwarn com.nostra13.universalimageloader.**

####################zxing#####################
-keep class com.google.zxing.** {*;}
-dontwarn com.google.zxing.**


####################support#####################
-keep class android.support.** { *; }
-dontwarn android.support.**

####################Butter Knife#####################
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

####################Retrofit#####################
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

####################RxJava RxAndroid#####################
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

####################simpl-xml#####################
-dontwarn org.simpleframework.**
-keep class org.simpleframework.** { *; }

####################Gson#####################
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson 下面替换成自己的实体类
-keep class com.example.bean.** { *; }

####################OkHttp3#####################
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
-keep class okio.**{*;}

#okgo
-dontwarn com.lzy.okgo.**
-keep class com.lzy.okgo.**{*;}

#okrx
-dontwarn com.lzy.okrx.**
-keep class com.lzy.okrx.**{*;}

#okserver
-dontwarn com.lzy.okserver.**
-keep class com.lzy.okserver.**{*;}

####################Jackson#####################
-dontwarn com.fasterxml.jackson.**
-keep class com.fasterxml.jackson.** { *;}

####################ormlite#####################
-dontwarn com.j256.**
-keep class com.j256.** { *;}
-keep class com.j256.**
-keepclassmembers class com.j256.** { *; }
-keep enum com.j256.**
-keepclassmembers enum com.j256.** { *; }
-keep interface com.j256.**
-keepclassmembers interface com.j256.** { *; }
-keepclassmembers class classpath.** {
  public *;
}

####################dom4j#####################
-dontwarn org.dom4j.**
-keep class org.dom4j.** { *;}

####################xstream#####################
-dontwarn com.thoughtworks.xstream.**
-keep class com.thoughtworks.xstream.** { *;}

####################MPChart#####################
-dontwarn com.github.mikephil.**
-keep class com.github.mikephil.** { *; }

####################Picasso#####################
-dontwarn com.squareup.picasso.**
-keep class com.squareup.picasso.** { *;}

####################图片选择工具#####################
-dontwarn io.valuesfeng.picker.**
-keep class io.valuesfeng.picker.** { *;}
-dontwarn com.bumptech.glide.**
-keep class com.bumptech.glide.** { *;}

####################第三方登录分享工具#####################
-dontwarn me.shaohui.shareutil.**
-keep class me.shaohui.shareutil.** { *;}

###################高德地图####################
#3D 地图
-keep class com.amap.api.mapcore.**{*;}
-keep class com.amap.api.maps.**{*;}
-keep class com.autonavi.amap.*{*;}
-dontwarn com.amap.api.mapcore.**
-dontwarn com.amap.api.maps.**
-dontwarn com.autonavi.amap.**

#定位

-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}
-dontwarn com.amap.api.location.**
-dontwarn com.amap.api.fence.**
-dontwarn com.autonavi.aps.amapapi.model.**

#搜索

-keep class com.amap.api.services.**{*;}
-dontwarn com.amap.api.services.**

#2D地图

-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}
-dontwarn com.amap.api.maps2d.**
-dontwarn com.amap.api.mapcore2d.**

#导航

-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}
-dontwarn com.amap.api.navi.**
-dontwarn com.autonavi.**

#-libraryjars libs/AMap_Location_V3.3.0_20170118.jar
#
#-libraryjars libs/AMap_Search_V4.0.0_20170111.jar
#
#-libraryjars libs/Android_Map3D_SDK_V4.1.3_20161208.jar

-dontwarn com.amap.apis.**

-dontwarn com.amap.api.**

-dontwarn com.a.a.**

-dontwarn com.autonavi.**

-keep class com.amap.api.**{*;}

-keep class com.autonavi.**{*;}

-keep class com.a.a.**{*;}

###################other####################
# slidingmenu 的混淆
-dontwarn com.jeremyfeinstein.slidingmenu.lib.**
-keep class com.jeremyfeinstein.slidingmenu.lib.** { *; }

# ActionBarSherlock混淆
-dontwarn com.actionbarsherlock.**
-keep class com.actionbarsherlock.** { *; }
-keep interface com.actionbarsherlock.** { *; }
-keep class * extends java.lang.annotation.Annotation { *; }
-keepclasseswithmembernames class * {
    native <methods>;
}

-keep class com.jph.android.entity.** {
    <fields>;
    <methods>;
}

-dontwarn android.support.**
-dontwarn com.slidingmenu.lib.app.SlidingMapActivity
-keep class android.support.** { *; }
-keep class com.actionbarsherlock.** { *; }
-keep interface com.actionbarsherlock.** { *; }
-keep class com.slidingmenu.** { *; }
-keep interface com.slidingmenu.** { *; }
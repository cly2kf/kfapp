package talex.zsw.baselibrary.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 作用: Xml解析,拼装类
 * 作者: 赵小白 email:edisonzsw@icloud.com 
 * 日期: 2016/11/21 14:05 
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class XmlUtil
{

	/**
	 * 将xml解析成指定泛型并返回
	 *
	 * @param string xml数据
	 * @param <T>    指定泛型
	 */
	public static <T> T getObject(String string, Class<T> clazz)
	{
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(clazz);
		xstream.ignoreUnknownElements();
		return (T) xstream.fromXML(string);
	}

	/**
	 * @param object 指定类型
	 * @param <T>    指定泛型
	 * @return 将指定类变成XML型数据返回
	 */
	public static <T> String getData(Object object, Class<T> cls)
	{
		XStream xstream = new XStream();
		xstream.processAnnotations(cls);
		xstream.ignoreUnknownElements();
		return xstream.toXML(object);
	}
}

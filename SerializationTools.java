package com.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 序列化工具类
 * 
 * @author SHED 2012-07-31
 */
public class SerializationTools {

	/**
	 * 序列化对象为String字符串
	 * 
	 * @param o
	 *            Object
	 * @return String
	 * @throws Exception
	 */
	public static String writeObject(Object o) {
		String result = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(o);
			oos.flush();
			oos.close();
			bos.close();
			result = new BASE64Encoder().encode(bos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 反序列化字符串为对象
	 * 
	 * @param object
	 *            String
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T readObject(String object, Class<T> objType) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(
					new BASE64Decoder().decodeBuffer(object));
			ObjectInputStream ois = new ObjectInputStream(bis);
			Object o = ois.readObject();
			bis.close();
			ois.close();
			return (T) o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

package org.cmcc.ecip.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import org.apache.commons.io.serialization.ValidatingObjectInputStream;

/**
 * @ProjectName R-core
 * @PackageName org.cmcc.ecip.R.util
 * @ClassName SerializableUtil
 * @Description
 * @author guiyn
 * @date 2018年12月14日 上午11:18:34
 * @version 2018年12月14日 上午11:18:34
 * 
 */
public class SerializableUtil {

	/**
	 * 对象序列化 为String
	 * 
	 * @author guiyn
	 * @date 2018年12月14日 上午11:15:54
	 * @version 2018年12月14日
	 * @param obj
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static String serializeToString(Object obj) throws Exception {
//		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
//		ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
//		objOut.writeObject(obj);
//		byte[] str = byteOut.toByteArray();
		byte[] str = serializeToBytes(obj);
		return Base64.getEncoder().encodeToString(str);
	}

	public static byte[] serializeToBytes(Object obj) throws Exception {

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
		try {
			objOut.writeObject(obj);
			byte[] bytes = byteOut.toByteArray();
			return bytes;
		} finally {
			objOut.close();
			byteOut.close();
		}

	}

	/**
	 * 反序列化 字符串序列化为对象
	 * 
	 * @author guiyn
	 * @date 2018年12月14日 上午11:16:19
	 * @version 2018年12月14日
	 * @param str
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static <T> T deserializeToObject(String str, Class<T> c) throws Exception {

		byte[] s = Base64.getDecoder().decode(str);

		// ObjectInputStream ois = new ObjectInputStream(new
		// BufferedInputStream(new ByteArrayInputStream(s)));
		return deserializeToObject(s, c);
	}

	@SuppressWarnings("unchecked")
	public static <T> T deserializeToObject(byte[] bytes, Class<T> c) throws Exception {
		ValidatingObjectInputStream ois = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			// Use ValidatingObjectInputStream instead of InputStream
			ois = new ValidatingObjectInputStream(bais);

			// 只允许反序列化SerialObject class
			ois.accept(c);
			return (T) ois.readObject();
		} finally {
			if (ois != null)
				ois.close();
		}
	}
}

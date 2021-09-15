package org.cmcc.ecip.examples.zip;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator; 
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
 

 

	public static Key generator() throws Exception {
		// 获得KEY

		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

		keyGenerator.init(128);//默认密钥长度为：

		SecretKey secretKey = keyGenerator.generateKey();

		byte [] keyBytes = secretKey.getEncoded();

		//key转换

		Key key = new SecretKeySpec(keyBytes, "AES");
		return key;
	}

	public static byte[] ENCRYPT(byte[] b1, Key k) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

		cipher.init(Cipher.ENCRYPT_MODE, k);// 设置模式为加密

		byte[] result = cipher.doFinal(b1);
		return result;
	}

	/**
	 * 解密
	 * 
	 * @param b1
	 * @param k
	 * @return
	 * @throws Exception
	 */
	public static byte[] DECRYPT(byte[] b1, Key k) throws Exception {

		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

		cipher.init(Cipher.DECRYPT_MODE, k);// 设置模式为解密

		byte[] result = cipher.doFinal(b1);

		return result;

	}
}

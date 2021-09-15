package org.cmcc.ecip.examples.zip;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator; 
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PBE {
 
	static byte [] salt = new SecureRandom().generateSeed(8);
 

	public static Key generator() throws Exception {
		// 获得KEY

		String password = "imooc";

		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());//生成密钥转换对象

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");

		Key key = factory.generateSecret(pbeKeySpec);
		return key;
	}

	public static byte[] ENCRYPT(byte[] b1, Key k) throws Exception {
//		PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
		
		Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");

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

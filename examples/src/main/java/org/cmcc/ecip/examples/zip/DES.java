package org.cmcc.ecip.examples.zip;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator; 
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;

public class DES {
 

 

	public static Key generator( ) throws Exception {
		//获得KEY

		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");

		keyGenerator.init(56);//设置为默认值56即可

		//获得KEY对象

		SecretKey secrekeyone = keyGenerator.generateKey();

		byte [] byteskey = secrekeyone.getEncoded();

		//KEY转换

		DESKeySpec deskeyspec = new DESKeySpec(byteskey);

		SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");

		Key secerkeytwo = factory.generateSecret(deskeyspec);
	
		return secerkeytwo;
	}

	public static byte[] ENCRYPT(byte[] b1, Key k) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

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

		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

		cipher.init(Cipher.DECRYPT_MODE, k);// 设置模式为解密

		byte[] result = cipher.doFinal(b1);

		return result;

	}
}

package org.cmcc.ecip.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Digest {
	public static String MD5Digest(String src) {
		byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
            		src.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("not have md5 digest ");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
	}

	
}

// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   PublicSecurity.java

package org.cmcc.ecip.common.tw.service;



import com.trustwork.crypto.Digest;
import com.trustwork.crypto.digests.MD5Digest;
import com.trustwork.crypto.digests.SHA1Digest;
import com.trustwork.crypto.engines.IDEAEngine;
import com.trustwork.crypto.modes.CBCBlockCipher;
import com.trustwork.crypto.paddings.PaddedBufferedBlockCipher;
import com.trustwork.crypto.params.KeyParameter;
import com.trustwork.jce.provider.TrustWorkProvider;
import com.trustwork.openssl.PEMReader;
import com.trustwork.util.encoders.Base64;
import com.trustwork.util.encoders.Hex;



import java.io.*;

import java.security.*;
import java.security.cert.X509Certificate;
import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 加密机客户端调用服务 公共类
 * 
 * @ProjectName R-twcmcc
 * @PackageName com.cmcc.boss.security
 * @ClassName PublicSecurity
 * @author guiyn
 * @date 2018年12月6日 下午5:34:30
 * @version 2018年12月6日 下午5:34:30
 * 
 */

public class PublicSecurity {

	static Logger log= LoggerFactory.getLogger("TW"); 


	
	
	private static String halfTop = "Truework";

	
	

	public static String MD5Digest(String src) {
		Digest digest = new MD5Digest();
		byte resBuf[] = new byte[digest.getDigestSize()];
		src = new String(Hex.encode(src.getBytes()));
		byte bytes[] = Hex.decode(src);
		digest.update(bytes, 0, bytes.length);
		digest.doFinal(resBuf, 0);
		String resStr = new String(Hex.encode(resBuf));
		return resStr;
	}

	public static String SHA1Digest(String src) {
		Digest digest = new SHA1Digest();
		byte resBuf[] = new byte[digest.getDigestSize()];
		src = new String(Hex.encode(src.getBytes()));
		byte bytes[] = Hex.decode(src);
		digest.update(bytes, 0, bytes.length);
		digest.doFinal(resBuf, 0);
		String resStr = new String(Hex.encode(resBuf));
		return resStr;
	}

	public static String EncryptWithCert(String keystr, String cert_pem) throws Exception {
		StringReader sRd = new StringReader(cert_pem);
		PEMReader pemRd = new PEMReader(sRd, null);
		X509Certificate x509 = null;
		x509 = (X509Certificate) pemRd.readObject();
		pemRd.close();
		Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding", "TW");
		if (c == null) {
			return null;
		} else {
			c.init(1, x509);
			byte out[] = c.doFinal(keystr.getBytes());
			String pPem = new String(Hex.encode(out));
			return pPem;
		}

	}



	public static boolean validateCert(String certId, String cert, PublicKey pubkey) {
		Security.addProvider(new TrustWorkProvider());
		String cert_pem = String.valueOf(String.valueOf((new StringBuffer("-----BEGIN CERTIFICATE-----\n")).append(cert)
				.append("\n-----END CERTIFICATE-----")));
		StringReader sRd = new StringReader(cert_pem);
		PEMReader pemRd = new PEMReader(sRd, null);
		X509Certificate x509 = null;
		try {
			x509 = (X509Certificate) pemRd.readObject();
			x509.verify(pubkey);
			pemRd.close();
		} catch (Exception e) {
			log.error("  can't read cert " + certId + " .", e);
			return false;
		}
		return true;
	}

	public static String decryptPIN(String encpin, String halfBottom) throws Exception {
		String key = String.valueOf(halfTop) + String.valueOf(halfBottom);
		if (encpin == null)
			return null;
		if (key.length() != 16)
			return null;
		byte encpinfs[] = Base64.decode(encpin);
		byte IdeaKey[] = MD5Digest(key).getBytes();
		IdeaKey = Hex.decode(IdeaKey);

		ByteArrayOutputStream rst = null;
		try {
			rst = new ByteArrayOutputStream();
		} catch (Exception e) {
			if (rst != null) {
				rst.close();
				String s = null;
				return s;
			}
		}
		PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new IDEAEngine()));
		cipher.init(false, new KeyParameter(IdeaKey));
		int inBlockSize = encpinfs.length;
		byte outBufs[] = new byte[cipher.getOutputSize(inBlockSize)];
		try {
			int srcL = encpinfs.length;
			int outL = cipher.processBytes(encpinfs, 0, srcL, outBufs, 0);
			if (outL > 0)
				rst.write(outBufs, 0, outL);
			outL = cipher.doFinal(outBufs, 0);
			if (outL > 0)
				rst.write(outBufs, 0, outL);
			byte rstbyte[] = rst.toByteArray();
			rst.close();
			rst = null;
			String decryptfile = new String(rstbyte);
			String s2 = decryptfile;
			return s2;
		} catch (Exception e) {
			log.error("",e);
		}
		if (rst != null)
			rst.close();
		String s1 = null;
		return s1;
	}

}

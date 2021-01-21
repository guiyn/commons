// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   CenterSecurity.java

package org.cmcc.ecip.common.tw;

import com.trustwork.crypto.digests.SHA1Digest;
import com.trustwork.crypto.engines.IDEAEngine;
import com.trustwork.crypto.macs.HMac;
import com.trustwork.crypto.modes.CBCBlockCipher;
import com.trustwork.crypto.paddings.PaddedBufferedBlockCipher;
import com.trustwork.crypto.params.KeyParameter;
import com.trustwork.util.encoders.Hex;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

import org.cmcc.ecip.common.tw.cache.CertsTable;
import org.cmcc.ecip.common.tw.service.PublicSecurity;
import org.cmcc.ecip.common.tw.util.StringUtils;
import org.cmcc.ecip.common.tw.util.TwException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author guiyn
 *
 */

public class CenterSecurity {
	static Logger log = LoggerFactory.getLogger("TW");

	public CenterSecurity() {
	}

	/**
	 * 加密操作
	 * 
	 * @param pin    明文
	 * @param nodeid 机构编码
	 * @return
	 * @throws Exception
	 */
	public static String encryptPIN(String pin, String nodeid) throws Exception {
		log.debug("node id >> " + nodeid);

		if (pin == null) {
			throw new TwException(TwException.error_pin_null, "encryptPIN pin is null..");
		}
		if (nodeid == null) {
			throw new TwException(TwException.error_node_id_null, "encryptPIN nodeid is null..");
		}

		String key = getNewKey(nodeid);
		return encryptPINByKey(pin, key);

	}

	/**
	 * 加密操作
	 * 
	 * @param pin 明文
	 * @param key 机构密钥
	 * @return
	 * @throws Exception
	 */
	public static String encryptPINByKey(String pin, String key) throws Exception {

		log.debug("pin >> " + pin);
		log.debug("key >> " + key);
		if (pin == null) {
			throw new TwException(TwException.error_pin_null, "encryptPIN pin is null..");

		}
		if (key.length() != 32) {
			throw new TwException(TwException.error_key_length_not32,
					"encryptPIN nodeid key length is not 32 >>" + key);
		}
		byte srcdatabyte[] = pin.getBytes("utf-8");

		String enStr = encrypt(srcdatabyte, key);
		String md5 = PublicSecurity.MD5Digest(pin);
		if (log.isDebugEnabled())
			log.debug(enStr + " <<>>> " + md5);
		return enStr + md5;

	}

	/**
	 * 加密操作
	 * 
	 * @param srcdatabyte 需要加密的字节数组
	 * @param key         机构密钥
	 * @return
	 * @throws Exception
	 */
	protected static String encrypt(byte[] srcdatabyte, String key) throws Exception {

		if (key.length() != 32) {
			throw new TwException(TwException.error_key_length_not32,
					"encryptPIN nodeid key length is not 32 >>" + key);

		}
		byte[] data = cipherExecute(srcdatabyte, key, true);
		return new String(Hex.encode(data));
	}

	/**
	 * 解密 采用新旧密钥分别解密 带MD5验证
	 * 
	 * @param encpin 密文
	 * @param nodeid 机构ID
	 * @return
	 * @throws Exception
	 */
	public static String decryptPIN(String encpin, String nodeid) throws Exception {

		if (encpin.length() < 48) {
			throw new TwException(TwException.error_pin_length_less48, " encpin length is to short");
		}

		String key = getNewKey(nodeid);
		String val = decryptAndMD5(encpin, key);
		if (val != null) {
			return val;
		}
		key = getOldKey(nodeid);
		return decryptAndMD5(encpin, key);
	}

	/**
	 * 解密需要MD5认证的数据
	 * 
	 * @param encpin
	 * @param key
	 * @return
	 */
	public static String decryptAndMD5(String encpin, String key) throws Exception {
		String s1 = encpin;
		if (s1.length() < 48) {
			log.error("decryptPIN encpin the length of encpin must be at least 48.");
			throw new TwException(TwException.error_pin_length_less48,
					"decryptPIN encpin the length of encpin must be at least 48.");
		}
		encpin = s1.substring(0, s1.length() - 32);
		String digest = s1.substring(s1.length() - 32);

		if (key.length() != 32) {
			log.error("decryptPIN key has error length is not 32 >> " + key);
			throw new TwException(TwException.error_key_length_not32,
					"decryptPIN key has error length is not 32 >> " + key);
		}

		String decryptfile = decrypt(encpin, key);

		if (!digest.equals(PublicSecurity.MD5Digest(decryptfile))) {
			log.error("validate digest error. decryptPIN. " + decryptfile);

			throw new TwException(TwException.error_validate_digest, "validate digest error.");
		} else {
			return decryptfile;
		}

	}

	/**
	 * 解密不需要MD5验证的数据
	 * 
	 * @param encpin
	 * @param key
	 * @return
	 * @throws Exception
	 */
	protected static String decrypt(String encpin, String key) throws Exception {

		byte[] data = Hex.decode(encpin);
		byte[] dedata = cipherExecute(data, key, false);
		return new String(dedata, "utf-8");

	}

	/**
	 * 加解密操作方法
	 * 
	 * @param data 加密字节数据
	 * @param key  密钥
	 * @param flag 操作类型 true 加密 false 解密
	 * @return
	 * @throws Exception
	 */
	private static byte[] cipherExecute(byte[] data, String key, boolean flag) throws Exception {

		ByteArrayOutputStream rst = new ByteArrayOutputStream();
		PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new IDEAEngine()));
		byte IdeaKey[] = Hex.decode(key);
		cipher.init(flag, new KeyParameter(IdeaKey));
		int inBlockSize = data.length;
		byte[] outBufs = new byte[cipher.getOutputSize(inBlockSize)];
		try {
			int srcL = data.length;
			int outL = cipher.processBytes(data, 0, srcL, outBufs, 0);
			if (outL > 0)
				rst.write(outBufs, 0, outL);
			outL = cipher.doFinal(outBufs, 0);
			if (outL > 0)
				rst.write(outBufs, 0, outL);
			byte[] rstbyte = rst.toByteArray();
			return rstbyte;

		} catch (Exception e) {
			throw new TwException(TwException.error_cipher_execute, " sys error ", e);
		} finally {
			if (rst != null) {
				rst.close();
				rst = null;
			}
		}

	}

	/**
	 * 
	 * @param srcdata
	 * @param nodeid
	 * @return
	 */
	public static String generateSha(String srcdata, String nodeid) throws Exception {
		if (srcdata == null) {
			log.error("generateSha srcdata is null");
			return null;
		}
		if (nodeid == null) {
			log.error("generateSha nodeid is null");
			return null;
		}

		String key = null;
		try {
			key = getNewKey(nodeid);
		} catch (Exception re) {
			log.error("generateSha :", re);
			return null;
		}
		if (key.length() != 32) {
			log.error("key length is not 32");
			return null;
		} else {
			byte ideaKey[] = Hex.decode(key);
			byte srcdatabyte[] = srcdata.getBytes();
			HMac hmacl = new HMac(new SHA1Digest());
			hmacl.init(new KeyParameter(ideaKey));
			byte keyDigestBuff[] = new byte[hmacl.getMacSize()];
			hmacl.update(srcdatabyte, 0, srcdatabyte.length);
			hmacl.doFinal(keyDigestBuff, 0);
			String generateDigest = new String(Hex.encode(keyDigestBuff));
			return generateDigest;
		}
	}

	public static boolean validSha(String srcdata, String macdata, String key) {
		if (srcdata == null) {
			log.error("isValidSha srcdata is null");
			return false;
		}
		if (macdata == null) {
			log.error("isValidSha macdata is null");
			return false;
		}
		if (key.length() != 32) {
			log.error("isValidSha key length is not 32");
			return false;
		}
		byte ideaKey[] = Hex.decode(key);
		byte srcdatabyte[] = srcdata.getBytes();
		HMac hmacl = new HMac(new SHA1Digest());
		hmacl.init(new KeyParameter(ideaKey));
		byte keyDigestBuff[] = new byte[hmacl.getMacSize()];
		hmacl.update(srcdatabyte, 0, srcdatabyte.length);
		hmacl.doFinal(keyDigestBuff, 0);
		String generateDigest = new String(Hex.encode(keyDigestBuff));
		return generateDigest.equals(macdata);
	}

	public static boolean isValidSha(String srcdata, String macdata, String nodeid) throws Exception {
		String key = getNewKey(nodeid);
		boolean result = validSha(srcdata, macdata, key);
		if (!result) {
			String oldkey = getOldKey(nodeid);
			return validSha(srcdata, macdata, oldkey);
		} else {
			return result;
		}

	}

	public static String switchSHA(String srcdata, String oldmacdata, String oldnodeid, String newnodeid)
			throws Exception {
		if (srcdata == null) {
			log.error("switchSHA srcdata");
			return null;
		}
		if (oldmacdata == null) {
			log.error("switchSHA macdata");
			return null;
		}
		if (oldnodeid == null) {
			log.error("switchSHA ".concat(String.valueOf(String.valueOf(oldnodeid))));
			return null;
		}
		if (newnodeid == null) {
			log.error("switchSHA ".concat(String.valueOf(String.valueOf(newnodeid))));
			return null;
		}
		if (!isValidSha(srcdata, oldmacdata, oldnodeid))
			return null;
		else
			return generateSha(srcdata, newnodeid);
	}

	public static String switchPIN(String encrptPIN, String sendnodeid, String recvnodeid) throws Exception {

		if (sendnodeid == null) {
			throw new TwException(TwException.error_node_id_null, "switchPIN  sendnodeid  is null..");
		}
		if (recvnodeid == null) {
			throw new TwException(TwException.error_node_id_null, "switchPIN  recvnodeid  is null..");
		}

		if (encrptPIN == null) {
			throw new TwException(TwException.error_pin_null, "switchPIN encrptPIN is null..");
		}

		String srcdata = decryptPIN(encrptPIN, sendnodeid);
		if (srcdata == null) {
			throw new TwException(TwException.error_pin_decrypt_result_null, "switchPIN ,decryptPIN  result is null ");
		} else {
			String cryptodata = encryptPIN(srcdata, recvnodeid);
			return cryptodata;
		}
	}

	/**
	 * 20140918 glz the new package has no the below methods.So we copy the old
	 * method here.
	 * 
	 * 20140918 gyn add pin to md5
	 * 
	 */
	public static void encryptFile(String sourceFile, String destFile, String nodeid) throws Exception {
		InputStream is = null;
		OutputStream out = null;
		CipherInputStream cis = null;

		try {

			String pin = getNewKey(nodeid);

			byte[] keyBytes = Hex.decode(pin.substring(0, 16));
			SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			String pkey = PublicSecurity.MD5Digest(pin);
			is = new FileInputStream(sourceFile);
			out = new FileOutputStream(destFile);
			cis = new CipherInputStream(is, cipher);

			byte[] buffer = new byte[1024];

			int r = 0;

			out.write(pkey.getBytes());
			while ((r = cis.read(buffer)) > 0) {
				out.write(buffer, 0, r);
			}
		} finally {
			if (cis != null) {
				try {
					cis.close();
				} catch (IOException e) {
				}
			}

			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 文件解密
	 * 
	 * @param sourceFile
	 * @param destFile
	 * @param nodeid
	 * @throws Exception
	 */
	public static void decryptFile(String sourceFile, String destFile, String nodeid) throws Exception {
		InputStream is = null;
		OutputStream out = null;
		CipherOutputStream cos = null;

		try {

			String pin = getNewKey(nodeid);

			byte[] keyBytes = Hex.decode(pin.substring(0, 16));
			SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");

			Cipher cipher = Cipher.getInstance("DES");

			cipher.init(Cipher.DECRYPT_MODE, key);

			is = new FileInputStream(sourceFile);
			byte[] bpin = new byte[32];
			is.read(bpin, 0, 32);

			String s = new String(bpin);
			String pkey = PublicSecurity.MD5Digest(pin);
			if (!s.equals(pkey)) {
				throw new Exception("md5 key error..");
			}

			out = new FileOutputStream(destFile);
			cos = new CipherOutputStream(out, cipher);

			byte[] buffer = new byte[1024];

			int r = 0;

			while ((r = is.read(buffer)) > 0) {
				cos.write(buffer, 0, r);
			}

		} finally {
			if (cos != null) {
				try {
					cos.close();
				} catch (IOException e) {
				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}

			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}

	static String getNewKey(String nodeid) throws TwException {
		String key = "";
		try {
			key = CertsTable.getCert().getNewKey(nodeid);
		} catch (TwException e) {
			log.warn(e.getCode() + " >>>>> " + e.getMessage());
			try {
				try {
					key = RmiClient.getClient().getRMI().getnewkey(nodeid);
				} catch (Exception e1) {
					key = RmiClient.getClient().getNewRMI().getnewkey(nodeid);
				}

				log.debug("rmi get key " + nodeid + " >>>>> " + key);

			} catch (Exception re) {
				log.error("rmi get new key error ", e);
				throw new TwException(TwException.error_not_fand_by_nodeid,
						"tw rmi get new key error node id[" + nodeid + "]", e);
			}

			if (StringUtils.isTrimEmpty(key))
				throw new TwException(TwException.error_not_fand_by_nodeid,
						"tw rmi get new key error node id[" + nodeid + "]");
		}

		return key;
	}

	static String getOldKey(String nodeid) throws TwException {
		String key;
		try {
			key = CertsTable.getCert().getOldKey(nodeid);
		} catch (TwException e) {
			log.warn(e.getCode() + " >>>>> " + e.getMessage());
			try {
				try {
					key = RmiClient.getClient().getRMI().getnewkey(nodeid);	
				}catch(Exception e1)
				{
					key = RmiClient.getClient().getNewRMI().getnewkey(nodeid);
				}
				
			} catch (Exception re) {
				log.error("rmi get new key error ", e);
				throw new TwException(TwException.error_not_fand_by_nodeid,
						"tw rmi get old key error node id[" + nodeid + "]");
			}

			if (StringUtils.isTrimEmpty(key))
				throw new TwException(TwException.error_not_fand_by_nodeid,
						"tw rmi get old key error node id[" + nodeid + "]");
		}
		return key;

	}
}

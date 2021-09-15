package org.cmcc.ecip.examples.zip;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.cmcc.ecip.tw.common.CenterSecurity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Base64U {

	public static void main(String[] args) throws Exception {
		log.info("begin");
		FileInputStream fi = new FileInputStream(new File("d:/test3.jpg"));

		byte[] b1 = IOUtils.toByteArray(fi);
	 

//		System.arraycopy(b1, 0, b2, 0, b1.length);

		log.info("read over." + b1.length + " build byte over ..");
		long bt = System.currentTimeMillis();
		byte[] base64byte = Base64.getEncoder().encode(b1);
//		System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);
		log.info("base64 end byte  size " + base64byte.length + " use time " + (System.currentTimeMillis() - bt));
		log.info("zip begin..");
		bt = System.currentTimeMillis();
		ByteArrayOutputStream outbyte = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(outbyte);
		zos.putNextEntry(new ZipEntry(""));
		zos.write(b1);
		byte[] zipbyte = outbyte.toByteArray();
		log.info("zip end byte size " + zipbyte.length + " use time " + (System.currentTimeMillis() - bt));

 
		Key dk= DES.generator();
		bt = System.currentTimeMillis();
		log.info("des begin..");
		byte[] desout= DES.ENCRYPT(b1, dk);
		log.info("des end byte size " + desout.length + " use time " + (System.currentTimeMillis() - bt));

		
		Key ak= AES.generator();
		bt = System.currentTimeMillis();
		log.info("aes begin..");
		byte[] aesout= AES.ENCRYPT(b1, ak);
		log.info("aes end byte size " + aesout.length + " use time " + (System.currentTimeMillis() - bt));

		
		
		Key pk= PBE.generator();
		bt = System.currentTimeMillis();
		log.info("pbe begin..");
		byte[] pbeout= PBE.ENCRYPT(b1, pk);
		log.info("pbe end byte size " + pbeout.length + " use time " + (System.currentTimeMillis() - bt));

		
		log.info("end");

	}

	public void writeFile(byte[] b1, File outfile) {

	}
}

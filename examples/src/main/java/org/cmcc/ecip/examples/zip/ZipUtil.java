package org.cmcc.ecip.examples.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZipUtil {
	private void zip(String zipFileName, File inputFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		log.info("zip begin");
		zip(out, inputFile, "");
		log.info("zip end");
		out.close();
	}

	private void zip(ZipOutputStream out, File f, String base) throws Exception { // 方法重载
		if (f.isDirectory()) { // 测试此抽象路径名表示的文件是否是一个目录
			File[] fl = f.listFiles(); // 获取路径数组
			out.putNextEntry(new ZipEntry(base + "/")); // 写入此目录的entry
			base = base.length() == 0 ? "" : base + "/"; // 判断参数是否为空
			for (int i = 0; i < fl.length; i++) { // 循环遍历数组中文件
				zip(out, fl[i], base + fl[i]);
			}
		} else {
			out.putNextEntry(new ZipEntry(base)); // 创建新的进入点
			// 创建FileInputStream对象
			FileInputStream in = new FileInputStream(f);
			int b; // 定义int型变量
			log.info(base);
			while ((b = in.read()) != -1) { // 如果没有到达流的尾部
				out.write(b); // 将字节写入当前ZIP条目
			}
			in.close(); // 关闭流
		}
	}

	public static void main(String[] temp) { // 主方法
		ZipUtil book = new ZipUtil(); // 创建本例对象
		try {
			log.info("sys zip begin.."); // 输出信息
			// 调用方法，参数为压缩后文件与要压缩文件
			book.zip("hello.zip", new File("d://test.pdf"));
			log.info("sys zip end.."); // 输出信息
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

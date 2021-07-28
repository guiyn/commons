package org.cmcc.ecip.examples.tableImage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TableImage cg = new TableImage();
		try {
			List<String> colnmus = new ArrayList<>();
			colnmus.add("时间");
			colnmus.add("错误代码");
			colnmus.add("错误数");
			colnmus.add("错误占比");

			List<List<String>> datas = new ArrayList<>();
			Random r = new Random(System.currentTimeMillis());
			int max = 9999;
			int min = 1000;
			for (int i = 0; i < 10; i++) {
				List<String> data = new ArrayList<>();
				data.add("20210726 12:12:30");
				data.add(String.valueOf(r.nextInt(max) % (max - min + 1) + min));
				data.add(String.valueOf(r.nextInt(max) % (max - min + 1) + min)+"0");
				data.add("12%");
				datas.add(data);

			}
			Draw d = new Draw() {

				@Override
				public void draw(int dataline, int datacol, Graphics graphics, String value, int imagex, int imagey) {
					try {
						int i = Integer.parseInt(value);
						if (i > 50000 && datacol == 2) {
							graphics.setColor(Color.red);
						}  
					} catch (Exception e) {

					}

					graphics.drawString(value, imagex, imagey);
					graphics.setColor(Color.black);
				}
			};
			BufferedImage bi = cg.createTable(datas, colnmus, "这是一个测试用的数据", d);
			cg.createImage(bi, "pic.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

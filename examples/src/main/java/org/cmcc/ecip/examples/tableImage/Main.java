package org.cmcc.ecip.examples.tableImage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

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
			for(int i=0;i<10;i++)
			{
				List<String> data = new ArrayList<>();
				data.add("20210726 12:12:30");
				data.add("0101");
				data.add("230");
				data.add("12%");
				
				datas.add(data);
				
			}
			
			
			
			
			BufferedImage bi=cg.createTable(datas, colnmus, "这是一个测试用的数据");
			cg.createImage(bi,"pic.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

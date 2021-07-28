package org.cmcc.ecip.examples.tableImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import java.util.List;

import javax.imageio.ImageIO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableImage {

	// 行高
	static int rowheight = 30;
	static int rowwide = 100;
	// 字体大小
	static int fontTitileSize = 15;
	static int fontwide = 10;

	// 起始高度
	static int startHeight = 5;
	// 起始宽度
	static int startWidth = 5;

	Font BOlD_font = new Font("微软雅黑", Font.BOLD, fontTitileSize);
	Font PLAIN_font = new Font("微软雅黑", Font.PLAIN, fontTitileSize);

	public BufferedImage createTable(List<List<String>> data, List<String> columnsName, String title, Draw draw) {

		// 横线的行数 [需要画多少条横线]
		int totalrow = data.size() + 2;

		// 图片高度
		int imageHeight = totalrow * rowheight + startHeight * 2;
		log.info("image height >>" + imageHeight);

		// 竖线的行数 [需要画多少条竖线]
		int totalcol = columnsName.size();
		// 数据每一列多宽
		data.add(0, columnsName);
		Integer[] colswidth = getColsWidth(data, totalcol);
		data.remove(0);

		// 图片宽度
		int imageWidth = getImgWidth(colswidth);

		int colwidth = (imageWidth - 10) / columnsName.size();

		log.info("image width >>" + imageWidth);
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = image.getGraphics();
		graphics.setColor(Color.white);// 设置颜色
		graphics.fillRect(0, 0, imageWidth, imageHeight);// 填充背景色

		graphics.setColor(Color.black);

		// 写标题
		if (title != null) {
			// 设置字体
			graphics.setFont(BOlD_font);
			graphics.drawString(title, startWidth, startHeight + rowheight - 10);
		}

		// 表头 画横线
		graphics.drawLine(startWidth, startHeight + rowheight, imageWidth - startWidth, startHeight + rowheight);

		int x1 = startWidth;
		int x2 = startWidth;
		for (int j = 0; j < columnsName.size(); j++) {
			colwidth = colswidth[j] * fontwide;

			String value = columnsName.get(j);

			graphics.drawString(value, x1 + 5, startHeight + rowheight * 2 - 5);
			x2 = x2 + colwidth;
			graphics.drawLine(x2, startHeight + rowheight, x2, startHeight + rowheight * 2);
			x1 = x1 + colwidth;

		}
		graphics.drawLine(startWidth, startHeight + 2 * rowheight, imageWidth - startWidth,
				startHeight + 2 * rowheight);
		// 数据内容
		graphics.setFont(PLAIN_font);
		for (int row = 0; row < data.size(); row++) {
			List<String> rowData = data.get(row);

			x1 = startWidth;
			x2 = startWidth;
			for (int col = 0; col < columnsName.size(); col++) {
				String colData = rowData.get(col);
				colwidth = colswidth[col] * fontwide;

				draw.draw(row, col, graphics, colData, x1 + 5, startHeight + rowheight * (3 + row) - 5);

//				graphics.drawString(colData, x1 + 5, startHeight + rowheight * (3 + row) - 5);
				x1 = x1 + colwidth;
				x2 = x2 + colwidth;
				graphics.drawLine(x2, startHeight + rowheight * (2 + row), x2, startHeight + rowheight * (3 + row));

			}
			// 一行数据完成加一条线
			graphics.drawLine(startWidth, startHeight + (row + 3) * rowheight, imageWidth - startWidth,
					startHeight + (row + 3) * rowheight);
		}
		// 数据全部写完。将最左边的线加上
		graphics.drawLine(startHeight, startWidth + rowheight, startHeight, startWidth + rowheight * totalrow);

		return image;
	}

	public BufferedImage createTable(List<List<String>> data, List<String> columnsName, String title) {

		return createTable(data, columnsName, title, new DrawString());
	}

	public int getColwidth(List<String> cols) {
		int tmp = 1;
		for (String col : cols) {
			if (col.length() > tmp)
				tmp = col.length();
		}
		return tmp * fontwide;
	}

	public Integer[] getColsWidth(List<List<String>> datas, int size) {

		Integer[] li = new Integer[size];

		for (int r = 0; r < datas.size(); r++) {

			List<String> cs = datas.get(r);

			for (int c = 0; c < size; c++) {
				Integer tmp = 1;
				if (li.length > c) {
					tmp = li[c];
					if (tmp == null)
						tmp = 1;
				}

				String val = cs.get(c);

				if (val != null && val.length() > tmp) {
					tmp = val.length();
					if (tmp < 8)
						tmp = 8;
				}

				li[c] = tmp;

			}
		}

		return li;
	}

	public int getImgWidth(Integer[] is) {

		int li = 0;
		for (Integer i : is) {

			li = li + i;
		}
		return startWidth + li * fontwide + startWidth;
	}

	public void createImage(BufferedImage image, String fileLocation) {
		try {
			FileOutputStream fos = new FileOutputStream(fileLocation);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			ImageIO.write(image, "jpg", bos);

			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

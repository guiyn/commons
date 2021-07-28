package org.cmcc.ecip.examples.tableImage;

import java.awt.Graphics;

public class DrawString implements Draw {

	@Override
	public void draw(int dataline, int datacol, Graphics graphics, String value, int imagex, int imagey) {
		graphics.drawString(value, imagex, imagey);

	}

}

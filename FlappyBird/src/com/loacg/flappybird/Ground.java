package com.loacg.flappybird;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * Groudn class
 * @author Xiqian
 *
 */
public class Ground {
	BufferedImage image;
	public int x, y;
	public int width;
	public int height;

	public Ground() throws Exception {
		image = ImageIO.read(getClass().getResource("ground.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 0;
		y = 500;
	}

	
	public void step() {
		x--;
		if (x == -109) {
			x = 0;
		}
	}
}

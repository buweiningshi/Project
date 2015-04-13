package com.loacg.flappybird;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * bird class
 * 
 * @author Xiqian
 *
 */
public class Bird {
	BufferedImage image;
	int x, y;
	int width, height;
	int size;

	// use these variable to calculate the position of the birds
	double g;// graviational acceleration
	double t;// time
	double v0;// v0
	double speed;// current v
	double s;
	double alpha;// angle

	BufferedImage[] images;
	int index;

	public Bird() throws Exception {
		image = ImageIO.read(getClass().getResource("0.png"));
		width = image.getWidth();
		height = image.getHeight();
		x = 132;
		y = 280;
		size = 40;
		g = 4;
		v0 = 20;
		t = 0.25;
		speed = v0;
		s = 0;
		alpha = 0;
		

		images = new BufferedImage[8];
		for (int i = 0; i < 8; i++) {
			// i = 0 1 2 3 4 5 6 7
			images[i] = ImageIO.read(getClass().getResource(i + ".png"));
		}
		index = 0;
	}

	// fly
	public void fly() {
		index++;
		image = images[index%8];
		//image = images[(index / 12) % 8];
	}

	public void step() {
		double v0 = speed;
		s = v0 * t + g * t * t / 2;
		y = y - (int) s;
		double v = v0 - g * t;
		speed = v;

		alpha = Math.atan(s / 8);
	}


	public void flappy() {
		speed = v0;
	}


	public boolean hit(Ground ground) {
		boolean hit = y + size / 2 > ground.y;
		if (hit) {
			y = ground.y - size / 2;
			alpha = -3.14159265358979323 / 2;
		}
		return hit;
	}


	public boolean hit(Column column) {

		if (x > column.x - column.width / 2 - size / 2
				&& x < column.x + column.width / 2 + size / 2) {

			if (y > column.y - column.gap / 2 + size / 2
					&& y < column.y + column.gap / 2 - size / 2) {
				return false;
			}
			return true;
		}
		return false;
	}
}

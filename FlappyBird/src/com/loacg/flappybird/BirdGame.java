package com.loacg.flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BirdGame extends JPanel {
	Bird bird;
	Column column1, column2;
	Ground ground;
	BufferedImage background;
	boolean gameOver;
	BufferedImage gameOverImage;
	boolean started;
	BufferedImage startImage;
	int score;	

	/**
	 *  BirdGame
	 * @throws Exception
	 */
	public BirdGame() throws Exception {
		started = false;
		startImage = ImageIO.read(getClass().getResource("start.png"));
		gameOver = false;
		gameOverImage = ImageIO.read(getClass().getResource("gameover.png"));
		score = 1;
		bird = new Bird();
		column1 = new Column(1);
		column2 = new Column(2);
		ground = new Ground();
		background = ImageIO.read(getClass().getResource("bg.png"));
	}

	/** 
	 * paint
	 * 
	 */
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		g.drawImage(column1.image, column1.x - column1.width / 2, column1.y
				- column1.height / 2, null);
		g.drawImage(column2.image, column2.x - column2.width / 2, column2.y
				- column2.height / 2, null);
		// paint
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g.setFont(f);
		g.drawString("" + score, 40, 60);
		g.setColor(Color.WHITE);
		g.drawString("" + score, 40 - 3, 60 - 3);

		g.drawImage(ground.image, ground.x, ground.y, null);
		// rotate
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird.alpha, bird.x, bird.y);
		g.drawImage(bird.image, bird.x - bird.width / 2, bird.y - bird.height
				/ 2, null);
		g2.rotate(bird.alpha, bird.x, bird.y);
		// paint
		if (gameOver) {
			g.drawImage(gameOverImage, 0, 0, null);
		}
		if (!started) {
			g.drawImage(startImage, 0, 0, null);
		}
		
	

	}// paint

	// BirdGame action()
	public void action() throws Exception {
		MouseListener l = new MouseAdapter() {
			// Pressed
			public void mousePressed(MouseEvent e) {
				try {
					if (gameOver) {
						synchronized (BirdGame.this) {
							column1 = new Column(1);
							column2 = new Column(2);
							bird = new Bird();
							started = false;
							gameOver = false;
							score = 1;
						}
					} else {
						started = true;
						
						bird.flappy();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		//game
		addMouseListener(l);

		while (true) {
			if (!gameOver) {
				if (started) {
					column1.step();
					column2.step();
					bird.step();
				}
				bird.fly();
				ground.step();
				
				if (bird.x == column1.x || bird.x == column2.x) {
					score++;
				}
			}
			synchronized (BirdGame.this) {
				
				if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
					gameOver = true;
				}
			}

			repaint();
			Thread.sleep(1000 / 60);
		}
	}

	/**
	 * Flappy Bird 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame(); //JFrame
		BirdGame game = new BirdGame();  
		frame.add(game);
		frame.setSize(440, 670);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true);
		game.action();
	}
}
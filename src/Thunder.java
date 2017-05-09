import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Thunder extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x = 0;
	public int y = 0;
	public int x2 = 0;
	public int y2 = 0;
	public int time = 0;
	public int limitTime = 5;
	int speedX = 0;
	int speedY = 0;
	public ArrayList<Strike> strikeList = new ArrayList<Strike>();
	boolean isDead = false;

	public Thunder(int limitTime, int X, int Y, int x2, int y2, int speedX, int speedY) {
		x = X;
		y = Y;
		this.x2 = x2;
		this.y2 = y2;
		this.limitTime = limitTime;
		this.speedX = speedX;
		this.speedY = speedY;
		setBackground(Color.WHITE);
		setBounds(0, 0, 6, 6);
		setOpaque(false);
		setLocation(x, y);
		// System.out.println(x2+" "+y2);
	}

	public void motion() {
		x += speedX;
		x2 += speedX;
		y += speedY;
		y2 += speedY;
		setLocation(x, y);
	}

	public void increaseTime() {
		time++;
	}

	public void generateStrikes(int count) {
		if (!isDead) {
			for (int i = 0; i < count; i++) {
				int xS = x + (int) (Math.random() * (x2 - x));
				int yS = y + (int) (Math.random() * (y2 - y));
				strikeList.add(new Strike(xS, yS));
				// System.out.println(xS+" "+yS);
			}
		}
	}

}
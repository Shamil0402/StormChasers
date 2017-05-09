import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Strike extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x = 0;
	public int y = 0;
	public Image skin;
	public int time = 5;


	public Strike(int X, int Y) {
		try {
			skin = ImageIO.read(new File("img/strike" + time + ".png"));
		} catch (IOException e) {
			System.out.println("Warning");
		}
		x = X;
		y = Y;
		setBackground(Color.WHITE);
		setBounds(0, 0, 6, 6);
		setOpaque(false);
		setLocation(x, y);
	}
	
	public void increaseTime(){
		time+=5;

		try {
			skin = ImageIO.read(new File("img/strike" + time + ".png"));
		} catch (IOException e) {
		//	System.out.println("Warning strike");
		}
		repaint();
	}
	
	public void paintComponent(final Graphics gr) {
		super.paintComponent(gr);
		gr.drawImage(skin, 0, 0, 6, 6, this);

	}
	

}
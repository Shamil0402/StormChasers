import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class Front extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x = 0;
	public Image skin;
	public int cape;
	public int li;
	public int temp;
	public int hydro;
	public boolean isThunder;
	public int isFront;
	public int time = 5;
	int type = 1;
	int speed = 1;


	public Front(int type, int X, int speed) {
		try {
			skin = ImageIO.read(getClass().getResourceAsStream("img/front"+type+".png")/*new File("img/front"+type+".png")*/);
		} catch (IOException e) {
			System.out.println("Warning");
		}
		this.speed = speed;
		this.type = type;
		x = X;
		setBackground(Color.WHITE);
		setBounds(0, 0, 40, 305);
		setOpaque(false);
		setLocation(x, 0);
	}
	public void motion(){
		x+=speed;
		setLocation(x, 0);
		repaint();
	}

	public void paintComponent(final Graphics gr) {
		super.paintComponent(gr);
		for(int i = 0;i<15;i++){
		gr.drawImage(skin, 0, i*20, 20, 20, this);
		}
		}

}
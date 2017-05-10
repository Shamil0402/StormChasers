import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Town extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x = 0;
	public int y = 0;
	public int screenX = 0;
	public int screenY = 0;
	public int id;
	public String name;
	public String value;
	public Image skin;
	public boolean isSelected = true;
	public int cape;
	public int li;
	public int temp;
	public int hydro;
	public int isThunder;
	public int isFront;

	public Town(String name, String value, int id, int X, int Y, boolean isSelected) {
		try {
			skin = ImageIO.read(getClass().getResourceAsStream("img/town.png")/*new File("img/town.png")*/);
		} catch (IOException e) {
			System.out.println("Warning");
		}
		this.isSelected = isSelected;
		x = X;
		y = Y;
		this.name = name;
		this.value = value;
		setBackground(Color.WHITE);
		setBounds(0, 0, 100, 30);
		setOpaque(false);
		if (isSelected != true) {
			//Panel.selectedIndex = id;
			isSelected = true;
			setBorder(new LineBorder(Color.BLUE, 0));
		} else {
			isSelected = false;
			setBorder(new LineBorder(Color.BLUE, 1));
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void mouseClicked(MouseEvent e) {
		if (isSelected == false) {
			isSelected = true;
		} else if(isSelected == true){
			isSelected = false;
		}
		update();
	}
	public void update(){
		if (isSelected != true) {
			//Panel.selectedIndex = id;
			setBorder(new LineBorder(Color.BLUE, 0));

		} else {
			setBorder(new LineBorder(Color.BLUE, 1));
			
		}
	}
	
	public void mousePressed(MouseEvent e) {

		screenX = e.getXOnScreen();
		screenY = e.getYOnScreen();

	}

	public void mouseDragged(MouseEvent e) {
		if(isSelected==true){
		int deltaX = e.getXOnScreen() - screenX;
		int deltaY = e.getYOnScreen() - screenY;

		screenX = e.getXOnScreen();
		screenY = e.getYOnScreen();

		x += deltaX;
		y += deltaY;
		if(x+100>352){
			x=252;
		}
		if(y+30>305){
			y=275;
		}
		setLocation(x, y);
		
		//System.out.println(isSelected);

		}
	}

	public void paintComponent(final Graphics gr) {
		super.paintComponent(gr);
		Graphics g = (Graphics2D) skin.getGraphics();

		g.setFont(new Font("Arial", Font.BOLD, 27));
		g.setColor(Color.BLUE);
		g.drawString(name, 30, 20);
		g.drawString(value, 30, 45);
		gr.drawImage(skin, 0, 0, 100, 30, this);

	}
	public int getCoordX(){
		return getX()+17;
	}
	public int getCoordY(){
		return getY()+17;
	}

}
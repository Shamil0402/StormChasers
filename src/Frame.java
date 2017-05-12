import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame() {
		try {
			setIconImage(ImageIO.read(getClass().getResourceAsStream("img/icon.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Panel myPanel = new Panel();
		
		Container cont = getContentPane();
		cont.add(myPanel);
		
		setTitle("StormChasers");
		setBounds(0, 0, 951, 700);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
		
		setVisible(true);
		
	}
}

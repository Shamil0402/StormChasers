import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame() {
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
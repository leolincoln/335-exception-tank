import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class GUI {
	private static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static void main(String[] args) {
		TankView t = new TankView();
		t.setSize(screen);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setBackground(Color.BLACK);
		t.setVisible(true);
	}

}

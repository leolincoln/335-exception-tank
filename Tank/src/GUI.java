import java.awt.Color;

import javax.swing.JFrame;


public class GUI {
	
	public static void main(String[] args) {
		TankView t = new TankView();
		t.setSize(1000, 1000);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setBackground(Color.BLACK);
		t.setVisible(true);
	}

}

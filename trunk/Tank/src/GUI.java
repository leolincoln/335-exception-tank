import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 
 * @author Team Exception
 * 
 *         This class contains the main class which contains the main thread in
 *         which the GUI of the tank field operates.
 * 
 */
public class GUI {

	private static Dimension screen = Toolkit.getDefaultToolkit()
			.getScreenSize();

	public static void main(String[] args) {
		TankView t = new TankView();
		t.setSize(screen);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.buildMap();
		t.setBackground(Color.BLACK);
		t.setVisible(true);
	}

}

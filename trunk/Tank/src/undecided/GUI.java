package undecided;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import View.MasterView;

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

	/**
	 * Main Thread to begin the entire program. It begins by creating a new
	 * masterview and new tankview.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		MasterView m = new MasterView();
		m.setLocationRelativeTo(null);
		TankView t = new TankView(m);

	}

}

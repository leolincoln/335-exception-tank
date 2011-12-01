package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 
 * @author Team Exception
 * 
 *         This class contains the rectangles for the crates that will be used
 *         in collisions.
 * 
 */
public class CrateRectangle extends ObstacleRectangle {

	private static final long serialVersionUID = 1L;

	public CrateRectangle(int x, int y) {
		super(x, y);

	}

	public Color setColor() {
		return Color.yellow;
	}

	public Image getImage() {
		Image crate = new ImageIcon("images/crate.png").getImage();
		return crate;
	}

}

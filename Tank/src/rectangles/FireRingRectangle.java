package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 
 * @author Team Exception
 * 
 *         This class contains the rectangles for the fire ring that will be
 *         used for collisions.
 * 
 */
public class FireRingRectangle extends ObstacleRectangle {

	private static final long serialVersionUID = 1L;

	public FireRingRectangle(int x, int y) {
		super(x, y);

	}

	public Color setColor() {
		return Color.red;
	}

	public Image getImage() {
		Image fireImage = new ImageIcon("images/fireRing.png").getImage();
		return fireImage;
	}

}

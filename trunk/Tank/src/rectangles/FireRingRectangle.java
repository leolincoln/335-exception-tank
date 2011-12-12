package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class contains the rectangles for the fire ring that will be used for
 * collisions.
 * 
 * @author Team Exception
 * 
 * @extends ObstacleRectangle
 * 
 * @see FireRing, Rectangle, ObstacelRectangle
 * 
 */
public class FireRingRectangle extends ObstacleRectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;

	/**
	 * This is the class constructor for the FireRingRectangle class. It
	 * will create the geometry class collision rectangle for this object.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public FireRingRectangle(int x, int y) {
		super(x, y);

	}

	/**
	 * This method sets the color of the shape to be red.
	 * 
	 * Note: This method is no longer in use but used as a place holder.
	 */
	public Color setColor() {
		return Color.red;
	}

	/**
	 * This method retrieves the FireRing obstacle image and returns it
	 * 
	 * @return image of FireRing
	 */
	public Image getImage() {
		Image fireImage = new ImageIcon("images/fireRing.png").getImage();
		return fireImage;
	}

}

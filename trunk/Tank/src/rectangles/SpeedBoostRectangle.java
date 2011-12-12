package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class contains the rectangles for the speed boost that willbe used for
 * collisions.
 * 
 * @author Team Exception
 * 
 * @extends ItemRectangle
 * 
 * @see SpeedBoost, Rectangle, ItemRectangle
 * 
 */
public class SpeedBoostRectangle extends ItemRectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;

	/**
	 * This is the class constructor for the SpeedBoostRectangle class. It
	 * will create the geometry class collision rectangle for this object.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public SpeedBoostRectangle(int x, int y) {
		super(x, y);

	}

	/**
	 * This method sets the color of the shape to be cyan.
	 * 
	 * Note: This method is no longer in use but used as a place holder.
	 */
	@Override
	public Color setColor() {
		return Color.cyan;
	}

	/**
	 * This method retrieves the SpeedBoost item image and returns it
	 * 
	 * @return image of SpeedBoost
	 */
	public Image getImage() {
		Image crate = new ImageIcon("images/speedBoost.png").getImage();
		return crate;
	}
}

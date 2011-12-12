package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class contains the bubble shield rectangle that will be used for
 * collisions.
 * 
 * @author Team Exception
 * 
 * @extends ItemRectangle
 * 
 * @see BubbleShield, Rectangle, ItemRectangle
 * 
 */
public class BubbleShieldRectangle extends ItemRectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;

	/**
	 * This is the class constructor for the BubbleShieldRectangle class. It
	 * will create the geometry class collision rectangle for this object.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public BubbleShieldRectangle(int x, int y) {
		super(x, y);

	}

	/**
	 * This method sets the color of the shape to be blue.
	 * 
	 * Note: This method is no longer in use but used as a place holder.
	 */
	@Override
	public Color setColor() {
		return Color.blue;
	}

	/**
	 * This method retrieves the BubbleShield item image and returns it
	 * 
	 * @return image of BubbleShield
	 */
	public Image getImage() {
		Image crate = new ImageIcon("images/bubbleShield.png").getImage();
		return crate;
	}

}

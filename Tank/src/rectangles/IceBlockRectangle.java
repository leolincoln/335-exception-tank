package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 
 * This class contains the rectangles for the IceBlock that will be used for
 * collisions.
 * 
 * @author Team Exception
 * 
 * @extends ItemRectangel
 * 
 * @see IceBlock, Rectangle, ItemRectangle
 * 
 */
public class IceBlockRectangle extends ItemRectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;

	/**
	 * This is the class constructor for the IceBlockRectangle class. It
	 * will create the geometry class collision rectangle for this object.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public IceBlockRectangle(int x, int y) {
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
	 * This method retrieves the IceBlock item image and returns it
	 * 
	 * @return image of IceBlock
	 */
	public Image getImage() {
		Image crate = new ImageIcon("images/iceBlock.png").getImage();
		return crate;
	}
}

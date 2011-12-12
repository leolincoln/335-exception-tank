package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class contains the rectangles for the TNT that will be used for
 * collisions.
 * 
 * @author Team Exception
 * 
 * @extends ObstacleRectangle
 * 
 * @see TNT, Rectangle, ObstacleRectangle
 * 
 */
public class TNTRectangle extends ObstacleRectangle {

	// declare instance variables
	private static final long serialVersionUID = 1L;

	/**
	 * This is the class constructor for the TNTRectangle class. It
	 * will create the geometry class collision rectangle for this object.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public TNTRectangle(int x, int y) {
		super(x, y);
	}

	/**
	 * This method sets the color of the shape to be orange.
	 * 
	 * Note: This method is no longer in use but used as a place holder.
	 */
	@Override
	public Color setColor() {
		return Color.orange;
	}

	/**
	 * This method retrieves the TNT obstacle image and returns it
	 * 
	 * @return image of TNT
	 */
	public Image getImage() {
		Image t = new ImageIcon("images/tnt.png").getImage();
		return t;
	}

}

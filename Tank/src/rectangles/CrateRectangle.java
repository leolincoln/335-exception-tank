package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/** 
 * This class contains the rectangles for the crates that will be used in
 * collisions.
 * 
 * @author Team Exception
 * 
 * @extends ObstacleRectangle
 * 
 * @see Crate, Rectangle, ObstacleRectangle
 * 
 */
public class CrateRectangle extends ObstacleRectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;

	/**
	 * This is the class constructor for the CrateRectangle class. It
	 * will create the geometry class collision rectangle for this object.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public CrateRectangle(int x, int y) {
		super(x, y);

	}
	
	/**
	 * This method sets the color of the shape to be yellow.
	 * 
	 * Note: This method is no longer in use but used as a place holder.
	 */
	public Color setColor() {
		return Color.yellow;
	}

	/**
	 * This method retrieves the Crate obstacle image and returns it
	 * 
	 * @return image of Crate
	 */
	public Image getImage() {
		Image crate = new ImageIcon("images/crate.png").getImage();
		return crate;
	}

}

package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class contains the rectangles for the spike pits that will be used for
 * collisions.
 * 
 * @author Team Exception
 * 
 * @extends ObstacleRectangle
 * 
 * @see SpikePit, Rectangle, ObstacleRectangle
 * 
 */
public class SpikePitRectangle extends ObstacleRectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;

	/**
	 * This is the class constructor for the SpikePitRectangle class. It
	 * will create the geometry class collision rectangle for this object.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public SpikePitRectangle(int x, int y) {
		super(x, y);

	}

	/**
	 * This method sets the color of the shape to be light_gray.
	 * 
	 * Note: This method is no longer in use but used as a place holder.
	 */
	public Color setColor() {
		return Color.LIGHT_GRAY;
	}

	/**
	 * This method retrieves the SpikePit obstacle image and returns it
	 * 
	 * @return image of SpikePit
	 */
	public Image getImage() {
		Image s = new ImageIcon("images/spikePit.png").getImage();
		return s;
	}

}

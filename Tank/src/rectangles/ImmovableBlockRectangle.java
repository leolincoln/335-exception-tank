package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * This class contains the rectangles for the immovable blocks that will be used
 * for collisions.
 * 
 * @author Team Exception
 * 
 * @extends ObstacleRectangle
 * 
 * @see ImmovableBlock, Rectangle, ObstacleRectangle
 * 
 */
public class ImmovableBlockRectangle extends ObstacleRectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;

	/**
	 * This is the class constructor for the ImmovableBlockRectangle class. It
	 * will create the geometry class collision rectangle for this object.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public ImmovableBlockRectangle(int x, int y) {
		super(x, y);

	}

	/**
	 * This method sets the color of the shape to be grey.
	 * 
	 * Note: This method is no longer in use but used as a place holder.
	 */
	public Color setColor() {
		return Color.gray;
	}

	/**
	 * This method retrieves the ImmovableBlock obstacle image and returns it
	 * 
	 * @return image of ImmovableBlock
	 */
	public Image getImage() {
		Image blockImage = new ImageIcon("images/block.png").getImage();
		return blockImage;
	}

}

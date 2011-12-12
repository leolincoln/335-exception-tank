package rectangles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * This class contains the rectangles for the obstacles that will be used for
 * collisions.
 * 
 * @author Team Exception
 * 
 * @extends Rectangle
 * 
 * @see Obstacle, Rectangle
 * 
 */
public abstract class ObstacleRectangle extends Rectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;
	private int x, y, w, h;

	/**
	 * This is the class constructor for the ObstacleRectangle class. It
	 * will create the geometry class collision rectangle for these obstacles in general.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public ObstacleRectangle(int x, int y) {
		this.setSize(new Dimension(50, 50));
		this.setLocation(x, y);
		this.x = x;
		this.y = y;
		this.w = 50;
		this.h = 50;
	}

	/**
	 * This method returns the x coordinate of the ObstacleRectangle.
	 * 
	 * @return x coordinate of ObstalceRectangle
	 */
	public int xCoord() {
		return x;
	}

	/**
	 * This method returns the y coordinate of the ObstacleRectangle.
	 * 
	 * @return y coordinate of ObstalceRectangle
	 */
	public int yCoord() {
		return y;
	}

	/**
	 * This method finds the center of the ObstacleRectangle.
	 * 
	 * @return center of the ObstacleRectangle (a Dimension not a point).
	 */
	public Dimension findCenter() {
		int xCenter = 0;
		int yCenter = 0;
		xCenter = x + (w / 2);
		yCenter = y - (h / 2);
		Dimension d = new Dimension(xCenter, yCenter);
		return d;
	}

	/**
	 * This method sets the color of the shape.
	 */
	public abstract Color setColor();

}

package rectangles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * This class contains the rectangles for the tanks that will be used for
 * collisions.
 * 
 * @author Team Exception
 * 
 * @extends Rectangle
 * 
 * @see Tank, Rectangle
 * 
 */
public class TankRectangle extends Rectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;
	private int x, y, w, h;
	private Direction d;

	/**
	 * This is the class constructor for the TankRectangle class. It
	 * will create the geometry class collision rectangle for these tanks in general.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public TankRectangle(int x, int y) {
		this.setSize(new Dimension(50, 50));
		this.setLocation(x, y);
		this.x = x;
		this.y = y;
		this.w = 50;
		this.h = 50;

	}

	/**
	 * This method sets the direction of the tank
	 * 
	 * @param dir direction that the tank is facing
	 */
	public void setDirection(Direction dir) {
		d = dir;

	}

	/**
	 * This method returns the x coordinate of the TankRectangle.
	 * 
	 * @return x coordinate of TankRectangle
	 */
	public int xCoord() {
		return x;
	}

	/**
	 * This method returns the y coordinate of the TankRectangle.
	 * 
	 * @return y coordinate of TankRectangle
	 */
	public int yCoord() {
		return y;
	}

	/**
	 * This method returns the current direction that the tank is facing.
	 * 
	 * @return current direction of the tank
	 */
	public Direction getDirection() {
		return d;
	}

	/**
	 * This method finds the center of the TankRectangle.
	 * 
	 * @return center of the TankRectangle (a Dimension not a point).
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
	 * This method sets the color of the shape to be green.
	 * 
	 * Note: This method is no longer in use but used as a place holder.
	 */
	public Color setColor() {
		return Color.green;

	}

	/**
	 * This method retrieves the Tank obstacle image and returns it
	 * 
	 * @return image of tank
	 */
	public Image getImage() {
		Image t = new ImageIcon("images/tank.png").getImage();
		return t;
	}

}

package rectangles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * This class contains the rectangles for the items that will be used for
 * collisions. It is the super class for all items and will construct items of
 * the size 50*50 pixels each.
 * 
 * @author Team Exception
 * 
 * @extends Rectangle
 * 
 * @see Rectangle, Item
 * 
 */
public abstract class ItemRectangle extends Rectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;
	private int x, y, w, h;

	/**
	 * This is the class constructor for the ItemRectangle class. It
	 * will create the geometry class collision rectangle for these items in general.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public ItemRectangle(int x, int y) {
		this.setSize(new Dimension(50, 50));
		this.setLocation(x, y);
		this.x = x;
		this.y = y;
		this.w = 50;
		this.h = 50;

	}
	
	/**
	 * This method returns the x coordinate of the ItemRectangle.
	 * 
	 * @return x coordinate of ItemRectangle
	 */
	public int xCoord() {
		return x;
	}

	/**
	 * This method returns the y coordinate of the ItemRectangle.
	 * 
	 * @return y coordinate of ItemRectangle
	 */
	public int yCoord() {
		return y;
	}

	/**
	 * This method finds the center of the ItemRectangle.
	 * 
	 * @return center of the ItemRectangle (a Dimension not a point).
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

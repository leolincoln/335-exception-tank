package rectangles;

import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * This class contains the rectangles that will be used for the blast radius of
 * the TNT for collisions.
 * 
 * @author Team Exception
 * 
 * @extends Rectangle
 * 
 * @see TNT, TNTRectangle, Rectangle
 * 
 */
public class BlastRadiusRectangle extends Rectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;
	private int x, y, w, h;
	private Direction d;

	/**
	 * This is the class constructor for the BlastRadiusRectangle which controls
	 * the range of damage a TNT will deal when hit with a projectile or
	 * detonated by another TNT.
	 * 
	 * @category constructor 
	 * 
	 * @param x
	 *            horizontal range of blast
	 * @param y
	 *            vertical range of blast
	 */
	public BlastRadiusRectangle(int x, int y) {
		this.setSize(new Dimension(100, 100));
		this.setLocation(x, y);
		this.x = x;
		this.y = y;
		this.w = 100;
		this.h = 100;

	}

	/**
	 * This method sets the direction of the TNT
	 * 
	 * @param dir
	 *            direction of the TNT
	 */
	public void setDirection(Direction dir) {
		d = dir;

	}

	/**
	 * This method returns the x coordinate of the TNT's blast.
	 * 
	 * @return x coordinate of TNT's blast
	 */
	public int xCoord() {
		return x;
	}

	/**
	 * This method returns the y coordinate of the TNT's blast.
	 * 
	 * @return y coordinate of TNT's blast
	 */
	public int yCoord() {
		return y;
	}

	/**
	 * This method returns the current direction of the TNT.
	 * 
	 * @return current direction of TNT
	 */
	public Direction getDirection() {
		return d;
	}

	/**
	 * This method finds the center of the blast radius rectangle of the TNT.
	 * 
	 * @return center of blast radius for TNT (a Dimension not a point).
	 */
	public Dimension findCenter() {
		int xCenter = 0;
		int yCenter = 0;
		xCenter = x + (w / 2);
		yCenter = y - (h / 2);
		Dimension d = new Dimension(xCenter, yCenter);
		return d;
	}

}

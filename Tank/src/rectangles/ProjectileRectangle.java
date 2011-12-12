package rectangles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * This class contains the rectangles for the normal projectile that will be
 * used for collisions.
 * 
 * @author Team Exception
 * 
 * @extends Rectangle
 * 
 * @see Projectile, Rectangle
 * 
 */
public class ProjectileRectangle extends Rectangle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;
	private int x, y, w, h;
	private Direction d;

	/**
	 * This is the class constructor for the ProjectileRectangle class. It
	 * will create the geometry class collision rectangle for these projectiles in general.
	 * 
	 * @category constructor
	 * 
	 * @param x
	 *            horizontal component of its location
	 * @param y
	 *            vertical component of its location
	 */
	public ProjectileRectangle(int x, int y) {
		this.setSize(new Dimension(10, 10));
		this.setLocation(x, y);
		this.x = x;
		this.y = y;
		this.w = 10;
		this.h = 10;

	}

	/**
	 * This method sets the direction of the projectile
	 * 
	 * @param dir direction that the projectile is facing
	 */
	public void setDirection(Direction dir) {
		d = dir;

	}

	/**
	 * This method returns the y coordinate of the ProjectileRectangle.
	 * 
	 * @return y coordinate of ProjectileRectangle
	 */
	public int xCoord() {
		return x;
	}

	/**
	 * This method returns the y coordinate of the ProjectileRectangle.
	 * 
	 * @return y coordinate of ProjectileRectangle
	 */
	public int yCoord() {
		return y;
	}

	/**
	 * This method returns the current direction of the projectile
	 * 
	 * @return current direction of the projectile
	 */
	public Direction getDirection() {
		return d;
	}

	/**
	 * This method finds the center of the ProjectileRectangle.
	 * 
	 * @return center of the ProjectileRectangle (a Dimension not a point).
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
	 * This method sets the color of the shape to be pink.
	 * 
	 * Note: This method is no longer in use but used as a place holder.
	 */
	public Color setColor() {
		return Color.pink;

	}
	
	/**
	 * This method retrieves the Projectile  image and returns it
	 * 
	 * @return image of projectile
	 */
	public Image getImage() {
		Image p = new ImageIcon("images/projectile.png").getImage();
		return p;
	}

}
package rectangles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * 
 * @author Team Exception
 * 
 *         This class contains the rectangles for the normal projectile that
 *         will be used for collisions.
 * 
 */
public class ProjectileRectangle extends Rectangle {

	private static final long serialVersionUID = 1L;
	private int x, y, w, h;
	private Direction d;

	public ProjectileRectangle(int x, int y) {
		this.setSize(new Dimension(6, 6));
		this.setLocation(x, y);
		this.x = x;
		this.y = y;
		this.w = 6;
		this.h = 6;

	}

	public void setDirection(Direction dir) {
		d = dir;

	}

	public int xCoord() {
		return x;
	}

	public int yCoord() {
		return y;
	}

	public Direction getDirection() {
		return d;
	}

	public Dimension findCenter() {
		int xCenter = 0;
		int yCenter = 0;
		xCenter = x + (w / 2);
		yCenter = y - (h / 2);
		Dimension d = new Dimension(xCenter, yCenter);
		return d;
	}

	public Color setColor() {
		return Color.pink;

	}

	public Image getImage() {
		Image p = new ImageIcon("images/projectile.png").getImage();
		return p;
	}

}
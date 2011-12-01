package rectangles;

import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * 
 * @author Team Exception
 * 
 *         This class contains the rectangles that will be used for the blast
 *         radius of the TNT for collisions.
 * 
 */
public class BlastRadiusRectangle extends Rectangle {
	private static final long serialVersionUID = 1L;
	private int x, y, w, h;
	private Direction d;

	public BlastRadiusRectangle(int x, int y) {
		this.setSize(new Dimension(100, 100));
		this.setLocation(x, y);
		this.x = x;
		this.y = y;
		this.w = 100;
		this.h = 100;

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

}

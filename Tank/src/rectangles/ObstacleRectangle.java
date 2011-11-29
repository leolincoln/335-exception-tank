package rectangles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

public abstract class ObstacleRectangle extends Rectangle {

	private static final long serialVersionUID = 1L;
	private int x, y, w, h;
	
	
	public ObstacleRectangle(int x, int y) {
		this.setSize(new Dimension(50, 50));
		this.setLocation(x, y);
		this.x = x;
		this.y = y;
		this.w = 50;
		this.h = 50;
	}
	
	public int xCoord() {
		return x;
	}
	public int yCoord() {
		return y;
	}
	
	public Dimension findCenter() {
		int xCenter = 0;
		int yCenter = 0;
		xCenter = x + (w/2);
		yCenter = y - (h/2);
		Dimension d = new Dimension(xCenter, yCenter);
		return d;
	}
	
	public abstract Color setColor();
	

}

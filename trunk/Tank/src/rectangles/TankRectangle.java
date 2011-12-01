package rectangles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class TankRectangle extends Rectangle{
	

	private static final long serialVersionUID = 1L;
	private int x, y, w, h;
	private Direction d;
	
	
	public TankRectangle(int x, int y) {
		this.setSize(new Dimension(50, 50));
		this.setLocation(x, y);
		this.x = x;
		this.y = y;
		this.w = 50;
		this.h = 50;

	
		
		
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
		xCenter = x + (w/2);
		yCenter = y - (h/2);
		Dimension d = new Dimension(xCenter, yCenter);
		return d;
	}
	
	public Color setColor() {
		return Color.green;
	

}
	public Image getImage() {
		Image t = new ImageIcon("images/tank.png").getImage();
		return t;
	}


}

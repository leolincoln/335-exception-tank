package rectangles;

import java.awt.Color;

public class FireRingRectangle extends ObstacleRectangle {

	private static final long serialVersionUID = 1L;

	public FireRingRectangle(int x, int y) {
		super(x, y);
		
	}
	
	public Color setColor() {
		return Color.red;
	}
 

}

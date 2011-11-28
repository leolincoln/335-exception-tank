package rectangles;

import java.awt.Color;

public class CrateRectangle extends ObstacleRectangle {

	private static final long serialVersionUID = 1L;

	public CrateRectangle(int x, int y) {
		super(x, y);
		
	}
	
	public Color setColor() {
		return Color.yellow;
	}

}

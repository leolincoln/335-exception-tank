package rectangles;

import java.awt.Color;

public class SpikePitRectangle extends ObstacleRectangle {

	private static final long serialVersionUID = 1L;

	public SpikePitRectangle(int x, int y) {
		super(x, y);
		
	}
	
	public Color setColor() {
		return Color.black;
	}

}

package rectangles;

import java.awt.Color;

public class TNTRectangle extends ObstacleRectangle {
	
	private static final long serialVersionUID = 1L;

	public TNTRectangle(int x, int y) {
		super(x, y);
	}

	@Override
	public Color setColor() {
		return Color.orange;
	}

}

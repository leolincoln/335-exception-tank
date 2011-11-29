package rectangles;

import java.awt.Color;

public class ImmovableBlockRectangle extends ObstacleRectangle{

	private static final long serialVersionUID = 1L;

	public ImmovableBlockRectangle(int x, int y) {
		super(x, y);
		
	}
	
	public Color setColor() {
		return Color.white;
	}
	
}

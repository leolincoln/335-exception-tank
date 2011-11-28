package rectangles;

import java.awt.Color;

public class BlockRectangle extends ObstacleRectangle {

	private static final long serialVersionUID = 1L;

	public BlockRectangle(int x, int y) {
		super(x, y);
		
	}
	
	public Color setColor() {
		return Color.gray;
	}
 

}

package rectangles;

import java.awt.Color;

public class SpeedBoostRectangle extends ItemRectangle{

	private static final long serialVersionUID = 1L;

	public SpeedBoostRectangle(int x, int y) {
		super(x, y);
		
	}

	@Override
	public Color setColor() {
		return Color.cyan;
	}

}

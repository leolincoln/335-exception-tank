package rectangles;

import java.awt.Color;

public class DualShotRectangle extends ItemRectangle{

	private static final long serialVersionUID = 1L;

	public DualShotRectangle(int x, int y) {
		super(x, y);
		
	}

	@Override
	public Color setColor() {
		return Color.white;
	}

}

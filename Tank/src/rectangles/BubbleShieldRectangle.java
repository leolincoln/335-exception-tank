package rectangles;

import java.awt.Color;

public class BubbleShieldRectangle extends ItemRectangle {

	private static final long serialVersionUID = 1L;

	public BubbleShieldRectangle(int x, int y) {
		super(x, y);

	}

	@Override
	public Color setColor() {
		return Color.blue;
	}

}

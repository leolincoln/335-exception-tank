package rectangles;

import java.awt.Color;

/**
 * 
 * @author Team Exception
 * 
 *         This class contains the bubble shield rectangle that will be used for
 *         collisions.
 * 
 */
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

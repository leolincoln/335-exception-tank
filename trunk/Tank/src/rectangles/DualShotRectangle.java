package rectangles;

import java.awt.Color;

/**
 * 
 * @author Team Exception
 * 
 *         This class contains the rectangles for the double bullet that will be
 *         used for collisions.
 * 
 */
public class DualShotRectangle extends ItemRectangle {

	private static final long serialVersionUID = 1L;

	public DualShotRectangle(int x, int y) {
		super(x, y);

	}

	@Override
	public Color setColor() {
		return Color.white;
	}

}

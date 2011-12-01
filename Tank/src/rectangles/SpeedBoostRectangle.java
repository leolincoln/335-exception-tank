package rectangles;

import java.awt.Color;

/**
 * 
 * @author Team Exception
 * 
 *         This class contains the rectangles for the speed boost that willbe
 *         used for collisionis.
 * 
 */
public class SpeedBoostRectangle extends ItemRectangle {

	private static final long serialVersionUID = 1L;

	public SpeedBoostRectangle(int x, int y) {
		super(x, y);

	}

	@Override
	public Color setColor() {
		return Color.cyan;
	}

}

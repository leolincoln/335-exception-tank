package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 
 * @author Team Exception
 * 
 *         This class contains the rectangles for the speed boost that willbe
 *         used for collisionis.
 * 
 */
public class IceBlockRectangle extends ItemRectangle {

	private static final long serialVersionUID = 1L;

	public IceBlockRectangle(int x, int y) {
		super(x, y);

	}

	@Override
	public Color setColor() {
		return Color.cyan;
	}
	public Image getImage() {
		Image crate = new ImageIcon("images/iceBlock.png").getImage();
		return crate;
	}
}

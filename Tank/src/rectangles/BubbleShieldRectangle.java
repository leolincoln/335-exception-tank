package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

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
	
	public Image getImage() {
		Image crate = new ImageIcon("images/bubbleShield.png").getImage();
		return crate;
	}

}

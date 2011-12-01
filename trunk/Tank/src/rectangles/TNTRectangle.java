package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class TNTRectangle extends ObstacleRectangle {
	
	private static final long serialVersionUID = 1L;

	public TNTRectangle(int x, int y) {
		super(x, y);
	}

	@Override
	public Color setColor() {
		return Color.orange;
	}
	public Image getImage() {
		Image t = new ImageIcon("images/tnt.png").getImage();
		return t;
	}

}

package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 
 * @author Team Exception
 * 
 *         This class contains the rectangles for the immovable blocks that will
 *         be used for collisions.
 * 
 */
public class ImmovableBlockRectangle extends ObstacleRectangle {

	private static final long serialVersionUID = 1L;

	public ImmovableBlockRectangle(int x, int y) {
		super(x, y);

	}

	public Color setColor() {
		return Color.gray;
	}

	public Image getImage() {
		Image blockImage = new ImageIcon("images/block.png").getImage();
		return blockImage;
	}

}

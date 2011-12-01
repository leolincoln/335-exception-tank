package rectangles;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 
 * @author Team Exception
 * 
 *         This class contains the rectangles for the spike pits that will be
 *         used for collisions.
 * 
 */
public class SpikePitRectangle extends ObstacleRectangle {

	private static final long serialVersionUID = 1L;

	public SpikePitRectangle(int x, int y) {
		super(x, y);

	}

	public Color setColor() {
		return Color.LIGHT_GRAY;
	}

	public Image getImage() {
		Image s = new ImageIcon("images/spikePit.png").getImage();
		return s;
	}

}

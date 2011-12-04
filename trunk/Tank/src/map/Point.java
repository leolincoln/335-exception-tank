package map;

import java.io.Serializable;

/**
 * 
 * @author Team Exception
 * 
 *         This class is a point (overriding Java's Point class) that will be
 *         used to determine the location of tanks and obstacles
 * 
 */
public class Point implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final int row;
	public final int col;

	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}

}

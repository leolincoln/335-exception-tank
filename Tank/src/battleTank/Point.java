package battleTank;

import java.io.Serializable;

/**
 * This class is a point (overriding Java's Point class) that will be used to
 * determine the location of tanks and obstacles
 * 
 * @author Team Exception
 * 
 * @implements Serializable
 * 
 * @see PlayerTank, EnemyTank, Item, Map
 * 
 * @serial
 * 
 */
public class Point implements Serializable {

	// declaring instance variables
	private static final long serialVersionUID = 1L;
	public final int row;
	public final int col;

	/**
	 * This is the Point class constructor which contains the row and column for
	 * each point.
	 * 
	 * @param row
	 *            y value for this particular point
	 * @param col
	 *            x value for this particular point
	 */
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}

}

package battleTank;

import java.util.Observable;
import rectangles.SpikePitRectangle;

/**
 * This class is the SpikePit object which essentially destroys the PlayerTank
 * if it moves over it on the field. It has a location and map which it is on
 * but it has no health so can't be destroyed.
 * 
 * @author Team Exception
 * 
 * @see Item, TankView, PlayerTank, FireRing,Crate, ImmovableBlock, TNT
 * 
 * @extends Observable
 * 
 * @implements Obstacle
 * 
 */
public class SpikePit extends Observable implements Obstacle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;
	private int health;// health of the spike pit (-1)
	private SpikePitRectangle rect;// shape for spike pit controlling collisions
	private Point location;// location of spike pit
	private Map map;

	/**
	 * This is the class constructor for the SpikePit class. The SpikePit is an
	 * object that has no health so can't be destroyed but has a location and a
	 * map to be placed on.
	 * 
	 * @category constructor
	 * 
	 * @param p
	 *            point of the location at which the SpikePit is to be created.
	 * @param map
	 *            map on which this SpikePit is to be created.
	 * 
	 */
	public SpikePit(Point p, Map map) {
		this.map = map;
		location = p;
		health = -1;// spike pit can't be destroyed
		// 25 is to offset for the size so it's not off the field
		rect = new SpikePitRectangle(location.col - 25, location.row - 25);

	}

	/**
	 * This method deals damage on the SpikePit due to the input parameter (but
	 * no health).
	 * 
	 * @param dmg
	 *            damage that this object will take
	 */
	public void recieveDamage(int dmg) {
		health = health + dmg - dmg;// troll math
	}

	/**
	 * Determines whether this object should be removed from the field.
	 * 
	 * @return always returns false
	 */
	public boolean removeObstacle() {
		return false;// this object can't be removed
	}

	/**
	 * This method returns the health of the SpikePit.
	 * 
	 * @return the health of the object
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * This method returns the location of the SpikePit.
	 * 
	 * @return location of the spike pit object
	 */
	public Point getLocation() {
		return this.location;
	}

	/**
	 * This method returns the collision rectangle of the SpikePit.
	 * 
	 * @return collision rectangle of the SpikePit
	 */
	public SpikePitRectangle getRectangle() {
		return rect;
	}

	/**
	 * This method sets the damage the spike pit will inflict when run over.
	 * 
	 * @param dmg
	 *            this is the damage that the spike pit will inflict when the
	 *            tank runs over it
	 */
	public void setDamage(int dmg) {

	}

}

package battleTank;

import java.util.Observable;
import rectangles.ImmovableBlockRectangle;

/**
 * This class is the immovable object which has health (-1), a rectangle that
 * will be used to determine object collisions, and a location. The immovable
 * object may be created, determined if has health, receive damage, and
 * determined if needed to be removed from the field.
 * 
 * @author Team Exception
 * 
 * @extends Observable
 * 
 * @implements Obstacle
 * 
 * @see Obstacle, TankView, Crate, FireRing, SpikePit, TNT
 * 
 */
public class ImmovableBlock extends Observable implements Obstacle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;
	private int health;// health of the crate (either 0 or 1)
	private ImmovableBlockRectangle rect;// shape for crate controlling
											// collisions
	@SuppressWarnings("unused")
	private Map map;
	private Point location;// location of crate

	/**
	 * This is the class constructor for the ImmovableBLock class which simply
	 * is only placed on a given map and serves no other purpose. It has health
	 * but it is assigned a value of -1 so it may never be destroyed.
	 * 
	 * @category constructor
	 * 
	 * @param p
	 *            location at which the immovable block is to placed
	 * @param map
	 *            the map on which this immovable object is on
	 */
	public ImmovableBlock(Point p, Map map) {
		this.map = map;
		location = p;// setting location of immovable block
		health = -1;// -1 invincible so -1 health
		// creating new rectangle to detect collisions
		rect = new ImmovableBlockRectangle(location.col - 25, location.row - 25);

	}

	/**
	 * This method will deal the damage appropriate to this immovable object
	 * 
	 * @param dmg
	 *            damage that this object will take
	 */
	public void recieveDamage(int dmg) {
		health = health + dmg - dmg;// troll math
	}

	/**
	 * This method will return whether this object is to be removed from the
	 * field
	 * 
	 * @return whether this object should be removed (never will be) so always
	 *         returns false
	 */
	public boolean removeObstacle() {
		return false;// this object can't be removed
	}

	/**
	 * This method returns the health of this object.
	 * 
	 * @return the health of the object
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * This method sets the location to which this immovable object is to be
	 * set.
	 * 
	 * @return the location of the immovable object
	 */
	public Point getLocation() {
		return this.location;
	}

	/**
	 * This method returns the appropriate collision rectangle for this
	 * ImmovableBlock.
	 * 
	 * @return collision rectangle for this ImmovableBlock
	 */
	public ImmovableBlockRectangle getRectangle() {
		return rect;
	}

}

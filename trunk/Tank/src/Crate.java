import java.util.Observable;
import rectangles.CrateRectangle;

/**
 * 
 * @author Team Exception
 * 
 *         This class is the crate object which has health, a rectangle that
 *         will be used to determine object collisions, and a location. The
 *         crate object may be created, determined if has health, receive
 *         damage, and determined if needed to be removed from the field.
 * 
 */
public class Crate extends Observable implements Obstacle {

	private int health;// health of the crate (either 0 or 1)
	private CrateRectangle rect;// shape for crate controlling collisions
	private Point location;// location of crate

	/**
	 * Class Constructor
	 * 
	 * @param p
	 *            point of the location at which the crate is to be created
	 */
	public Crate(Point p) {

		location = p;
		health = 1;// one shot death
		// 25 is to offset for the size so it's not off the field
		rect = new CrateRectangle(location.col - 25, location.row - 25);
	}

	/**
	 * The method receiveDamage will modify the health of this crate object
	 * according to the damage done to it.
	 * 
	 * @param dmg
	 *            damage that will be done to the crate
	 */
	public void recieveDamage(int dmg) {
		health = health - dmg;
		if (this.removeObstacle()) {
			rect = new CrateRectangle(-1, -1);// removing off field
			setChanged();
			notifyObservers("CrateDead");
		}

	}

	/**
	 * @return boolean returns whether the obstacle is dead or not and needs to
	 *         be removed
	 */
	public boolean removeObstacle() {
		if (health == 0) {// if health is 0
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return int returns the health of the crate
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @return Point returns the location of the crate
	 */
	public Point getLocation() {
		return location;
	}
	

	/**
	 * @return CrateRectangle returns the rectangle object that will represent
	 *         the collisions for the crate
	 */
	public CrateRectangle getRectangle() {
		return rect;
	}

}

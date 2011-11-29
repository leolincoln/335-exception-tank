import java.util.Observable;
import rectangles.ImmovableBlockRectangle;

/**
 * 
 * @author Team Exception
 * 
 *         This class is the immovable object which has health (-1), a rectangle
 *         that will be used to determine object collisions, and a location. The
 *         immovable object may be created, determined if has health, receive
 *         damage, and determined if needed to be removed from the field.
 * 
 */
public class ImmovableBlock extends Observable implements Obstacle {

	private int health;// health of the crate (either 0 or 1)
	private ImmovableBlockRectangle rect;// shape for crate controlling
											// collisions
	private Point location;// location of crate

	/**
	 * Class constructor
	 * 
	 * @param p
	 *            location at which the immovable block is to placed
	 */
	public ImmovableBlock(Point p) {

		location = p;// setting location of immovable block
		health = -1;// -1 invincible so -1 health
		// creating new rectangle to detect collisions
		rect = new ImmovableBlockRectangle(location.col - 25, location.row - 25);

	}

	/**
	 * @param dmg
	 *            damage that this object will take
	 */
	public void recieveDamage(int dmg) {
		health = health + dmg - dmg;// troll math
	}

	/**
	 * 
	 * @return boolean returns whether this object should be removed (never will
	 *         be) so always returns false
	 */
	public boolean removeObstacle() {
		return false;// this object can't be removed
	}

	/**
	 * @return int returns the health of the object
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * @return Point returns the location of the immovable object
	 */
	public Point getLocation() {
		return this.location;
	}

	public ImmovableBlockRectangle getRectangle() {
		return rect;
	}

}

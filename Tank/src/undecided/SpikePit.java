package undecided;
import java.util.Observable;
import rectangles.SpikePitRectangle;

public class SpikePit extends Observable implements Obstacle {

	private int health;// health of the spike pit (-1)
	private SpikePitRectangle rect;// shape for spike pit controlling collisions
	private Point location;// location of spike pit
	private Map map;

	/**
	 * Class constructor
	 * 
	 * @param p
	 *            location that the spike pit is to be set
	 */
	public SpikePit(Point p, Map map) {
		this.map = map;
		location = p;
		health = -1;// spike pit can't be destroyed
		// 25 is to offset for the size so it's not off the field
		rect = new SpikePitRectangle(location.col - 25, location.row - 25);

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
	 * @return Point returns the location of the spike pit object
	 */
	public Point getLocation() {
		return this.location;
	}

	public SpikePitRectangle getRectangle() {
		return rect;
	}

	
	
	
	
	
	/**
	 * 
	 * @param dmg
	 *            this is the damage that the spike pit will inflict when the
	 *            tank runs over it
	 */
	public void setDamage(int dmg) {

		
	}

}

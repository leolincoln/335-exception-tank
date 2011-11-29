import java.util.Observable;
import rectangles.TNTRectangle;

public class TNT extends Observable implements Obstacle {

	private int health;// health of the TNT (1 or 0)
	private TNTRectangle rect;// shape for TNT controlling collisions
	private Point location;// location of the TNT

	/**
	 * Class constructor
	 * 
	 * @param p
	 *            location that the TNT is to be set
	 */
	public TNT(Point p) {

		location = p;
		health = 1;// TNT starts with 1 health
		// 25 is to offset for the size so it's not off the field
		rect = new TNTRectangle(location.col - 25, location.row - 25);

	}

	/**
	 * The method receiveDamage will modify the health of this TNT object
	 * according to the damage done to it.
	 * 
	 * @param dmg
	 *            damage that will be done to the TNT
	 */
	public void recieveDamage(int dmg) {
		health = health - dmg;
		if (this.removeObstacle()) {
			rect = new TNTRectangle(-1, -1);// removing off field
			setChanged();
			notifyObservers("TNTDead");
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
	 * @return int returns the health of the TNT
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * @return Point returns the location of the TNT
	 */
	public Point getLocation() {
		return this.location;
	}

	
	/**
	 * @return CrateRectangle returns the rectangle object that will represent
	 *         the collisions for the TNT
	 */
	public TNTRectangle getRectangle() {
		return rect;
	}

	
	
	
	
	/**
	 * 
	 * @param dmg
	 *            this is the damage that the TNT is to inflict when destroyed.
	 */
	public void setDamage(int dmg) {

	}

}

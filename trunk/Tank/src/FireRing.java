import java.util.Observable;
import rectangles.FireRingRectangle;

public class FireRing extends Observable implements Obstacle {

	private int health;// health of the fire ring (either 0 or 1)
	private FireRingRectangle rect;// shape for crate controlling collisions
	private Point location;// location of fire ring


	/**
	 * Class constructor
	 * 
	 * @param p
	 *            location that the fire ring is to be set
	 */
	public FireRing(Point p) {

		location = p;
		health = 1;// one shot death (ASSUMING THE RING CAN BE DESTROYED!)
		// 25 is to offset for the size so it's not off the field
		rect = new FireRingRectangle(location.col - 25, location.row - 25);

	}

	/**
	 * The method receiveDamage will modify the health of this fire ring object
	 * according to the damage done to it.
	 * 
	 * @param dmg
	 *            damage that will be done to the fire ring
	 */
	public void recieveDamage(int dmg) {
		health = health - dmg;
		if (this.removeObstacle()) {
			rect = new FireRingRectangle(-1, -1);// removing off field
			setChanged();
			notifyObservers("FireRingDead");
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
	 * @return int returns the health of the fire ring
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * @return Point returns the location of the fire ring
	 */
	public Point getLocation() {
		return this.location;
	}
	

	/**
	 * @return CrateRectangle returns the rectangle object that will represent
	 *         the collisions for the fire ring
	 */
	public FireRingRectangle getRectangle() {
		return rect;
	}

	/**
	 * 
	 * @param dly
	 *            this is the delay to be set for the movement of the fire ring
	 */
	public void setDelay(int dly) {

	}

	/**
	 * 
	 * @param p
	 *            the point to which the fire ring is to move
	 * @return boolean returns whether the fire ring has moved properly
	 */
	public boolean move(Point p) {

		// if within the window
		if (p.row <= 25 || p.row >= 985 || p.col <= 25 || p.col >= 985) {
			this.location = p;
			notifyObservers(this);
			setChanged();
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param dmg
	 *            this is the damage that the fire ring is to inflict when
	 *            collides with another object
	 */
	public void setDamage(int dmg) {

	}

}

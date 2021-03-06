package undecided;

import java.io.Serializable;

/**
 * This interface is implemented by all the non user controlled objects on the
 * field: crate, fire ring, barrier, spike pit, and TNT box.
 * 
 * @author Team Exception
 * 
 * @extends Serializable
 * 
 * 
 */
public interface Obstacle extends Serializable {

	/**
	 * The method receiveDamage will modify the health of this object according
	 * to the damage done to it.
	 * 
	 * @param dmg
	 *            damage that will be done to the crate
	 */
	public void recieveDamage(int dmg);

	/**
	 * @return boolean returns whether the obstacle is dead or not and needs to
	 *         be removed
	 */
	public boolean removeObstacle();

	/**
	 * @return int returns the health of the crate
	 */
	public int getHealth();

	/**
	 * @return Point returns the location of the crate
	 */
	public Point getLocation();

}

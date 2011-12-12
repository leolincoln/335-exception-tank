package undecided;

import java.io.Serializable;

/**
 * This interface class simple describes the effect that different terrain will
 * have on the PlayerTank and EnemyTank speed. It is extended by the Ice, Grass,
 * and Sand classes.
 * 
 * @author Team Exception
 * 
 * @extends Serializable
 * 
 * @see Ice, Grass, Sand
 */
public interface Terrain extends Serializable {

	/**
	 * This method returns the speed of the given terrain.
	 * 
	 * @return speed of this terrain
	 */
	public int setSpeed();

}

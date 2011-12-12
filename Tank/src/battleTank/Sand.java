package battleTank;

/**
 * This simple class simulates the speed on which the tanks will move if on
 * sand. If the tanks are on sand, the movement speed will be 3.
 * 
 * @author Team Exception
 * 
 * @see Terrain, Ice, Sand
 * 
 * @serial
 */
public class Sand implements Terrain {

	private static final long serialVersionUID = 1L;

	/**
	 * This method returns the speed at which the tank moves on sand
	 * 
	 * @return speed of 3 for on sand
	 */
	@Override
	public int setSpeed() {
		return 3;
	}

}

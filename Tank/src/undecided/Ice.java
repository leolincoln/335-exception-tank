package undecided;

/**
 * This simple class simulates the speed on which the tanks will move if on
 * ice. If the tanks are on ice, the movement speed will be 7.
 * 
 * @author Team Exception
 * 
 * @see Terrain, Ice, Sand
 * 
 * @serial
 */
public class Ice implements Terrain {

	private static final long serialVersionUID = 1L;

	/**
	 * This method returns the speed at which the tank moves on gice
	 * 
	 * @return speed of 7 for on ice
	 */
	@Override
	public int setSpeed() {
		return 7;
	}

}

package undecided;

/**
 * This interface class describes the effects that the item type classes should
 * have, namely activateEffect on either the PlayerTank or the EnemyTank.
 * 
 * @author Team Exception
 * 
 * @see BubbleShield, IceBlock, SpeedBoost
 * 
 */
public interface Item {

	/**
	 * This method will activate the effect of the item for the PlayerTank.
	 * 
	 * @param t
	 *            the PlayerTank that is activating the item
	 */
	public void activateEffect(PlayerTank t);

	/**
	 * This method will activate the effect of the item for the EnemyTank.
	 * 
	 * @param t
	 *            the EnemyTank that is activating the item
	 */
	public void activateEffect(EnemyTank t);

}

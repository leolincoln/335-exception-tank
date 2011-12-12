package battleTank;

import java.util.Observable;
import rectangles.IceBlockRectangle;

/**
 * This class is an item that will appear on any map. If either of the tanks
 * run into an ice block, the opposing tank's speed will be set to 2 for 2
 * seconds before it can move again.
 * 
 * @author Team Exception
 * 
 * @extends Observable
 * 
 * @implements Item
 * 
 * @see Item, PlayerTank, EnemyTank
 */
public class IceBlock extends Observable implements Item {

	// declaring instance variables
	private IceBlockRectangle rect;
	private Point location;
	private PlayerTank player;
	private EnemyTank enemy;
	private Map map;

	/**
	 * This is the class constructor for the IceBlock class and sets the
	 * location of the ice block, creates a collision rectangle, and on which
	 * map it is on.
	 * 
	 * @param p
	 *            location it is to be on the map
	 * @param map
	 *            the map it is on
	 */
	public IceBlock(Point p, Map map) {
		location = p;
		rect = new IceBlockRectangle(location.col - 25, location.row - 25);
		this.map = map;
		player = this.map.getPlayers().getFirst();
		enemy = this.map.getEnemies().getFirst();

	}

	/**
	 * This method creates a collision rectangle for the IceBlock
	 * 
	 * @return the collision rectangle for the IceBlock
	 */
	public IceBlockRectangle getRectangle() {
		return rect;
	}

	/**
	 * This method returns the location of the IceBlock.
	 * 
	 * @return the location of the IceBlock
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * This method activates the item if the player gets the IceBlock item first
	 * slowing down the EnemyTank's speed.
	 */
	@Override
	public void activateEffect(PlayerTank t) {

		TimerThread time = new TimerThread();
		time.start();

	}

	/**
	 * This method activates the item if the EnemyTank gets the IceBlock item
	 * first slowing down the PlayerTank's speed.
	 */
	@Override
	public void activateEffect(EnemyTank t) {

		EnemyTimerThread time = new EnemyTimerThread();
		time.start();

	}

	/**
	 * This private inner class controls how long the IceBlock will be in effect
	 * if the enemyTank gets to the IceBlock first slowing down the speed of the
	 * PlayerTank.
	 * 
	 * @author Team Exception
	 * 
	 * @category inner class
	 * 
	 * @see Thread, IceBlock, EnemyTank, PlayerTank
	 */
	private class EnemyTimerThread extends Thread {
		private int timePassed = 0;

		public void run() {
			while (timePassed < 10) {
				player.setSpeed(0);
				if (timePassed == 9) {
					player.setSpeed(5);
				}
				try {
					Thread.sleep(200);
				} catch (Exception e) {

				}
				timePassed++;
			}
		}

	}

	/**
	 * This private inner class controls how long the IceBlock will be in effect
	 * if the PlayerTank gets to the IceBlock first slowing down the speed of
	 * the EnemyTank.
	 * 
	 * @author Team Exception
	 * 
	 * @category inner class
	 * 
	 * @see Thread, IceBlock, EnemyTank, PlayerTank
	 */
	private class TimerThread extends Thread {

		private int timePassed = 0;

		public void run() {
			while (timePassed < 10) {
				enemy.setSpeed(0);
				if (timePassed == 9) {
					enemy.setSpeed(1);
				}
				try {
					Thread.sleep(200);
				} catch (Exception e) {

				}
				timePassed++;

			}

		}
	}

}

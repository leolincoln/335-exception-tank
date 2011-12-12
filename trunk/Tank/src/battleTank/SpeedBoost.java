package battleTank;

import java.util.Observable;

import rectangles.SpeedBoostRectangle;

/**
 * This class is an item that will appear on any map. If either of the two tanks
 * gets this item, it will receive a temporary speed boost increasing the
 * velocity in which the tank moves across the map.
 * 
 * @author Team Exception
 * 
 * @extends Observable
 * 
 * @implements Item
 * 
 * @see Item, PlayerTank, EnemyTank
 */
public class SpeedBoost extends Observable implements Item {

	// declaring instance variables
	private SpeedBoostRectangle rect;
	private Point location;
	private PlayerTank player;
	private EnemyTank enemy;

	/**
	 * This is the class constructor for the SpeedBoost class. It basically
	 * includes the location of the item and the collision rectangle.
	 * 
	 * @param p location of the SpeedBoost
	 */
	public SpeedBoost(Point p) {
		location = p;
		rect = new SpeedBoostRectangle(location.col - 25, location.row - 25);

	}

	/**
	 * This method returns the collision rectangle for the SpeedBoost.
	 * 
	 * @return collision rectangle for the SpeedBoost
	 */
	public SpeedBoostRectangle getRectangle() {
		return rect;
	}

	/**
	 * This method returns the location of the SpeedBoost
	 * 
	 * @return location of the SpeedBoost
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

		player = t;
		t.setActiveBoost(true);
		TimerThread time = new TimerThread();
		time.start();

	}

	/**
	 * This method activates the item if the EnemyTank gets the IceBlock item
	 * first slowing down the PlayerTank's speed.
	 */
	@Override
	public void activateEffect(EnemyTank t) {

		enemy = t;
		EnemyTimerThread time = new EnemyTimerThread();
		time.start();

	}

	/**
	 * This private inner class controls how long the SpeedBoost will be in effect
	 * the EnemyTank.
	 * 
	 * @author Team Exception
	 * 
	 * @category inner class
	 * 
	 * @see Thread, SpeedBoost, IceBlock, EnemyTank, PlayerTank
	 */
	private class EnemyTimerThread extends Thread {
		private int timePassed = 0;

		public void run() {
			while (timePassed < 10) {
				if (timePassed == 9) {
					enemy.setSpeed(enemy.getSpeed() / 2);
				} else if (timePassed == 0) {
					enemy.setSpeed(enemy.getSpeed() * 2);
				} else {

				}
				timePassed++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

				}

			}
		}
	}

	/**
	 * This private inner class controls how long the SpeedBoost will effect
	 * the PlayerTank.
	 * 
	 * @author Team Exception
	 * 
	 * @category inner class
	 * 
	 * @see Thread, SpeedBoost, IceBlock, EnemyTank, PlayerTank
	 */
	private class TimerThread extends Thread {

		private int timePassed = 0;

		public void run() {
			while (timePassed < 10) {
				if (timePassed == 9) {
					player.setActiveBoost(false);
					player.setSpeed(player.getSpeed() / 2);
				} else if (timePassed == 0) {
					player.setSpeed(player.getSpeed() * 2);
				} else {

				}
				timePassed++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

				}

			}

		}

	}

}

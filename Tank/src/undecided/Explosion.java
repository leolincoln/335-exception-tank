package undecided;

import java.awt.Image;
import java.util.Observable;

import javax.swing.ImageIcon;

/**
 * This class will control the explosions for when either the EnemyTank or the
 * PlayerTank is destroyed. It will basically start a thread at the given point
 * on a given map and cycle through 10 images that will simulate an animation of
 * the tank exploding.
 * 
 * @author Team Exception
 * 
 * @extends Observable
 * 
 * @see TankView, EnemyTank, PlayerTank
 * 
 */
public class Explosion extends Observable {

	// declaring instance variables
	private int timePassed;
	private Image img;
	private Point location;
	private Map map;

	/**
	 * This is the class constructor for the Explosion class. It will set the
	 * time to 0, map that is is on, retrieve the correct image, and the
	 * location on the map that the explosion is to occur.
	 * 
	 * @param p
	 *            point on map at which the explosion is to occur
	 * @param map
	 *            current map
	 */
	public Explosion(Point p, Map map) {
		timePassed = 0;
		this.map = map;
		img = new ImageIcon("images/explosion1.png").getImage();
		location = p;

		ExplosionThread et = new ExplosionThread();
		et.start();

	}

	/**
	 * THis method returns the location of the explosion
	 * 
	 * @return location of explosion
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * This method returns the current image that is being used for the
	 * explosion animation.
	 * 
	 * @return current image of the explosion
	 */
	public Image getImage() {
		return img;
	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private inner class runs the thread that will control the
	 *         explosion animation at any given point within the map. It will
	 *         cycle through 10 separate images of the explosion to simulate the
	 *         animation triggering each at a specific time.
	 * 
	 * @extends Thread
	 * 
	 * @see Thread
	 * 
	 */
	private class ExplosionThread extends Thread {
		/**
		 * This method will run the thread controlling the animation for the
		 * explosion. It will cycle through 10 separate images over a fixed
		 * period of time.
		 * 
		 * @see Thread
		 * 
		 */
		@Override
		public void run() {
			while (timePassed <= 15) {
				if (timePassed == 0) {
					img = new ImageIcon("images/explosion1.png").getImage();
					location = new Point(location.row - 2, location.col - 2);
					notifyObservers("Boom");
					setChanged();
				}
				if (timePassed == 2) {
					img = new ImageIcon("images/explosion2.png").getImage();
					location = new Point(location.row - 2, location.col - 2);
					notifyObservers("Boom");
					setChanged();
				}
				if (timePassed == 4) {
					img = new ImageIcon("images/explosion3.png").getImage();
					location = new Point(location.row - 2, location.col - 2);
					notifyObservers("Boom");
					setChanged();
				}
				if (timePassed == 6) {
					img = new ImageIcon("images/explosion4.png").getImage();
					location = new Point(location.row - 2, location.col - 2);
					notifyObservers("Boom");
					setChanged();
				}
				if (timePassed == 8) {
					img = new ImageIcon("images/explosion5.png").getImage();
					location = new Point(location.row - 2, location.col - 2);
					notifyObservers("Boom");
					setChanged();
				}
				if (timePassed == 10) {
					img = new ImageIcon("images/explosion6.png").getImage();
					location = new Point(location.row - 2, location.col - 2);
					notifyObservers("Boom");
					setChanged();
				}
				if (timePassed == 12) {
					img = new ImageIcon("images/explosion7.png").getImage();
					location = new Point(location.row - 2, location.col - 2);
					notifyObservers("Boom");
					setChanged();
				}
				if (timePassed == 12) {
					img = new ImageIcon("images/explosion8.png").getImage();
					location = new Point(location.row - 2, location.col - 2);
					notifyObservers("Boom");
					setChanged();
				}
				if (timePassed == 12) {
					img = new ImageIcon("images/explosion9.png").getImage();
					location = new Point(location.row - 2, location.col - 2);
					notifyObservers("Boom");
					setChanged();
				}
				if (timePassed == 12) {
					img = new ImageIcon("images/explosion10.png").getImage();
					location = new Point(location.row - 2, location.col - 2);
					notifyObservers("Boom");
					setChanged();
				}

				if (timePassed == 14) {
					location = new Point(-200, -200);
					map.getExplosions().remove(this);
				}
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {

				}
				timePassed++;

			}
		}

	}
}

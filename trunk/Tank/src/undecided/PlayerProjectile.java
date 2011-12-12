package undecided;

import java.util.Observable;

import rectangles.ProjectileRectangle;

/**
 * 
 * @author Team Exception
 * 
 *         The PlayerProjectile class is the bullets that PlayerTank will fire.
 *         It consists of a location speed in both the x and y direction and a
 *         projection of itself as a geometry class rectangle to control
 *         collisions.
 * 
 * @see TankView, EnemyTank
 * 
 * @extends Observable
 * 
 * @implements Projectile
 * 
 */
public class PlayerProjectile extends Observable implements Projectile {

	// declaring instance variables
	private Point p;// location of the projectile
	private int speed;// speed of the projectile
	public int xspeed, yspeed;// x and y speeds of projectile
	boolean exists;// determines whether the projectile is in the field
	private ProjectileRectangle rect;// rectangle of the projectile that will be
										// used to determine collisions
	private PlayerProjectile thisMissle;
	private PlayerTank tank;
	private Map map;

	/**
	 * This is the class constructor for the PlayerProjectile class. It will
	 * initialize the bearings meaning the direction in which it will go
	 * including the x and y directions so it can travel diagonally as well.
	 * 
	 * @param p
	 *            location of the projectile
	 * @param d
	 *            direction of the projectile
	 */
	public PlayerProjectile(Point p, int x, int y, PlayerTank t, Map map) {
		this.p = p;
		speed = 20;
		this.map = map;
		this.xspeed = x;
		this.yspeed = y;
		rect = new ProjectileRectangle(p.col - 3, p.row - 3);// creating new
																// projectile
																// rectangle
		exists = true;
		tank = t;

		thisMissle = this;

		Thread pt = new ProjectileThread();// creating new thread for the
		System.out.println("Thread created"); // projectile's movement
		pt.start();// beginning the projectile's movement
	}

	/**
	 * This method returns the location of the projectile
	 * 
	 * @return the current location of the projectile
	 */
	public Point getLocation() {
		return p;
	}

	/**
	 * This method returns the current PlayerTank
	 * 
	 * @return current PlayerTank
	 */
	public PlayerTank getTank() {
		return tank;
	}

	/**
	 * This method returns the collision rectangle for the projectile
	 * 
	 * @return the rectangle that will determine the projectile's collisions
	 */
	public ProjectileRectangle getRectangle() {
		return rect;
	}

	/**
	 * This method sets the location of the projectile
	 * 
	 * @param p
	 *            location of the projectile
	 */
	public void setLocation(Point p) {
		this.p = p;
	}

	/**
	 * This location returns the damage that the projectile will do upon impact.
	 * 
	 * @return the damage that the projectile will do on impact
	 */
	public int getDamage() {
		return 1;
	}

	/**
	 * This method determines if the projectile is still in the window/field and
	 * if so notifies observers that is still in the field and may be colliding
	 * with other objects on the field
	 * 
	 * @see TankView, Rectangle
	 */
	public void exists() {
		if (p.row <= 15 || p.row >= 665 || p.col <= 15 || p.col >= 975
				|| map.isOver()) {
			p = new Point(-1, -1);
			rect = new ProjectileRectangle(-100, -100);
			exists = false;

		}
	}

	/**
	 *  This private inner class controls the projectile's movement
	 *  through a separate thread.
	 * 
	 * @author Team Exception

	 * @category inner class
	 * 
	 * @extends Thread
	 * 
	 */
	private class ProjectileThread extends Thread {

		/**
		 * This method will run the thread that controls the projectile's
		 * movement across the screen. It will allow only one projectile of each
		 * tank on the field at a time and will be removed from the field upon
		 * impact of an obstacle.
		 */
		public synchronized void run() {
			while (exists) {
				int x = p.col;
				int y = p.row;
				x = x + xspeed;// incrementing location of new point!!!
				y = y + yspeed;

				p = new Point(y, x);// new calculated point
				rect = new ProjectileRectangle(p.col - 3, p.row - 3);
				
				exists();
				try {
					notifyObservers(thisMissle);
					setChanged();
				} catch (NullPointerException e) {
					map.getProjectiles().remove(this);
				}
				try {
					sleep(speed);
				} catch (InterruptedException e) {

				}

			}
		}
	}

	/**
	 * This method will set the projectile's existence to false therefore to be
	 * removed later from the field.
	 */
	public void collided() {
		exists = false;

	}

}

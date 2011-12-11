package undecided;
import View.NetWorkPlayerTank;
import java.util.Observable;

import rectangles.ProjectileRectangle;

/**
 * 
 * @author Team Exception
 * 
 *         This class models the player's controlled tank's bullets. These
 *         projectiles have a location, speed,direction, and rectangular
 *         projecting the munition's location on the field and will be used to
 *         detect collisions.
 * 
 */

public class PlayerProjectile extends Observable  implements Projectile {

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
	 * Class constructor
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
		this.yspeed= y;
		rect = new ProjectileRectangle(p.col - 3, p.row - 3);// creating new projectile rectangle
		exists = true;
		 tank = t;
		
		thisMissle = this;
		
		
		Thread pt = new ProjectileThread();// creating new thread for the
											// projectile's movement
		pt.start();// beginning the projectile's movement
	}
	

	
	
	/**
	 * 
	 * @return Point returns the current location of the projectile
	 */
	public Point getLocation() {
		return p;
	}
	public PlayerTank getTank() {
		return tank;
	}

	/**
	 * 
	 * @return ProjectileRectangle returns the rectangle that will determine the
	 *         projectile's collisions
	 */
	public ProjectileRectangle getRectangle() {
		return rect;
	}

	/**
	 * 
	 * @param p
	 *            location of the projectile
	 */
	public void setLocation(Point p) {
		this.p = p;
	}

	/**
	 * 
	 * @return int returns the damage that the projectile will do on the object
	 *         it hits
	 */
	public int getDamage() {
		return 1;
	}

	/**
	 * This method determines if the projectile is still in the window/field and
	 * if so notifies observers that is still in the field and may be colliding
	 * with other objects on the field
	 */
	public void exists() {
		if (p.row <= 15 || p.row >= 665 || p.col <= 15 || p.col >= 975 || map.isOver()) {
			p = new Point(-1, -1);
			rect = new ProjectileRectangle(-100, -100);
			exists = false;
		
			
			
		}
	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private inner class controls the projectile's movement
	 *         through a separate thread.
	 * 
	 */
	private class ProjectileThread extends Thread {

		public synchronized void run() {
			while (exists) {

				int x = p.col;
				int y = p.row;
				x = x + xspeed;// incrementing location of new point!!!
				y = y + yspeed;
				
				p = new Point(y, x);// new calculated point
				rect = new ProjectileRectangle(p.col - 3, p.row - 3);// new projectile rectangle at new location
				
//				if (d == Direction.EAST) {
//					p = new Point(p.row, p.col + 3);
//					rect = new ProjectileRectangle(p.col - 3, p.row - 3);
//				}
//				if (d == Direction.WEST) {
//					p = new Point(p.row, p.col - 3);
//					rect = new ProjectileRectangle(p.col - 3, p.row - 3);
//				}
//				if (d == Direction.NORTH) {
//					p = new Point(p.row - 3, p.col);
//					rect = new ProjectileRectangle(p.col - 3, p.row - 3);
//				}
//				if (d == Direction.SOUTH) {
//					p = new Point(p.row + 3, p.col);
//					rect = new ProjectileRectangle(p.col - 3, p.row - 3);
//				}
				exists();
				try {
				notifyObservers(thisMissle);
				setChanged();
				} catch(NullPointerException e) {
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
	 * This method determines if the projectile has collided into another
	 * object.
	 */
	public void collided() {
		exists = false;

	}

}

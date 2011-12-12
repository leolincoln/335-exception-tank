package undecided;

import java.awt.Image;

import java.util.LinkedList;
import java.util.Observable;

import javax.swing.ImageIcon;

import rectangles.TankRectangle;

/**
 * This class is the tank object that is controlled by the player that can be
 * moved by arrow keys and shoot independently via the mouse. It contains the
 * tanks location, health, and shape that detects collisions.
 * 
 * @author Team Exception
 * 
 * @extends Observable
 * 
 * @see EnemyTank, TankView, Items, Observable
 * 
 */
public class PlayerTank extends Observable {

	// declaring instance variables
	private Point p;
	// the speed at which the tank moves across the screen
	private int speed;
	// PlayerTank's associated rectangle
	private TankRectangle t;
	private int health;
	private Direction d;
	private Image img;
	private Map map;
	private boolean activeShield, activeBoost, activeIceBlock;

	/**
	 * This is the class constructor for the PlayerTank class. It contains
	 * values such as its health, location, and which map it is on.
	 * 
	 * @category constructor
	 * 
	 * @param p
	 *            the location at which it is to be assigned
	 * @param map
	 *            the map on which it is on
	 */
	public PlayerTank(Point p, Map map) {
		this.p = p;
		health = 1;
		this.map = map;
		activeShield = false;
		activeBoost = false;
		activeIceBlock = false;
		d = Direction.EAST;
		t = new TankRectangle(p.col - 25, p.row - 25);
		img = new ImageIcon("images/tankSOUTH.png").getImage();
		speed = 5;

	}

	/**
	 * This method returns the shape of the collision rectangle for the tank.
	 * 
	 * @return rectangle for tank collisions
	 * 
	 */
	public TankRectangle getRectangle() {
		return t;
	}

	/**
	 * This method returns the direction in which the PlayerTank is facing.
	 * 
	 * @return direction in which the PlayerTank is facing
	 */
	public Direction getDirection() {
		return d;
	}

	/**
	 * This method returns the curretn location of the tank.
	 * 
	 * @return the player controlled tank's position
	 */
	public Point getLocation() {
		return p;
	}

	/**
	 * This method returns the current image of the tank.
	 * 
	 * @return current image of the tank
	 */
	public Image getImage() {
		return img;
	}

	/**
	 * This method sets the location of the PlayerTank to the input parameter.
	 * 
	 * @param p
	 *            this location at which the tank is to be set at
	 */
	public void setLocation(Point p) {
		this.p = p;
	}

	/**
	 * This method returns the current health of the PlayerTank.
	 * 
	 * @return current health of the PlayerTank
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * This method sets the health to the desire input parameter.
	 * 
	 * @param x
	 *            health to be set at
	 */
	public void setHealth(int x) {
		if (x == 2) {
			activeShield = true;
		}
		health = x;
	}

	/**
	 * This class will determine the speed of the PlayerTank depending on the
	 * terrain that the tank is traversing across
	 * 
	 * @param t
	 *            this is the terrain in which the PlayerTank is on whether
	 *            grass, ice, or sand
	 */
	public void setSpeed(int x) {
		if (x == 0) {
			activeIceBlock = true;
			img = new ImageIcon("images/tankFrozen" + d + ".png").getImage();
			speed = 0;
		} else {
			activeIceBlock = false;
			speed = x;
		}
	}

	/**
	 * This method returns the current speed of the PlayerTank.
	 * 
	 * @return the current speed of the PlayerTank
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * This method moves (if possible) the PlayerTank one unit in the up
	 * direction. It will take into account whether it is destroyed by moving
	 * into a FireRing or SpikePit, and will adjust the image of the tank
	 * depending on whether it has a BubbleShield, IceBlock or neither. It will
	 * also choose the tank image that is facing North.
	 * 
	 * @return whether it has successfully moved up
	 */
	public boolean moveUp() {
		LinkedList<Obstacle> obs = map.getObstacles();
		LinkedList<EnemyTank> enemies = map.getEnemies();
		d = Direction.NORTH;
		p = new Point(p.row - this.speed, p.col);
		t = new TankRectangle(p.col - 25, p.row - 25);
		if (health == 1) {
			img = new ImageIcon("images/tankNORTH.png").getImage();
		}
		if (health == 2) {
			img = new ImageIcon("images/tankShieldNORTH.png").getImage();
		}
		if (activeIceBlock) {
			img = new ImageIcon("images/tankFrozenNORTH.png").getImage();
		}
		for (int i = 0; i < enemies.size(); i++) {
			EnemyTank e = enemies.get(i);
			if (e.getRectangle().intersects(t)) {
				p = new Point(p.row + this.speed, p.col);
				t = new TankRectangle(p.col - 25, p.row - 25);
				return false;
			}
		}
		for (int i = 0; i < obs.size(); i++) {
			Obstacle o = obs.get(i);
			if (o instanceof ImmovableBlock) {
				ImmovableBlock b = (ImmovableBlock) o;
				if (b.getRectangle().intersects(t)) {
					p = new Point(p.row + this.speed, p.col);
					t = new TankRectangle(p.col - 25, p.row - 25);
					return false;

				}
			}
			if (o instanceof Crate) {
				Crate c = (Crate) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row + this.speed, p.col);
						t = new TankRectangle(p.col - 25, p.row - 25);
						return false;
					}
				}
			}
			if (o instanceof TNT) {
				TNT c = (TNT) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row + this.speed, p.col);
						t = new TankRectangle(p.col - 25, p.row - 25);
						return false;
					}
				}
			}

		}

		if (p.row < 30) {
			p = new Point(p.row + this.speed, p.col);
			t = new TankRectangle(p.col - 25, p.row - 25);
			return false;
		}
		notifyObservers(this);
		setChanged();
		return true;
	}

	/**
	 * This method moves (if possible) the PlayerTank one unit in the down
	 * direction. It will take into account whether it is destroyed by moving
	 * into a FireRing or SpikePit, and will adjust the image of the tank
	 * depending on whether it has a BubbleShield, IceBlock or neither. It will
	 * also choose the tank image that is facing South.
	 * 
	 * @return whether it has successfully moved down
	 */
	public boolean moveDown() {
		LinkedList<Obstacle> obs = map.getObstacles();
		LinkedList<EnemyTank> enemies = map.getEnemies();
		d = Direction.SOUTH;
		p = new Point(p.row + this.speed, p.col);
		t = new TankRectangle(p.col - 25, p.row - 25);
		if (health == 1) {
			img = new ImageIcon("images/tankSOUTH.png").getImage();
		}
		if (health == 2) {
			img = new ImageIcon("images/tankShieldSOUTH.png").getImage();
		}
		if (activeIceBlock) {
			img = new ImageIcon("images/tankFrozenSOUTH.png").getImage();
		}
		for (int i = 0; i < enemies.size(); i++) {

			EnemyTank e = enemies.get(i);
			if (e.getRectangle().intersects(t)) {
				p = new Point(p.row - this.speed, p.col);
				t = new TankRectangle(p.col - 25, p.row - 25);
				System.out.println("stopped at enemy");
				return false;
			}
		}
		for (int i = 0; i < obs.size(); i++) {
			Obstacle o = obs.get(i);
			if (o instanceof ImmovableBlock) {
				ImmovableBlock b = (ImmovableBlock) o;
				if (b.getRectangle().intersects(t)) {
					p = new Point(p.row - this.speed, p.col);
					t = new TankRectangle(p.col - 25, p.row - 25);
					System.out.println("stopped at obs");
					return false;
				}

			}
			if (o instanceof Crate) {
				Crate c = (Crate) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row - this.speed, p.col);
						t = new TankRectangle(p.col - 25, p.row - 25);
						System.out.println("stopped at crate");
						return false;
					}
				}
			}
			if (o instanceof TNT) {
				TNT c = (TNT) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row - this.speed, p.col);
						t = new TankRectangle(p.col - 25, p.row - 25);
						System.out.println("stopped at crate");
						return false;
					}
				}
			}
		}

		if (p.row > 665) {
			p = new Point(p.row - this.speed, p.col);
			t = new TankRectangle(p.col - 25, p.row - 25);
			System.out.println("stopped at row");
			return false;
		}
		notifyObservers(this);
		setChanged();
		return true;
	}

	/**
	 * This method moves (if possible) the PlayerTank one unit in the right
	 * direction. It will take into account whether it is destroyed by moving
	 * into a FireRing or SpikePit, and will adjust the image of the tank
	 * depending on whether it has a BubbleShield, IceBlock or neither. It will
	 * also choose the tank image that is facing EAST.
	 * 
	 * @return whether it has successfully moved right
	 */
	public boolean moveRight() {
		LinkedList<Obstacle> obs = map.getObstacles();
		LinkedList<EnemyTank> enemies = map.getEnemies();
		d = Direction.EAST;
		p = new Point(p.row, p.col + this.speed);
		t = new TankRectangle(p.col - 25, p.row - 25);
		if (health == 1) {
			img = new ImageIcon("images/tankEAST.png").getImage();
		}
		if (health == 2) {
			img = new ImageIcon("images/tankShieldEAST.png").getImage();
		}
		if (activeIceBlock) {
			img = new ImageIcon("images/tankFrozenEAST.png").getImage();
		}
		for (int i = 0; i < enemies.size(); i++) {
			EnemyTank e = enemies.get(i);
			if (e.getRectangle().intersects(t)) {
				p = new Point(p.row, p.col - this.speed);
				t = new TankRectangle(p.col - 25, p.row - 25);
				return false;
			}
		}
		for (int i = 0; i < obs.size(); i++) {
			Obstacle o = obs.get(i);
			if (o instanceof ImmovableBlock) {
				ImmovableBlock b = (ImmovableBlock) o;
				if (b.getRectangle().intersects(t)) {
					p = new Point(p.row, p.col - this.speed);
					t = new TankRectangle(p.col - 25, p.row - 25);
					return false;
				}

			}
			if (o instanceof Crate) {
				Crate c = (Crate) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row, p.col - this.speed);
						t = new TankRectangle(p.col - 25, p.row - 25);
						return false;
					}
				}
			}
			if (o instanceof TNT) {
				TNT c = (TNT) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row, p.col - this.speed);
						t = new TankRectangle(p.col - 25, p.row - 25);
						return false;
					}
				}
			}

		}

		if (p.col > 955) {
			p = new Point(p.row, p.col - this.speed);
			t = new TankRectangle(p.col - 25, p.row - 25);
			return false;
		}
		notifyObservers(this);
		setChanged();
		return true;
	}

	/**
	 * This method returns if the BubbleShield is active on the PlayerTank.
	 * 
	 * @return if BubbleShield is active on the PlayerTank
	 */
	public boolean isActiveShield() {
		return activeShield;
	}

	/**
	 * This method sets active value of the BubbleShield to the desired input
	 * parameter.
	 * 
	 * @param activeShield
	 *            boolean describing whether the BubbleShield is to be set to on
	 *            or off.
	 */
	public void setActiveShield(boolean activeShield) {
		this.activeShield = activeShield;
	}

	/**
	 * This method determines if the SpeedBoost is active on the PlayerTank.
	 * 
	 * @return whether the SpeedBoost is active on the PlayerTank
	 */
	public boolean isActiveBoost() {
		return activeBoost;
	}

	/**
	 * This method sets the active value of the SPeedBoost to the desired input
	 * parameter.
	 * 
	 * @param activeBoost
	 *            boolean describing whether the SpeedBoost is to be set to on
	 *            or off.
	 */
	public void setActiveBoost(boolean activeBoost) {
		this.activeBoost = activeBoost;
	}

	/**
	 * This method moves (if possible) the PlayerTank one unit in the left
	 * direction. It will take into account whether it is destroyed by moving
	 * into a FireRing or SpikePit, and will adjust the image of the tank
	 * depending on whether it has a BubbleShield, IceBlock or neither. It will
	 * also choose the tank image that is facing West.
	 * 
	 * @return whether it has successfully moved left
	 */
	public boolean moveLeft() {
		LinkedList<Obstacle> obs = map.getObstacles();
		LinkedList<EnemyTank> enemies = map.getEnemies();
		d = Direction.WEST;
		p = new Point(p.row, p.col - this.speed);
		t = new TankRectangle(p.col - 25, p.row - 25);
		if (health == 1) {
			img = new ImageIcon("images/tankWEST.png").getImage();
		}
		if (health == 2) {
			img = new ImageIcon("images/tankShieldWEST.png").getImage();
		}
		if (activeIceBlock) {
			img = new ImageIcon("images/tankFrozenWEST.png").getImage();
		}
		for (int i = 0; i < enemies.size(); i++) {
			EnemyTank e = enemies.get(i);
			if (e.getRectangle().intersects(t)) {
				p = new Point(p.row, p.col + this.speed);
				t = new TankRectangle(p.col - 25, p.row - 25);
				return false;
			}
		}
		for (int i = 0; i < obs.size(); i++) {
			Obstacle o = obs.get(i);
			if (o instanceof ImmovableBlock) {
				ImmovableBlock b = (ImmovableBlock) o;
				if (b.getRectangle().intersects(t)) {
					p = new Point(p.row, p.col + this.speed);
					t = new TankRectangle(p.col - 25, p.row - 25);
					return false;
				}

			}
			if (o instanceof Crate) {
				Crate c = (Crate) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row, p.col + this.speed);
						t = new TankRectangle(p.col - 25, p.row - 25);
						return false;
					}
				}
			}
			if (o instanceof TNT) {
				TNT c = (TNT) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row, p.col + this.speed);
						t = new TankRectangle(p.col - 25, p.row - 25);
						return false;
					}
				}
			}

		}

		if (p.col < 30) {
			p = new Point(p.row, p.col + this.speed);
			t = new TankRectangle(p.col - 25, p.row - 25);
			return false;
		}
		notifyObservers(this);
		setChanged();
		return true;
	}

	/**
	 * This method determines the direction of the tank and shoots a single
	 * animated projectile in the appropriate direction. The direction may be
	 * diagonally, having an x and y direction.
	 * 
	 * @param p
	 *            current location of the projectile
	 * @param x
	 *            horizontal component speed of the projectile
	 * @param y
	 *            vertical component speed of the projectile
	 */
	public void shoot(Point p, int x, int y) {

		PlayerProjectile missle = new PlayerProjectile(p, x, y, this, map);
		notifyObservers(missle);
		setChanged();

	}

	/**
	 * This method will deal damage to the PlayerTank according to the input
	 * parameter value.
	 * 
	 * @param damage
	 *            this is the damage that the player controlled tank is to
	 *            receive
	 */
	public void recieveDamage(int damage) {
		health = health - damage;
		if (this.isDead()) {
			t = new TankRectangle(-100, -100);
			p = new Point(-1, -1);
			map.getPlayers().remove(this);
		} else if (health == 1) {
			activeShield = false;
			ImmuneThread it = new ImmuneThread();
			it.start();
		}

	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private inner class will control the tanks animation on the
	 *         GUI.
	 * 
	 * @extends Thread
	 * 
	 * @category inner class
	 * 
	 *           @ see Thread
	 * 
	 * @override run() in Thread
	 * 
	 */
	private class ImmuneThread extends Thread {

		private int timePassed;

		/**
		 * This is the class constructor for the ImmuneThread class and simply
		 * sets the timePassed to 0
		 * 
		 * @category constructor
		 */
		public ImmuneThread() {
			timePassed = 0;
		}

		/**
		 * This method runs the ImmuneThread and delays the tanks movement
		 * accordingly
		 * 
		 * @overide run() in Thread
		 */
		@Override
		public void run() {
			while (timePassed < 17) {
				if (timePassed < 16) {
					health = 10000;

				}
				if (timePassed == 16) {
					health = 1;
				}
				if (timePassed % 2 == 0) {
					img = new ImageIcon("images/tank" + d + ".png").getImage();
				}
				if (timePassed % 2 == 1) {
					img = new ImageIcon("images/tankShield" + d + ".png")
							.getImage();

				}
				timePassed++;
				try {
					Thread.sleep(125);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * THis method will determine if the PlayerTank is dead.
	 * 
	 * @return whether PlayerTank has no health
	 */
	public boolean isDead() {
		return (health == 0);
	}

}

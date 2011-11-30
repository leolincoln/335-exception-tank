package undecided;

import java.util.Observable;

import rectangles.TankRectangle;

/**
 * 
 * @author Team Exception
 * 
 *         This class is the tank object that is controlled by the player that
 *         can be moved by arrow keys and shoot independently via the mouse. It
 *         contains the tanks location, health, and shape that detects
 *         collisions.
 * 
 */
public class PlayerTank extends Observable {

	// X and Y coordinates for the Tank's location
	private Point p;

	// the speed at which the tank moves across the screen
	private int speed;

	// PlayerTank's associated rectangle
	private TankRectangle t;
	private int health;
	private Direction d;

	/**
	 * Class constructor
	 * 
	 * @param p
	 *            location of the player controlled tank
	 */
	public PlayerTank(Point p) {
		this.p = p;
		speed = 5;
		health = 1;
		d = Direction.EAST;
		t = new TankRectangle(p.col - 25, p.row - 25);

	}

	/**
	 * 
	 * @return TankRectangle returns the shape of the tank that detects
	 *         collisions
	 */
	public TankRectangle getRectangle() {
		return t;
	}

	/**
	 * 
	 * @return Direction returns direction in which the tank is facing
	 */
	public Direction getDirection() {
		return d;
	}

	/**
	 * 
	 * @return Point returns the player controlled tank's position
	 */
	public Point getLocation() {
		return p;
	}

	/**
	 * 
	 * @param p
	 *            this location at which the tank is to be set at
	 */
	public void setLocation(Point p) {
		this.p = p;
	}

	/**
	 * This class will determine the speed of the tank depending on the terrain
	 * that the tank is traversing across
	 * 
	 * @param t
	 *            this is the terrain in which the tank is on whether grass,
	 *            ice, or sand
	 */
	public void setSpeed(Terrain t) {
		if (t instanceof Ice) {
			speed = 7;
		}
		if (t instanceof Sand) {
			speed = 3;
		}
		if (t instanceof Grass) {
			speed = 5;
		}

	}

	/**
	 * 
	 * @return int this returns the current speed of the tank
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * 
	 * @return Point returns the point that is above the tank's current position
	 */
	public Point moveUp() {
		d = Direction.NORTH;
		p = new Point(p.row - this.speed, p.col);
		t = new TankRectangle(p.col - 25, p.row - 25);
		if (p.row < 55) {
			p = new Point(55, p.col);
			t = new TankRectangle(p.col - 25, p.row - 25);
		}
		notifyObservers(t);
		setChanged();
		return p;
	}

	/**
	 * 
	 * @return Point returns the point that is below the tank's current position
	 */
	public Point moveDown() {
		d = Direction.SOUTH;
		p = new Point(p.row + this.speed, p.col);
		t = new TankRectangle(p.col - 25, p.row - 25);
		if (p.row > 965) {
			p = new Point(965, p.col);
			t = new TankRectangle(p.col - 25, p.row - 25);
		}
		notifyObservers(t);
		setChanged();
		return p;
	}

	/**
	 * 
	 * @return Point returns the point that is to the right of the tank's
	 *         current position
	 */
	public Point moveRight() {
		d = Direction.EAST;
		p = new Point(p.row, p.col + this.speed);
		t = new TankRectangle(p.col - 25, p.row - 25);
		if (p.col > 965) {
			p = new Point(p.row, 965);
			t = new TankRectangle(p.col - 25, p.row - 25);
		}
		notifyObservers(t);
		setChanged();
		return p;
	}

	/**
	 * 
	 * @return Point returns the point that is to the left of the tank's current
	 *         position
	 */
	public Point moveLeft() {
		d = Direction.WEST;
		p = new Point(p.row, p.col - this.speed);
		t = new TankRectangle(p.col - 25, p.row - 25);
		if (p.col < 35) {
			p = new Point(p.row, 35);
			t = new TankRectangle(p.col - 25, p.row - 25);
		}
		notifyObservers(t);
		setChanged();
		return p;
	}

	/**
	 * The method shoot determines the direction of the tank and shoots a single
	 * animated projectile in the appropriate direction
	 */
	public void shoot() {
		
		
		
		if (d == Direction.EAST) {
			Projectile missle = new Projectile(new Point(p.row, p.col),
					Direction.EAST);
			notifyObservers(missle);
			setChanged();
		}
		if (d == Direction.WEST) {
			Projectile missle = new Projectile(new Point(p.row, p.col),
					Direction.WEST);
			notifyObservers(missle);
			setChanged();
		}
		if (d == Direction.NORTH) {
			Projectile missle = new Projectile(new Point(p.row, p.col),
					Direction.NORTH);
			notifyObservers(missle);
			setChanged();
		}
		if (d == Direction.SOUTH) {
			Projectile missle = new Projectile(new Point(p.row, p.col),
					Direction.SOUTH);
			notifyObservers(missle);
			setChanged();

		}
	}
	
	
	// new version!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void shoot(Point p, int x, int y) {
		
		Projectile missle = new Projectile(p, x, y);
			notifyObservers(missle);
			setChanged();
	
	}
	
	

	/**
	 * 
	 * @param damage
	 *            this is the damage that the player controlled tank is to
	 *            receive
	 */
	public void recieveDamage(int damage) {
		health = health - damage;
		if (this.isDead()) {
			t = null;
			p = null;
			notifyObservers(this);
			setChanged();
		}

	}

	/**
	 * 
	 * @return boolean returns whether this player controlled tank is alive or
	 *         at zero health
	 */
	public boolean isDead() {
		return (health == 0);
	}
}

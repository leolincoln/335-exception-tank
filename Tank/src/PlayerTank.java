

import java.util.Observable;

import rectangles.TankRectangle;

public class PlayerTank extends Observable {

	// X and Y coordinates for the Tank's location
	private Point p;

	// the speed at which the tank moves across the screen
	private int speed;

	// PlayerTank's associated rectangle
	private TankRectangle t;
	
	private int health;

	private Direction d;

	public PlayerTank(Point p) {
		this.p = p;
		speed = 5;
		health = 1;
		d = Direction.EAST;
		t = new TankRectangle(p.col - 25, p.row - 25);

	}

	public TankRectangle getRectangle() {
		return t;
	}
	public Direction getDirection() {
		return d;
	}

	public Point getLocation() {
		return p;
	}

	public void setLocation(Point p) {
		this.p = p;
	}

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

	public int getSpeed() {
		return speed;
	}

	//Changes the direction of the tank and causes it to move up
	
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
	
	//Changes the direction of the tank and causes it to move down

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
	
	//Changes the direction of the tank and causes it to move right

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
	
	//Changes the direction of the tank and causes it to move left

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

	public void shoot() {
		if (d == Direction.EAST) {
			Projectile missle = new Projectile(new Point(p.row, p.col), Direction.EAST);
			notifyObservers(missle);
			setChanged();
		}
		if (d == Direction.WEST) {
			Projectile missle = new Projectile(new Point(p.row, p.col), Direction.WEST);
			notifyObservers(missle);
			setChanged();
		}
		if (d == Direction.NORTH) {
			Projectile missle = new Projectile(new Point(p.row, p.col), Direction.NORTH);
			notifyObservers(missle);
			setChanged();
		}
		if (d == Direction.SOUTH) {
			Projectile missle = new Projectile(new Point(p.row, p.col), Direction.SOUTH);
			notifyObservers(missle);
			setChanged();

		}
	}

	public void recieveDamage(int damage) {
		health = health - damage;
		if(this.isDead()) {
			t = null;
			p = null;
			notifyObservers(this);
			setChanged();
		}
		
	}
	
	public boolean isDead() {
	return (health == 0);
	}
}

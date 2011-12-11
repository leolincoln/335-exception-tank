package undecided;

import java.awt.Image;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import javax.swing.ImageIcon;

import rectangles.TankRectangle;

public class EnemyTank extends Observable {
	// X and Y coordinates for the Tank's location
	private Point p;

	// the speed at which the tank moves across the screen
	private int speed;

	// PlayerTank's associated rectangle
	private TankRectangle t;
	private int health;
	private Direction d;
	private Image img;
	private boolean moveable;
	private Map map;

	public EnemyTank(Point p, Map map) {
		health = 1;
		this.p = p;
		this.map = map;
		d = Direction.NORTH;
		speed = 1;
		moveable = true;
		t = new TankRectangle(p.col - 25, p.row - 25);
		img = new ImageIcon("images/tankEnemyEast.png").getImage();
		EnemyThread et = new EnemyThread();
		et.start();

	}

	public Point getLocation() {
		return p;
	}

	public void setLocation(Point p) {
		this.p = p;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public TankRectangle getRectangle() {
		return t;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public Direction getD() {
		return d;
	}

	public Image getImage() {
		return img;
	}

	public boolean moveUp() {
		LinkedList<Obstacle> obs = map.getObstacles();
		LinkedList<PlayerTank> players = map.getPlayers();
		d = Direction.NORTH;
		p = new Point(p.row - this.speed, p.col);
		t = new TankRectangle(p.col - 25, p.row - 25);
		for (int i = 0; i < players.size(); i++) {
			PlayerTank e = players.get(i);
			if (e.getRectangle().intersects(t)) {
				p = new Point(p.row + this.speed, p.col);
				t = new TankRectangle(p.col - 25, p.row - 25); 
				moveable = false;
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
					moveable = false;
					return false;

				}
			}
			if (o instanceof Crate) {
				Crate c = (Crate) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row + this.speed, p.col);
						t = new TankRectangle(p.col - 25, p.row - 25);
						moveable = false;
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
						moveable = false;
						return false;
					}
				}
			}
			if (o instanceof FireRing) {
				FireRing c = (FireRing) o;
				if (c.getRectangle().intersects(t)) {
						p = new Point(p.row + this.speed, p.col);
						t = new TankRectangle(p.col - 25, p.row - 25);
						moveable = false;
						return false;
					
				}
			}
			if (o instanceof SpikePit) {
				SpikePit c = (SpikePit) o;
				if (c.getRectangle().intersects(t)) {
						p = new Point(p.row + this.speed, p.col);
						t = new TankRectangle(p.col - 25, p.row - 25);
						moveable = false;
						return false;
				}
			}

		}

		if (p.row < 30) {
			p = new Point(p.row + this.speed, p.col);
			t = new TankRectangle(p.col - 25, p.row - 25);
			moveable = false;
			return false;
		}
		img = new ImageIcon("images/tankEnemyNorth.png").getImage();
		notifyObservers(this);
		setChanged();
		moveable = true;
		return true;
	}

	/**
	 * 
	 * @return Point returns the point that is below the tank's current position
	 */
	public boolean moveDown() {
		LinkedList<Obstacle> obs = map.getObstacles();
		LinkedList<PlayerTank> players = map.getPlayers();
		d = Direction.SOUTH;
		p = new Point(p.row + this.speed, p.col);
		t = new TankRectangle(p.col - 25, p.row - 25);
		for (int i = 0; i < players.size(); i++) {
			PlayerTank e = players.get(i);
			if (e.getRectangle().intersects(t)) {
				p = new Point(p.row - this.speed, p.col);
				t = new TankRectangle(p.col - 25, p.row - 25);
				moveable = false;
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
					moveable = false;
					return false;
				}

			}
			if (o instanceof SpikePit) {
				SpikePit b = (SpikePit) o;
				if (b.getRectangle().intersects(t)) {
					p = new Point(p.row - this.speed, p.col);
					t = new TankRectangle(p.col - 25, p.row - 25);
					moveable = false;
					return false;
				}

			}
			if (o instanceof Crate) {
				Crate c = (Crate) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row - this.speed, p.col);
						t = new TankRectangle(p.col - 25, p.row - 25);
						moveable = false;
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
						moveable = false;
						return false;
					}
				}
			}
			if (o instanceof FireRing) {
				FireRing c = (FireRing) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row - this.speed, p.col);
						t = new TankRectangle(p.col - 25, p.row - 25);
						moveable = false;
						return false;
					}
				}
			}
		}

		if (p.row > 665) {
			p = new Point(p.row - this.speed, p.col);
			t = new TankRectangle(p.col - 25, p.row - 25);
			moveable = false;
			return false;
		}
		img = new ImageIcon("images/tankEnemySouth.png").getImage();
		notifyObservers(this);
		setChanged();
		moveable = true;
		return true;
	}

	/**
	 * 
	 * @return Point returns the point that is to the right of the tank's
	 *         current position
	 */
	public boolean moveRight() {
		LinkedList<Obstacle> obs = map.getObstacles();
		LinkedList<PlayerTank> players = map.getPlayers();
		d = Direction.EAST;
		p = new Point(p.row, p.col + this.speed);
		t = new TankRectangle(p.col - 25, p.row - 25);
		for (int i = 0; i < players.size(); i++) {
			PlayerTank e = players.get(i);
			if (e.getRectangle().intersects(t)) {
				p = new Point(p.row, p.col - this.speed);
				t = new TankRectangle(p.col - 25, p.row - 25);
				moveable = false;
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
					moveable = false;
					return false;
				}

			}
			if (o instanceof SpikePit) {
				SpikePit b = (SpikePit) o;
				if (b.getRectangle().intersects(t)) {
					p = new Point(p.row, p.col - this.speed);
					t = new TankRectangle(p.col - 25, p.row - 25);
					moveable = false;
					return false;
				}

			}
			if (o instanceof Crate) {
				Crate c = (Crate) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row, p.col - this.speed);
						t = new TankRectangle(p.col - 25, p.row - 25);
						moveable = false;
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
						moveable = false;
						return false;
					}
				}
			}
			if (o instanceof FireRing) {
				FireRing c = (FireRing) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row, p.col - this.speed);
						t = new TankRectangle(p.col - 25, p.row - 25);
						moveable = false;
						return false;
					}
				}
			}

		}

		if (p.col > 955) {
			p = new Point(p.row, p.col - this.speed);
			t = new TankRectangle(p.col - 25, p.row - 25);
			moveable = false;
			return false;
		}
		img = new ImageIcon("images/tankEnemyEast.png").getImage();
		notifyObservers(this);
		setChanged();
		moveable = true;
		return true;
	}

	/**
	 * 
	 * @return Point returns the point that is to the left of the tank's current
	 *         position
	 */
	public boolean moveLeft() {
		LinkedList<Obstacle> obs = map.getObstacles();
		LinkedList<PlayerTank> players = map.getPlayers();
		d = Direction.WEST;
		p = new Point(p.row, p.col - this.speed);
		t = new TankRectangle(p.col - 25, p.row - 25);
		for (int i = 0; i < players.size(); i++) {
			PlayerTank e = players.get(i);
			if (e.getRectangle().intersects(t)) {
				p = new Point(p.row, p.col + this.speed);
				t = new TankRectangle(p.col - 25, p.row - 25);
				moveable = false;
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
					moveable = false;
					return false;
				}

			}
			if (o instanceof SpikePit) {
				SpikePit b = (SpikePit) o;
				if (b.getRectangle().intersects(t)) {
					p = new Point(p.row, p.col + this.speed);
					t = new TankRectangle(p.col - 25, p.row - 25);
					moveable = false;
					return false;
				}

			}
			if (o instanceof Crate) {
				Crate c = (Crate) o;
				if (c.getRectangle().intersects(t)) {
					if (!c.move(d)) {
						p = new Point(p.row, p.col + this.speed);
						t = new TankRectangle(p.col - 25, p.row - 25);
						moveable = false;
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
						moveable = false;
						return false;
					}
				}
			}
				if (o instanceof FireRing) {
					FireRing c = (FireRing) o;
					if (c.getRectangle().intersects(t)) {
						if (!c.move(d)) {
							p = new Point(p.row, p.col + this.speed);
							t = new TankRectangle(p.col - 25, p.row - 25);
							moveable = false;
							return false;
						}
					}
			}

		}

		if (p.col < 30) {
			p = new Point(p.row, p.col + this.speed);
			t = new TankRectangle(p.col - 25, p.row - 25);
			moveable = false;
			return false;
		}
		img = new ImageIcon("images/tankEnemyWest.png").getImage();
		notifyObservers(this);
		setChanged();
		moveable = true;
		return true;
	}

	public void shoot(Point p, int x, int y) {

		EnemyProjectile missle = new EnemyProjectile(p, x, y, this, map);
		notifyObservers(missle);
		setChanged();

	}

	public boolean isDead() {
		return health == 0;
	}

	private class EnemyThread extends Thread {
		private int tick, timePassed;
		private boolean exists;

		public EnemyThread() {
			tick = 0;
			exists = true;
			timePassed = 0;
		}

		@Override
		public synchronized void run() {
			while (exists || map.getPlayers().size() != 0) {
				Random rnd = new Random();
				if(map.getPlayers().size() == 1) {
				PlayerTank player = map.getPlayers().getFirst();
				
				int x = player.getLocation().col;
				int y = player.getLocation().row;
				
				int xdiff = x - p.col + rnd.nextInt(100);
				int ydiff = y - p.row + rnd.nextInt(100);
				
				if (tick == 0) {
					int rndDirection = rnd.nextInt(4) + 1;
					if (rndDirection == 1) {
						d = Direction.NORTH;
					}
					if (rndDirection == 2) {
						d = Direction.SOUTH;

					}
					if (rndDirection == 3) {
						d = Direction.EAST;
					}
					if (rndDirection == 4) {
						d = Direction.WEST;
					}
				}

				if (d == Direction.NORTH) {
					moveUp();
				}
				if (d == Direction.WEST) {
					moveLeft();
				}
				if (d == Direction.SOUTH) {
					moveDown();
				}
				if (d == Direction.EAST) {
					moveRight();
				}
				if (!moveable) {
					tick = 400;
				}
				if (timePassed % 100 == 0 && timePassed != 0) {
					LinkedList<Projectile> proj = map.getProjectiles();
					int count = 0;
					for(Projectile p : proj) {
						if(p instanceof EnemyProjectile) {
							count++;
						}
					}
					
					if(count == 0) {
					shoot(new Point(p.row, p.col),
							(int) (xdiff * (5 /  Math.sqrt(xdiff * xdiff + ydiff * ydiff))),
							(int) (ydiff * (5 / Math.sqrt(xdiff * xdiff + ydiff * ydiff))));
					}
				}
				}

				if (tick < 400) {
					tick++;
				} else {
					tick = 0;
				}
				if(map.getEnemies().size() == 0) {
					exists = false;
					break;
				}
				timePassed++;
				try {
					Thread.sleep(10);
				} catch (Exception e) {

				}


			}

		}

	}

	public void recieveDamage(int i) {
		health = health - i;
		if (isDead()) {
			map.getEnemies().remove(this);
		}

	}

	

}

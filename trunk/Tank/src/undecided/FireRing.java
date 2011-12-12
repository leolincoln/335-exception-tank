package undecided;

import java.awt.Image;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import javax.swing.ImageIcon;

import rectangles.FireRingRectangle;
/**
 * This class is the fire ring obstacle. It will randomly roam the map avoiding
 * other obstacles such as crates and immovable blocks. It has a built in AI
 * that will not allow it to be stuff in any given location.
 * 
 * @author Team Exception
 * 
 * @extends Observable
 * 
 * @implements Obstacle
 * 
 * @see Obstacle, TankView, Crate, SpikePit, TNT, ImmovableBlock
 * 
 * @serial
 */
public class FireRing extends Observable implements Obstacle {

	private int health;// health of the fire ring (either 0 or 1)
	private FireRingRectangle rect;// shape for crate controlling collisions
	private Point location;// location of fire ring
	private Direction d;
	private int tick, tick2;
	private boolean moveable;
	private Map map;
	private EnemyTank enemy;
	private Image fireImage;

	/**
	 * This is the class constructor for the fire ring which initialies its
	 * location, map that it is on, initial image, and the rectangle that will
	 * project it's location on a map containing all the geometry class shapes
	 * of all the other objects to control collisions.
	 * 
	 * @param p
	 *            location that the fire ring is to be set
	 * @param map
	 *            the map on which this fire ring is on
	 */
	
	public FireRing(Point p, Map map) {
		moveable = true;
		tick = 0;
		tick2 = 0;
		location = p;
		this.map = map;
		fireImage = new ImageIcon("images/fireRing.png").getImage();
		health = -1;// can't be destroyed
		// 25 is to offset for the size so it's not off the field
		d = Direction.NORTH;
		rect = new FireRingRectangle(location.col - 25, location.row - 25);
		FireThread ft = new FireThread();
		ft.start();

	}

	/**
	 * The method receiveDamage will modify the health of this fire ring object
	 * according to the damage done to it.
	 * 
	 * @param dmg
	 *            damage that will be done to the fire ring
	 */
	public void recieveDamage(int dmg) {
		health = health - dmg;

	}
	/**
	 * This method will return the current image of the fire ring.
	 * 
	 * @return the current image of the fire ring
	 */
	public Image getImage() {
		return fireImage;
	}

	/**
	 * This method returns whether the fire ring is to be eliminated from the
	 * map or not dpending if its health is 0 or not.
	 * 
	 * @return whether the obstacle is dead or not and needs to be removed
	 */
	public boolean removeObstacle() {
		if (health == 0) {// if health is 0
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method will return the current health of the fire ring.
	 * 
	 * @return returns the health of the fire ring
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * @return Point returns the location of the fire ring
	 */
	public Point getLocation() {
		return this.location;
	}

	/**
	 * @return FireRingRectangle returns the rectangle object that will
	 *         represent the collisions for the fire ring
	 */
	public FireRingRectangle getRectangle() {
		return rect;
	}
	/**
	 * This method moves the FireRing in the specified Direction if it is
	 * possible to move in that direction. This is the method that actually
	 * controls whether it moves into another space by determining if the
	 * desired space it would like to move to has an obstacle or not.
	 * 
	 * @param d
	 *            the direction that the fire ring is to move
	 * 
	 * @return if it successfully moved in that direction
	 */
	public synchronized boolean move(Direction d) {
		LinkedList<Obstacle> obs = map.getObstacles();
		LinkedList<EnemyTank> enemies = map.getEnemies();
		if(enemies.size() != 0) {
		enemy = enemies.getFirst();
		} 
		if (d == Direction.EAST) {
			location = new Point(location.row, location.col + 1);
			rect = new FireRingRectangle(location.col - 25, location.row - 25);
			if(enemy.getHuman() != 0) {
			if(enemy.getRectangle().intersects(rect)) {
				location = new Point(location.row, location.col - 1);
				rect = new FireRingRectangle(location.col - 25, location.row - 25);
				moveable = false;
				return false;
			}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
					if (o instanceof ImmovableBlock) {
						if (((ImmovableBlock) o).getRectangle()
								.intersects(rect)) {
							location = new Point(location.row, location.col - 1);
							rect = new FireRingRectangle(location.col - 25,
									location.row - 25);
							moveable = false;
							return false;
						}

					}
					if (o instanceof TNT) {
						if (((TNT) o).getRectangle().intersects(rect)) {
							location = new Point(location.row, location.col - 1);
							rect = new FireRingRectangle(location.col - 25,
									location.row - 25);
							moveable = false;
							return false;
						}
					}
					if (o instanceof Crate) {
						if (((Crate) o).getRectangle().intersects(rect)) {
							location = new Point(location.row, location.col - 1);
							rect = new FireRingRectangle(location.col - 25,
									location.row - 25);
							moveable = false;
							return false;
						}
					}
					if (o instanceof FireRing) {
						if (o != this) {
							if (((FireRing) o).getRectangle().intersects(rect)) {
								location = new Point(location.row,
										location.col - 1);
								rect = new FireRingRectangle(location.col - 25,
										location.row - 25);
								moveable = false;
								return false;
							}
						}
					}

				}
			
			if (location.col > 955) {
				location = new Point(location.row, location.col - 1);
				rect = new FireRingRectangle(location.col - 25,
						location.row - 25);
				moveable = false;
				return false;
			}
			moveable = true;
			notifyObservers(this);
			setChanged();

		}
		if (d == Direction.WEST) {
			location = new Point(location.row, location.col - 1);
			rect = new FireRingRectangle(location.col - 25, location.row - 25);
			if(enemy.getHuman() != 0) {
			if(enemy.getRectangle().intersects(rect)) {
				location = new Point(location.row, location.col + 1);
				rect = new FireRingRectangle(location.col - 25, location.row - 25);
				moveable = false;
				return false;
			}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col + 1);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;

					}
				}
					if (o instanceof TNT) {
						if (((TNT) o).getRectangle().intersects(rect)) {
							location = new Point(location.row, location.col + 1);
							rect = new FireRingRectangle(location.col - 25,
									location.row - 25);
							moveable = false;
							return false;
						}
					}
					if (o instanceof Crate) {
						if (((Crate) o).getRectangle().intersects(rect)) {
							location = new Point(location.row, location.col + 1);
							rect = new FireRingRectangle(location.col - 25,
									location.row - 25);
							moveable = false;
							return false;
						}
					}
					if (o instanceof FireRing) {
						if (o != this) {
							if (((FireRing) o).getRectangle().intersects(rect)) {
								location = new Point(location.row,
										location.col + 1);
								rect = new FireRingRectangle(location.col - 25,
										location.row - 25);
								moveable = false;
								return false;
							}
						}
					}
				}
			if (location.col < 30) {
				location = new Point(location.row, location.col + 1);
				rect = new FireRingRectangle(location.col - 25,
						location.row - 25);
				moveable = false;
				return false;
			}
			moveable = true;
			notifyObservers(this);
			setChanged();
		}
		if (d == Direction.NORTH) {
			location = new Point(location.row - 1, location.col);
			rect = new FireRingRectangle(location.col - 25, location.row - 25);
			if(enemy.getHuman() != 0) {
			if(enemy.getRectangle().intersects(rect)) {
				location = new Point(location.row + 1, location.col);
				rect = new FireRingRectangle(location.col - 25, location.row - 25);
				moveable = false;
				return false;
			}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + 1, location.col);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
				}

					if (o instanceof TNT) {
						if (((TNT) o).getRectangle().intersects(rect)) {
							location = new Point(location.row + 1, location.col);
							rect = new FireRingRectangle(location.col - 25,
									location.row - 25);
							moveable = false;
							return false;
						}
					}
	
				if(o instanceof Crate) {
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + 1, location.col);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;


					}

				}
				if(o instanceof FireRing) {
					if(o != this) {
					if(((FireRing) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + 1, location.col);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
					}
				}

			}
			if (location.row < 30) {
				location = new Point(location.row + 1, location.col);
				rect = new FireRingRectangle(location.col - 25,
						location.row - 25);
				moveable = false;
				return false;
			}
			moveable = true;
			notifyObservers(this);
			setChanged();

		}
		if (d == Direction.SOUTH) {
			location = new Point(location.row + 1, location.col);
			rect = new FireRingRectangle(location.col - 25, location.row - 25);
			if(enemy.getHuman() != 0) {
			if(enemy.getRectangle().intersects(rect)) {
				location = new Point(location.row - 1, location.col);
				rect = new FireRingRectangle(location.col - 25, location.row - 25);
				moveable = false;
				return false;
			}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - 1, location.col);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
				}
					if (o instanceof TNT) {
						if (((TNT) o).getRectangle().intersects(rect)) {
							location = new Point(location.row - 1, location.col);
							rect = new FireRingRectangle(location.col - 25,
									location.row - 25);
							moveable = false;
							return false;
						}
					}
					if (o instanceof Crate) {
						if (((Crate) o).getRectangle().intersects(rect)) {
							location = new Point(location.row - 1, location.col);
							rect = new FireRingRectangle(location.col - 25,
									location.row - 25);
							moveable = false;
							return false;
						}
					}
					if (o instanceof FireRing) {
						if (o != this) {
							if (((FireRing) o).getRectangle().intersects(rect)) {
								location = new Point(location.row - 1,
										location.col);
								rect = new FireRingRectangle(location.col - 25,
										location.row - 25);
								moveable = false;
								return false;
							}
						}
					}

			}
			if (location.row > 665) {
				location = new Point(location.row - 1, location.col);
				rect = new FireRingRectangle(location.col - 25,
						location.row - 25);
				moveable = false;
				return false;
			}
			notifyObservers(this);
			setChanged();

		}
		moveable = true;
		return true;

	}

	// Thread that causes the FireRing to move to across the map
	private class FireThread extends Thread {
		@Override
		public synchronized void run() {
			while (true) {
				if (tick == 0) {
					Random rnd = new Random();
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
				move(d);
				if(tick2 % 32 == 4) {
					fireImage = new ImageIcon("images/fireRing2.png").getImage();
				}
				if(tick2 % 32 == 8) {
					fireImage = new ImageIcon("images/fireRing3.png").getImage();
				}
				if(tick2 % 32 == 12) {
					fireImage = new ImageIcon("images/fireRing4.png").getImage();
				}
				if(tick2 % 32 == 16) {
					fireImage = new ImageIcon("images/fireRing5.png").getImage();
				}
				if(tick2 % 32 == 20) {
					fireImage = new ImageIcon("images/fireRing6.png").getImage();
				}
				if(tick2 % 32 == 24) {
					fireImage = new ImageIcon("images/fireRing7.png").getImage();
				}
				if(tick2 % 32 == 28) {
					fireImage = new ImageIcon("images/fireRing8.png").getImage();
				}
				if(tick2 % 32 == 0) {
					fireImage = new ImageIcon("images/fireRing.png").getImage();
				}

				if (!moveable) {
					tick = 100;
				}

				if (tick < 100) {
					tick++;
				} else {
					tick = 0;
				}
				if(map.isOver()) {
					break;
				}
				try {
					Thread.sleep(10);
				} catch (Exception e) {

				}
				tick2++;

			}

		}

	}
}

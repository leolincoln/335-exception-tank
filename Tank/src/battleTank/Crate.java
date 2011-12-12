package battleTank;

import java.util.LinkedList;
import java.util.Observable;
import rectangles.CrateRectangle;

/**
 * This class is the crate object which has health, a rectangle that will be
 * used to determine object collisions, and a location. The crate object may be
 * created, determined if has health, receive damage, and determined if needed
 * to be removed from the field.
 * 
 * @author Team Exception
 * 
 * @see Item, TankView, PlayerTank
 * 
 * @extends Observable
 * 
 * @implements Obstacle
 * 
 */
public class Crate extends Observable implements Obstacle {

	// declaring instance variables
	private static final long serialVersionUID = 1L;
	private int health;// health of the crate (either 0 or 1)
	private CrateRectangle rect;// shape for crate controlling collisions
	private Point location;// location of crate
	private PlayerTank player;// current tank of player
	private Map map;// map that this crate is on

	/**
	 * This is the class constructor for the Crate class. The create is an
	 * object with health (so can be destroyed) and can be moved around the
	 * field when collided into by a tank.
	 * 
	 * @category constructor
	 * 
	 * @param p
	 *            point of the location at which the crate is to be created.
	 * @param map
	 *            map on which this crate is to be created.
	 * 
	 */
	public Crate(Point p, Map map) {
		this.map = map;
		player = map.getPlayers().getFirst();
		location = p;
		health = 1;// one shot death
		// 25 is to offset for the size so it's not off the field
		rect = new CrateRectangle(location.col - 25, location.row - 25);
	}

	/**
	 * The method receiveDamage will modify the health of this crate object
	 * according to the damage done to it.
	 * 
	 * @param dmg
	 *            damage that will be done to the crate
	 */
	public void recieveDamage(int dmg) {
		health = health - dmg;
		if (this.removeObstacle()) {
			rect = new CrateRectangle(-1, -1);// removing off field
			map.getObstacles().remove(this);
		}

	}

	/**
	 * This method will remove the crate obstacle from the field if it has a
	 * health of 0.
	 * 
	 * @return returns whether the obstacle is dead or not and needs to be
	 *         removed
	 */
	public boolean removeObstacle() {
		if (health == 0) {// if health is 0
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method will return the current health of this instance of crate.
	 * 
	 * @return returns the health of the crate
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * 
	 * This method will move the crate in the direction it is being pushed by
	 * the player's tank.
	 * 
	 * @return if it has successfully moved the crate
	 */
	public boolean move(Direction d) {
		LinkedList<Obstacle> obs = map.getObstacles();
		LinkedList<PlayerTank> players = map.getPlayers();
		LinkedList<EnemyTank> enemies = map.getEnemies();

		if (d == Direction.EAST) {
			location = new Point(location.row, location.col + player.getSpeed());
			rect = new CrateRectangle(location.col - 25, location.row - 25);
			for (int i = 0; i < players.size(); i++) {
				PlayerTank p = players.get(i);
				if (p.getRectangle().intersects(rect)) {
					location = new Point(location.row, location.col
							- player.getSpeed());
					rect = new CrateRectangle(location.col - 25,
							location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < enemies.size(); i++) {
				EnemyTank p = enemies.get(i);
				if (p.getRectangle().intersects(rect)) {
					location = new Point(location.row, location.col
							- player.getSpeed());
					rect = new CrateRectangle(location.col - 25,
							location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if (o instanceof ImmovableBlock) {
					if (((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col
								- player.getSpeed());
						rect = new CrateRectangle(location.col - 25,
								location.row - 25);
						return false;
					}
				}
				if (o instanceof TNT) {
					if (((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col
								- player.getSpeed());
						rect = new CrateRectangle(location.col - 25,
								location.row - 25);
						return false;
					}
				}
				if (o instanceof SpikePit) {
					if (((SpikePit) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
					}
				}
				if (o instanceof Crate) {
					if (o != this) {
						if (((Crate) o).getRectangle().intersects(rect)) {
							location = new Point(location.row, location.col
									- player.getSpeed());
							rect = new CrateRectangle(location.col - 25,
									location.row - 25);
							return false;
						}
					}
				}
				if (o instanceof FireRing) {
					if (((FireRing) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
						break;
					}
				}
			}
			if (location.col > 955) {
				location = new Point(location.row, location.col
						- player.getSpeed());
				rect = new CrateRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveCrate");
			setChanged();

		}
		if (d == Direction.WEST) {
			location = new Point(location.row, location.col - player.getSpeed());
			rect = new CrateRectangle(location.col - 25, location.row - 25);
			for (int i = 0; i < players.size(); i++) {
				PlayerTank p = players.get(i);
				if (p.getRectangle().intersects(rect)) {
					location = new Point(location.row, location.col
							+ player.getSpeed());
					rect = new CrateRectangle(location.col - 25,
							location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < enemies.size(); i++) {
				EnemyTank p = enemies.get(i);
				if (p.getRectangle().intersects(rect)) {
					location = new Point(location.row, location.col
							+ player.getSpeed());
					rect = new CrateRectangle(location.col - 25,
							location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if (o instanceof ImmovableBlock) {
					if (((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col
								+ player.getSpeed());
						rect = new CrateRectangle(location.col - 25,
								location.row - 25);
						return false;
					}
				}
				if (o instanceof TNT) {
					if (((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col
								+ player.getSpeed());
						rect = new CrateRectangle(location.col - 25,
								location.row - 25);
						return false;
					}
				}
				if (o instanceof Crate) {
					if (o != this) {
						if (((Crate) o).getRectangle().intersects(rect)) {
							location = new Point(location.row, location.col
									+ player.getSpeed());
							rect = new CrateRectangle(location.col - 25,
									location.row - 25);
							return false;
						}
					}
				}
				if (o instanceof SpikePit) {
					if (((SpikePit) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
					}
				}
				if (o instanceof FireRing) {
					if (((FireRing) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
						break;
					}
				}
			}
			if (location.col < 30) {
				location = new Point(location.row, location.col
						+ player.getSpeed());
				rect = new CrateRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveCrate");
			setChanged();

		}
		if (d == Direction.NORTH) {
			location = new Point(location.row - player.getSpeed(), location.col);
			rect = new CrateRectangle(location.col - 25, location.row - 25);
			for (int i = 0; i < players.size(); i++) {
				PlayerTank p = players.get(i);
				if (p.getRectangle().intersects(rect)) {
					location = new Point(location.row + player.getSpeed(),
							location.col);
					rect = new CrateRectangle(location.col - 25,
							location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < enemies.size(); i++) {
				EnemyTank p = enemies.get(i);
				if (p.getRectangle().intersects(rect)) {
					location = new Point(location.row + player.getSpeed(),
							location.col);
					rect = new CrateRectangle(location.col - 25,
							location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if (o instanceof ImmovableBlock) {
					if (((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + player.getSpeed(),
								location.col);
						rect = new CrateRectangle(location.col - 25,
								location.row - 25);
						return false;
					}
				}
				if (o instanceof TNT) {
					if (((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + player.getSpeed(),
								location.col);
						rect = new CrateRectangle(location.col - 25,
								location.row - 25);
						return false;
					}
				}
				if (o instanceof Crate) {
					if (o != this) {
						if (((Crate) o).getRectangle().intersects(rect)) {
							location = new Point(location.row
									+ player.getSpeed(), location.col);
							rect = new CrateRectangle(location.col - 25,
									location.row - 25);
							return false;
						}
					}
				}
				if (o instanceof SpikePit) {
					if (((SpikePit) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);

					}
				}
				if (o instanceof FireRing) {
					if (((FireRing) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
						break;
					}
				}
			}
			if (location.row < 30) {
				location = new Point(location.row + player.getSpeed(),
						location.col);
				rect = new CrateRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveCrate");
			setChanged();

		}
		if (d == Direction.SOUTH) {
			location = new Point(location.row + player.getSpeed(), location.col);
			rect = new CrateRectangle(location.col - 25, location.row - 25);
			for (int i = 0; i < players.size(); i++) {
				PlayerTank p = players.get(i);
				if (p.getRectangle().intersects(rect)) {
					location = new Point(location.row - player.getSpeed(),
							location.col);
					rect = new CrateRectangle(location.col - 25,
							location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < enemies.size(); i++) {
				EnemyTank p = enemies.get(i);
				if (p.getRectangle().intersects(rect)) {
					location = new Point(location.row - player.getSpeed(),
							location.col);
					rect = new CrateRectangle(location.col - 25,
							location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if (o instanceof ImmovableBlock) {
					if (((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - player.getSpeed(),
								location.col);
						rect = new CrateRectangle(location.col - 25,
								location.row - 25);
						return false;
					}
				}
				if (o instanceof TNT) {
					if (((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - player.getSpeed(),
								location.col);
						rect = new CrateRectangle(location.col - 25,
								location.row - 25);
						return false;
					}
				}
				if (o instanceof Crate) {
					if (o != this) {
						if (((Crate) o).getRectangle().intersects(rect)) {
							location = new Point(location.row
									- player.getSpeed(), location.col);
							rect = new CrateRectangle(location.col - 25,
									location.row - 25);
							return false;
						}
					}
				}
				if (o instanceof SpikePit) {
					if (((SpikePit) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
						notifyObservers(new Point(location.row - 12,
								location.col - 12));
						setChanged();
					}
				}
				if (o instanceof FireRing) {
					if (((FireRing) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
						break;
					}
				}
			}
			if (location.row > 665) {
				location = new Point(location.row - player.getSpeed(),
						location.col);
				rect = new CrateRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveCrate");
			setChanged();

		}
		return true;

	}

	/**
	 * This method returns the current location of the crate
	 * 
	 * @return the location of the crate
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * This method returns the rectangle object that will represent the
	 * collisions for the crate.
	 * 
	 * @return the rectangle object that will represent the collisions for the
	 *         crate
	 */
	public CrateRectangle getRectangle() {
		return rect;
	}

}

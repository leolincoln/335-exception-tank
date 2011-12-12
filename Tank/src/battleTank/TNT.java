package battleTank;

import java.util.LinkedList;
import java.util.Observable;

import rectangles.BlastRadiusRectangle;
import rectangles.TNTRectangle;

/**
 * This class is the TNT object which essentially when destroyed, will deal
 * damage to a certain radius around it.
 * 
 * @author Team Exception
 * 
 * @see Item, TankView, PlayerTank, SpikePit, FireRing,Crate, ImmovableBlock
 * 
 * @extends Observable
 * 
 * @implements Obstacle
 * 
 */


public class TNT extends Observable implements Obstacle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int health;// health of the TNT (1 or 0)
	private TNTRectangle rect;// shape for TNT controlling collisions
	private Point location;// location of the TNT
	private Map map;
	private PlayerTank player;
	/**
	 * This is the class constructor for the TNT class. The TNT is an object
	 * that does contain health and will detonate when damage is dealt to it.
	 * 
	 * @category constructor
	 * 
	 * @param p
	 *            point of the location at which the TNT is to be created.
	 * @param map
	 *            map on which this TNT is to be created.
	 * 
	 */
	public TNT(Point p, Map map) {
		this.map = map;
		player = map.getPlayers().getFirst();
		location = p;
		health = 1;// TNT starts with 1 health
		// 25 is to offset for the size so it's not off the field
		rect = new TNTRectangle(location.col - 25, location.row - 25);

	}

	/**
	 * The method receiveDamage will modify the health of this TNT object
	 * according to the damage done to it.
	 * 
	 * @param dmg
	 *            damage that will be done to the TNT
	 */
	public void recieveDamage(int dmg) {
		health = health - dmg;
		if (this.removeObstacle()) {
			rect = new TNTRectangle(-100, -100);// removing off field
			BlastRadiusRectangle b = new BlastRadiusRectangle(location.col - 50, location.row - 50);
			LinkedList<Obstacle> obs = map.getObstacles();
			LinkedList<PlayerTank> tank = map.getPlayers();
			LinkedList<EnemyTank> enemies = map.getEnemies();
			for(int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof Crate) {
					Crate c = (Crate)o;
					if(b.intersects(c.getRectangle())) {
						notifyObservers(new Point(c.getLocation().row - 12, c.getLocation().col - 12));
						setChanged();
						c.recieveDamage(1);
						i = 0;
					}
				}
				if(o instanceof TNT) {
					if(o != this) {
					TNT c = (TNT)o;  
					if(c.getRectangle().intersects(b)) {
						notifyObservers(new Point(c.getLocation().row - 12, c.getLocation().col - 12));
						setChanged();
						c.recieveDamage(1);
						i = 0;
					
					}
					}
				}
			}
			for(int i = 0; i < enemies.size(); i++) {
				EnemyTank t = enemies.get(i);
				if(t.getHuman() != 0) {
				if(t.getRectangle().intersects(b)) {
					notifyObservers(new Point(t.getLocation().row - 12, t.getLocation().col - 12));
					setChanged();
					t.recieveDamage(1);
					i = 0;
				}
				}
			}
			for(int i = 0; i < tank.size(); i++) {
				PlayerTank t = tank.get(i);
				if(t.getRectangle().intersects(b)) {
					notifyObservers(new Point(t.getLocation().row - 12, t.getLocation().col - 12));
					setChanged();
					t.recieveDamage(1);
					i = 0;
				}
			}
			
			

		}
		notifyObservers(new Point(this.getLocation().row - 12, this.getLocation().col - 12));
		setChanged();
		map.getObstacles().remove(this);
	}
	/**
	 * This method will control how the TNT box is moved; tanks can run into
	 * this obstacle and move it accordingly without detonating this object. It
	 * will only detonate when hit with a projectile or by the blast of another
	 * TNT.
	 * 
	 * @param d
	 *            direction it is to be moved
	 * 
	 * @return if it has successfully moved or not
	 */
	public boolean move(Direction d) {
		LinkedList<Obstacle> obs = map.getObstacles();
		LinkedList<PlayerTank> players = map.getPlayers();
		LinkedList<EnemyTank> enemies = map.getEnemies();
		
		if(d == Direction.EAST) {
			location = new Point(location.row, location.col + player.getSpeed());
			rect = new TNTRectangle(location.col - 25, location.row - 25);
			for(int i = 0; i < players.size(); i++) {
				PlayerTank p = players.get(i); 
				if(p.getRectangle().intersects(rect)) {
					location = new Point(location.row, location.col - player.getSpeed());
					rect = new TNTRectangle(location.col - 25, location.row - 25);
					return false;
				}
			}
			for(int i = 0; i < enemies.size(); i++) {
				EnemyTank p = enemies.get(i);
				if(p.getRectangle().intersects(rect)) {
					location = new Point(location.row, location.col - player.getSpeed());
					rect = new TNTRectangle(location.col - 25, location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col - player.getSpeed());
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
				}
				if(o instanceof TNT) {
					if(o != this) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col - player.getSpeed());
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof Crate) {
					if(o != this) {
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col - player.getSpeed());
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof FireRing) {
					if(((FireRing) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
						break;
					}
				}
			}
			if (location.col > 955) {
				location = new Point(location.row, location.col - player.getSpeed());
				rect = new TNTRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveTNT");
			setChanged();
			return true;
			
		}
		if(d == Direction.WEST) {
			location = new Point(location.row, location.col - player.getSpeed());
			rect = new TNTRectangle(location.col - 25, location.row - 25);
			for(int i = 0; i < players.size(); i++) {
				PlayerTank p = players.get(i); 
				if(p.getRectangle().intersects(rect)) {
					location = new Point(location.row, location.col + player.getSpeed());
					rect = new TNTRectangle(location.col - 25, location.row - 25);
					return false;
				}
			}
			for(int i = 0; i < enemies.size(); i++) {
				EnemyTank p = enemies.get(i);
				if(p.getRectangle().intersects(rect)) {
					location = new Point(location.row, location.col + player.getSpeed());
					rect = new TNTRectangle(location.col - 25, location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col + player.getSpeed());
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
				}
				if(o instanceof TNT) {
					if(o != this) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col + player.getSpeed());
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof Crate) {
					if(o != this) {
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col + player.getSpeed());
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof FireRing) {
					if(((FireRing) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
						break;
					}
				}
			}
			if (location.col < 30) {
				location = new Point(location.row, location.col + player.getSpeed());
				rect = new TNTRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveTNT");
			setChanged();
			return true;
			
		}
		if(d == Direction.NORTH) {
			location = new Point(location.row - player.getSpeed(), location.col);
			rect = new TNTRectangle(location.col - 25, location.row - 25);
			for(int i = 0; i < players.size(); i++) {
				PlayerTank p = players.get(i); 
				if(p.getRectangle().intersects(rect)) {
					location = new Point(location.row + player.getSpeed(), location.col);
					rect = new TNTRectangle(location.col - 25, location.row - 25);
					return false;
				}
			}
			for(int i = 0; i < enemies.size(); i++) {
				EnemyTank p = enemies.get(i);
				if(p.getRectangle().intersects(rect)) {
					location = new Point(location.row + player.getSpeed(), location.col);
					rect = new TNTRectangle(location.col - 25, location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + player.getSpeed(), location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
				}
				if(o instanceof TNT) {
					if(o != this) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + player.getSpeed(), location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof Crate) {
					if(o != this) {
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + player.getSpeed(), location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof FireRing) {
					if(((FireRing) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
						break;
					}
				}
			}
			if (location.row < 30) {
				location = new Point(location.row + player.getSpeed(), location.col);
				rect = new TNTRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveTNT");
			setChanged();
			return true;
	
		}
		if(d == Direction.SOUTH) {
			location = new Point(location.row + player.getSpeed(), location.col);
			rect = new TNTRectangle(location.col - 25, location.row - 25);
			for(int i = 0; i < players.size(); i++) {
				PlayerTank p = players.get(i); 
				if(p.getRectangle().intersects(rect)) {
					location = new Point(location.row - player.getSpeed(), location.col);
					rect = new TNTRectangle(location.col - 25, location.row - 25);
					return false;
				}
			}
			for(int i = 0; i < enemies.size(); i++) {
				EnemyTank p = enemies.get(i);
				if(p.getRectangle().intersects(rect)) {
					location = new Point(location.row - player.getSpeed(), location.col);
					rect = new TNTRectangle(location.col - 25, location.row - 25);
					return false;
				}
			}
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - player.getSpeed(), location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
				}
				if(o instanceof TNT) {
					if(o != this) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - player.getSpeed(), location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof Crate) {
					if(o != this) {
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - player.getSpeed(), location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof FireRing) {
					if(((FireRing) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
						break;
					}
				}
			}
			if (location.row > 665) {
				location = new Point(location.row - player.getSpeed(), location.col);
				rect = new TNTRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveTNT");
			setChanged();
			return true;
		}
		return true;

	}

	/**
	 * This method returns whether this obstacle is to be removed from the
	 * field.
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
	 * This method returns the current health of this TNT
	 * 
	 * @return the health of the TNT
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * This method returns the current location of this TNT
	 * 
	 * @return the location of the TNT
	 */
	public Point getLocation() {
		return this.location;
	}

	
	/**
	 * This method returns the collision rectangle for this TNT
	 * 
	 * @return collision rectangle for this TNT
	 */
	public TNTRectangle getRectangle() {
		return rect;
	}

	
	
	
	
	/**
	 * 
	 * This method sets the damage that this TNT will deal to surrounding
	 * objects.
	 * 
	 * @param dmg
	 *            damage that the TNT is to inflict when destroyed.
	 */
	public void setDamage(int dmg) {

	}

}

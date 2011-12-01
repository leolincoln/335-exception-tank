package undecided;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;

import rectangles.BlastRadiusRectangle;
import rectangles.TNTRectangle;

public class TNT extends Observable implements Obstacle {

	private int health;// health of the TNT (1 or 0)
	private TNTRectangle rect;// shape for TNT controlling collisions
	private Point location;// location of the TNT

	/**
	 * Class constructor
	 * 
	 * @param p
	 *            location that the TNT is to be set
	 */
	public TNT(Point p) {

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
			LinkedList<Obstacle> obs = TankView.obstacleList;
			LinkedList<PlayerTank> tank = TankView.tankList;
			for(int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof Crate) {
					Crate c = (Crate)o;
					if(b.intersects(c.getRectangle())) {
						c.recieveDamage(1);
						i = 0;
					}
				}
				if(o instanceof TNT) {
					if(o != this) {
					TNT c = (TNT)o;  
					if(c.getRectangle().intersects(b)) {
						c.recieveDamage(1);
					
					}
					}
				}
			}
			for(int i = 0; i < tank.size(); i++) {
				PlayerTank t = tank.get(i);
				if(t.getRectangle().intersects(b)) {
					t.recieveDamage(1);
				}
			}
			

		}
		TankView.obstacleList.remove(this);
	}
	public boolean move(Direction d) {
		LinkedList<Obstacle> obs = TankView.obstacleList;
		
		if(d == Direction.EAST) {
			location = new Point(location.row, location.col + 5);
			rect = new TNTRectangle(location.col - 25, location.row - 25);
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col - 5);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
				}
				if(o instanceof TNT) {
					if(o != this) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col - 5);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof Crate) {
					
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col - 5);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					
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
				location = new Point(location.row, location.col - 5);
				rect = new TNTRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveCrate");
			setChanged();
			
		}
		if(d == Direction.WEST) {
			location = new Point(location.row, location.col - 5);
			rect = new TNTRectangle(location.col - 25, location.row - 25);
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col + 5);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
				}
				if(o instanceof TNT) {
					if(o != this) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col + 5);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof Crate) {
					
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col + 5);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					
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
				location = new Point(location.row, location.col + 5);
				rect = new TNTRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveCrate");
			setChanged();
			
		}
		if(d == Direction.NORTH) {
			location = new Point(location.row - 5, location.col);
			rect = new TNTRectangle(location.col - 25, location.row - 25);
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + 5, location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
				}
				if(o instanceof TNT) {
					if(o != this) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + 5, location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof Crate) {
					
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + 5, location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					
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
				location = new Point(location.row + 5, location.col);
				rect = new TNTRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveCrate");
			setChanged();
	
		}
		if(d == Direction.SOUTH) {
			location = new Point(location.row + 5, location.col);
			rect = new TNTRectangle(location.col - 25, location.row - 25);
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - 5, location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
				}
				if(o instanceof TNT) {
					if(o != this) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - 5, location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					}
					}
				}
				if(o instanceof Crate) {
				
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - 5, location.col);
						rect = new TNTRectangle(location.col - 25, location.row - 25);
						return false;
					
					}
				}
				if(o instanceof FireRing) {
					if(((FireRing) o).getRectangle().intersects(rect)) {
						this.recieveDamage(1);
						break;
					}
				}
			}
			if (location.row > 905) {
				location = new Point(location.row - 5, location.col);
				rect = new TNTRectangle(location.col - 25, location.row - 25);
				return false;
			}
			notifyObservers("moveCrate");
			setChanged();
	
		}
		return true;

	}

	/**
	 * @return boolean returns whether the obstacle is dead or not and needs to
	 *         be removed
	 */
	public boolean removeObstacle() {
		if (health == 0) {// if health is 0
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return int returns the health of the TNT
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * @return Point returns the location of the TNT
	 */
	public Point getLocation() {
		return this.location;
	}

	
	/**
	 * @return TNTRectangle returns the rectangle object that will represent
	 *         the collisions for the TNT
	 */
	public TNTRectangle getRectangle() {
		return rect;
	}

	
	
	
	
	/**
	 * 
	 * @param dmg
	 *            this is the damage that the TNT is to inflict when destroyed.
	 */
	public void setDamage(int dmg) {

	}

}

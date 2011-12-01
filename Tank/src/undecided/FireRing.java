package undecided;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import rectangles.FireRingRectangle;



public class FireRing extends Observable implements Obstacle {

	private int health;// health of the fire ring (either 0 or 1)
	private FireRingRectangle rect;// shape for crate controlling collisions
	private Point location;// location of fire ring
	private Direction d;
	private int tick; 
	private boolean moveable;

	/**
	 * Class constructor
	 * 
	 * @param p
	 *            location that the fire ring is to be set
	 */
	public FireRing(Point p) {
		moveable = true;
		tick = 0;
		location = p;
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
	 * @return int returns the health of the fire ring
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
	 * @return FireRingRectangle returns the rectangle object that will represent
	 *         the collisions for the fire ring
	 */
	public FireRingRectangle getRectangle() {
		return rect;
	}

	//moves the FireRing in the specified Direction if it is possible to move there
	public boolean move(Direction d) {
		LinkedList<Obstacle> obs = TankView.obstacleList;
		
		if(d == Direction.EAST) {
			location = new Point(location.row, location.col + 1);
			rect = new FireRingRectangle(location.col - 25, location.row - 25);
			for (int i = 0; i < obs.size(); i++) {
				Obstacle o = obs.get(i);
				if(o instanceof ImmovableBlock) {
					if(((ImmovableBlock) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col - 1);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
				}
				if(o instanceof TNT) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col - 1);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
				}
				if(o instanceof Crate) {
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col - 1);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
				}
			
			}
			if (location.col > 955) {
				location = new Point(location.row, location.col - 1);
				rect = new FireRingRectangle(location.col - 25, location.row - 25);
				moveable = false;
				return false;
			}
			notifyObservers(this);
			setChanged();
			
		}
		if(d == Direction.WEST) {
			location = new Point(location.row, location.col - 1);
			rect = new FireRingRectangle(location.col - 25, location.row - 25);
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
				if(o instanceof TNT) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col + 1);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
				}
				if(o instanceof Crate) {
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row, location.col + 1);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
				}
			
			}
			if (location.col < 30) {
				location = new Point(location.row, location.col + 1);
				rect = new FireRingRectangle(location.col - 25, location.row - 25);
				moveable = false;
				return false;
			}
			notifyObservers(this);
			setChanged();
			
		}
		if(d == Direction.NORTH) {
			location = new Point(location.row - 1, location.col);
			rect = new FireRingRectangle(location.col - 25, location.row - 25);
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
				if(o instanceof TNT) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row + 1, location.col);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
				}
				if(o instanceof Crate) {
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - 1, location.col);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
				}
			
			}
			if (location.row < 30) {
				location = new Point(location.row + 1, location.col);
				rect = new FireRingRectangle(location.col - 25, location.row - 25);
				moveable = false;
				return false;
			}
			notifyObservers(this);
			setChanged();
	
		}
		if(d == Direction.SOUTH) {
			location = new Point(location.row + 1, location.col);
			rect = new FireRingRectangle(location.col - 25, location.row - 25);
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
				if(o instanceof TNT) {
					if(((TNT) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - 1, location.col);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
				}
				if(o instanceof Crate) {
					if(((Crate) o).getRectangle().intersects(rect)) {
						location = new Point(location.row - 1, location.col);
						rect = new FireRingRectangle(location.col - 25, location.row - 25);
						moveable = false;
						return false;
					}
				}
				
			}
			if (location.row > 905) {
				location = new Point(location.row - 1, location.col);
				rect = new FireRingRectangle(location.col - 25, location.row - 25);
				moveable = false;
				return false;
			}
			notifyObservers(this);
			setChanged();
	
		}
		moveable = true;
		return true;

	}
		

	

	//Thread that causes the FireRing to move to across the map
	private class FireThread extends Thread {
		@Override
		public synchronized void run() {
			while(true) {
				if(tick == 0) {
				Random rnd = new Random();
				int rndDirection = rnd.nextInt(4) + 1;
				if(rndDirection == 1) {
					d = Direction.NORTH;
				}
				if(rndDirection == 2) {
					d = Direction.SOUTH;
					
				}
				if(rndDirection == 3) {
					d = Direction.EAST;
				}
				if(rndDirection == 4) {
					d = Direction.WEST;
				}
				}
				move(d);
			
				if(!moveable) {
					tick = 100;
				}
				
				if(tick < 100) {
				tick++;
				}
				else {
					tick = 0;
				}
				try {
					Thread.sleep(10);
				}
				catch(Exception e) {
					
				}
					
				}
				
				
			
		}
		
	}
	}



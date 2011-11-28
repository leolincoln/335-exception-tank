import java.util.Observable;

import rectangles.CrateRectangle;


public class Crate extends Observable implements Obstacle{
	
	private int health;
	private CrateRectangle rect;
	private Point location;
	
	
	
	public Crate(Point p) {
		location = p;
		health = 1;
		rect = new CrateRectangle(location.col - 25, location.row - 25);
		
	}

	@Override
	public void recieveDamage(int dmg) {
		health = health - dmg;
		if(this.removeObstacle()) {
			rect = new CrateRectangle(-1, -1);
			setChanged();
			notifyObservers("CrateDead");
		}
		
	}
	public CrateRectangle getRectangle() {
		return rect;
	}

	@Override
	public boolean removeObstacle() {
		if(health == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public Point getLocation() {
		return location;
	}

}

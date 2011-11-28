
import java.util.Observable;


import rectangles.ProjectileRectangle;


public class Projectile extends Observable {
	

	private Point p;
	private int speed;
	boolean exists;
	private Direction d;
	private ProjectileRectangle rect;
	private Projectile thisMissle;
	
	public Projectile(Point p, Direction d) {
		this.p = p;
		speed = 10;
		rect = new ProjectileRectangle(p.col - 3, p.row - 3);
		exists = true;
		this.d = d;
		thisMissle = this;
		Thread pt = new ProjectileThread();
		pt.start();
	}

	public Point getLocation() {
		return p;
	}
	public ProjectileRectangle getRectangle() {
		return rect;
	}
	
	public void setLocation(Point p) {
		this.p = p;
	}
	
	public int getDamage() {
		return 1;
	}
	
	
	public void exists() {
		if(p.row <= 40 || p.row >= 985 || p.col <= 15 || p.col >= 985) {
			exists = false;
			p = new Point(-1, -1);
			rect = new ProjectileRectangle(-1, -1);
			notifyObservers(this);
			setChanged();
		}
	}
	

	
	private class ProjectileThread extends Thread {
		
		public synchronized void run() {
			while(exists) {
				
				if(d == Direction.EAST) {
				p = new Point(p.row, p.col + 3);
				rect = new ProjectileRectangle(p.col - 3, p.row - 3);
				}
				if(d == Direction.WEST){
				p = new Point(p.row, p.col - 3);
				rect = new ProjectileRectangle(p.col - 3, p.row - 3);
				}
				if(d == Direction.NORTH) {
				p = new Point(p.row - 3, p.col);
				rect = new ProjectileRectangle(p.col - 3, p.row - 3);
				}
				if(d == Direction.SOUTH) {
				p = new Point(p.row + 3, p.col);
				rect = new ProjectileRectangle(p.col - 3, p.row - 3);
				}
				exists();
				notifyObservers(thisMissle);
				setChanged();
				try {
					sleep(speed);
				} catch (InterruptedException e) {
					
				}
				
			}
		}
	}



	public void collided() {
		exists = false;
		
	}
	

}

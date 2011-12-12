package undecided;

import java.awt.Image;
import java.util.Observable;

import javax.swing.ImageIcon;

public class Explosion extends Observable  {

	private int timePassed;
	private Image img;
	private Point location;
	private Map map;

	
	public Explosion(Point p, Map map) {
		timePassed = 0;
		this.map = map;
		img = new ImageIcon("images/explosion1.png").getImage();
		location = p;
		
		ExplosionThread et = new ExplosionThread();
		et.start();
		
		
	}
	
	public Point getLocation() {
		return location;
	}
	
	public Image getImage() {
		return img;
	}
	
	
	private class ExplosionThread extends Thread {
	@Override
	public void run() {
		while(timePassed <= 15) {
			if(timePassed == 0) {
				img = new ImageIcon("images/explosion1.png").getImage();
				location = new Point(location.row - 2, location.col - 2);
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 2) {
				img = new ImageIcon("images/explosion2.png").getImage();
				location = new Point(location.row - 2, location.col - 2);
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 4) {
				img = new ImageIcon("images/explosion3.png").getImage();
				location = new Point(location.row - 2, location.col - 2);
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 6) {
				img = new ImageIcon("images/explosion4.png").getImage();
				location = new Point(location.row - 2, location.col - 2);
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 8) {
				img = new ImageIcon("images/explosion5.png").getImage();
				location = new Point(location.row - 2, location.col - 2);
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 10) {
				img = new ImageIcon("images/explosion6.png").getImage();
				location = new Point(location.row - 2, location.col - 2);
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 12) {
				img = new ImageIcon("images/explosion7.png").getImage();
				location = new Point(location.row - 2, location.col - 2);
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 12) {
				img = new ImageIcon("images/explosion8.png").getImage();
				location = new Point(location.row - 2, location.col - 2);
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 12) {
				img = new ImageIcon("images/explosion9.png").getImage();
				location = new Point(location.row - 2, location.col - 2);
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 12) {
				img = new ImageIcon("images/explosion10.png").getImage();
				location = new Point(location.row - 2, location.col - 2);
				notifyObservers("Boom");
				setChanged();
			}
			
			if(timePassed == 14) {
				location = new Point(-200, -200);
				img = new ImageIcon("images/phony.png").getImage();
				map.getExplosions().remove(this);
			}
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
			
			}
			timePassed++;
			
		}
	}

	
	}
	}

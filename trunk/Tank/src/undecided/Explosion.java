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
		img = new ImageIcon("images/explosion1").getImage();
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
		while(timePassed <= 28) {
			if(timePassed == 0) {
				img = new ImageIcon("images/explosion1").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 2) {
				img = new ImageIcon("images/explosion2").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 4) {
				img = new ImageIcon("images/explosion3").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 6) {
				img = new ImageIcon("images/explosion4").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 8) {
				img = new ImageIcon("images/explosion5").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 10) {
				img = new ImageIcon("images/explosion6").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 12) {
				img = new ImageIcon("images/explosion7").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 14) {
				img = new ImageIcon("images/explosion6").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 16) {
				img = new ImageIcon("images/explosion5").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 18) {
				img = new ImageIcon("images/explosion4").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 20) {
				img = new ImageIcon("images/explosion3").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 22) {
				img = new ImageIcon("images/explosion2").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 24) {
				img = new ImageIcon("images/explosion1").getImage();
				notifyObservers("Boom");
				setChanged();
			}
			if(timePassed == 28) {
				map.getExplosions().remove(this);
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			
			}
			timePassed++;
			
		}
	}

	
	}
	}

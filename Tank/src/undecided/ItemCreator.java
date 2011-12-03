package undecided;

import java.util.LinkedList;
import java.util.Random;

import rectangles.BubbleShieldRectangle;
import rectangles.CrateRectangle;
import rectangles.FireRingRectangle;
import rectangles.ImmovableBlockRectangle;
import rectangles.SpeedBoostRectangle;
import rectangles.SpikePitRectangle;
import rectangles.TNTRectangle;
import rectangles.TankRectangle;

public class ItemCreator extends Thread {
	
	private BubbleShield b;
	private SpeedBoost s;
	private boolean exists;
	
	public ItemCreator() {
		exists = true;
	}
	

	public synchronized void run() {
	
		while(exists) {
			Random rnd = new Random();
			int creation = rnd.nextInt(300) + 1;
			if(creation == 1 || creation == 2) {
				if(!hasBubbleShield()) {
					if(spawnBubbleShield()) {
						TankView.itemList.add(b);
					}
 				}
			}
			if(creation == 3 || creation == 4) {
				if(!hasSpeedBoost()) {
					if(spawnSpeedBoost()) {
						TankView.itemList.add(s);
					}
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				
			}
		}
	}

	private boolean spawnSpeedBoost() {
		Random rnd = new Random();
		int x = rnd.nextInt(600) + 25;
		int y = rnd.nextInt(875) + 25;
		s = new SpeedBoost(new Point(x, y));
		SpeedBoostRectangle rect = s.getRectangle();
		LinkedList<Obstacle> obs = TankView.obstacleList;
		LinkedList<PlayerTank> tank = TankView.tankList;
		LinkedList<Item> item = TankView.itemList;
		for (PlayerTank p : tank) {
			TankRectangle tRect = p.getRectangle();
			if(tRect.intersects(rect)) {
				return false;
			}
			
		}
		for (Item p : item) {
			if(p instanceof SpeedBoost) {
			SpeedBoost s = (SpeedBoost)p;
			SpeedBoostRectangle tRect = s.getRectangle();
			if(tRect.intersects(rect)) {
				return false;
			}
			
		}
			
		}
		for (int i = 0; i < obs.size(); i++) {
			Obstacle p = obs.get(i);
			if (p instanceof Crate) {// for instance of crate
				Crate c = (Crate) p;
				CrateRectangle tRect = c.getRectangle();
				if(tRect.intersects(rect)) {
					return false;
				}
				
			}
			if (p instanceof ImmovableBlock) {// for instance of immovableBlock
				ImmovableBlock ib = (ImmovableBlock) p;
				ImmovableBlockRectangle tRect = ib.getRectangle();
				if(tRect.intersects(rect)) {
					return false;
				}
				
			}
			if (p instanceof SpikePit) {// for instance of SpikePit
				SpikePit sp = (SpikePit) p;
				SpikePitRectangle tRect = sp.getRectangle();
				if(tRect.intersects(rect)) {
					return false;
				}
				
			}
			if (p instanceof FireRing) {// for instance of fireRing
				FireRing fr = (FireRing) p;
				FireRingRectangle tRect = fr.getRectangle();
				if(tRect.intersects(rect)) {
					return false;
				}
				
			}
			if (p instanceof TNT) {// for instance of TNT
				TNT tnt = (TNT) p;
				TNTRectangle tRect = tnt.getRectangle();
				if(tRect.intersects(rect)) {
					return false;
				}
				
			}

		}
		return true;
		
	}

	private boolean spawnBubbleShield() {
		Random rnd = new Random();
		int x = rnd.nextInt(600) + 25;
		int y = rnd.nextInt(875) + 25;
		b = new BubbleShield(new Point(x, y));
		BubbleShieldRectangle rect = b.getRectangle();
		LinkedList<Obstacle> obs = TankView.obstacleList;
		LinkedList<PlayerTank> tank = TankView.tankList;
		LinkedList<Item> item = TankView.itemList;
		for (PlayerTank p : tank) {
			TankRectangle tRect = p.getRectangle();
			if(tRect.intersects(rect)) {
				return false;
			}
			
		}
		for (Item p : item) {
			if(p instanceof BubbleShield) {
			BubbleShield s = (BubbleShield)p;
			BubbleShieldRectangle tRect = s.getRectangle();
			if(tRect.intersects(rect)) {
				return false;
			}
			
		}
			
		}
		for (int i = 0; i < obs.size(); i++) {
			Obstacle p = obs.get(i);
			if (p instanceof Crate) {// for instance of crate
				Crate c = (Crate) p;
				CrateRectangle tRect = c.getRectangle();
				if(tRect.intersects(rect)) {
					return false;
				}
				
			}
			if (p instanceof ImmovableBlock) {// for instance of immovableBlock
				ImmovableBlock ib = (ImmovableBlock) p;
				ImmovableBlockRectangle tRect = ib.getRectangle();
				if(tRect.intersects(rect)) {
					return false;
				}
				
			}
			if (p instanceof SpikePit) {// for instance of SpikePit
				SpikePit sp = (SpikePit) p;
				SpikePitRectangle tRect = sp.getRectangle();
				if(tRect.intersects(rect)) {
					return false;
				}
				
			}
			if (p instanceof FireRing) {// for instance of fireRing
				FireRing fr = (FireRing) p;
				FireRingRectangle tRect = fr.getRectangle();
				if(tRect.intersects(rect)) {
					return false;
				}
				
			}
			if (p instanceof TNT) {// for instance of TNT
				TNT tnt = (TNT) p;
				TNTRectangle tRect = tnt.getRectangle();
				if(tRect.intersects(rect)) {
					return false;
				}
				
			}

		}
		return true;
		
		
	}

	private boolean hasSpeedBoost() {
		LinkedList<Item> it = TankView.itemList;
		for(int i = 0; i < it.size(); i++) {
			if(it.get(i) instanceof SpeedBoost) {
				return true;
			}
		}
		return false;
	}

	private boolean hasBubbleShield() {
		LinkedList<Item> it = TankView.itemList;
		for(int i = 0; i < it.size(); i++) {
			if(it.get(i) instanceof BubbleShield) {
				return true;
			}
		}
		return false;
	}
	


}

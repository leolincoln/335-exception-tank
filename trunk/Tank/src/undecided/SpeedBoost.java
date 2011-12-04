package undecided;


import java.util.Observable;





import rectangles.SpeedBoostRectangle;


public class SpeedBoost extends Observable implements Item {
	
	private SpeedBoostRectangle rect;
	private Point location;
	private PlayerTank player;
	private EnemyTank enemy;
	
	public SpeedBoost(Point p) {
		location = p;
		rect = new SpeedBoostRectangle(location.col - 25, location.row - 25);
		
	}
	
	public SpeedBoostRectangle getRectangle() {
		return rect;
	}
	
	public Point getLocation() {
		return location;
	}

	@Override
	public void activateEffect(PlayerTank t) {
		
		player = t;
		TimerThread time = new TimerThread();
		time.start();
	
		
	}
	@Override
	public void activateEffect(EnemyTank t) {
		
		enemy = t;
		EnemyTimerThread time = new EnemyTimerThread();
		time.start();
	
		
	}
	private class EnemyTimerThread extends Thread {
		private int timePassed = 0;
		
		public void run() {
			while(timePassed < 10) {
				if(timePassed == 9) {
					enemy.setSpeed(enemy.getSpeed() / 2);
				}
				else if(timePassed == 0) {
				enemy.setSpeed(enemy.getSpeed() * 2);
				}
				else {
					
				}
				timePassed++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				
				}
				
			}
		}
	}
	
	
	private class TimerThread extends Thread {
		
		private int timePassed = 0;
		
		public void run() {
			while(timePassed < 10) {
				if(timePassed == 9) {
					player.setSpeed(player.getSpeed() / 2);
				}
				else if(timePassed == 0) {
				player.setSpeed(player.getSpeed() * 2);
				}
				else {
					
				}
				timePassed++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				
				}
				
			}
			
			
		}
		
	}
	
	
	

}

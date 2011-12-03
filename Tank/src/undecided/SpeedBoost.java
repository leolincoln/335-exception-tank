package undecided;


import java.util.Observable;




import rectangles.SpeedBoostRectangle;

public class SpeedBoost extends Observable implements Item {
	
	private SpeedBoostRectangle rect;
	private Point location;
	private PlayerTank player;
	
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

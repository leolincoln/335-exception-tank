package undecided;


import java.util.Observable;





import rectangles.IceBlockRectangle;



public class IceBlock extends Observable implements Item {
	
	private IceBlockRectangle rect;
	private Point location;
	private PlayerTank player;
	private EnemyTank enemy;
	private Map map;
	
	public IceBlock(Point p, Map map) {
		location = p;
		rect = new IceBlockRectangle(location.col - 25, location.row - 25);
		this.map = map;
		player = this.map.getPlayers().getFirst();
		enemy = this.map.getEnemies().getFirst();
		
	}
	
	public IceBlockRectangle getRectangle() {
		return rect;
	}
	
	public Point getLocation() {
		return location;
	}

	@Override
	public void activateEffect(PlayerTank t) {
		
		TimerThread time = new TimerThread();
		time.start();
	
		
	}
	@Override
	public void activateEffect(EnemyTank t) {
		
		EnemyTimerThread time = new EnemyTimerThread();
		time.start();
	
		
	}
	private class EnemyTimerThread extends Thread {
		private int timePassed = 0;
		
		public void run() {
			while(timePassed < 10) {
				player.setSpeed(0);
				if(timePassed == 9) {
					player.setSpeed(5);
				}
				try {
					Thread.sleep(200);
				} catch(Exception e) {
					
				}
				timePassed++;
			}
			}
				
	}
	
	
	private class TimerThread extends Thread {
		
		private int timePassed = 0;
		
		public void run() {
			while(timePassed < 10) {
				enemy.setSpeed(0);
				if(timePassed == 9) {
					enemy.setSpeed(1);
				}
				try {
					Thread.sleep(200);
				} catch(Exception e) {
					
				}
				timePassed++;
			
		}
		
	}
	}
	
	

}

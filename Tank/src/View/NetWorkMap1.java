//package View;
//
//
//import java.util.LinkedList;
//import java.util.Observer;
//
//import undecided.Crate;
//
//import undecided.Explosion;
//import undecided.FireRing;
//import undecided.ImmovableBlock;
//import undecided.Item;
//import undecided.Map;
//import undecided.Obstacle;
//import undecided.PlayerTank;
//import undecided.Point;
//import undecided.Projectile;
//import undecided.SpikePit;
//import undecided.TNT;
//
//
//
//
//public class NetWorkMap1 extends Map implements Observer {
//	public LinkedList<Obstacle> obstacleList;	
//	public LinkedList<PlayerTank> tankList;
//	public LinkedList<Projectile> projectileList;
//	
//	public NetWorkMap1(){
//		obstacleList = new LinkedList<Obstacle>();
//		tankList = new LinkedList<PlayerTank>();
//		projectileList = new LinkedList<Projectile>();
//	}
//	
//
//public void addObstacle(Obstacle o) {
//	System.out.println(o);
//	obstacleList = new LinkedList<Obstacle>();
//	obstacleList.add(o);
//	if (o instanceof TNT) {
//		TNT t = (TNT) o;
//		t.addObserver(this);
//	}
//	if (o instanceof Crate) {
//		Crate t = (Crate) o;
//		t.addObserver(this);
//	}
//	if (o instanceof ImmovableBlock) {
//		ImmovableBlock t = (ImmovableBlock) o;
//		t.addObserver(this);
//	}
//	if (o instanceof SpikePit) {
//		SpikePit t = (SpikePit) o;
//		t.addObserver(this);
//	}
//	if (o instanceof FireRing) {
//		FireRing t = (FireRing) o;
//		t.addObserver(this);
//	}
//
//}
//
//	public void setUpMap() {
//		for (int i = 0; i < 750; i = i + 50) {
//			ImmovableBlock b = new ImmovableBlock(new Point(i, 25), this);
//			addObstacle(b);
//		}
//		for (int i = 30; i < 1000; i = i + 50) {
//			ImmovableBlock b = new ImmovableBlock(new Point(665, i), this);
//			addObstacle(b);
//		}
//		for (int i = 10; i < 750; i = i + 50) {
//			ImmovableBlock b = new ImmovableBlock(new Point(i, 960), this);
//			addObstacle(b);
//		}
//		
//		for (int i = 60; i < 1000; i = i + 50) {
//			ImmovableBlock b = new ImmovableBlock(new Point(25, i), this);
//			addObstacle(b);
//		}
//		
//		for (int i = 75; i < 275; i += 50) {
//			
//			Crate c = new Crate(new Point(i, 480), this);
//			addObstacle(c);
//		}
//		for (int i = 465; i < 665; i += 50) {
//			Crate c = new Crate(new Point(i, 480), this);
//			addObstacle(c);
//		}
//		for (int i = 75; i < 325; i += 50) {
//			ImmovableBlock b = new ImmovableBlock(new Point(i, 175), this);
//			addObstacle(b);
//		}
//		for (int i = 415; i < 665; i += 50) {
//			ImmovableBlock b = new ImmovableBlock(new Point(i, 810), this);
//			addObstacle(b);
//		}
//		for (int i = 75; i < 175; i += 50) {
//			TNT t = new TNT(new Point(75, i), this);
//			addObstacle(t);
//		}
//		for (int i = 860; i < 960; i += 50) {
//			TNT t = new TNT(new Point(615, i), this);
//			addObstacle(t);
//		}
//		for (int i = 225; i < 325; i += 50) {
//			SpikePit s = new SpikePit(new Point(75, i), this);
//			addObstacle(s);
//		}
//		for (int i = 710; i < 810; i += 50) {
//			SpikePit s = new SpikePit(new Point(615, i), this);
//			addObstacle(s);
//		}
//		
//	}
//
//	@Override
//	public Point playerStart() {
//		return new Point(400, 100);
//	}
//
//	@Override
//	public Point enemyStart() {
//		return new Point(100, 900);
//	}
//
//	@Override
//	public int getLevelNumber() {
//		// TODO Auto-generated method stub
//		return 1;
//	}
//	public LinkedList<Obstacle> getObstacles() {
//		return obstacleList;
//	}
//
//
//	/**
//	 * 
//	 * @return Returns the list containing this map's Projectiles
//	 */
//	public LinkedList<Projectile> getProjectiles() {
//		return projectileList;
//	}
//	/**
//	 * 
//	 * @return Returns the list containing this map's player
//	 */
//	public LinkedList<PlayerTank> getPlayers() {
//		return tankList;
//	}
//	public void addPlayers(PlayerTank p){
//		PlayerTank temp = p;
//		tankList.add(temp);
//	}
//	
//}

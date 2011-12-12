package undecided;



public class Arena1 extends Map{
	


	
private ItemCreator creator;

		
	public Arena1() {
			super();
			
		}
		
		public void interruptCreator(){
			if(creator.isAlive()) creator.interrupt();
		}
		
		public void setUpMap() {
			for (int i = 0; i < 750; i = i + 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, 25), this);
				addObstacle(b);
			}
			for (int i = 30; i < 1000; i = i + 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(665, i), this);
				addObstacle(b);
			}
			for (int i = 10; i < 750; i = i + 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, 960), this);
				addObstacle(b);
			}
			
			for (int i = 60; i < 1000; i = i + 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(25, i), this);
				addObstacle(b);
			}
			
			for (int i = 75; i < 275; i += 50) {
				Crate c = new Crate(new Point(i, 480), this);
				addObstacle(c);
			}
			for (int i = 465; i < 665; i += 50) {
				Crate c = new Crate(new Point(i, 480), this);
				addObstacle(c);
			}
			for (int i = 75; i < 325; i += 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, 175), this);
				addObstacle(b);
			}
			for (int i = 415; i < 665; i += 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, 810), this);
				addObstacle(b);
			}
			for (int i = 75; i < 175; i += 50) {
				TNT t = new TNT(new Point(75, i), this);
				addObstacle(t);
			}
			for (int i = 860; i < 960; i += 50) {
				TNT t = new TNT(new Point(615, i), this);
				addObstacle(t);
			}
			
			for (int i = 225; i < 325; i += 50) {
				SpikePit s = new SpikePit(new Point(75, i), this);
				addObstacle(s);
			}
			for (int i = 710; i < 810; i += 50) {
				SpikePit s = new SpikePit(new Point(615, i), this);
				addObstacle(s);
			}
	
			
			
		}

		@Override
		public Point playerStart() {
			return new Point(400, 100);
		}

		@Override
		public Point enemyStart() {
			return new Point(110, 880);
		}

		@Override
		public int getLevelNumber() {
			// TODO Auto-generated method stub
			return 1;
		}

		
		
	}



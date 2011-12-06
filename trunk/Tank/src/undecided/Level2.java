package undecided;


import java.util.Observer;


public class Level2 extends Map implements Observer {
	
	private ItemCreator creator; 
	
	public Level2() {
		super();
		creator = new ItemCreator(this);
		creator.start();
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
			for (int j = 75; j < 275; j += 50) {
				if(i == 225 ) {
					ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
					addObstacle(b);
				}
				if(j == 225 && i != 225) {
					Crate c = new Crate(new Point(i, j), this);
					addObstacle(c);
				}
			}
			
		}
		for (int i = 465; i < 665; i += 50) {
			for (int j = 760; j < 960; j += 50) {
				if(i == 465 ) {
					ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
					addObstacle(b);
				}
				if(j == 760 && i != 465) {
					Crate c = new Crate(new Point(i, j), this);
					addObstacle(c);
				}
			}
		}
		for (int i = 246; i < 496; i += 50) {
			for (int j = 380; j < 630; j += 50) {
				if((i == 246 || i == 446 || j == 380 || j == 580) && (j != 480 && (i != 246 || i != 446)) && (i != 346 && (j != 380 || j != 580))) {
					Crate c = new Crate(new Point(i, j), this);
					addObstacle(c);
				}
				if(i == 346 && j == 480) {
					FireRing fr = new FireRing(new Point(i, j), this);
					addObstacle(fr);
				}
				if(j == 480 && (i == 246 || i == 446)) {
					TNT t = new TNT(new Point(i, j), this);
					addObstacle(t);
				}
				if(i == 346 && (j == 380 || j == 580)) {
					TNT t = new TNT(new Point(i, j), this);
					addObstacle(t);
				}
			}
		}
		for (int i = 83; i < 233; i += 50) {
			SpikePit s = new SpikePit(new Point(i, 480), this);
			addObstacle(s);
		}
		for (int i = 507; i < 657; i += 50) {
			SpikePit s = new SpikePit(new Point(i, 480), this);
			addObstacle(s);
		}
		for (int i = 565; i < 665; i += 50) {
			for(int j = 75; j < 325; j += 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
				addObstacle(b);
			}
		}
		for (int i = 75; i < 175; i += 50) {
			for(int j = 710; j < 1010; j += 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
				addObstacle(b);
			}
		}
	}


	@Override
	public Point playerStart() {
		return new Point(115, 125);
	}

	@Override
	public Point enemyStart() {
		return new Point(575, 860);
	}


}

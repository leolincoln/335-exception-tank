package undecided;


import java.util.Observer;


public class Level5 extends Map implements Observer {
	
	private ItemCreator creator; 

	
	public Level5() {
		super();
		creator = new ItemCreator(this);
		creator.start();
	
	}
	
	public void setUpMap() {
		for (int i = 0; i < 750; i = i + 50) {
			if(i > 100 && i < 600) {
			SpikePit b = new SpikePit(new Point(i, 25), this);
			addObstacle(b);
			}
			else {
				ImmovableBlock b = new ImmovableBlock(new Point(i, 25), this);
				addObstacle(b);
			}
		}
		for (int i = 30; i < 1000; i = i + 50) {
			if(i > 130 && i < 850) {
			SpikePit b = new SpikePit(new Point(665, i), this);
			addObstacle(b);
			}
			else {
				ImmovableBlock b = new ImmovableBlock(new Point(665, i), this);
				addObstacle(b);
			}
		}
		for (int i = 10; i < 750; i = i + 50) {
			if(i > 110 && i < 600) {
			SpikePit b = new SpikePit(new Point(i, 960), this);
			addObstacle(b);
			}
			else {
				ImmovableBlock b = new ImmovableBlock(new Point(i, 960), this);
				addObstacle(b);
			}
		}
		
		for (int i = 60; i < 1000; i = i + 50) {
			if(i > 130 && i < 850) {
			SpikePit b = new SpikePit(new Point(25, i), this);
			addObstacle(b);
			}
			else {
				ImmovableBlock b = new ImmovableBlock(new Point(25, i), this);
				addObstacle(b);
			}
		}
		for (int i = 140; i < 590; i += 50) {
			SpikePit b = new SpikePit(new Point(i, 150), this);
			addObstacle(b);
			SpikePit b2 = new SpikePit(new Point(i, 835), this);
			addObstacle(b2);
		}
		for (int i = 85; i < 285; i += 50) {
			for (int j = 400; j < 600; j += 50) {
				if(i == 85 || i == 235 || j == 400 || j == 550) {
				TNT t = new TNT(new Point(i, j), this);
				addObstacle(t);
				}
				else {
					SpikePit b2 = new SpikePit(new Point(i, j), this);
					addObstacle(b2);
				}
				
			}
		}
		for (int i = 285; i < 485; i += 50) {
			for (int j = 400; j < 600; j += 50) {
				if(i == 285 || i == 435 || j == 400 || j == 550) {
				TNT t = new TNT(new Point(i, j), this);
				addObstacle(t);
				}
				else {
					SpikePit b2 = new SpikePit(new Point(i, j), this);
					addObstacle(b2);
				}
			}
		}
		for (int i = 485; i < 635; i += 50) {
			for (int j = 400; j < 600; j += 50) {
				if(i == 485 || i == 585 || j == 400 || j == 550) {
				TNT t = new TNT(new Point(i, j), this);
				addObstacle(t);
				}
				else {
					SpikePit b2 = new SpikePit(new Point(i, j), this);
					addObstacle(b2);
				}
			}
		}
		FireRing fr = new FireRing(new Point(160, 475), this);
		addObstacle(fr);
		FireRing fr2 = new FireRing(new Point(360, 475), this);
		addObstacle(fr2);
		FireRing fr3 = new FireRing(new Point(535, 475), this);
		addObstacle(fr3);
	}


	@Override
	public Point playerStart() {
		return new Point(350, 85);
	}

	@Override
	public Point enemyStart() {
		return new Point(350, 900);
	}

	@Override
	public int getLevelNumber() {
		// TODO Auto-generated method stub
		return 5;
	}


	


}
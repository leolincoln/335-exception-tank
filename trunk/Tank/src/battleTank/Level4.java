package battleTank;


import java.util.Observer;

/**
 * This class is the "level4" map for the campaign mode of tanks. It contains a
 * unique ItemCreator and a preset locations to place the obstacles, PlayerTank,
 * and the EnemyTank.
 * 
 * @author Team Exception
 * 
 * @extends Map
 * 
 * @implements Observer
 * 
 * @see ItemCreator, TankView, PlayerTank, EnemyTank
 */
public class Level4 extends Map implements Observer {
	
	private ItemCreator creator;

	/**
	 * This is the Level1 class constructor and simply creates an ItemCreator
	 * and starts the ItemCreator thread to begin spawning items onto the field.
	 * 
	 * @category constructor
	 */
	public Level4() {
		super();
		startCreator();
	}
	public void startCreator(){
		creator = new ItemCreator(this);
		creator.start();
	}
	/**
	 * This method actually sets up the predetermined map that has been designed
	 * as seen below. It will include adding all the five different obstacles
	 * onto the map including immovable blocks, crates, fire rings, TNT, and
	 * spike pits.
	 * 
	 * @see Crate, FireRing,ImmovableBlock, SpikePit, TNT
	 */
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
	
		for (int i = 145; i < 595; i += 50) {
			for(int j = 143; j < 893; j += 50) {
				if((i < 245 || i > 445 || j < 243 || j > 743) && ( j != 443 && j != 543 && j != 493)) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
				addObstacle(b);
				}
				if(j == 443 || j == 543) {
					Crate b = new Crate(new Point(i, j), this);
					addObstacle(b);
				}
				if(j == 493 && (i > 245 && i < 445)) {
					SpikePit b = new SpikePit(new Point(i, j), this);
					addObstacle(b);
				}
				
			}
		}
		for(int i = 443; i < 593; i += 50) {
		TNT t = new TNT(new Point(87, i), this);
		addObstacle(t);
		TNT t1 = new TNT(new Point(607, i), this);
		addObstacle(t1);
		}
		for(int i = 300; i < 450; i += 50) {
		TNT t2 = new TNT(new Point(i, 85), this);
		addObstacle(t2);
		TNT t3 = new TNT(new Point(i, 902), this);
		addObstacle(t3);
		}
		FireRing fr = new FireRing(new Point(350, 700), this);
		addObstacle(fr);
		
	}


	/**
	 * This method returns the location that the PlayerTank is to start.
	 * 
	 * @return starting location of the PlayerTank
	 */
	@Override
	public Point playerStart() {
		return new Point(350, 325);
	}

	/**
	 * This method returns the location that the EnemyTank is to start.
	 * 
	 * @return starting location of the EnemyTank
	 */
	@Override
	public Point enemyStart() {
		return new Point(80, 860);
	}

	/**
	 * This method returns the number of the current level.
	 * 
	 * return level number of current level
	 */
	@Override
	public int getLevelNumber() {
		// TODO Auto-generated method stub
		return 4;
	}

	
}
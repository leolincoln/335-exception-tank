package undecided;

import java.util.Observer;
/**
 * This class is the "level3" map for the campaign mode of tanks. It contains a
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
public class Level3 extends Map implements Observer {

	private ItemCreator creator;
	/**
	 * This is the Level1 class constructor and simply creates an ItemCreator
	 * and starts the ItemCreator thread to begin spawning items onto the field.
	 * 
	 * @category constructor
	 */
	public Level3() {
		super();

	}

	public void startCreator() {
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
		for (int i = 75; i < 575; i += 50) {
			for (int j = 75; j < 275; j += 50) {
				if (j == 225) {
					ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
					addObstacle(b);
				}
				if (i == 125 && j < 175) {
					ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
					addObstacle(b);
				}
				if (i == 225 && j > 75) {
					ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
					addObstacle(b);
				}
				if (i == 325 && j < 175) {
					ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
					addObstacle(b);
				}
				if (i == 425 && j > 75) {
					ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
					addObstacle(b);
				}
				if (i == 525 && j < 175) {
					ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
					addObstacle(b);
				}
			}
		}
		for (int i = 225; i < 875; i += 50) {
			ImmovableBlock b = new ImmovableBlock(new Point(525, i), this);
			addObstacle(b);
		}
		for (int i = 325; i < 825; i += 50) {
			if (i % 100 == 25) {
				TNT t = new TNT(new Point(600, i), this);
				addObstacle(t);
			}
		}
		for (int i = 425; i < 525; i += 50) {
			for (int j = 275; j < 375; j += 50) {
				SpikePit s = new SpikePit(new Point(i, j), this);
				addObstacle(s);
			}
		}
		for (int j = 125; j < 525; j += 50) {
			for (int i = 275; i < 525; i += 50) {
				if (j == 125 || i == 475) {
					Crate c = new Crate(new Point(j, i), this);
					addObstacle(c);
				}
				if (j == 325 && i == 325) {
					FireRing fr = new FireRing(new Point(j, i), this);
					addObstacle(fr);
				}
			}
		}
		for (int i = 75; i < 425; i += 50) {
			for (int j = 710; j < 1010; j += 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, j), this);
				addObstacle(b);
			}
		}
		for (int i = 175; i < 575; i += 50) {
			if (i % 100 == 75) {
				Crate c = new Crate(new Point(i, 125), this);
				addObstacle(c);
			}
		}
	}
	/**
	 * This method returns the location that the PlayerTank is to start.
	 * 
	 * @return starting location of the PlayerTank
	 */
	@Override
	public Point playerStart() {
		return new Point(75, 85);
	}

	/**
	 * This method returns the location that the EnemyTank is to start.
	 * 
	 * @return starting location of the EnemyTank
	 */
	@Override
	public Point enemyStart() {
		return new Point(75, 285);
	}

	/**
	 * This method returns the number of the current level.
	 * 
	 * return level number of current level
	 */
	@Override
	public int getLevelNumber() {
		// TODO Auto-generated method stub
		return 3;
	}

}
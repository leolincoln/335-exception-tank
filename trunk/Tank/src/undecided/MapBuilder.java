package undecided;

import java.util.LinkedList;
import java.io.*;

import map.Map;

public class MapBuilder {
	private LinkedList<Obstacle> obstacleList;
	private LinkedList<Terrain> terrain;
	private Map map;
	public MapBuilder() {
		obstacleList = new LinkedList<Obstacle>();
		terrain = new LinkedList<Terrain>();
		map = new Map();
	}

	public static void main(String Args[]) {
		MapBuilder mb = new MapBuilder();
		mb.buildToFile(mb.buildMapOne(),"map1.map");
	}
	private void buildToFile(Map m,String fileName){
		File f=new File(fileName);
		FileOutputStream fos;
	
	try {
		fos = new FileOutputStream(f);
		ObjectOutputStream oops = new ObjectOutputStream(fos);
		oops.writeObject(m);
		oops.close();
		fos.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	private Map buildMapOne() {
		Map m = new Map();
		for (int i = 0; i < 750; i = i + 50) {
			ImmovableBlock b = new ImmovableBlock(new Point(i, 25));
			obstacleList.add(b);

		}
		for (int i = 30; i < 1000; i = i + 50) {
			ImmovableBlock b = new ImmovableBlock(new Point(665, i));
			obstacleList.add(b);

		}
		for (int i = 10; i < 750; i = i + 50) {
			ImmovableBlock b = new ImmovableBlock(new Point(i, 960));
			obstacleList.add(b);

		}

		for (int i = 60; i < 1000; i = i + 50) {
			ImmovableBlock b = new ImmovableBlock(new Point(25, i));
			obstacleList.add(b);

		}
		for (int i = 75; i < 275; i = i + 50) {
			for (int j = 300; j < 550; j = j + 50) {
				if (j == 300 || j == 500) {
					ImmovableBlock b = new ImmovableBlock(new Point(j, i));
					obstacleList.add(b);

				}
				if (j != 300 && j != 500 && i == 225) {
					Crate c = new Crate(new Point(j, i));
					obstacleList.add(c);

				}
				if (j != 300 && j != 500 && j != 400 && i == 75) {
					SpikePit s = new SpikePit(new Point(j, i));
					obstacleList.add(s);

				}
			}
		}
		for (int i = 555; i < 655; i = i + 50) {
			for (int j = 75; j < 225; j = j + 50) {
				if (i == 605 && j == 125) {
					TNT t = new TNT(new Point(i, j));
					obstacleList.add(t);

				} else {
					Crate c = new Crate(new Point(i, j));
					obstacleList.add(c);

				}
			}
		}
		for (int i = 710; i < 1000; i += 50) {
			ImmovableBlock b = new ImmovableBlock(new Point(125, i));
			obstacleList.add(b);

		}
		FireRing fr = new FireRing(new Point(75, 910));
		obstacleList.add(fr);

		for (int i = 60; i < 500; i += 50) {
			for (int j = 460; j < 560; j += 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, j));
				obstacleList.add(b);

			}
		}
		for (int i = 513; i < 650; i += 50) {
			Crate c = new Crate(new Point(i, 500));
			obstacleList.add(c);

		}
		for (int i = 75; i < 275; i += 50) {
			for (int j = 75; j < 275; j += 50) {
				TNT t = new TNT(new Point(i, j));
				obstacleList.add(t);

			}
		}
		for (int i = 560; i < 810; i += 50) {
			for (int j = 225; j < 325; j += 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(j, i));
				obstacleList.add(b);
			}
		}
		FireRing fr2 = new FireRing(new Point(600, 900));
		obstacleList.add(fr2);

		for (int i = 490; i < 590; i += 50) {
			for (int j = 700; j < 900; j += 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, j));
				obstacleList.add(b);

			}
		}
		for (int i = 560; i < 660; i += 50) {
			for (int j = 325; j < 425; j += 50) {
				TNT t = new TNT(new Point(j, i));
				obstacleList.add(t);
			}
		}
		// add obstacle to the map class and return
		m.setObstacle(obstacleList);
		m.setTerrain(null);
		return m;

	}
}

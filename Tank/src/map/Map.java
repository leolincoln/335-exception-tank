package map;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import undecided.Obstacle;



public class Map implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Obstacle> obs;
	ArrayList<Terrain> terrain;
	public ArrayList<Obstacle> getObstacle(){
		return null;
	}
	public ArrayList<Terrain> getTerrain(){
		return terrain;
		
	}
	public Point getStartingPoint(){
		return null;
		
	}
	public Point getEndingPoint(){
		return null;
		
	}
}

package map;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import undecided.Obstacle;



public class Map implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LinkedList<Obstacle> obs;
	LinkedList<Terrain> terrain;
	Point hostStart,clientStart;
	
	public Map(Point host,Point client, LinkedList obs,LinkedList terrain){
		this.hostStart=host;
		this.clientStart=client;
		this.obs=obs;
		this.terrain=terrain;
	}
	public LinkedList<Obstacle> getObstacle(){
		return obs;
	}
	public LinkedList<Terrain> getTerrain(){
		return terrain;
		
	}
	public Point getStartingPoint(){
		return hostStart;
		
	}
	public Point getEndingPoint(){
		return clientStart;
		
	}
}

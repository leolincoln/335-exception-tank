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
	public Map(){
		this(null,null,new LinkedList<Obstacle>(),new LinkedList<Terrain>());
	}
	public Map(Point host,Point client, LinkedList<Obstacle> obs,LinkedList<Terrain> terrain){
		this.hostStart=host;
		this.clientStart=client;
		this.obs=obs;
		this.terrain=terrain;
	}
	public void setObstacle(LinkedList<Obstacle> obs){
		this.obs=obs;
	}
	public LinkedList<Obstacle> getObstacle(){
		return obs;
	}
	public void setTerrain(LinkedList<Terrain> t){
		this.terrain=t;
	}
	public LinkedList<Terrain> getTerrain(){
		return terrain;
	}
	public void setHostStartingPoint(Point host){
		this.hostStart=host;
	}
	
	public Point getHostStartingPoint(){
		return hostStart;
		
	}
	public void setClientStartingPoint(Point client){
		this.clientStart=client;
	}
	public Point getClientStartingPoint(){
		return clientStart;
		
	}
}

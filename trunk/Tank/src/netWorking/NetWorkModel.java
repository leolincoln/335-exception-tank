package netWorking;

import java.io.Serializable;

import map.Point;

public class NetWorkModel implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1245784278;
private String command;
private Point location;

public NetWorkModel(String command, Point location){
	this.command = command;
	this.location = location;
}
public Point getLocation(){
	return location;
}
public String getCommand(){
	return command;
}
}

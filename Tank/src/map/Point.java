package map;

import java.io.Serializable;

public class Point implements Serializable{
private int row;
private int col;
public Point(int r, int c){
	this.row = r;
	this.col=c;
}
public int getRow(){
	return row;
}
public int getCol(){
	return col;
}
}

package netWorking;

import java.io.Serializable;

public class SimpleShoot implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int r,c,x,y;
	public SimpleShoot(int r, int c, int x, int y){
		this.r = r;
		this.c = c;
		this.x=x;
		this.y=y;
	}
	
}

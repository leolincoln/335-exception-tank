package netWorking;

import java.io.Serializable;
/**this class is a serializable 4 int class for transmitting information
 * on a enemy projectile. 
 * @author TeamException
 * 
 */
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

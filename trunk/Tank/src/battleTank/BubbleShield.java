package battleTank;

import java.util.Observable;

import rectangles.BubbleShieldRectangle;
/**
 * This class implements items and is a shield that protects the tank from
 * projectiles for a short period of time. It consists of a geometry class
 * rectangle to control its collisions anda location to be placed on the map.
 * 
 * @author Team Exception
 * 
 * @see Item, PlayerTank, Projectile, EnemyTank, EnemyProjectile
 * 
 * @extends Observable
 * 
 * @implements Item
 */
public class BubbleShield extends Observable implements Item{
	
	private BubbleShieldRectangle rect;
	private Point location;
	/**
	 * This is the class constructor that sets location for the BubbleShield
	 * instance and creates a collision rectangle for it
	 * 
	 * @param p
	 *            the location that the BubbleShield instance is to be placed.
	 */
	public BubbleShield(Point p) {
		location = p;
		rect = new BubbleShieldRectangle(location.col - 25, location.row - 25);
		
	}
	/**
	 * This method will return the rectangle representing this BubbleShield
	 * 
	 * @return the rectangle that will represent the shield for collisions
	 */
	
	public BubbleShieldRectangle getRectangle() {
		return rect;
	}
	/**
	 * Returns the location of this BubbleShield
	 * 
	 * @return the location of this BubbleShield
	 */
	public Point getLocation() {
		return location;
	}
	/**
	 * This method determines if the BubbleShield is active on the PLAYER'S tank
	 * to determine if the tank has "extra health."
	 */
	@Override
	public void activateEffect(PlayerTank t) {
		t.setHealth(t.getHealth() + 1);
		
	}
	/**
	 * This method determines if the BubbleShield is active on the ENEMY tank to
	 * determine if the tank has "extra health."
	 */
	@Override
	public void activateEffect(EnemyTank t) {
		t.setHealth(t.getHealth() + 1);
		
	}
	
	

}

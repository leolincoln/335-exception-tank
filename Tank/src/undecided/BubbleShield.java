package undecided;

import java.util.Observable;

import rectangles.BubbleShieldRectangle;

public class BubbleShield extends Observable implements Item{
	
	private BubbleShieldRectangle rect;
	private Point location;
	
	public BubbleShield(Point p) {
		location = p;
		rect = new BubbleShieldRectangle(location.col - 25, location.row - 25);
		
	}
	
	public BubbleShieldRectangle getRectangle() {
		return rect;
	}
	
	public Point getLocation() {
		return location;
	}

	@Override
	public void activateEffect(PlayerTank t) {
		t.setHealth(t.getHealth() + 1);
		
	}

	@Override
	public void activateEffect(EnemyTank t) {
		t.setHealth(t.getHealth() + 1);
		
	}
	
	

}

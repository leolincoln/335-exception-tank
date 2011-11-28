package map;

public abstract class Obstacle {
public abstract void setHealth();
public abstract void setLocation();
public abstract void toRemove();
public abstract void receiveDamage(int dmg);
}

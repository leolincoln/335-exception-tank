package battleModel;

import map.Point;
import map.Tile;

public abstract class Tank {
private boolean activeItem;
private int health =1;
private Point locationPoint;
private Tile locationTile;

public abstract int getHealth();
public abstract void receiveDamage(int dmg);
public abstract void setLocation(Point p);
public abstract Point getLocation();
public abstract void move(Direction d);

}

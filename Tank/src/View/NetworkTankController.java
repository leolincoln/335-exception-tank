package View;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import rectangles.TankRectangle;
import undecided.BubbleShield;
import undecided.Crate;

import undecided.EnemyProjectile;
import undecided.EnemyTank;
import undecided.Explosion;
import undecided.FireRing;
import undecided.IceBlock;
import undecided.ImmovableBlock;
import undecided.Item;
import undecided.Level1;
import undecided.Map;
import undecided.Obstacle;
import undecided.PlayerProjectile;
import undecided.PlayerTank;
import undecided.Point;
import undecided.Projectile;
import undecided.SpeedBoost;
import undecided.SpikePit;
import undecided.TNT;

public class NetworkTankController extends Observable implements Observer {

	private LinkedList<Obstacle> obstacleList;
	private LinkedList<PlayerTank> tankList;
	private LinkedList<Projectile> projectileList;
	private LinkedList<EnemyTank> enemyList;
	private LinkedList<Item> itemList;
	private PlayerTank player;
	EnemyTank enemy;
	int i;
	Map map;
	MasterView m;
	private int playerScore, enemyScore;

	public NetworkTankController(MasterView m, int i) {
		this.m = m;
		this.i = i;
		obstacleList = new LinkedList<Obstacle>();
		tankList = new LinkedList<PlayerTank>();
		itemList = new LinkedList<Item>();
		playerScore = 0;
		enemyScore = 0;
		map = new Level1();
		map.projectileList = new LinkedList<Projectile>();
		this.projectileList = map.projectileList;
		addPlayers();

	}

	public int getPlayerScore() {
		return playerScore;
	}

	public int getEnemyScore() {
		return enemyScore;
	}

	public void addPlayers() {
		map.enemyList.remove();
		map.tankList.remove();
		if (i == 0) {
			System.out.println("if");
			setPlayerStart(map.playerStart());
			setEnemyStart(map.enemyStart());
		} else {
			System.out.println("else");
			setPlayerStart(map.enemyStart());
			setEnemyStart(map.playerStart());
		}
	}

	public void setEnemyStart(Point p) {

		enemy = new EnemyTank(p, map);
		enemy.addObserver(this);
		map.enemyList.add(enemy);

	}

	public void setPlayerStart(Point p) {
		player = new PlayerTank(p, map);
		player.addObserver(this);
		map.tankList.add(player);
	}

	public Map getMap() {
		return map;

	}

	public boolean isOver() {
		if (player.isDead() || enemy.isDead()) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized void update(Observable v, Object o) {
		obstacleList = new LinkedList<Obstacle>();
		tankList = new LinkedList<PlayerTank>();
		projectileList = new LinkedList<Projectile>();
		itemList = new LinkedList<Item>();
		enemyList = new LinkedList<EnemyTank>();

		// Creates a new explosion at the passed Point

		if (o instanceof Point) {
			Point p = (Point) o;
			Explosion e = new Explosion(p, getMap());
			getMap().addExplosion(e);
		}

		// Calls for the view to be repainted whenever a PlayerTank or EnemyTank
		// is pushing a crate or TNT

		if (o instanceof String) {
			String s = (String) o;
			if (s.equals("moveCrate")) {
				notifyObservers();
				setChanged();
			}

			if (s.equals("moveTNT")) {
				notifyObservers();
				setChanged();
			}
			if (s.equals("Boom")) {
				notifyObservers();
				setChanged();
			}
		}

		/*
		 * Whenever a FireRing moves it is passed to this method which then
		 * determines whether or not it has collided with the player
		 */
		if (o instanceof FireRing) {
			FireRing fr = (FireRing) o;
			for (int i = 0; i < tankList.size(); i++) {
				PlayerTank t = tankList.get(i);
				if (t.getRectangle().intersects(fr.getRectangle())) {
					notifyObservers(new Point(t.getLocation().row - 12,
							t.getLocation().col - 12));
					t.recieveDamage(1);
					setChanged();
					break;
				}
			}
			notifyObservers();
			setChanged();
		}

		// Adds the created PlayerProjectile to the map whenever the player's
		// shoot method is called. If there is
		// already a PlayerProjectile on the map this will keep track of that
		// projectile's location and will determine
		// whether or not it has collided with any objects.

		if (o instanceof PlayerProjectile) {
			PlayerProjectile p = (PlayerProjectile) o;
			System.out.println("printing projectile");
			if (!projectileList.contains(p)) {
				projectileList.add(p);
				p.addObserver(this);

			} else {

				// The xCoord will be set to -1 when the Projectile goes off the
				// screen. This will make sure it is properly removed from the
				// map.

				if (p.getRectangle().xCoord() <= 0) {
					projectileList.remove(p);
				}

				// The projectile will destroy any item it collides with

				for (Item i : itemList) {

					if (i instanceof BubbleShield) {
						BubbleShield c = (BubbleShield) i;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							itemList.remove(c);
							notifyObservers(new Point(p.getLocation().row - 12,
									p.getLocation().col - 12));
							setChanged();
							break;
						}
					}
					if (i instanceof IceBlock) {
						IceBlock c = (IceBlock) i;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							itemList.remove(c);
							notifyObservers(new Point(c.getLocation().row - 12,
									c.getLocation().col - 12));
							setChanged();
							break;
						}
					}
					if (i instanceof SpeedBoost) {
						SpeedBoost c = (SpeedBoost) i;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							itemList.remove(c);
							notifyObservers(new Point(p.getLocation().row - 12,
									p.getLocation().col - 12));
							setChanged();
							break;
						}
					}
				}

				// Removes the EnemyTank if it is hit by the PlayerTank's
				// projectile

				for (EnemyTank h : enemyList) {
					if (h.getRectangle().intersects(p.getRectangle())) {
						p.collided();
						projectileList.remove(p);
						h.recieveDamage(p.getDamage());
						notifyObservers(new Point(p.getLocation().row - 12,
								p.getLocation().col - 12));
						setChanged();
						break;
					}
				}
				for (Obstacle obs : obstacleList) {

					// Crate's are destroyed when a projectile collides with
					// them

					if (obs instanceof Crate) {
						Crate c = (Crate) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers(new Point(p.getLocation().row - 12,
									p.getLocation().col - 12));
							setChanged();
							break;
						}
					}

					// TNT's are destroyed when a projectile collides with them,
					// they also create a blast radius
					// that will cause any object somewhat near the exploded TNT
					// to take damage

					if (obs instanceof TNT) {
						TNT c = (TNT) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers(new Point(p.getLocation().row - 12,
									p.getLocation().col - 12));
							setChanged();
							break;
						}
					}

					// ImmovableBlock's can not be destroyed. If a projectile
					// collides with one, the projectile is removed.

					if (obs instanceof ImmovableBlock) {
						ImmovableBlock c = (ImmovableBlock) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers(new Point(p.getLocation().row - 12,
									p.getLocation().col - 12));
							setChanged();
							break;

						}
					}

					// FireRing's can not be destroyed. If a projectile collides
					// with one, the projectile is removed.

					if (obs instanceof FireRing) {
						FireRing c = (FireRing) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers(new Point(p.getLocation().row - 12,
									p.getLocation().col - 12));
							setChanged();
							break;

						}
					}

				}

				notifyObservers();
				setChanged();
			}
		}

		// Adds the created EnemyProjectile to the map whenever the enemy's
		// shoot method is called. If there is
		// already a EnemyProjectile on the map this will keep track of that
		// projectile's location and will determine
		// whether or not it has collided with any objects.

		if (o instanceof EnemyProjectile) {
			EnemyProjectile p = (EnemyProjectile) o;

			if (!projectileList.contains(p)) {
				projectileList.add(p);
				p.addObserver(this);

			} else {

				// The xCoord will be set to -1 when the Projectile goes off the
				// screen. This will make sure it is properly removed from the
				// map.

				if (p.getRectangle().xCoord() <= 0) {
					projectileList.remove(p);
				}

				// The projectile will destroy any item it collides with

				for (Item i : itemList) {
					if (i instanceof BubbleShield) {
						BubbleShield c = (BubbleShield) i;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							itemList.remove(c);
							notifyObservers(new Point(c.getLocation().row - 12,
									c.getLocation().col - 12));
							setChanged();
							break;
						}
					}
					if (i instanceof IceBlock) {
						IceBlock c = (IceBlock) i;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							itemList.remove(c);
							notifyObservers(new Point(c.getLocation().row - 12,
									c.getLocation().col - 12));
							setChanged();
							break;
						}
					}
					if (i instanceof SpeedBoost) {
						SpeedBoost c = (SpeedBoost) i;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							itemList.remove(c);
							notifyObservers(new Point(c.getLocation().row - 12,
									c.getLocation().col - 12));
							setChanged();
							break;
						}
					}
				}

				// Removes the PlayerTank if it is hit by the EnemyTank's
				// projectile

				for (PlayerTank h : tankList) {
					if (h.getRectangle().intersects(p.getRectangle())) {
						p.collided();
						projectileList.remove(p);
						notifyObservers(new Point(h.getLocation().row - 12,
								h.getLocation().col - 12));
						setChanged();
						h.recieveDamage(p.getDamage());
						break;
					}
				}

				for (Obstacle obs : obstacleList) {

					// Crate's are destroyed when a projectile collides with
					// them

					if (obs instanceof Crate) {
						Crate c = (Crate) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							notifyObservers(new Point(c.getLocation().row - 12,
									c.getLocation().col - 12));
							setChanged();
							c.recieveDamage(p.getDamage());
							break;
						}
					}

					// TNT's are destroyed when a projectile collides with them,
					// they also create a blast radius
					// that will cause any object somewhat near the exploded TNT
					// to take damage

					if (obs instanceof TNT) {
						TNT c = (TNT) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							notifyObservers(new Point(c.getLocation().row - 12,
									c.getLocation().col - 12));
							setChanged();
							c.recieveDamage(p.getDamage());
							break;
						}
					}

					// ImmovableBlock's can not be destroyed. If a projectile
					// collides with one, the projectile is removed.

					if (obs instanceof ImmovableBlock) {
						ImmovableBlock c = (ImmovableBlock) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers(new Point(p.getLocation().row - 12,
									p.getLocation().col - 12));
							setChanged();
							break;

						}
					}

					// FireRing's can not be destroyed. If a projectile collides
					// with one, the projectile is removed.

					if (obs instanceof FireRing) {
						FireRing c = (FireRing) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers(new Point(p.getLocation().row - 12,
									p.getLocation().col - 12));
							setChanged();
							break;

						}
					}

				}

				notifyObservers();
				setChanged();
			}
		}

		// Whenever the EnemyTank moves this will check to see if it has
		// collided with anything. Depeding
		// on the object it has collided with the proper action will occur.

		if (o instanceof EnemyTank) {
			EnemyTank p = (EnemyTank) o;
			TankRectangle rect = p.getRectangle();

			// When the EnemyTank collides with an item it will activate the
			// item's effect on that EnemyTank

			for (Item i : itemList) {
				if (i instanceof BubbleShield) {
					BubbleShield c = (BubbleShield) i;
					if (c.getRectangle().intersects(p.getRectangle())) {
						// Can not have more than one BubbleShield active at a
						// time
						if (p.getHealth() == 1) {
							c.activateEffect(p);
						}
						itemList.remove(c);
						notifyObservers();
						setChanged();
						break;
					}
				}
				if (i instanceof SpeedBoost) {
					SpeedBoost c = (SpeedBoost) i;
					if (c.getRectangle().intersects(p.getRectangle())) {
						c.activateEffect(p);
						itemList.remove(c);
						notifyObservers();
						setChanged();
						break;
					}
				}
				if (i instanceof IceBlock) {
					IceBlock c = (IceBlock) i;
					if (c.getRectangle().intersects(p.getRectangle())) {
						c.activateEffect(p);
						itemList.remove(c);
						notifyObservers();
						setChanged();
						break;
					}
				}
			}
			for (Obstacle obs : obstacleList) {
				// This tank will push the crate if the crate is movable
				if (obs instanceof Crate) {
					Crate c = (Crate) obs;
					if (rect.intersects(c.getRectangle())) {
						c.move(tankList.getFirst().getDirection());
					}
				}
				// This tank will push the TNT if the TNT is movable
				if (obs instanceof TNT) {
					TNT c = (TNT) obs;
					if (rect.intersects(c.getRectangle())) {
						c.move(tankList.getFirst().getDirection());
					}
				}
				// FireRing's can not kill the AI for difficulty purposes
				if (obs instanceof FireRing) {
					FireRing c = (FireRing) obs;
					if (rect.intersects(c.getRectangle())) {
						p.recieveDamage(0);

					}
				}
				// SpikePit's can not kill the AI either for difficulty purposes
				if (obs instanceof SpikePit) {
					SpikePit c = (SpikePit) obs;
					if (rect.intersects(c.getRectangle())) {
						p.recieveDamage(0);
					}
				}
			}
			notifyObservers();
			setChanged();
		}

		// Whenever the EnemyTank moves this will check to see if it has
		// collided with anything. Depeding
		// on the object it has collided with the proper action will occur.

		if (o instanceof PlayerTank) {
			PlayerTank p = (PlayerTank) o;
			TankRectangle rect = p.getRectangle();
			for (Item i : itemList) {
				// Only one BubbleShield can be active at a time
				if (i instanceof BubbleShield) {
					BubbleShield c = (BubbleShield) i;
					if (c.getRectangle().intersects(p.getRectangle())) {
						if (p.getHealth() == 1) {
							c.activateEffect(p);
						}
						itemList.remove(c);
						notifyObservers();
						setChanged();
						break;
					}
				}
				if (i instanceof SpeedBoost) {
					SpeedBoost c = (SpeedBoost) i;
					if (c.getRectangle().intersects(p.getRectangle())) {
						if (!p.isActiveBoost()) {
							c.activateEffect(p);
						}
						itemList.remove(c);
						notifyObservers();
						setChanged();
						break;
					}
				}
				if (i instanceof IceBlock) {
					IceBlock c = (IceBlock) i;
					if (c.getRectangle().intersects(p.getRectangle())) {
						c.activateEffect(p);
						itemList.remove(c);
						notifyObservers();
						setChanged();
						break;
					}
				}
			}
			for (Obstacle obs : obstacleList) {
				// The PlayerTank will push the crate if it is movable
				if (obs instanceof Crate) {
					Crate c = (Crate) obs;
					if (rect.intersects(c.getRectangle())) {
						c.move(tankList.getFirst().getDirection());
					}
				}
				// The PlayerTank will push the TNT if it is movable
				if (obs instanceof TNT) {
					TNT c = (TNT) obs;
					if (rect.intersects(c.getRectangle())) {
						c.move(tankList.getFirst().getDirection());
					}
				}
				// If the PlayerTank collides with a FireRing it will die
				if (obs instanceof FireRing) {
					FireRing c = (FireRing) obs;
					if (rect.intersects(c.getRectangle())) {
						notifyObservers(new Point(p.getLocation().row - 12,
								p.getLocation().col - 12));
						p.recieveDamage(1);
					}
				}
				// If the PlayerTank collides with a SpikePit it will die
				if (obs instanceof SpikePit) {
					SpikePit c = (SpikePit) obs;
					if (rect.intersects(c.getRectangle())) {
						notifyObservers(new Point(p.getLocation().row - 12,
								p.getLocation().col - 12));
						p.recieveDamage(1);
					}
				}
			}
			notifyObservers();
			setChanged();
		}
		if (o instanceof Crate) {
			Crate c = (Crate) o;
			c.recieveDamage(1);
		}

	}

}

package undecided;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import rectangles.TankRectangle;

/**
 * 
 * @author Team Exception
 * 
 * 		The map class is the class that keeps track of all objects that are on the board.
 * 		It observes everything so that it can tell the view whenever some action occurs that
 * 		changes the state of the game. It also allows for the creation of unique levels
 * 		that have obstacles placed in unique locations.
 *
 */
public abstract class Map extends Observable implements Observer {

	private LinkedList<Obstacle> obstacleList;
	private LinkedList<PlayerTank> tankList;
	private LinkedList<EnemyTank> enemyList;
	private LinkedList<Projectile> projectileList;
	private LinkedList<Item> itemList;
	private PlayerTank player;
	private EnemyTank enemy;
	
	/**
	 * 		Every time a new map is constructed the lists are reset so that objects from the previos
	 * 		level will not be painted on the screen. The constructor also sets the player and enemy's 
	 * 		starting points as well as sets up the specified map.
	 */
	public Map() {
		obstacleList = new LinkedList<Obstacle>();
		tankList = new LinkedList<PlayerTank>();
		enemyList = new LinkedList<EnemyTank>();
		projectileList = new LinkedList<Projectile>();
		itemList = new LinkedList<Item>();
		setPlayerStart(playerStart());
		setEnemyStart(enemyStart());
		setUpMap();
	}
	
	/**
	 * @param Item i
	 * 
	 * 		This method adds the specified item to the map which allows it to 
	 * 		be painted on to the view.
	 */
	public void addItem(Item i) {
		itemList.add(i);

	}
	/**
	 * 		This abstract method will vary for each constructed level. It will place all 
	 *  	the obstacles on the board prior to the painting call giving each level a
	 *  	distinct layout. 
	 */

	public abstract void setUpMap();
	
	/**
	 * 		This method will vary for each constructed level and returns the point at
	 * 		which the player will originally start at when the level begins.
	 * 
	 * @return Point 
	 */
	
	public abstract Point playerStart();
	
	/**
	 * 		This method will vary for each constructed level and returns the point at
	 * 		which the enemy tank will originally start at when the level begins.
	 * 
	 * @return Point 
	 */

	public abstract Point enemyStart();
	
	/**
	 * @param Obstacle o
	 * 
	 * 		This method adds the passed obstacle to the map. After the obstacle is added
	 * 		to the map it then adds the map as one of it's observers. This allows the map
	 * 		to keep track of any important events or movements that occur on the obstacle.
	 */
	
	/**
	 * 
	 * @return This method returns the number of the current level the player is on.
	 */
	public abstract int getLevelNumber();
	
	public void addObstacle(Obstacle o) {
		obstacleList.add(o);
		if (o instanceof TNT) {
			TNT t = (TNT) o;
			t.addObserver(this);
		}
		if (o instanceof Crate) {
			Crate t = (Crate) o;
			t.addObserver(this);
		}
		if (o instanceof ImmovableBlock) {
			ImmovableBlock t = (ImmovableBlock) o;
			t.addObserver(this);
		}
		if (o instanceof SpikePit) {
			SpikePit t = (SpikePit) o;
			t.addObserver(this);
		}
		if (o instanceof FireRing) {
			FireRing t = (FireRing) o;
			t.addObserver(this);
		}

	}
	
	/**
	 * @param Point p
	 * 
	 * 		Creates the EnemyTank for this map at the passed location. The enemy is then
	 * 		added to the map and adds the map as it's observer.
	 */

	public void setEnemyStart(Point p) {
		enemy = new EnemyTank(p, this);
		enemy.addObserver(this);
		enemyList.add(enemy);
	}
	
	/**
	 * @param Point p
	 * 
	 * 		Creates the PlayerTank for this map at the passed location. The player is then
	 * 		added to the map and adds the map as it's observer.
	 */

	public void setPlayerStart(Point p) {
		player = new PlayerTank(p, this);
		player.addObserver(this);
		tankList.add(player);

	}
	
	/**
	 * 		This method determines whether or not the current map is completed or not. If the enemy has dead
	 * 		or if the player has died then this method will return true.
	 * 
	 * @return Returns true if player or enemy is dead.
	 */

	public boolean isOver() {
		if (player.isDead() || enemy.isDead()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 		This method performs specific actions based on the object that is passed. In general, the update method
	 * 		handles all collision detection and will perform the necessary action depending on what type of 
	 * 		collision took place.
	 */

	public synchronized void update(Observable v, Object o) {
		
		// Adds a new BubbleShield to the map if it was successfully created in the ItemCreator thread
		
		if (o instanceof BubbleShield) {
			BubbleShield b = (BubbleShield) o;
			itemList.add(b);
			notifyObservers();
			setChanged();
		}
		
		// Adds a new SpeedBoost to the map if it was successfully created in the ItemCreator thread
		
		if (o instanceof SpeedBoost) {
			SpeedBoost b = (SpeedBoost) o;
			itemList.add(b);
			notifyObservers();
			setChanged();
		}
		
		// Calls for the view to be repainted whenever a PlayerTank or EnemyTank is pushing a crate or TNT
		
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
		}
		
		/* Whenever a FireRing moves it is passed to this method which then determines whether or not
		 it has collided with the player
		*/
		if (o instanceof FireRing) {
			FireRing fr = (FireRing) o;
			for (int i = 0; i < tankList.size(); i++) {
				PlayerTank t = tankList.get(i);
				if (t.getRectangle().intersects(fr.getRectangle())) {
					t.recieveDamage(1);
					notifyObservers();
					setChanged();
					break;
				}
			}
			notifyObservers();
			setChanged();
		}
		
		// Adds the created PlayerProjectile to the map whenever the player's shoot method is called. If there is 
		// already a PlayerProjectile on the map this will keep track of that projectile's location and will determine 
		// whether or not it has collided with any objects.

		if (o instanceof PlayerProjectile) {
			PlayerProjectile p = (PlayerProjectile) o;

			if (!projectileList.contains(p)) {
				projectileList.add(p);
				p.addObserver(this);

			} else {
				
				// The xCoord will be set to -1 when the Projectile goes off the screen. This will make sure it is properly removed from the map.
				
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
							notifyObservers();
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
							notifyObservers();
							setChanged();
							break;
						}
					}
				}
				
				//Removes the EnemyTank if it is hit by the PlayerTank's projectile
				
				for (EnemyTank h : enemyList) {
					if (h.getRectangle().intersects(p.getRectangle())) {
						p.collided();
						projectileList.remove(p);
						h.recieveDamage(p.getDamage());
						notifyObservers();
						setChanged();
						break;
					}
				}
				for (Obstacle obs : obstacleList) {
					
					// Crate's are destroyed when a projectile collides with them
					
					if (obs instanceof Crate) {
						Crate c = (Crate) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers();
							setChanged();
							break;
						}
					}
					
					// TNT's are destroyed when a projectile collides with them, they also create a blast radius
					// that will cause any object somewhat near the exploded TNT to take damage
					
					if (obs instanceof TNT) {
						TNT c = (TNT) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers();
							setChanged();
							break;
						}
					}
					
					//ImmovableBlock's can not be destroyed. If a projectile collides with one, the projectile is removed.
					
					if (obs instanceof ImmovableBlock) {
						ImmovableBlock c = (ImmovableBlock) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers();
							setChanged();
							break;

						}
					}

					//FireRing's can not be destroyed. If a projectile collides with one, the projectile is removed.
					
					if (obs instanceof FireRing) {
						FireRing c = (FireRing) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers();
							setChanged();
							break;

						}
					}

				}

				notifyObservers();
				setChanged();
			}
		}
		
		// Adds the created EnemyProjectile to the map whenever the enemy's shoot method is called. If there is 
		// already a EnemyProjectile on the map this will keep track of that projectile's location and will determine 
		// whether or not it has collided with any objects.
		
		if (o instanceof EnemyProjectile) {
			EnemyProjectile p = (EnemyProjectile) o;

			if (!projectileList.contains(p)) {
				projectileList.add(p);
				p.addObserver(this);

			} else {
				
				// The xCoord will be set to -1 when the Projectile goes off the screen. This will make sure it is properly removed from the map.
				
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
							notifyObservers();
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
							notifyObservers();
							setChanged();
							break;
						}
					}
				}
				
				//Removes the PlayerTank if it is hit by the EnemyTank's projectile
				
				for (PlayerTank h : tankList) {
					if (h.getRectangle().intersects(p.getRectangle())) {
						p.collided();
						projectileList.remove(p);
						h.recieveDamage(p.getDamage());
						notifyObservers();
						setChanged();
						break;
					}
				}

				for (Obstacle obs : obstacleList) {
					
					// Crate's are destroyed when a projectile collides with them
					
					if (obs instanceof Crate) {
						Crate c = (Crate) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers();
							setChanged();
							break;
						}
					}
					
					// TNT's are destroyed when a projectile collides with them, they also create a blast radius
					// that will cause any object somewhat near the exploded TNT to take damage
					
					if (obs instanceof TNT) {
						TNT c = (TNT) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers();
							setChanged();
							break;
						}
					}
					
					// ImmovableBlock's can not be destroyed. If a projectile collides with one, the projectile is removed.
					
					if (obs instanceof ImmovableBlock) {
						ImmovableBlock c = (ImmovableBlock) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers();
							setChanged();
							break;

						}
					}
					
					//FireRing's can not be destroyed. If a projectile collides with one, the projectile is removed.
					
					if (obs instanceof FireRing) {
						FireRing c = (FireRing) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							notifyObservers();
							setChanged();
							break;

						}
					}

				}

				notifyObservers();
				setChanged();
			}
		}
		
		// Whenever the EnemyTank moves this will check to see if it has collided with anything. Depeding 
		// on the object it has collided with the proper action will occur. 
		
		if (o instanceof EnemyTank) {
			EnemyTank p = (EnemyTank) o;
			TankRectangle rect = p.getRectangle();
			
			// When the EnemyTank collides with an item it will activate the item's effect on that EnemyTank
			
			for (Item i : itemList) {
				if (i instanceof BubbleShield) {
					BubbleShield c = (BubbleShield) i;
					if (c.getRectangle().intersects(p.getRectangle())) {
						// Can not have more than one BubbleShield active at a time
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
		
		// Whenever the EnemyTank moves this will check to see if it has collided with anything. Depeding 
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
						p.recieveDamage(1);
					}
				}
				// If the PlayerTank collides with a SpikePit it will die
				if (obs instanceof SpikePit) {
					SpikePit c = (SpikePit) obs;
					if (rect.intersects(c.getRectangle())) {
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
	/**
	 * 
	 * @return Returns the list containing this map's obstacles
	 */
	public LinkedList<Obstacle> getObstacles() {
		return obstacleList;
	}
	/**
	 * 
	 * @return Returns the list containing this map's items
	 */
	public LinkedList<Item> getItems() {
		return itemList;
	}
	/**
	 * 
	 * @return Returns the list containing this map's enemy
	 */
	public LinkedList<EnemyTank> getEnemies() {
		return enemyList;
	}
	/**
	 * 
	 * @return Returns the list containing this map's Projectiles
	 */
	public LinkedList<Projectile> getProjectiles() {
		return projectileList;
	}
	/**
	 * 
	 * @return Returns the list containing this map's player
	 */
	public LinkedList<PlayerTank> getPlayers() {
		return tankList;
	}

}

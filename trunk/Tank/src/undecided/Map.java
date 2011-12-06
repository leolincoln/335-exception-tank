package undecided;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import rectangles.TankRectangle;

public abstract class Map extends Observable implements Observer{
	
	private LinkedList<Obstacle> obstacleList;
	private LinkedList<PlayerTank> tankList;
	private LinkedList<EnemyTank> enemyList;
	private LinkedList<Projectile> projectileList;
	private LinkedList<Item> itemList;
	private PlayerTank player;
	private EnemyTank enemy;

	
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
	
	public void addItem(Item i) {
		itemList.add(i);
		
	}
	public abstract void setUpMap();
	
	public abstract Point playerStart();
	
	public abstract Point enemyStart();

	public void addObstacle(Obstacle o) {
		obstacleList.add(o);
		if(o instanceof TNT) {
			TNT t = (TNT)o;
			t.addObserver(this);
		}
		if(o instanceof Crate) {
			Crate t = (Crate)o;
			t.addObserver(this);
		}
		if(o instanceof ImmovableBlock) {
			ImmovableBlock t = (ImmovableBlock)o;
			t.addObserver(this);
		}
		if(o instanceof SpikePit) {
			SpikePit t = (SpikePit)o;
			t.addObserver(this);
		}
		if(o instanceof FireRing) {
			FireRing t = (FireRing)o;
			t.addObserver(this);
		}
		
		
	}

	public void setEnemyStart(Point p) {
		enemy = new EnemyTank(p, this);
		enemy.addObserver(this);
		enemyList.add(enemy);
		
		
	}

	public void setPlayerStart(Point p) {
		player = new PlayerTank(p, this);
		player.addObserver(this);
		tankList.add(player);
		
	}
	

	public boolean isOver() {
		if(player.isDead() || enemy.isDead()) {
			
			
			return true;
		}
		else {
			return false;
		}
	}

	public synchronized void update(Observable v, Object o) {
		if(o instanceof BubbleShield) {   
			BubbleShield b = (BubbleShield)o;
			itemList.add(b);
			notifyObservers();
			setChanged();
		}
		if(o instanceof SpeedBoost) {
			SpeedBoost b = (SpeedBoost)o;
			itemList.add(b);
			notifyObservers();
			setChanged();
		}
		if (o instanceof String) {
			String s = (String) o;
			if (s.equals("moveCrate")) {
				notifyObservers();
				setChanged();
			}
		}
		
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

		if (o instanceof PlayerProjectile) {
				PlayerProjectile p = (PlayerProjectile)o;	
				
				if (!projectileList.contains(p)) {
					projectileList.add(p);
					p.addObserver(this);

				} else {
					if (p.getRectangle().xCoord() <=0) {
						projectileList.remove(p);
					}
					for(Item i : itemList) {
						if(i instanceof BubbleShield) {
							BubbleShield c = (BubbleShield)i;
							if (c.getRectangle().intersects(p.getRectangle())) {
								p.collided();
								projectileList.remove(p);
								itemList.remove(c);
								notifyObservers();
								setChanged();
								break;
							}
						}
						if(i instanceof SpeedBoost) {
							SpeedBoost c = (SpeedBoost)i;
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
					
					for(EnemyTank h : enemyList) {
						if(h.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							h.recieveDamage(p.getDamage());
							notifyObservers();
							setChanged();
							break;
						}
					}
					for (Obstacle obs : obstacleList) {
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
		if (o instanceof EnemyProjectile) {
			EnemyProjectile p = (EnemyProjectile)o;	
			
			if (!projectileList.contains(p)) {
				projectileList.add(p);
				p.addObserver(this);

			} else {
				if (p.getRectangle().xCoord() <=0) {
					projectileList.remove(p);
				}
				for(Item i : itemList) {
					if(i instanceof BubbleShield) {
						BubbleShield c = (BubbleShield)i;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							itemList.remove(c);
							notifyObservers();
							setChanged();
							break;
						}
					}
					if(i instanceof SpeedBoost) {
						SpeedBoost c = (SpeedBoost)i;
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
				
				for(PlayerTank h : tankList) {
					if(h.getRectangle().intersects(p.getRectangle())) {
						p.collided();
						projectileList.remove(p);
						h.recieveDamage(p.getDamage());
						notifyObservers();
						setChanged();
						break;
					}
				}
				for (Obstacle obs : obstacleList) {
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

		
		if (o instanceof EnemyTank) {
			EnemyTank p = (EnemyTank) o;
			TankRectangle rect = p.getRectangle();
			for(Item i : itemList) {
				if(i instanceof BubbleShield) {
					BubbleShield c = (BubbleShield)i;
					if (c.getRectangle().intersects(p.getRectangle())) {
						if(p.getHealth() == 1) {
						c.activateEffect(p);
						}
						itemList.remove(c);
						notifyObservers();
						setChanged();
						break;
					}
				}
				if(i instanceof SpeedBoost) {
					SpeedBoost c = (SpeedBoost)i;
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
				if (obs instanceof Crate) {
					Crate c = (Crate) obs;
					if (rect.intersects(c.getRectangle())) {
						c.move(tankList.getFirst().getDirection());
					}
				}
				if (obs instanceof TNT) {
					TNT c = (TNT) obs;
					if (rect.intersects(c.getRectangle())) {
						c.move(tankList.getFirst().getDirection());
					}
				}
				if (obs instanceof FireRing) {
					FireRing c = (FireRing) obs;
					if (rect.intersects(c.getRectangle())) {
						p.recieveDamage(0);
					}
				}
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
		if (o instanceof PlayerTank) {
			PlayerTank p = (PlayerTank) o;
			TankRectangle rect = p.getRectangle();
			for(Item i : itemList) {
				if(i instanceof BubbleShield) {
					BubbleShield c = (BubbleShield)i;
					if (c.getRectangle().intersects(p.getRectangle())) {
						if(p.getHealth() == 1) {
						c.activateEffect(p);
						}
						itemList.remove(c);
						notifyObservers();
						setChanged();
						break;
					}
				}
				if(i instanceof SpeedBoost) {
					SpeedBoost c = (SpeedBoost)i;
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
				if (obs instanceof Crate) {
					Crate c = (Crate) obs;
					if (rect.intersects(c.getRectangle())) {
						c.move(tankList.getFirst().getDirection());
					}
				}
				if (obs instanceof TNT) {
					TNT c = (TNT) obs;
					if (rect.intersects(c.getRectangle())) {
						c.move(tankList.getFirst().getDirection());
					}
				}
				if (obs instanceof FireRing) {
					FireRing c = (FireRing) obs;
					if (rect.intersects(c.getRectangle())) {
						p.recieveDamage(1);
					}
				}
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

	public LinkedList<Obstacle> getObstacles() {
		return obstacleList;
	}

	public LinkedList<Item> getItems() {
		return itemList;
	}

	public LinkedList<EnemyTank> getEnemies() {
		return enemyList;
	}

	public LinkedList<Projectile> getProjectiles() {
		return projectileList;
	}

	public LinkedList<PlayerTank> getPlayers() {
		return tankList;
	}

}




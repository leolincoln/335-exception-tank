package undecided;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import map.Map;

import View.MasterView;
import View.MasterViewPanel;

import rectangles.BlastRadiusRectangle;
import rectangles.BubbleShieldRectangle;
import rectangles.CrateRectangle;
import rectangles.FireRingRectangle;
import rectangles.ImmovableBlockRectangle;
import rectangles.ProjectileRectangle;
import rectangles.SpeedBoostRectangle;
import rectangles.SpikePitRectangle;
import rectangles.TNTRectangle;
import rectangles.TankRectangle;

/**
 * 
 * @author Team Exception
 * 
 *         This class is the primary battle view in which the player controls a
 *         single tank that can move through the arrow keys and shoot through
 *         the mouse.
 * 
 */
public class TankView extends MasterViewPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private Image dbImage;
	private Graphics dbg;
	private PlayerTank player;
	private Crate crate;
	private ImmovableBlock immovableBlock;
	private FireRing fireRing;
	private SpikePit spikePit;
	private TNT tnt;
	public static LinkedList<Projectile> projectileList;
	public static LinkedList<Obstacle> obstacleList;
	public static LinkedList<PlayerTank> tankList;
	public static LinkedList<Item> itemList;
	private TankView thisView;
	private ItemCreator creator;
	java.util.Vector<Projectile> pVector; // a vector of projectiles
	private Map map;

	/**
	 * Class constructor
	 * 
	 * @ param m this is the masterview
	 */
	public TankView(MasterView m) {
		super(m);

		player = new PlayerTank(new Point(400, 125));
		obstacleList = new LinkedList<Obstacle>();
		projectileList = new LinkedList<Projectile>();
		tankList = new LinkedList<PlayerTank>();
		itemList = new LinkedList<Item>();
		creator = new ItemCreator();
		creator.start();
		tankList.add(player);
		player.addObserver(this);
		panel = new JPanel();
		add(panel);
		addKeyListener(new moveAndShootListener());// adding the movement and

		Handlerclass handler = new Handlerclass();// creating the mouse handler
													// class
		this.addMouseListener(handler);// adding mouse actions to be detected on
										// the java panel
		this.addMouseMotionListener(handler);// adding mouse motion to be
												// detected on the java panel

		this.buildMap(null);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
		thisView = this;

	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This is basically the private inner class that handles the mouse
	 *         listener and mouse motion listener methods that controls the
	 *         direction and whether or not the tank is shooting.
	 * 
	 */
	private class Handlerclass implements MouseListener, MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		/**
		 * @param arg0
		 *            mouse event argument
		 *            
		 *           
		 */
		public void mousePressed(MouseEvent arg0) {

			// finding difference in player and target location
			int xdiff = arg0.getX() - player.getLocation().col;
			int ydiff = arg0.getY() - player.getLocation().row;

			// calculating the distance between the player and the mouse
			double length = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

			// create a new shot, with position relative to location of tank,
			// the speed in the x and y directions
			player.shoot(
					new Point(player.getLocation().row,
							player.getLocation().col),
					(int) (xdiff * (5 / length)), (int) (ydiff * (5 / length)));

			// player.shoot();

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * This method paints the TankView graphics when called.
	 */
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}

	/**
	 * 
	 * @param g
	 *            graphics component that java uses to paint components. It will
	 *            repaint all the components including the projectile, the tanks
	 *            in the tank list, and all the objects in the obstacle list.
	 */
	public void paintComponent(Graphics g) {

		for (Projectile p : projectileList) {
			ProjectileRectangle rect = p.getRectangle();
			g.drawImage(rect.getImage(), rect.xCoord(), rect.yCoord(), null);
		}
		
		for (PlayerTank p : tankList) {
			TankRectangle tRect = p.getRectangle();
			g.drawImage(p.getImage(), tRect.xCoord(), tRect.yCoord(), null);
		}
		for (Item p : itemList) {
			if(p instanceof SpeedBoost) {
			SpeedBoost s = (SpeedBoost)p;
			SpeedBoostRectangle tRect = s.getRectangle();
			g.drawImage(tRect.getImage(), tRect.xCoord(), tRect.yCoord(), null);
		}
			if(p instanceof BubbleShield) {
			BubbleShield s = (BubbleShield)p;
			BubbleShieldRectangle tRect = s.getRectangle();
			g.drawImage(tRect.getImage(), tRect.xCoord(), tRect.yCoord(), null);
			}
		}
		for (int i = 0; i < obstacleList.size(); i++) {
			Obstacle p = obstacleList.get(i);
			if (p instanceof Crate) {// for instance of crate
				Crate c = (Crate) p;
				CrateRectangle cRect = c.getRectangle();
				g.drawImage(cRect.getImage(), cRect.xCoord(), cRect.yCoord(),
						null);
			}
			if (p instanceof ImmovableBlock) {// for instance of immovableBlock
				ImmovableBlock ib = (ImmovableBlock) p;
				ImmovableBlockRectangle ibRect = ib.getRectangle();
				g.drawImage(ibRect.getImage(), ibRect.xCoord(),
						ibRect.yCoord(), null);
			}
			if (p instanceof SpikePit) {// for instance of SpikePit
				SpikePit sp = (SpikePit) p;
				SpikePitRectangle spRect = sp.getRectangle();
				g.drawImage(spRect.getImage(), spRect.xCoord(),
						spRect.yCoord(), null);
			}
			if (p instanceof FireRing) {// for instance of fireRing
				FireRing fr = (FireRing) p;
				FireRingRectangle frRect = fr.getRectangle();
				g.setColor(frRect.setColor());
				g.drawImage(frRect.getImage(), frRect.xCoord(),
						frRect.yCoord(), null);
			}
			if (p instanceof TNT) {// for instance of TNT
				TNT tnt = (TNT) p;
				TNTRectangle tntRect = tnt.getRectangle();
				g.drawImage(tntRect.getImage(), tntRect.xCoord(),
						tntRect.yCoord(), null);
			}

		}
	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private inner class controls the player controlled tank
	 *         allowing it to move via key listeners and shoot through the mouse
	 *         lister. The tank will move on key pressed.
	 * 
	 */
	private class moveAndShootListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			int keyEvent = e.getKeyCode();
			if (keyEvent == KeyEvent.VK_W) {
				player.moveUp();
			}
			if (keyEvent == KeyEvent.VK_S) {
				player.moveDown();
			}
			if (keyEvent == KeyEvent.VK_A) {
				player.moveLeft();
			}
			if (keyEvent == KeyEvent.VK_D) {
				player.moveRight();
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

	/**
	 * This method will be notified when the observed are called and will either
	 * remove dead obstacles and repaint the projectiles.
	 */
	public synchronized void update(Observable v, Object o) {
		if(o instanceof BubbleShield) {
			BubbleShield b = (BubbleShield)o;
			itemList.add(b);
			repaint();
		}
		if(o instanceof SpeedBoost) {
			SpeedBoost b = (SpeedBoost)o;
			itemList.add(b);
			repaint();
		}
		if (o instanceof String) {
			String s = (String) o;
			if (s.equals("moveCrate")) {
				repaint();
			}
		}
		
		if (o instanceof FireRing) {
			FireRing fr = (FireRing) o;
			for (int i = 0; i < tankList.size(); i++) {
				PlayerTank t = tankList.get(i);
				if (t.getRectangle().intersects(fr.getRectangle())) {
					t.recieveDamage(1);
					repaint();
					break;
				}
			}
			repaint();
		}

		if (o instanceof Projectile) {

			Projectile p = (Projectile) o;
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
							repaint();
							break;
						}
					}
					if(i instanceof SpeedBoost) {
						SpeedBoost c = (SpeedBoost)i;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							itemList.remove(c);
							repaint();
							break;
						}
					}
				}
				for (Obstacle obs : obstacleList) {
					if (obs instanceof Crate) {
						Crate c = (Crate) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							repaint();
							break;
						}
					}
					if (obs instanceof TNT) {
						TNT c = (TNT) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							repaint();
							break;
						}
					}
					if (obs instanceof ImmovableBlock) {
						ImmovableBlock c = (ImmovableBlock) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							repaint();
							break;

						}
					}
					if (obs instanceof FireRing) {
						FireRing c = (FireRing) obs;
						if (c.getRectangle().intersects(p.getRectangle())) {
							p.collided();
							projectileList.remove(p);
							c.recieveDamage(p.getDamage());
							repaint();
							break;

						}
					}

				}

				repaint();

			}

		}
		if (o instanceof PlayerTank) {
			PlayerTank p = (PlayerTank) o;
			TankRectangle rect = p.getRectangle();
			for(Item i : itemList) {
				if(i instanceof BubbleShield) {
					BubbleShield c = (BubbleShield)i;
					if (c.getRectangle().intersects(p.getRectangle())) {
						c.activateEffect(p);
						itemList.remove(c);
						repaint();
						break;
					}
				}
				if(i instanceof SpeedBoost) {
					SpeedBoost c = (SpeedBoost)i;
					if (c.getRectangle().intersects(p.getRectangle())) {
						c.activateEffect(p);
						itemList.remove(c);
						repaint();
						break;
					}
				}
			}
			for (Obstacle obs : obstacleList) {
				if (obs instanceof Crate) {
					Crate c = (Crate) obs;
					if (rect.intersects(c.getRectangle())) {
						c.move(player.getDirection());
					}
				}
				if (obs instanceof TNT) {
					TNT c = (TNT) obs;
					if (rect.intersects(c.getRectangle())) {
						c.move(player.getDirection());
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
			repaint();
		}
		if (o instanceof Crate) {
			Crate c = (Crate) o;
			c.recieveDamage(1);
		}

	}

	/**
	 * This method will dynamically build the map.
	 */
	public void buildMap(String mapFile) {
		// remove later!!!!
		
		for (int i = 0; i < 750; i = i + 50) {
			ImmovableBlock b = new ImmovableBlock(new Point(i, 25));
			obstacleList.add(b);
			b.addObserver(this);
		}
		for (int i = 30; i < 1000; i = i + 50) {
			ImmovableBlock b = new ImmovableBlock(new Point(665, i));
			obstacleList.add(b);
			b.addObserver(this);
		}
		for (int i = 10; i < 750; i = i + 50) {
			ImmovableBlock b = new ImmovableBlock(new Point(i, 960));
			obstacleList.add(b);
			b.addObserver(this);
		}
		
		for (int i = 60; i < 1000; i = i + 50) {
			ImmovableBlock b = new ImmovableBlock(new Point(25, i));
			obstacleList.add(b);
			b.addObserver(this);
		}
		for (int i = 75; i < 275; i = i + 50) {
			for (int j = 300; j < 550; j = j + 50) {
				if (j == 300 || j == 500) {
					ImmovableBlock b = new ImmovableBlock(new Point(j, i));
					obstacleList.add(b);
					b.addObserver(this);
				}
				if (j != 300 && j != 500 && i == 225) {
					Crate c = new Crate(new Point(j, i));
					obstacleList.add(c);
					c.addObserver(this);
				}
				if (j != 300 && j != 500 && j != 400 && i == 75) {
					SpikePit s = new SpikePit(new Point(j, i));
					obstacleList.add(s);
					s.addObserver(this);
				}
			}
		}
		for (int i = 555; i < 655; i = i + 50) {
			for (int j = 75; j < 225; j = j + 50) {
				if (i == 605 && j == 125) {
					TNT t = new TNT(new Point(i, j));
					obstacleList.add(t);
					t.addObserver(this);
				} else {
					Crate c = new Crate(new Point(i, j));
					obstacleList.add(c);
					c.addObserver(this);
				}
			}
		}
		for (int i = 710; i < 1000; i += 50) {
			ImmovableBlock b = new ImmovableBlock(new Point(125, i));
			obstacleList.add(b);
			b.addObserver(this);
		}
		FireRing fr = new FireRing(new Point(75, 910));
		obstacleList.add(fr);
		fr.addObserver(this);

		for (int i = 60; i < 500; i += 50) {
			for (int j = 460; j < 560; j += 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, j));
				obstacleList.add(b);
				b.addObserver(this);
			}
		}
		for (int i = 513; i < 650; i += 50) {
			Crate c = new Crate(new Point(i, 500));
			obstacleList.add(c);
			c.addObserver(this);
		}
		for (int i = 75; i < 275; i += 50) {
			for (int j = 75; j < 275; j += 50) {
				TNT t = new TNT(new Point(i, j));
				obstacleList.add(t);
				t.addObserver(this);
			}
		}
		for (int i = 560; i < 810; i += 50) {
			for (int j = 225; j < 325; j += 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(j, i));
				obstacleList.add(b);
				b.addObserver(this);
			}
		}
		FireRing fr2 = new FireRing(new Point(600, 900));
		obstacleList.add(fr2);
		fr2.addObserver(this);

		for (int i = 490; i < 590; i += 50) {
			for (int j = 700; j < 900; j += 50) {
				ImmovableBlock b = new ImmovableBlock(new Point(i, j));
				obstacleList.add(b);
				b.addObserver(this);
			}
		}
		for (int i = 560; i < 660; i += 50) {
			for (int j = 325; j < 425; j += 50) {
				TNT t = new TNT(new Point(j, i));
				obstacleList.add(t);
				t.addObserver(this);
			}
		}

	}
	
	
}


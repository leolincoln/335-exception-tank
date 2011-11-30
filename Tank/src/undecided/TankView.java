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

import View.MasterView;
import View.MasterViewPanel;

import rectangles.CrateRectangle;
import rectangles.FireRingRectangle;
import rectangles.ImmovableBlockRectangle;
import rectangles.ProjectileRectangle;
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
	private LinkedList<Projectile> projectileList;
	private LinkedList<Obstacle> obstacleList;
	private LinkedList<PlayerTank> tankList;
	java.util.Vector<Projectile> pVector; //a vector of projectiles

	/**
	 * Class constructor
	 */
	public TankView(MasterView m) {
		super(m);

		player = new PlayerTank(new Point(0, 0));
		obstacleList = new LinkedList<Obstacle>();
		projectileList = new LinkedList<Projectile>();
		tankList = new LinkedList<PlayerTank>();
		tankList.add(player);
		player.addObserver(this);
		panel = new JPanel();
		add(panel);
		addKeyListener(new moveAndShootListener());// adding the movement and
		
		Handlerclass handler = new Handlerclass();// creating the mouse handler class
		this.addMouseListener(handler);// adding mouse actions to be detected on the java panel
		this.addMouseMotionListener(handler);// adding mouse motion to be detected on the java panel
		
		
		this.buildMap();
		this.setBackground(Color.BLACK);
		this.setVisible(true);
		
	}

	
	private class Handlerclass implements MouseListener, MouseMotionListener{

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

		@Override
		public void mousePressed(MouseEvent arg0) {
		
			// finding difference in player and target location
			int xdiff = arg0.getX()-player.getLocation().col;
			int ydiff = arg0.getY()-player.getLocation().row;
			
			//calculating the distance between the player and the mouse
			double length = Math.sqrt(xdiff*xdiff+ydiff*ydiff);
			
			//create a new shot, with position relative to location of tank, the speed in the x and y directions
			player.shoot(new Point(player.getLocation().row, player.getLocation().col), (int)(xdiff*(5/length)), (int)(ydiff*(5/length)));

			//player.shoot();
			
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
	 *            graphics component that java uses to paint components
	 */
	public void paintComponent(Graphics g) {
		for (Projectile p : projectileList) {
			ProjectileRectangle rect = p.getRectangle();
			g.setColor(rect.setColor());
			g.fillRect(rect.xCoord(), rect.yCoord(), rect.width, rect.height);
		}
		for (PlayerTank p : tankList) {
			TankRectangle tRect = p.getRectangle();
			g.setColor(tRect.setColor());
			g.fillRect(tRect.xCoord(), tRect.yCoord(), tRect.width,
					tRect.height);
		}
		for (Obstacle p : obstacleList) {
			if (p instanceof Crate) {// for instance of crate
				Crate c = (Crate) p;
				CrateRectangle cRect = c.getRectangle();
				g.setColor(cRect.setColor());
				g.fillRect(cRect.xCoord(), cRect.yCoord(), cRect.width,
						cRect.height);
			}
			if (p instanceof ImmovableBlock) {// for instance of immovableBlock
				ImmovableBlock ib = (ImmovableBlock) p;
				ImmovableBlockRectangle ibRect = ib.getRectangle();
				g.setColor(ibRect.setColor());
				g.fillRect(ibRect.xCoord(), ibRect.yCoord(), ibRect.width,
						ibRect.height);
			}
			if (p instanceof SpikePit) {// for instance of SpikePit
				SpikePit sp = (SpikePit) p;
				SpikePitRectangle spRect = sp.getRectangle();
				g.setColor(spRect.setColor());
				g.fillRect(spRect.xCoord(), spRect.yCoord(), spRect.width,
						spRect.height);
			}
			if (p instanceof FireRing) {// for instance of fireRing
				FireRing fr = (FireRing) p;
				FireRingRectangle frRect = fr.getRectangle();
				g.setColor(frRect.setColor());
				g.fillRect(frRect.xCoord(), frRect.yCoord(), frRect.width,
						frRect.height);
			}
			if (p instanceof TNT) {// for instance of TNT
				TNT tnt = (TNT) p;
				TNTRectangle tntRect = tnt.getRectangle();
				g.setColor(tntRect.setColor());
				g.fillRect(tntRect.xCoord(), tntRect.yCoord(), tntRect.width,
						tntRect.height);
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
			if (keyEvent == KeyEvent.VK_UP) {
				player.moveUp();
			}
			if (keyEvent == KeyEvent.VK_DOWN) {
				player.moveDown();
			}
			if (keyEvent == KeyEvent.VK_LEFT) {
				player.moveLeft();
			}
			if (keyEvent == KeyEvent.VK_RIGHT) {
				player.moveRight();
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			int keyEvent = e.getKeyCode();
			if (keyEvent == KeyEvent.VK_SPACE) {
				player.shoot();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

	/**
	 * This method will be notified when the observed are called and will either
	 * remove dead obstacles and repaint the projectiles.
	 */
	@Override
	public void update(Observable v, Object o) {
		if (o instanceof String) {
			String s = (String) o;
			if (s.equals("CrateDead")) {// removing crate
				obstacleList.remove(crate);
			}
			if (s.equals("FireRingDead")) {// removing fire ring
				obstacleList.remove(fireRing);
			}
			if (s.equals("TNTDead")) {// removing tnt
				obstacleList.remove(tnt);
			}
		}
		if (o instanceof Projectile) {
			Projectile p = (Projectile) o;
			if (!projectileList.contains(p)) {
				projectileList.add(p);
				p.addObserver(this);
				repaint();
			} else {
				if (p.getRectangle().xCoord() == -1) {
					projectileList.remove(p);
					repaint();
				}
				if (crate.getRectangle().intersects(p.getRectangle())) {
					p.collided();
					projectileList.remove(p);
					crate.recieveDamage(1);
					repaint();
				}
				if (fireRing.getRectangle().intersects(p.getRectangle())) {
					p.collided();
					projectileList.remove(p);
					fireRing.recieveDamage(1);
					repaint();
				}
				if (tnt.getRectangle().intersects(p.getRectangle())) {
					p.collided();
					projectileList.remove(p);
					tnt.recieveDamage(1);
					repaint();
				}

				else {
					repaint();
				}
			}

		}
		if (o instanceof TankRectangle) {
			repaint();
		}

	}
//for building the map. 
	public void buildMap() {
		//remove later!!!!
		crate = new Crate(new Point(600, 400));
		immovableBlock = new ImmovableBlock((new Point(650, 400)));
		fireRing = new FireRing((new Point(300, 400)));
		spikePit = new SpikePit((new Point(100, 200)));
		tnt = new TNT((new Point(650, 600)));
		obstacleList.add(crate);
		obstacleList.add(immovableBlock);
		obstacleList.add(fireRing);
		obstacleList.add(spikePit);
		obstacleList.add(tnt);
		crate.addObserver(this);
		immovableBlock.addObserver(this);
		fireRing.addObserver(this);
		spikePit.addObserver(this);
		tnt.addObserver(this);
		
	}
}

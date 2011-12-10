package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import rectangles.BubbleShieldRectangle;
import rectangles.CrateRectangle;
import rectangles.FireRingRectangle;
import rectangles.ImmovableBlockRectangle;
import rectangles.ProjectileRectangle;
import rectangles.SpeedBoostRectangle;
import rectangles.SpikePitRectangle;
import rectangles.TNTRectangle;
import rectangles.TankRectangle;

import undecided.BubbleShield;
import undecided.Crate;
import undecided.EnemyProjectile;
import undecided.EnemyTank;
import undecided.FireRing;
import undecided.ImmovableBlock;
import undecided.Item;
import undecided.ItemCreator;
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



public class NetworkTankView extends MasterViewPanel implements Observer {

	public NetworkTankModel model;
	private Image dbImage;
	private Graphics dbg;
	private PlayerTank player;
	private PlayerTank enemy;
	private LinkedList<Projectile> projectileList;
	private LinkedList<Obstacle> obstacleList;
	private LinkedList<PlayerTank> tankList;
	private LinkedList<Item> itemList;
	private JPanel panel;
	java.util.Vector<Projectile> pVector; // a vector of projectiles
	private boolean won, lost;
	Map map;
	private Image camo, wheel, steel, gold;
	
	// if i=0, then its the host, if i=1, then its the client
	public NetworkTankView(MasterView m,int i) {
		super(m);
		camo = new ImageIcon("images/camo.png").getImage();
		wheel = new ImageIcon("images/wheel-md.png").getImage();
		steel = new ImageIcon("images/steel.png").getImage();
		gold = new ImageIcon("images/gold.png").getImage();

		this.map = new Level1();
		model = new NetworkTankModel(m, map, i);
		tankList = map.getPlayers();
		projectileList = map.getProjectiles();
		obstacleList = map.getObstacles();
	
		itemList = map.getItems();
		GameThread gt = new GameThread();
		gt.start();

		this.setFocusable(true);

		if(i==0) player = tankList.getFirst();
		if(i==1) player = tankList.getLast();
		add(panel);
		// adding the movement and
		addKeyListener(new moveAndShootListener());
		// creating the mouse handler
		Handlerclass handler = new Handlerclass();
		// adding mouse actions to be detected on	the java panel								
		this.addMouseListener(handler);
										
		this.addMouseMotionListener(handler);// adding mouse motion to be detected on the java panel
												
		this.setVisible(true);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 == null) {
			repaint();
		}
	}

	public void paint(Graphics g) {
		try {
			dbImage = createImage(getWidth(), getHeight());
			dbg = dbImage.getGraphics();
			paintComponent(dbg);
			g.drawImage(dbImage, 0, 0, this);
		} catch (Exception e) {

		}
	}

	private class GameThread extends Thread {

		private boolean exists;

		public GameThread() {
			exists = true;
		}

		@Override
		public synchronized void run() {
			while (exists) {
				if (map.getPlayers().size() == 1) {
					lost = true;
					repaint();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {

					}
					m.changeView(Views.NETWORKTANKVIEW,3);
					exists = false;
				} else if (map.getEnemies().size() == 0) {
					
				} else {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {

					}
				}

			}

		}

	}

	/**
	 * 
	 * @param g
	 *            graphics component that java uses to paint components. It will
	 *            repaint all the components including the projectile, the tanks
	 *            in the tank list, and all the objects in the obstacle list.
	 */
	public void paintComponent(Graphics g) {

		for (Item p : itemList) {
			if (p instanceof SpeedBoost) {
				SpeedBoost s = (SpeedBoost) p;
				SpeedBoostRectangle tRect = s.getRectangle();
				g.drawImage(tRect.getImage(), tRect.xCoord(), tRect.yCoord(),
						null);
			}
			if (p instanceof BubbleShield) {
				BubbleShield s = (BubbleShield) p;
				BubbleShieldRectangle tRect = s.getRectangle();
				g.drawImage(tRect.getImage(), tRect.xCoord(), tRect.yCoord(),
						null);
			}
		}
		for (int i = 0; i < obstacleList.size(); i++) {
			Obstacle p = obstacleList.get(i);
			if (p instanceof SpikePit) {// for instance of SpikePit
				SpikePit sp = (SpikePit) p;
				SpikePitRectangle spRect = sp.getRectangle();
				g.drawImage(spRect.getImage(), spRect.xCoord(),
						spRect.yCoord(), null);
			}
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
		
			if (p instanceof TNT) {// for instance of TNT
				TNT tnt = (TNT) p;
				TNTRectangle tntRect = tnt.getRectangle();
				g.drawImage(tntRect.getImage(), tntRect.xCoord(),
						tntRect.yCoord(), null);
			}

		}
		for (PlayerTank p : tankList) {
			TankRectangle tRect = p.getRectangle();
			g.drawImage(p.getImage(), tRect.xCoord(), tRect.yCoord(), null);
		}
		
		for (Projectile p : projectileList) {
			if (p instanceof PlayerProjectile) {
				PlayerProjectile s = (PlayerProjectile) p;
				ProjectileRectangle rect = s.getRectangle();
				g.drawImage(rect.getImage(), rect.xCoord(), rect.yCoord(), null);
			}
			if (p instanceof EnemyProjectile) {
				EnemyProjectile s = (EnemyProjectile) p;
				ProjectileRectangle rect = s.getRectangle();
				g.drawImage(rect.getImage(), rect.xCoord(), rect.yCoord(), null);
			}
		}
		if (won == true) {
			Font font = new Font("Times New Roman", Font.BOLD, 28);
			String jb = "Mission Complete!";
			AttributedString att = new AttributedString(jb);
			att.addAttribute(TextAttribute.FOREGROUND, Color.YELLOW);
			att.addAttribute(TextAttribute.FONT, font);
			g.drawString(att.getIterator(), 400, 350);
		}
		if (lost == true) {
			Font font = new Font("Times New Roman", Font.BOLD, 28);
			String jb = "Mission Failed!";
			AttributedString att = new AttributedString(jb);
			att.addAttribute(TextAttribute.FOREGROUND, Color.RED);
			att.addAttribute(TextAttribute.FONT, font);
			g.drawString(att.getIterator(), 400, 350);
		}
		for(int i = 0; i < 700; i += 50) {
			for(int j = 985; j < 1200; j += 50) {
				if(i == 150 || i == 200 || i == 350 || i == 400) {
					g.drawImage(steel, j, i, null);
				}
				else {
				g.drawImage(camo, j, i, null);
			}
			}
		}
		for(int i = 0; i < 700; i += 20) {
			for(int j = 985; j < 1200; j += 20) {
				if(i == 0 || i == 680 || j == 985 || j == 1165) {
					g.drawImage(gold, j, i, null);
				}
			}
			}
	
		Font font = new Font("Times New Roman", Font.BOLD, 20);
		String score = "Score Board";
		AttributedString att = new AttributedString(score);
		att.addAttribute(TextAttribute.FOREGROUND, Color.WHITE);
		att.addAttribute(TextAttribute.FONT, font);
		g.drawString(att.getIterator(), 1018, 44);
		
		for(int i = 0; i < MasterView.playerLives; i++) {
			for(int j = 0; j < MasterView.playerLives * 50; j += 55) {
			g.drawImage(wheel, 1005 + j, 65, null);
			}
		}
		String curr = "Current Level: " + map.getLevelNumber();
		AttributedString att3 = new AttributedString(curr);
		att3.addAttribute(TextAttribute.FOREGROUND, Color.WHITE);
		att3.addAttribute(TextAttribute.FONT, font);
		g.drawString(att3.getIterator(), 1013, 300);
		
		String item = "Active Items";
		AttributedString att6 = new AttributedString(item);
		att6.addAttribute(TextAttribute.FOREGROUND, Color.WHITE);
		att6.addAttribute(TextAttribute.FONT, font);
		g.drawString(att6.getIterator(), 1030, 485);
		
		if(player.isActiveShield()) {
			g.drawImage(new BubbleShieldRectangle(-10, -10).getImage(), 1020, 515, null);
		}
		if(player.isActiveBoost()) {
			g.drawImage(new SpeedBoostRectangle(-10, -10).getImage(), 1100, 515, null);
		}
	}

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
			int count = 0;
			for (Projectile p : map.getProjectiles()) {
				if (p instanceof PlayerProjectile) {
					count++;
				}
			}

			if (count == 0) {
				// finding difference in player and target location
				int xdiff = arg0.getX() - player.getLocation().col;
				int ydiff = arg0.getY() - player.getLocation().row;

				// calculating the distance between the player and the mouse
				double length = Math.sqrt(xdiff * xdiff + ydiff * ydiff);

				// create a new shot, with position relative to location of
				// tank,
				// the speed in the x and y directions
				player.shoot(
						new Point(player.getLocation().row, player
								.getLocation().col),
						(int) (xdiff * (5 / length)),
						(int) (ydiff * (5 / length)));
				// player.shoot();
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

}
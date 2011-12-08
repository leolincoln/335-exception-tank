package View;

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
import undecided.TankView.GameThread;
import undecided.TankView.Handlerclass;
import undecided.TankView.moveAndShootListener;

/*import undecided.TankView.Handlerclass;
 import undecided.TankView.moveAndShootListener;*/

public class NetWorkTankView extends MasterViewPanel implements Observer {
	
	
public NetWorkTankModel model;
private Image dbImage;
private Graphics dbg;
private PlayerTank player;
private EnemyTank enemy;
private LinkedList<Projectile> projectileList;
private LinkedList<Obstacle> obstacleList;
private LinkedList<PlayerTank> tankList;
private LinkedList<Item> itemList;
private LinkedList<EnemyTank> enemyList;
java.util.Vector<Projectile> pVector; // a vector of projectiles
private boolean won, lost;
Map map;
	public NetWorkTankView(MasterView m) {
		super(m);
		this.map = new Level1();	
		model = new NetWorkTankModel(m, map);
		tankList = map.getPlayers();
		projectileList = map.getProjectiles();
		obstacleList = map.getObstacles();
		enemyList = map.getEnemies();
		itemList = map.getItems();
		GameThread gt = new GameThread();
		gt.start();
		
		this.setFocusable(true);
		
		player = tankList.getFirst();
		add(panel);
		addKeyListener(new moveAndShootListener());// adding the movement and

		Handlerclass handler = new Handlerclass();// creating the mouse handler
													// class
		this.addMouseListener(handler);// adding mouse actions to be detected on
										// the java panel
		this.addMouseMotionListener(handler);// adding mouse motion to be
												// detected on the java panel
		this.setVisible(true);
	

	}

		
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
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
			for(Projectile p : map.getProjectiles()) {
				if(p instanceof PlayerProjectile) {
					count++;
				}
			}
			
			if(count == 0) {
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
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	
}

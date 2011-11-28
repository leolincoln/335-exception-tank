import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import rectangles.CrateRectangle;
import rectangles.ProjectileRectangle;
import rectangles.TankRectangle;

public class TankView extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;

	private JPanel panel;

	private Image dbImage;
	private Graphics dbg;
	private PlayerTank player;
	private Crate crate;
	private LinkedList<Projectile> projectileList;
	private LinkedList<Obstacle> obstacleList;
	private LinkedList<PlayerTank> tankList;

	public TankView() {
		super("Tank Test Game");
		player = new PlayerTank(new Point(100, 100));
		crate = new Crate(new Point(600, 400));
		obstacleList = new LinkedList<Obstacle>();
		projectileList = new LinkedList<Projectile>();
		tankList = new LinkedList<PlayerTank>();
		tankList.add(player);
		obstacleList.add(crate);
		player.addObserver(this);
		crate.addObserver(this);
		panel = new JPanel();
		add(panel);
		addKeyListener(new moveAndShootListener());

	}

	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}

	public void paintComponent(Graphics g) {
		for (Projectile p : projectileList) {
			ProjectileRectangle rect = p.getRectangle();
			g.setColor(rect.setColor());
			g.fillRect(rect.xCoord(), rect.yCoord(), rect.width, rect.height);
		}
		for (PlayerTank p : tankList) {
		TankRectangle tRect = p.getRectangle();
		g.setColor(tRect.setColor());
		g.fillRect(tRect.xCoord(), tRect.yCoord(), tRect.width, tRect.height);
		}
		for (Obstacle p : obstacleList) {
		if(p instanceof Crate) {
			Crate c = (Crate)p;
			CrateRectangle cRect = c.getRectangle();
			g.setColor(cRect.setColor());
			g.fillRect(cRect.xCoord(), cRect.yCoord(), cRect.width, cRect.height);
		}
		
		}
	}

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

	@Override
	public void update(Observable v, Object o) {
		if(o instanceof String) {
			String s = (String)o;
			if(s.equals("CrateDead")) {
				obstacleList.remove(crate);
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

				else {
					repaint();
				}
			}

		}
		if (o instanceof TankRectangle) {
			repaint();
		}

	}
}

package netWorking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import undecided.EnemyTank;
import undecided.Level1;
import undecided.PlayerTank;
import undecided.Point;

import View.HostView;
import View.MasterView;
import View.Views;

public class HostModel extends Observable implements Observer{
	boolean first = true;
	private ServerSocket host;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public static ArrayList<String> ipList;
	private Socket client;

	public HostView hv;
	public MasterView m;
	// dont forget to set the p after changing to network tank view.
	public PlayerTank p;
	public EnemyTank e;
	public boolean connected = false;

	public HostModel(HostView hv, MasterView m) {
		this.hv = hv;
		this.m = m;
		p = new PlayerTank(new Point(-100, -100), new Level1());
		e = new EnemyTank(new Point(-100, -100), new Level1());
		try {
			host = new ServerSocket(4000);
			client = host.accept();
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
			connected = true;
			sendObject("Welcome!");
			Thread receiver = new ReceivingThread();
			receiver.start();
			hv.hm=this;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setPlayer(PlayerTank p) {
		this.p = p;
	}
	public void setEnemy(EnemyTank e) {
		this.e = e;
	}

	public class ReceivingThread extends Thread {
		public void run() {
			while (connected) {
				try {
					Object o = in.readObject();
					if (o instanceof String) {
						String s = (String) o;
						if (s.equals("ready")) {
							hv.start.setEnabled(true);
						}
						if (s.equals("up")) {
							e.moveUp();
							notifyObservers(e);
							setChanged();
							
						}
						if (s.equals("down")) {
							e.moveDown();
							notifyObservers(e);
							setChanged();
						}
						if (s.equals("left")) {
							e.moveLeft();
							notifyObservers(e);
							setChanged();
						}
						if (s.equals("right")) {
							p.moveRight();
							notifyObservers(e);
							setChanged();
						}
					}
					if (o instanceof SimpleShoot) {
						SimpleShoot ss = (SimpleShoot) o;
						e.shoot(new Point(ss.c, ss.r), ss.x, ss.y);
						notifyObservers();
						setChanged();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public void sendObject(Object o) {
		try {
			out.writeObject(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

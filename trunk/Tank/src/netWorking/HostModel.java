package netWorking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import battleTank.EnemyTank;
import battleTank.Level1;
import battleTank.PlayerTank;
import battleTank.Point;


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
	private HostModel thisModel;

	public HostView hv;
	public MasterView m;
	// dont forget to set the p after changing to network tank view.
	public PlayerTank p;
	public EnemyTank e;
	private ReceivingThread receiver;
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
			receiver = new ReceivingThread();
			receiver.start();
			hv.hm=this;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thisModel = this;
	}
	public void setHostView(HostView hv) {
		this.hv = hv;
	}
	
	public void setPlayer(PlayerTank p) {
		this.p = p;
	}
	public void setEnemy(EnemyTank e) {
		this.e = e;
	}
	public void setThisModel() {
		thisModel = this;
	}
	public void closeConnection() {
		try {
			sendObject("close");
			connected = false;
			receiver.interrupt();
			client.close();
			host.close();
			
	
		} catch(Exception e) {
			
		}
	}

	private class ReceivingThread extends Thread {
		public void run() {
			while (connected) {
				try {
					Object o = in.readObject();
					if (o instanceof String) {
						String s = (String) o;
						if (s.equals("ready")) {
							
							hv.start.setEnabled(true);
						}
						if(s.equals("close")) {
							closeConnection();
						}
						if(s.equals("won")) {
							setThisModel();
							m.changeView(Views.HOST, thisModel);
						}
						if(s.equals("lost")) {
							setThisModel();
							m.changeView(Views.HOST, thisModel);
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
				} catch (SocketException e) {
					System.out.println("Socket Exception, connection lost");
					m.changeView(Views.TITLE, null);
					break;
				}
				catch (IOException e) {
					JOptionPane.showMessageDialog(m, "Remote Client disconnected");
					m.changeView(Views.LAN, null);
					break;
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(m, "Remote Client disconnected");
					m.changeView(Views.LAN, null);
					break;
				} 

			}
		}
	}

	public void sendObject(Object o) {
		try {
			out.writeObject(o);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(m, "Remote Client disconnected");
			m.changeView(Views.LAN, null);
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

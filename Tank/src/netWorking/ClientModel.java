package netWorking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import undecided.EnemyTank;
import undecided.Level1;
import undecided.PlayerTank;
import undecided.Point;

import View.ClientView;
import View.MasterView;
import View.Views;

public class ClientModel extends Observable implements Observer {
	String ip;
	public Socket socket = null;
	public ObjectOutputStream out;
	public ObjectInputStream in;
	private Object unKnown;
	private MasterView m;
	// dont forget to set the p after changing to network tank view.
	public PlayerTank p;
	public EnemyTank e;
	public boolean connected = false;
	private ClientModel thisModel;
	private ClientView cv;
	/**
	 * 
	 * @param m
	 *            MasterView
	 * @param o
	 *            ip address this class constructor takes in m and o and creats
	 *            a new socket it then call listenStart() to start listening to
	 *            socket. getting input stream if not null
	 */
	public ClientModel(ClientView cv,MasterView m, Object o) {
		ip = "127.0.0.1";
		this.m = m;
		p = new PlayerTank(new Point(-100, -100), new Level1());
		e = new EnemyTank(new Point(-100, -100), new Level1());
		if (o instanceof String) {
			ip = (String) o;
		}
		try {
			System.out.println("Trying to connect to remote host on " + ip);
			socket = new Socket(ip, 4000);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			connected = true;
			Thread listen = new GameListener(this);
			cv.cm=this;
			listen.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thisModel = this;

	}
	
	public void setPlayer(PlayerTank p) {
		this.p = p;
	}
	public void setEnemy(EnemyTank e) {
		this.e = e;
	}




	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}


	/**
	 * 
	 * this class will create a thread, tryint to read the input, and interpret
	 * the messages.
	 * 
	 * 
	 */
	private class GameListener extends Thread implements Runnable, Observer {
		private ClientModel cm;
		private boolean first = true;

		public GameListener(ClientModel cm) {
			this.cm = cm;
		}

		@Override
		public void run() {

			// while (in == null) {
			// try {
			// in = new ObjectInputStream(socket.getInputStream());
			// } catch (IOException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
			// }
			while (connected) {

				System.out.println("Trying to read");
				try {
					Object o = in.readObject();
					System.out.println(o);
					if (o instanceof String) {
						String s = (String)o;
						if (s.equals("Welcome!")) {
							if (first) {
								JOptionPane.showMessageDialog(null,
										"Connected to host!");
								first = false;
							}
						}  if (s.equals("start")) {
							JOptionPane.showMessageDialog(null,
									"Changing to game view");
							m.changeView(Views.NETWORKTANKVIEW, thisModel);
						}  if (s.equals("up")) {
							e.moveUp();
							notifyObservers(p);
							setChanged();
						}  if (s.equals("down")) {
							e.moveDown();
							notifyObservers(p);
							setChanged();
						}  if (s.equals("left")) {
							e.moveLeft();
							notifyObservers(p);
							setChanged();
						}  if (s.equals("right")) {
							e.moveRight();
							notifyObservers(p);
							setChanged();
						}
					} if(o instanceof SimpleShoot) {
						SimpleShoot ss = (SimpleShoot) o;
						e.shoot(new Point(ss.c, ss.r), ss.x, ss.y);

					}
				}

				catch (SocketException e) {
					System.out.println("Socket Exception, connection lost");
					m.changeView(Views.TITLE, null);
				}

				catch (IOException e) {
					System.out.println("IOException");
					e.printStackTrace();
					break;

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					m.changeView(Views.TITLE, null);

				}

			}

		}

		@Override
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * sending object o to the remote host
	 * 
	 * @param o
	 */

	public void sendObject(Object o) {
		try {
			out.writeObject(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}

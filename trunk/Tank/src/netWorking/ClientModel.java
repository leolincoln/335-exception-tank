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

import undecided.PlayerTank;
import undecided.Point;

import View.MasterView;
import View.Views;

public class ClientModel extends Observable implements Observer {
	String ip;
	public Socket socket = null;
	public ObjectOutputStream out;
	public ObjectInputStream in;
	private Object unKnown;
	private MasterView m;
	//dont forget to set the p after changing to network tank view. 
	public PlayerTank p;

	public ClientModel(MasterView m, Object o) {
		ip = "127.0.0.1";
		this.m = m;
		if (o instanceof String) {
			ip = (String) o;
		}
		try {
			System.out.println("Trying to connect to remote host on " + ip);
			socket = new Socket(ip, 4000);

			listenStart();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connected();

	}

	public void listenStart() {
		Thread listen = new GameListener(this);
		listen.start();
	}

	public void ready() {
		Thread send = new ReadySender();
		send.start();
	}

	public void connected() {
		Thread connected = new ConnectSender();
		connected.start();
		connected.interrupt();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	private class ConnectSender extends Thread implements Runnable {
		public void run() {
			try {
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(new String("connected"));
				System.out.println("connected sent to host");
				this.interrupt();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class ReadySender extends Thread implements Runnable {
		public void run() {
			try {
				out.writeObject(new String("ready"));
				System.out.println("Ready sent to host");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class GameListener extends Thread implements Runnable, Observer {
		private ClientModel cm;
		private boolean first = true;

		public GameListener(ClientModel cm) {
			this.cm = cm;
		}

		@Override
		public void run() {

			while (true) {
				System.out.println("Trying to read");
				try {
					in = null;
					in = new ObjectInputStream(socket.getInputStream());
					if (in != null)
						unKnown = in.readObject();
					System.out.println(in);
				}
				catch(SocketException e){
					System.out.println("Socket Exception, connection lost");
					m.changeView(Views.TITLE, null);
				}

				catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					m.changeView(Views.TITLE, null);
					interrupt();

				}

				if (unKnown != null) {
					if (unKnown instanceof String) {
						if (unKnown.equals("Welcome!")) {
							if (first) {
								JOptionPane.showMessageDialog(null,
										"Connected to host!");
								first = false;

							}
						} else if (unKnown.equals("start")) {
							JOptionPane.showMessageDialog(null,
									"Changing to game view");
							cm.startGame();
						} else if (unKnown.equals("up")) {
							p.moveUp();
						} else if (unKnown.equals("down")) {
							p.moveDown();
						} else if (unKnown.equals("left")) {
							p.moveLeft();
						} else if (unKnown.equals("right")) {
							p.moveRight();
						}
					}
					else
					{
						SimpleShoot ss = (SimpleShoot) unKnown;
						p.shoot(new Point(ss.c,ss.r), ss.x, ss.y);
					}
				}
				try {
					sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block

					System.out.println("interrupted thread listen");
				}
			}

		}

		@Override
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub

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

	public void startGame() {
		m.changeView(Views.NETWORKTANKVIEW, this);
	}

}

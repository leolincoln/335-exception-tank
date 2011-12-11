package netWorking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import undecided.PlayerTank;
import undecided.Point;

import View.HostView;
import View.MasterView;
import View.Views;

public class HostModel {

	private ServerSocket host;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public static ArrayList<String> ipList;
	private Socket client;

	public HostView hv;
	public MasterView m;
	// dont forget to set the p after changing to network tank view.
	public PlayerTank p;

	public HostModel(HostView hv, MasterView m) {
		this.hv = hv;
		this.m = m;

		ipList = new ArrayList<String>();
		try {
			setUpHost();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	/**
	 * 
	 * @throws IOException
	 * @name setUpHost this method sets up the host to serversocket 4000. then
	 *       prints connecting.
	 */
	public void setUpHost() throws IOException {
		host = new ServerSocket(4000);
		String ip = InetAddress.getLocalHost().toString();
		ipList.add(ip);
		System.out.println("Connecting");
		JOptionPane.showMessageDialog(null, "connecting");
	}

	/**
	 * this method sets the connection to start, call Thread connect and Thread
	 * waitforConnection
	 */
	public void connectionStart() {

		Thread connect = new Connect();
		connect.start();

		Thread connection = new WaitForConnection();
		connection.start();
	}

	/**
	 * 
	 * this class will try to read the client from serverSocket. eventually the
	 * client socket will be determined.
	 * 
	 */
	private class Connect extends Thread implements Runnable {
		public void run() {

			while (client == null) {
				try {
					System.out.println("trying to read the connection");

					client = host.accept();

					if (client != null) {
						System.out.println(client);

					}

				} catch (IOException e) {
				}
			}
		}
	}

	public void sendObject(Object o) {
		if (out == null) {
			try {
				out = new ObjectOutputStream(client.getOutputStream());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			try {
				out.writeObject(o);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	/**
	 * this class reads all incoming messages. it will try reading from the
	 * client socket. after reading the first string, its going to call send
	 * object.
	 * 
	 */
	private class WaitForConnection extends Thread implements Runnable {
		boolean first = true;

		public void run() {
			in = null;
			while (in == null) {
				try {
					if (client != null)
						in = new ObjectInputStream(client.getInputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			while (true) {
				System.out.println("waiting still waiting");
				if (client != null)
					try {
						System.out.println("Succesfully connected");
						System.out.println(client);

						if (first) {
							sendObject("Welcome!");
							first = false;
						}

						Object command = in.readObject();
						if (command instanceof String) {
							if (command.equals("connected")) {
								JOptionPane.showMessageDialog(null,
										"Successfully connected to "
												+ client.getInetAddress()
														.toString());
							} else if (command.equals("ready")) {
								System.out.println("ready received");
								hv.start.setEnabled(true);
							} else if (command.equals("up")) {
								p.moveUp();
							} else if (command.equals("down")) {
								p.moveDown();
							} else if (command.equals("left")) {
								p.moveLeft();
							} else if (command.equals("right")) {
								p.moveRight();
							} else if (command.equals("quit")) {
								out.close();
								in.close();
								client.close();
								break;
							}
						} else if (command instanceof SimpleShoot) {
							SimpleShoot ss = (SimpleShoot) command;
							p.shoot(new Point(ss.c, ss.r), ss.x, ss.y);
						}

					} catch (IOException ioe) {
						ioe.printStackTrace();
						m.changeView(Views.TITLE, null);
						interrupt();

					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}

	
	public void clientStart() throws IOException {
		out.writeObject(new String("start"));
	}
}

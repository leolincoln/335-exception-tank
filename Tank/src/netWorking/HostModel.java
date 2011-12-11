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

	public void setUpHost() throws IOException {
		host = new ServerSocket(4000);
		String ip = InetAddress.getLocalHost().toString();
		ipList.add(ip);
		System.out.println("Connecting");
		JOptionPane.showMessageDialog(null, "connecting");
	}

	public void connectionStart() {

		Thread connect = new Connect();
		connect.start();

		Thread connection = new WaitForConnection();
		connection.start();
	}

	private class Connect extends Thread implements Runnable {
		public void run() {
			try {
				System.out.println("trying to read the connection");

				client = host.accept();

				if (client != null) {
					System.out.println(client);

					this.interrupt();
				}

			} catch (IOException e) {
			}
		}
	}

	public void sendObject(Object o) {
		try {
			out = new ObjectOutputStream(client.getOutputStream());
			out.writeObject(o);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private class WaitForConnection extends Thread implements Runnable {
		boolean first = true;

		public void run() {
			while (true) {
				System.out.println("waiting still waiting");
				if (client != null)
					try {
						System.out.println("Succesfully connected");
						System.out.println(client);
						in = new ObjectInputStream(client.getInputStream());
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
							}
						} else {
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

	public void welcomeStart() {

		Thread welcome = new WelcomeMessage();
		welcome.start();
	}

	private class WelcomeMessage extends Thread implements Runnable {

		public void run() {
			try {
				out = new ObjectOutputStream(client.getOutputStream());
				out.writeObject(new String("Welcome!"));
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void clientStart() throws IOException {
		out = new ObjectOutputStream(client.getOutputStream());
		out.writeObject(new String("start"));
		out.close();

	}
}

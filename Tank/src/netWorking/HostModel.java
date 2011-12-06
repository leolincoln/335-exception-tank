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

import View.HostView;
import View.MasterView;

public class HostModel {

	private ServerSocket host;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public static ArrayList<String> ipList;
	private Socket client;
	private String ip;
	public HostView hv;
	public MasterView m;

	public HostModel(HostView hv, MasterView m) {
		this.hv = hv;
		this.m = m;
		ip = "";
		ipList = new ArrayList<String>();

		try {
			setUpHost();
		}

		catch (IOException ioe) {
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

	private class WaitForConnection extends Thread implements Runnable {
		boolean first = true;

		public void run() {
			while (true) {
				System.out.println("waiting still waiting");
				if (client != null)
					try {
						System.out.println("Succesfully connected");
						System.out.println(client);
						out = new ObjectOutputStream(client.getOutputStream());
						in = new ObjectInputStream(client.getInputStream());
						if (first) {
							out.writeObject(new String("Welcome!"));
							first = false;
						}
						Object command = in.readObject();
						if (command.equals("connected")) {
							JOptionPane.showMessageDialog(null,
									"Successfully connected to "
											+ client.getInetAddress()
													.toString());
						} else if (command.equals("ready")) {
							System.out.println("ready received");
							hv.start.setEnabled(true);
						}

					} catch (IOException ioe) {
						ioe.printStackTrace();

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

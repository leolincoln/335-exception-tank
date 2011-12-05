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

public class HostModel implements Runnable{
	
	private ServerSocket host;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public static ArrayList<String> ipList;
	private Socket client;
	private String ip;
	
	public HostModel() {
		
		ip = "";
		ipList = new ArrayList<String>();
		
		try{
			setUpHost();
		}
		
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		
	}
	
	public void setUpHost() throws IOException{
		host = new ServerSocket(4000);
		String ip = InetAddress.getLocalHost().toString();
		ipList.add(ip);
		JOptionPane.showMessageDialog(null, "connecting");

}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			setUpHost();
			client = host.accept();
			System.out.println("Succesfully connected");
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
		}
		
		catch(IOException ioe) {
			ioe.printStackTrace();
			
		}
	}
	
	public void broadcastStart() {
		
		Thread broadcast = new BroadcastSender();
		broadcast.start();
	}
	
	private class BroadcastSender extends Thread implements Runnable {
		
		public void run(){
			try{
				out = new ObjectOutputStream(client.getOutputStream());
				out.writeObject(new String("Accepting connections..."));
				out.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		
		
	}
}

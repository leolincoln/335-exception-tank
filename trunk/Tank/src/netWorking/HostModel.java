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

public class HostModel implements Runnable, Observer {
	
	private ServerSocket host;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public static ArrayList<String> ipList;
	private Socket client;
	
	public HostModel() {
		
		
	}
	
	public void setUpHost() throws IOException{
		host = new ServerSocket(4000);
		String ip = InetAddress.getLocalHost().toString();
		ipList.add(ip);
		JOptionPane.showMessageDialog(null, "connecting");

}

	@Override
	public void update(Observable o, Object arg) {
		
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			setUpHost();
			client = 
		}
		
		catch(IOException ioe) {
			
		}
		
	}
}

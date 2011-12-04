package netWorking;

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
	private ArrayList<String> ipList;
	
	public HostModel() {
		
	setUpHost();

		
	}
	
	public void setUpHost() throws IOException{
		host = new ServerSocket(4000);
		JOptionPane.showMessageDialog(null, "connecting");
		host.setSoTimeout(60000);
		out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject(m);

	

}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

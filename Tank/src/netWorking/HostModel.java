package netWorking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class HostModel {
	
	private Socket host;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public HostModel() {
		
	host = new Socket(InetAddress.getByName(ip), 4000);
		
	}
	
	public void runHost() {
//		
//		try {
//			connectToServer();
//			getStreams();
//			processConnection();
//		}
//	}
//	
//	private void connectToServer() throws IOException {
//	
//	}

	

}

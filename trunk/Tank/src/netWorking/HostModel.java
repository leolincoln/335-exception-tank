package netWorking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class HostModel extends Observable implements Runnable,  Observer {
	
	private Socket host;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public HostModel() {
		
	
		
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

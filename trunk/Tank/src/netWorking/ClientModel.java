package netWorking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

public class ClientModel  extends Observable implements Observer{
String ip;
public Socket socket;
public ObjectOutputStream out;
public ObjectInputStream in;
	public ClientModel(String ip){
		try {
			socket = new Socket(ip,4000);
			GameStart gameStart = new GameStart();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	private class GameStart implements Runnable{

		@Override
		public void run() {
			try{
				
			}
			
		}
		
	}
	

}

package netWorking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeoutException;

import javax.swing.JOptionPane;

public class ClientModel  extends Observable implements Observer{
String ip;
public Socket socket;
public ObjectOutputStream out;
public ObjectInputStream in;
	public ClientModel(String ip){
		try {
			socket = new Socket(ip,4000);
			Thread listen = new GameListener(socket);
			listen.run();
			
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
	private class GameSender extends Thread implements Runnable{

		@Override
		public void run() {
			
		}
		
	}
	private class GameListener extends Thread implements Runnable, Observer {
		private Socket socket=null;
		private ServerSocket serverSocket=null;
		Object unKnown;
		public GameListener(Object o){
			if(o instanceof Socket) this.socket=(Socket)o;
			if(o instanceof ServerSocket) this.serverSocket=(ServerSocket)o;
		}

		@Override
		public void run() {
			while (true){
			try{
				socket.setSoTimeout(50);
				in = new ObjectInputStream(socket.getInputStream());
				unKnown = in.readObject();	
			}

			catch(IOException e){
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("interrupted thread listen");
			}
			}
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}

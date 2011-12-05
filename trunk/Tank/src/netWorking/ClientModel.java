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

import View.MasterView;
import View.Views;

public class ClientModel  extends Observable implements Observer{
String ip;
public Socket socket=null;
public ObjectOutputStream out;
public ObjectInputStream in;
private Object unKnown;
private MasterView m;
	public ClientModel(MasterView m, Object o){
		this.m=m;
		if(o instanceof String){
			ip = (String)o;
		}
		try {
			socket = new Socket(ip,4000);
			socket.setSoTimeout(50);
			listenStart();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connected();
		
	}
	public void listenStart(){
		Thread listen = new GameListener(this);
		listen.start();
	}
	public void ready(){
		Thread send = new ReadySender();
		send.start();
	}
	public void connected(){
		Thread connected = new ConnectSender();
		connected.start();
		connected.interrupt();
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private class ConnectSender extends Thread implements Runnable{
		public void run(){
			try{
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(new String("connected"));
				out.close();
				this.interrupt();
			}
		catch(IOException e){
			e.printStackTrace();
		}
		}
	}
	
	private class ReadySender extends Thread implements Runnable{
		public void run(){
			try{
				out = new ObjectOutputStream(socket.getOutputStream());
				out.writeObject(new String("ready"));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
	private class GameListener extends Thread implements Runnable, Observer {
		private ClientModel cm;
		public GameListener(ClientModel cm){
			this.cm=cm;
		}
		@Override
		public void run() {
			while (true){
			try{
				in = new ObjectInputStream(socket.getInputStream());
				unKnown = in.readObject();	
			}

			catch(IOException e){
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(unKnown !=null){
				if(unKnown instanceof String){
					if (unKnown.equals("start")){
						cm.startGame();
					}
				}
				else if(true){
					
				}
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


	public void startGame() {
		m.changeView(Views.NETWORKTANKVIEW, this);	
	}



	

}

package netWorking;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class ServerModel implements Runnable, Observer {

	private ObjectOutputStream out = null;
	private Socket socket;
	private String ip;
	private NetWorkModel m;

	public ServerModel(String ip, NetWorkModel m) {
		this.ip = ip;
		this.m = m;
	}

	@Override
	public void run() {
		try {
			connect(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connect(String ip) throws IOException {
		socket = new Socket(ip, 4000);
		JOptionPane.showMessageDialog(null, "connecting");
		socket.setSoTimeout(60000);
		out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject(m);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.m = (NetWorkModel) arg1;
	}
}

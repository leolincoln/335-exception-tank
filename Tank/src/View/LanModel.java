package View;

import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import battleTank.PlayerTank;

/**
 * this class will be monitoring the lanView. 
 *
 * @author TeamException
 * 
 */
public class LanModel  {
	ArrayList<String> hostList;
	private ServerSocket Socket;
	private MasterView m;
	ObjectInputStream in;

	private DefaultListModel hosts;

	public LanModel(MasterView m, PlayerTank p) {
		this.m = m;
		hostList = new ArrayList<String>();
		System.out.println("hostList set to null");

	}

	private byte[] getLocalAddr() {
		byte[] ipAddr = new byte[] { (byte) 127, 0, 0, 1 };
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ipAddr = addr.getAddress();
			String hostname = addr.getHostName();
		} catch (UnknownHostException e) {

		}
		return ipAddr;
	}
}

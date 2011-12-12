package View;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import netWorking.ClientModel;

public class ClientView extends MasterViewPanel implements Observer {
	private JPanel host, client, hostNamePane, clientNamePane, hostTankPane,
			clientTankPane;
	private JLabel hostTop, clientTop, hostName, clientName, hostTankType,
			clientTankType, waitForClient;
	public JButton ready, start;
	public JTextField hostNameText, clientNameText;
	public String ip;
	public ClientModel cm;
	public boolean connectivity;
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public ClientView(MasterView m,Object o) {
		super(m);
		connectivity = true;
		if(!(o instanceof ClientModel))cm = new ClientModel(this, m,o);
		else {
			cm = (ClientModel)o;
			cm.setClientView(this);
		}
		buildMain();
		buildHostPanel();
		buildClientPanel();
		if(!connectivity) {
			this.removeAll();
			this.add(new LanView(m));
		}
		// TODO Auto-generated constructor stub
	}

	public void buildMain() {
		this.setLayout(new GridLayout(1, 2));
		this.setVisible(true);
	}

	public void buildHostPanel() {
		host = new JPanel(new GridLayout(4, 1));
		hostTop = new JLabel("HOST");
		hostNamePane = new JPanel(new GridLayout(1, 2));
		hostName = new JLabel("HostName: ");
		hostNameText = new JTextField();
		hostNameText.setText("Enter Name here");
		hostNamePane.add(hostName);
		hostNamePane.add(hostNameText);

		hostTankPane = new JPanel(new GridLayout(1, 2));
		hostTankType = new JLabel("Host Tank Type: ");
		hostTankPane.add(hostTankType);

		start = new JButton("Start");
		start.addActionListener(new startListener());
		start.setEnabled(false);

		host.add(hostTop);
		host.add(hostNamePane);
		host.add(hostTankPane);
		host.add(start);

		this.add(host);
	}

	public void buildClientPanel() {
		client = new JPanel(new GridLayout(4, 1));
		clientTop = new JLabel("CLIENT");
		clientNamePane = new JPanel(new GridLayout(1, 2));
		clientName = new JLabel("clientName: ");
		
		clientNameText = new JTextField();
		clientNameText.setText("Enter Name here");
		clientNameText.setSize(10, 10);
		
		clientNamePane.add(clientName);
		clientNamePane.add(clientNameText);

		clientTankPane = new JPanel(new GridLayout(1, 2));
		clientTankType = new JLabel("Client Tank Type: ");
		clientTankPane.add(clientTankType);
		ready = new JButton("Ready!");
		ready.addActionListener(new readyListener());
		ready.setEnabled(true);

		client.add(clientTop);
		client.add(clientNamePane);
		client.add(clientTankPane);
		client.add(ready);
		this.add(client);
	}

	private class startListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		}
	}

	private class readyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ready.setEnabled(false);
			cm.sendObject("ready");
		
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
}

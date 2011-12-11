package View;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import netWorking.HostModel;

public class HostView extends MasterViewPanel implements Observer {
	private JPanel host, client, hostNamePane, clientNamePane, hostTankPane,
			clientTankPane;
	private JLabel hostTop, clientTop, hostName, clientName, hostTankType,
			clientTankType, waitForClient;
	public JButton ready, start;
	public JTextField hostNameText, clientNameText;
	public HostModel hm;
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public HostView(MasterView m) {
		super(m);
		buildMain();
		buildHostPanel();
		buildClientPanel();
		this.setVisible(true);
		HostModel hm = new HostModel(this, m);
		
		hm.connectionStart();
		System.out.println(hm);
		// TODO Auto-generated constructor stub
	}

	public void buildMain() {
		this.setLayout(new GridLayout(1, 2));
		
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
		ready.setEnabled(false);

		client.add(clientTop);
		client.add(clientNamePane);
		client.add(clientTankPane);
		client.add(ready);
		this.add(client);
	}

	private class startListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(hm);
				hm.clientStart();
			m.changeView(Views.NETWORKTANKVIEW,hm);
		}
	}

	private class readyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}
}

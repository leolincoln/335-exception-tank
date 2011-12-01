package View;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HostView extends MasterViewPanel {
private JPanel host, client,hostNamePane,clientNamePane,hostTankPane,clientTankPane;
private JLabel hostTop, clientTop, hostName, clientName, hostTankType, clientTankType, waitForClient;
public JButton ready, start;
public JTextField hostNameText, clientNameText;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HostView(MasterView m) {
		super(m);
		buildMain();
		buildHostPanel();
		// TODO Auto-generated constructor stub
	}
	public void buildMain(){
		this.setLayout(new GridLayout(1,2));
		this.setVisible(true);
	}
	public void buildHostPanel(){
		host = new JPanel(new GridLayout(4,1));
		hostTop = new JLabel("HOST");
		hostNamePane = new JPanel(new FlowLayout());
		hostName = new JLabel("HostName: ");
		hostNameText = new JTextField();
		hostNameText.setText("enter your name here host");
		hostNamePane.add(hostName);
		hostNamePane.add(hostNameText);
		
		hostTankPane = new JPanel(new GridLayout(1,2));
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
	
	public void buildClientPanel(){
		client = new JPanel(new GridLayout(4,1));
		clientTop = new JLabel("HOST");
		clientNamePane = new JPanel(new FlowLayout());
		clientName = new JLabel("HostName: ");
		clientNameText = new JTextField();
		clientNameText.setText("enter your name here host");
		clientNamePane.add(hostName);
		clientNamePane.add(hostNameText);
		
		clientTankPane = new JPanel(new GridLayout(1,2));
		clientTankType = new JLabel("Host Tank Type: ");
		clientTankPane.add(hostTankType);
		
		this.add(client);
		ready = new JButton("Ready!");
		ready.addActionListener(new readyListener());
		ready.setEnabled(true);
	}
	
private class startListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
private class readyListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
}

package View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HostView extends MasterViewPanel {
private JPanel host, client,hostNamePane,clientNamePane,hostTankPane,clientTankPane;
private JLabel hostName, clientName, hostTankType, clientTankType, waitForClient;
public JButton ready, start;
public JTextField hostNameText, clientNameText;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HostView(MasterView m) {
		super(m);
		buildMain();
		// TODO Auto-generated constructor stub
	}
	public void buildMain(){
		this.setLayout(new GridLayout(1,2));
		this.setVisible(true);
	}
	public void buildHostPanel(){
		host = new JPanel(new GridLayout(4,1));
		hostName = new JLabel("HostName: ");
		hostTankType = new JLabel("Host Tank Type: ");
		start = new JButton("Start");
		start.setEnabled(false);
	}
	
	public void buildClientPanel(){
		client = new JPanel(new GridLayout(4,1));
		client = new JPanel(new GridLayout(4,1));
		clientName = new JLabel("HostName: ");
		clientTankType = new JLabel("Host Tank Type: ");
		ready= new JButton("Ready!");
	}
	

}

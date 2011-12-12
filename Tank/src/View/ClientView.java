package View;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import netWorking.ClientModel;

/**
 * Client view is the view client receive when click join and succefully joined
 * a host.
 * 
 * @author TeamException
 * @extends MasterViewPanel
 * @implements Observer
 * @see MasterViewPanel,Observer
 * 
 */
public class ClientView extends MasterViewPanel implements Observer {
	private JPanel host, client, hostNamePane, clientNamePane, hostTankPane,
			clientTankPane;
	private JLabel hostTop, clientTop, hostName, clientName, hostTankType,
			clientTankType, waitForClient;
	public JButton ready, start;
	JLabel hostNameText;
	public JLabel clientNameText;
	public String ip;
	public ClientModel cm;
	public boolean connectivity;
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for clientview
	 * 
	 * @category Constructor
	 * @param m
	 *            MasterView that takes in
	 * @param o
	 *            the Object thats either ClientModel or HostModel
	 */
	public ClientView(MasterView m, Object o) {
		super(m);
		connectivity = true;
		if (!(o instanceof ClientModel))
			cm = new ClientModel(this, m, o);
		else {
			cm = (ClientModel) o;
			cm.setClientView(this);
		}
		buildMain();
		buildHostPanel();
		buildClientPanel();
		if (!connectivity) {
			this.removeAll();
			this.add(new LanView(m));
		}
		// TODO Auto-generated constructor stub
	}
/**
 * build the main panel;
 */
	public void buildMain() {
		this.setLayout(new GridLayout(1, 2));
		this.setVisible(true);
	}
	/**
	 * build host panel on the right
	 */
	public void buildHostPanel() {
		host = new JPanel(new GridLayout(4, 1));
		hostTop = new JLabel("HOST");
		hostNamePane = new JPanel(new GridLayout(1, 2));
		hostName = new JLabel("Host: ");
		hostNameText = new JLabel(new ImageIcon("images/tankEnemyEAST.png"));
	
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
	/**
	 * build client panel on the right
	 */
	public void buildClientPanel() {
		client = new JPanel(new GridLayout(4, 1));
		clientTop = new JLabel("CLIENT");
		clientNamePane = new JPanel(new GridLayout(1, 2));
		clientName = new JLabel("client: ");

		clientNameText = new JLabel(new ImageIcon("images/tankEAST.png"));

		clientNamePane.add(clientName);
		clientNamePane.add(clientNameText);

		clientTankPane = new JPanel(new GridLayout(1, 2));
		clientTankType = new JLabel("Client Tank : ");
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
	/**
	 * this private inner class is a listener for a JBUtton start. 
	 * @implement ActionListener
	 * @Catagary innerClass
	 * 
	 */
	private class startListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
		}
	}
	/**this private inner class is a listner for a jButton ready. 
	 * will send ready through client model. 
	 * @implements Actionlistener
	 * @Catagary innerClass
	 */
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

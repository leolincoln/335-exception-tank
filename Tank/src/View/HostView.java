package View;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import netWorking.HostModel;

/**
 * this class is the host view after pressing the button "hostgame" in the lan view
 * it makes sure that both players agree to go on and play an online game
 * and make use of its listening thread to call update when anything is sent from 
 * the lan. 
 * 
 * @author TeamException
 * @implements Observer
 * @extends masterViewPanel
 *@see MasterViewPanel
 */
public class HostView extends MasterViewPanel implements Observer {
	private JPanel host, client, hostNamePane, clientNamePane, hostTankPane,
			clientTankPane;
	private JLabel hostTop, clientTop, hostName, clientName, hostTankType,
			clientTankType, waitForClient;
	public JButton ready, start;
	public JLabel hostNameText, clientNameText;
	public HostModel hm;

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	public HostView(MasterView m,Object o) {
		
		super(m);
		buildMain();
		buildHostPanel();
		buildClientPanel();
		this.setVisible(true);
		if(!(o instanceof HostModel))  hm = new HostModel(this, m);
		else {
			hm = (HostModel)o;
			hm.setHostView(this);
			System.out.println("hm refered?");
		}
		System.out.println(hm);
		// TODO Auto-generated constructor stub
	}
	/**
	 * build the layout for main. 
	 */


	public void buildMain() {
		this.setLayout(new GridLayout(1, 2));

	}
	/**
	 * build the host panel for host. 
	 * disable the buttons so that the host cannot start the game
	 * unless the client agreed to do so.  
	 */


	public void buildHostPanel() {
		host = new JPanel(new GridLayout(4, 1));
		hostTop = new JLabel("HOST");
		hostNamePane = new JPanel(new GridLayout(1, 2));
		hostName = new JLabel("Host: ");
		hostNameText = new JLabel(new ImageIcon("images/tankEAST.png"));
		
		hostNamePane.add(hostName);
		hostNamePane.add(hostNameText);

		hostTankPane = new JPanel(new GridLayout(1, 2));
		hostTankType = new JLabel("Host Tank ");
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

		clientNameText = new JLabel(new ImageIcon("images/tankEnemyEAST.png"));
		
		

		clientNamePane.add(clientName);
		clientNamePane.add(clientNameText);

		clientTankPane = new JPanel(new GridLayout(1, 2));
		clientTankType = new JLabel("Client Tank Type: ");
		
		ready = new JButton("Ready!");
		ready.addActionListener(new readyListener());
		ready.setEnabled(false);

		client.add(clientTop);
		client.add(clientNamePane);
		client.add(clientTankPane);
		client.add(ready);
		this.add(client);
	}
	/**
	 * @category innerClass
	 * this class listens to the start and change the view to networkTankView. 
	 * WHere the actual game starts.  
	 */

	private class startListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(hm);

			m.changeView(Views.NETWORKTANKVIEW, hm);
			hm.sendObject("start");
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

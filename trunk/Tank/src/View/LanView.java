package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import netWorking.HostModel;



/**
 * 
 * @author Team Exception
 * 
 *         This JPanel extends MasterViewPanel and opens up the panel that
 *         allows the user to join a LAN 1 v. 1 match.
 * 
 */
public class LanView extends MasterViewPanel {
	private String ip="127.0.0.1";


	private static final long serialVersionUID = 1L;
	private JPanel northPane, buttonPane, centerPane;
	public JButton back, create, join,search;
	private JLabel hosts, ping;
	public JList hostList, pingList;
	private DefaultListModel hostsInfo;
	
	

	/**
	 * Class constructor
	 * 
	 * @param m
	 *            this is the masterview
	 */
	public LanView(MasterView m) {
		super(m);
		
		setupPane();
		buildButtons();
		
		this.setVisible(true);
	}

	/**
	 * This is the method that sets up the majority of the LAN panel including
	 * the layouts and labels.
	 */
 
	private void setupPane() {
		this.setLayout(new BorderLayout());
		// setup labels
		northPane = new JPanel(new GridLayout(1, 2));
		hosts = new JLabel("Hosts");
		northPane.add(hosts, 0);
		
		ping = new JLabel("ping");
		northPane.add(ping, 1);
		this.add(northPane, BorderLayout.NORTH);

		centerPane = new JPanel(new GridLayout(1, 2));
		
		/*if(HostModel.ipList!=null &&HostModel.ipList.size() > 0) {
		
		for(String ip : HostModel.ipList) {
			hostsInfo.addElement();	
		}*/
		hostsInfo = new DefaultListModel();
		hostsInfo.addElement("127.0.1.1");
		
		hostList = new JList(hostsInfo);
		hostList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		hostList.addListSelectionListener(new HostSelectionListener());
		
		
		/*else {
			hostList = new JList();
		}*/
		centerPane.add(hostList, 0);
		pingList = new JList();
		centerPane.add(pingList, 1);

		this.add(centerPane, BorderLayout.CENTER);
	}

	/**
	 * This method builds and adds all the buttons to the LAN view panel.
	 */
	private void buildButtons() {
		buttonPane = new JPanel(new FlowLayout());

		back = new JButton("back");
		back.addActionListener(new backListener());
		
		create = new JButton("HostGame");
		create.addActionListener(new createListener());
		
		search = new JButton("SearchForHosts");
		search.addActionListener(new searchListener());
		
		join = new JButton("JoinGame");
		join.addActionListener(new joinListener());

		buttonPane.add(back);
		buttonPane.add(search);
		buttonPane.add(create);
		buttonPane.add(join);
		this.add(buttonPane, BorderLayout.SOUTH);
	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private class implements action listener for when the back
	 *         button is pressed which will change views to that of the previous
	 *         view.
	 * 
	 */
	private class backListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.changeView(Views.PREVIOUS,null);
		}
	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private class implements action listener for when the window
	 *         is to be changed to that of the host.
	 * 
	 */
	private class createListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			m.changeView(Views.HOST,null);
			
		}
	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This method implements action listener and changes the view to
	 *         that of the player that is waiting for the host or joining the
	 *         host.
	 */
	private class joinListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("IP sent is: " + ip);
			m.changeView(Views.CLIENT,ip);

		}

	}
	private class searchListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
		
		
		}
		
	}
	
	private class HostSelectionListener implements ListSelectionListener {
		
		public void valueChanged(ListSelectionEvent evt) {
			if(!evt.getValueIsAdjusting()) {
				JList hostList = (JList) evt.getSource();
			}
			
			Object[] selected = hostList.getSelectedValues();
			ip = (String) selected[0];
			System.out.println(ip);
			/*for(int i = 0; i < selected.length; i++) {
				Object sel = selected[i];
			}*/
		}
	}

}

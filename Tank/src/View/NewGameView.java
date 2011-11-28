package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class NewGameView extends MasterViewPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel northPane,buttonPane, centerPane;
	private JButton back, create, join;
	private JLabel hosts, ping;
	private JList hostList, pingList;

	public NewGameView(MasterView m) {
		super(m);
		setupPane();
		buildButtons();
		this.setVisible(true);

	}

	private void setupPane() {
		this.setLayout(new BorderLayout());
		// setup labels
		northPane = new JPanel(new GridLayout(1,2));
		hosts = new JLabel("Hosts");
		northPane.add(hosts, 0);

		ping = new JLabel("ping");
		northPane.add(ping, 1);
		this.add(northPane,BorderLayout.NORTH);
		
		centerPane = new JPanel(new GridLayout(1,2));
		hostList = new JList();
		centerPane.add(hostList, 0);
		pingList = new JList();
		centerPane.add(pingList, 1);

		this.add(centerPane, BorderLayout.CENTER);

	}

	private void buildButtons() {
		buttonPane = new JPanel(new FlowLayout());

		back = new JButton("back");
		back.addActionListener(new backListener());
		create = new JButton("Create");
		create.addActionListener(new createListener());
		join = new JButton("Join");
		join.addActionListener(new joinListener());

		buttonPane.add(back);
		buttonPane.add(create);
		buttonPane.add(join);
		this.add(buttonPane, BorderLayout.SOUTH);
	}
private class backListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		m.changeView(Views.PREVIOUS);
	}
	
}
private class createListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		m.changeView(Views.HOST);
		
	}
	
}
private class joinListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		m.changeView(Views.WAIT);
		
	}
	
}
}

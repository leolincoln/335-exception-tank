package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import undecided.TankView;

public class MasterView extends JFrame {
	JPanel body, currentPane, previousPane;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * 
	 */
	private static final long serialVersionUID = 5104052475766337179L;

	public MasterView() {
		buildFrame();
		buildMenu();
		setDefaultPane();
		this.setVisible(true);
	}

	public void buildFrame() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(50, 50);
		this.setSize(640, 400);

	}

	public void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem newGameMen = new JMenuItem("New Game");
		newGameMen.addActionListener(new newGameMenListener());
		file.add(newGameMen);
		JMenuItem exitMen = new JMenuItem("Exit");
		exitMen.addActionListener(new exitMenListener());
		file.add(exitMen);
		menuBar.add(file);
		this.add(menuBar, BorderLayout.NORTH);
	}

	public void setDefaultPane() {
		body = new JPanel();
		body.setLayout(new CardLayout());
		this.add(body, BorderLayout.CENTER);
		currentPane = new TitleView(this);
		body.add(currentPane, "TITLE");
		repaint();
	}

	public void changeView(Views v) {
		switch (v) {
		case LAN:
			previousPane = currentPane;
			currentPane = new LanView(this);
			body.removeAll();
			body.add(currentPane, "LAN");
			this.setLocation(50, 50);
			this.setSize(650,400);
			
			break;

		case PREVIOUS:
			JPanel temp = previousPane;
			previousPane = 
			currentPane = temp;
			body.removeAll();
			body.add(currentPane,v.name());
			if(currentPane instanceof TitleView){
				this.setLocation(50, 50);
				this.setSize(640, 400);
			}
			repaint();
			break;
		case NEWGAME:
			previousPane = currentPane;
			currentPane = new TankView(this);
		}

	}

	public static void main(String args[]) {
		new MasterView();

	}

	private class newGameMenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			changeView(Views.NEWGAME);
		}

	}

	private class exitMenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}

	}

}

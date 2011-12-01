package View;
//package there?
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
	JFrame m;
	/**
	 * 
	 */
	private static final long serialVersionUID = 5104052475766337179L;

	public MasterView() {

		buildFrame();
		buildMenu();
		setDefaultPane();
		this.setVisible(true);
		m = this;
	}

	public void buildFrame() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(640, 400);
		this.setBackground(Color.black);
		this.setLocationRelativeTo(null);
	}

	public void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");

		JMenuItem titleMen = new JMenuItem("MAINMENU");
		titleMen.addActionListener(new titleMenListener());
		file.add(titleMen);

		JMenuItem newGameMen = new JMenuItem("SINGLEGAME");
		newGameMen.addActionListener(new newGameMenListener());
		file.add(newGameMen);

		JMenuItem lanGameMen = new JMenuItem("LAN");
		lanGameMen.addActionListener(new lanGameMenListener());
		file.add(lanGameMen);

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
			this.setBackground(Color.white);
			previousPane = currentPane;
			currentPane = new LanView(this);
			body.removeAll();
			body.add(currentPane, "LAN");
			this.setLocationRelativeTo(null);
			this.setSize(650, 400);

			break;

		case PREVIOUS:

			JPanel temp = previousPane;
			previousPane = currentPane;
			currentPane = temp;
			if (currentPane instanceof LanView)
				changeView(Views.LAN);
			if (currentPane instanceof TitleView)
				changeView(Views.TITLE);
			if (currentPane instanceof TankView)
				changeView(Views.TANKVIEW);

			body.removeAll();
			body.add(currentPane, v.name());
			if (currentPane instanceof TitleView) {
				this.setLocation(50, 50);
				this.setSize(640, 400);
			}
			repaint();
			break;
		case TANKVIEW:
			this.setBackground(Color.black);
			this.setSize(1000, 750);
			previousPane = currentPane;
			currentPane = new TankView(this);
			body.removeAll();
			body.add(currentPane, "TANKVIEW");
			currentPane.requestFocus();
			this.setLocationRelativeTo(null);
//			this.setExtendedState(this.MAXIMIZED_BOTH);

			break;
		case TITLE:
			this.setBackground(Color.black);
			previousPane = currentPane;
			body.removeAll();
			currentPane = new TitleView(this);
			body.add(currentPane, "TITLE");
			this.setSize(640, 400);
			this.setLocationRelativeTo(null);
			repaint();
		}

	}

	public static void main(String args[]) {
		new MasterView();

	}

	private class newGameMenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.setSize(400, 600);
			m.setLocationRelativeTo(null);
			changeView(Views.TANKVIEW);

		}

	}

	private class exitMenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}

	}

	private class lanGameMenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.setSize(400, 600);
			m.setLocationRelativeTo(null);
			changeView(Views.LAN);
		}

	}

	private class titleMenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			m.setSize(400, 600);
			m.setLocationRelativeTo(null);
			changeView(Views.TITLE);

		}

	}
}

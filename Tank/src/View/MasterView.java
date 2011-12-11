package View;

//package there?
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import undecided.Level1;
import undecided.Level2;
import undecided.Level3;
import undecided.Level4;
import undecided.Level5;
import undecided.Map;
import undecided.TankView;

/**
 * 
 * @author Team Exception
 * 
 *         This class extends JFrame and is the view which will contain all the
 *         panels of all the views for this tanks game.
 * 
 */
public class MasterView extends JFrame {
	public static int currentLevel, playerLives;
	JPanel body, currentPane, previousPane;
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	JFrame m;
	private static final long serialVersionUID = 5104052475766337179L;

	/**
	 * Main thread
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		new MasterView();

	}

	/**
	 * Class constructor
	 */
	public MasterView() {
		currentLevel = 1;
		playerLives = 3;
		buildFrame();
		buildMenu();
		setDefaultPane();
		this.setVisible(true);
		m = this;
	}

	/**
	 * This is the method that builds and locates the frame of the tanks
	 * application which holds all the sub views.
	 */
	public void buildFrame() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(640, 400);
		this.setBackground(Color.black);
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method creates all the JMenu items and the JMenu bar for all the
	 * items. The items include main menu, single game, LAN, and exit.
	 */
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

	/**
	 * This method simply sets the default pane for the JFrame masterview which
	 * is titleview.
	 */
	public void setDefaultPane() {
		body = new JPanel();
		body.setLayout(new CardLayout());
		this.add(body, BorderLayout.CENTER);
		currentPane = new TitleView(this);
		body.add(currentPane, "TITLE");
		repaint();
	}

	/**
	 * This method controls all the calls to change view whether it be LAN,
	 * previous, tank, or title view.
	 * 
	 * @param v
	 *            this is the view that is to be switched to.
	 */
	public void changeView(Views v, Object o) {
		switch (v) {

		case CLIENT:
			this.setBackground(Color.white);
			previousPane = currentPane;
			currentPane = new ClientView(this, o);
			body.removeAll();
			body.add(currentPane, "CLIENT");
			this.setLocation(50, 50);
			this.setSize(600, 400);
			repaint();
			break;

		case HOST:
			this.setBackground(Color.white);
			System.out.println("HostviewWorking");
			previousPane = currentPane;
			currentPane = new HostView(this);
			body.removeAll();
			body.add(currentPane, "HOST");
			this.setLocationRelativeTo(null);
			this.setSize(600, 400);
			break;

		case NETWORKTANKVIEW:
			this.setBackground(Color.black);
			this.setSize(1000, 750);
			previousPane = currentPane;
			
			currentPane = new NetworkTankView(this,(Integer) o);
			System.out.println("changeview with o:"+o);
			body.removeAll();
			body.add(currentPane, "NETWORKTANKVIEW");
			currentPane.requestFocus();
			this.setLocationRelativeTo(null);
			// this.setExtendedState(this.MAXIMIZED_BOTH);

			break;

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
				changeView(Views.LAN, null);
			if (currentPane instanceof TitleView)
				changeView(Views.TITLE, null);
			if (currentPane instanceof TankView)
				changeView(Views.TANKVIEW, null);

			body.removeAll();
			body.add(currentPane, v.name());
			if (currentPane instanceof TitleView) {
				this.setLocation(50, 50);
				this.setSize(640, 400);
			}
			repaint();  
			break;
		case TANKVIEW:
			if (currentLevel == 1) {
				currentPane = new TankView(this, new Level1());
			}
			if (currentLevel == 2) {
				currentPane = new TankView(this, new Level2());
			}
			if (currentLevel == 3) {
				currentPane = new TankView(this, new Level3());
			}
			if (currentLevel == 4) {
				currentPane = new TankView(this, new Level4());
			}
			if (currentLevel == 5) {
				currentPane = new TankView(this, new Level5());
			}

			body.add(currentPane, "TANKVIEW");
			CardLayout c = (CardLayout) body.getLayout();
			c.show(body, "TANKVIEW");
			for (Component co : body.getComponents()) {
				if (co == currentPane) {
					currentPane.requestFocusInWindow();
				}
			}
			this.setBackground(Color.black);
			this.setSize(1200, 750);
			this.setLocationRelativeTo(null);
			if (currentLevel == 6) {
				playerLives = 3;
				currentLevel = 1;
				changeView(Views.TITLE, null);
			}
			// this.setExtendedState(this.MAXIMIZED_BOTH);

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
			break;
		}

	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private class creates the action listener that causes the
	 *         tanks view to show up when the user clicks on new game.
	 * 
	 */
	private class newGameMenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.setSize(400, 600);
			m.setLocationRelativeTo(null);
			changeView(Views.TANKVIEW, null);

		}

	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private class allows the user to close the application when
	 *         the exit button is pressed.
	 * 
	 */
	private class exitMenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}

	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private class allos the LAN view to show up when the LAN
	 *         button is pressed.
	 * 
	 */
	private class lanGameMenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.setSize(400, 600);
			m.setLocationRelativeTo(null);
			changeView(Views.LAN, null);
		}

	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private class allows the title view to show up when the
	 *         title view button is pressed
	 * 
	 */
	private class titleMenListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			m.setSize(400, 600);
			m.setLocationRelativeTo(null);
			changeView(Views.TITLE, null);

		}

	}
}

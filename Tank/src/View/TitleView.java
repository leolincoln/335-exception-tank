package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TitleView extends MasterViewPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel title;
	JButton newGame, option, exit;
	JPanel empty1 = new JPanel();
	JPanel empty2 = new JPanel();
	
	
	
	public TitleView(MasterView m) {
		super(m);
		setupPanel();
		setupLeft();
		setupTitle();
		setupRight();
		this.setVisible(true);
		// TODO Auto-generated constructor stub
	}

	public void setupPanel(){
		this.setLayout(new GridLayout(1,3));
	}
	public void setupLeft(){
		this.add(empty1);
	}
	public void setupTitle(){
		title=new JPanel(new GridLayout(3,1));
		newGame = new JButton("Start a new game");
		newGame.addActionListener(new newGameListener());
		option = new JButton("Options");
		option.addActionListener(new optionListener());
		exit = new JButton("Exit");
		exit.addActionListener(new exitListener());
		title.add(newGame);
		title.add(option);
		title.add(exit);
		this.add(title);
	}
	public void setupRight(){
		this.add(empty2);
	}
	private class newGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.changeView(Views.NEWGAME);
			
		}
		
	}
	private class optionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.changeView(Views.OPTION);
			
		}
		
	}
	
	private class exitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
			
		}
		
	}
}

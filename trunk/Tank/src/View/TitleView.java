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
	JButton newGame, lan,option, exit;
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
		title=new JPanel(new GridLayout(4,1));
		newGame = new JButton("SINGLEPLAYER");
		newGame.addActionListener(new newGameListener());
		lan = new JButton("LAN");
		lan.addActionListener(new lanListener());
		option = new JButton("OPTIONS");
		option.addActionListener(new optionListener());
		exit = new JButton("EXIT");
		exit.addActionListener(new exitListener());
		title.add(newGame);
		title.add(lan);
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
	private class lanListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.changeView(Views.NEWGAME);
			
		}
		
	}
}

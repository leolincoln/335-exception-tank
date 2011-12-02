package View;

import java.io.Serializable;

public class PlayerInfo implements Serializable{
	String playerName, tankColor;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PlayerInfo(String i, String j){
	this.playerName = i;
	this.tankColor = j;
		
	}
	
	public String getPlayerName(){
		return playerName;
	}
	public String getTankColor(){
		return tankColor;
	}
	
	
	

}

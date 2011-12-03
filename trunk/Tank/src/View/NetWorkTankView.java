package View;

import java.util.Observable;
import java.util.Observer;

import map.Map;

public class NetWorkTankView extends MasterViewPanel implements Observer{
private int mapNumber;
private Map map;

	public NetWorkTankView(MasterView m, int num) {
		super(m);
		this.mapNumber = num;
//		readMap();
//		buildMap();
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}

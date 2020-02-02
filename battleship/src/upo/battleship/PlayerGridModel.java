package upo.battleship;

import java.util.Observable;

public class PlayerGridModel extends Observable{
	
	private int value;

	public PlayerGridModel(int value) {
		super();
		this.setValue(value);
	}

	private void setValue(int value) {
		// TODO Auto-generated method stub
		
	}

	public int getValue() {
		return value;
	}

	

	

}

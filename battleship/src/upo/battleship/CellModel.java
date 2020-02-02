package upo.battleship;

import java.util.Observable;

public class CellModel extends Observable {
	
	public static final int GRIGIO = 3;
	public static final int AZZURRO = 2;
	public static final int GIALLO = 1;
	public static final int ROSSO = 0;
	
	private int value;
	
	public CellModel(int value) {
		super();
		this.setValue(value);
	}


	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		if(value < 0 || value > 3) throw new IllegalArgumentException("Value "+value+" not allowed");
		this.value = value;
		this.setChanged();
		this.notifyObservers(value);
	}

}

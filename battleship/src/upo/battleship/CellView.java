package upo.battleship;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import upo.battleship.CellModel;

public class CellView extends JPanel implements Observer{
	
	public CellView (CellModel m, CellController c) {
		m.addObserver(this);
		this.setSize(100, 100);
		this.updateView(m.getValue());
	
	
	/*
	 * 
	 * AZIONE CHE CAUSERA' IL CAMBIO DI COLORE DELLA CELLA
	 */
	
	String[] vals = {"ROSSO","GIALLO","AZZURRO","GRIGIO"};
	JComboBox<String> sel = new JComboBox<String>(vals);
	this.add(sel);
	sel.addActionListener(c);
	
	this.setVisible(true);
}


	@Override
	public void update(Observable arg0, Object arg1) {
		Integer colore = (Integer) arg1;
		this.updateView(colore);
	}
	
	private void updateView(Integer colore) {
		switch(colore) {
			
			case CellModel.GRIGIO:
				this.setBackground(Color.GRAY);
		
			case CellModel.AZZURRO :
				this.setBackground(Color.CYAN);
				break;
			case CellModel.GIALLO :
				this.setBackground(Color.YELLOW);
				break;
			case CellModel.ROSSO :
				this.setBackground(Color.RED);
				break;
		}
	}
}

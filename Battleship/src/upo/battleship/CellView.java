package upo.battleship;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class CellView extends JPanel implements Observer{
	
	public CellView(CellModel m, CellController c) {
		m.addObserver(this);
		this.setSize(100, 100);
		this.updateView(m.getValue());
		
		/*Da aggiungere azione che causerà il coloramento della cella*/
		String[] vals = {"AZZURRO","GIALLO","VERDE", "GRIGIO"};
		JComboBox<String> sel = new JComboBox<String>(vals);
		this.add(sel);
		sel.addActionListener(c);
		/*Parte qui sopra da sostituire*/
		
		this.setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Integer colore = (Integer) arg;
		this.updateView(colore);
		
	}

	private void updateView(Integer color) {
		switch(color) {
		case CellModel.AZZURRO :
			this.setBackground(Color.CYAN);
			break;
		case CellModel.GIALLO :
			this.setBackground(Color.YELLOW);
			break;
		case CellModel.ROSSO :
			this.setBackground(Color.RED);
			break;
		case CellModel.GRIGIO:
			this.setBackground(Color.GRAY);
			break;
	}
		
	}

	
}

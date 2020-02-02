package upo.battleship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;


public class CellController implements ActionListener{
	
	CellModel m;

	public CellController(CellModel cellModel) {
		
		m = cellModel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JComboBox<Integer> cb = (JComboBox) arg0.getSource();
		Integer i = (Integer) cb.getSelectedIndex();
		m.setValue(i);
	}

}

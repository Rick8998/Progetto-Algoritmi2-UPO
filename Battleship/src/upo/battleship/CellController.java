package upo.battleship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class CellController implements ActionListener{
	
	CellModel m;
	
	public void CellController(CellModel cm) {
		m = cm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<Integer> cb = (JComboBox) e.getSource();
		Integer i = (Integer) cb.getSelectedIndex();
		m.setValue(i);	
	}

}

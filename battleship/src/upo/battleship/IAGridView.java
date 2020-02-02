package upo.battleship;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JPanel;



public class IAGridView extends JPanel implements Observer{
	
	public void PlayerGridView(IAGridModel m, IAGridController c) {
		m.addObserver(this);
		
		this.setSize(100, 100);
		this.updateView(m.getValue());
		
		
		this.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	

	private void updateView(Object value) {
		// TODO Auto-generated method stub
		
	}

}

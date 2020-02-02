package upo.battleship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;



public class PlayerGridView extends JPanel implements Observer{
	
	public JComponent[][] grid;
	
	public PlayerGridView(PlayerGridModel m, PlayerGridController c) {
		m.addObserver(this);
		
		this.setSize(100, 100);
		this.setLayout(new GridLayout(11,11));
		grid = new JComponent[11][11];
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				
				grid[i][j] = new JButton("");
				grid[i][j].setPreferredSize(new Dimension(10, 10));
				//Setto l'orientamento orizzontale e verticale
				((AbstractButton) grid[i][j]).setHorizontalAlignment(JButton.CENTER);
				((AbstractButton) grid[i][j]).setVerticalAlignment(JButton.CENTER);
				grid[i][j].setOpaque(true);
				//Setto il colore del label a bianco
				grid[i][j].setBackground(Color.WHITE);
				//Aggiungo un bordo (creato prima del ciclo)
				grid[i][j].setBorder(border);
				
				//Condizione per saltare la prima cella e identificare le altre
				if(i == 0 && j > 0 ) { 
					grid[i][j] = new JLabel("" + j);
					grid[i][j].setPreferredSize(new Dimension(10, 10));
					//Setto l'orientamento orizzontale e verticale
					((JLabel) grid[i][j]).setHorizontalAlignment(JLabel.CENTER);
					((JLabel) grid[i][j]).setVerticalAlignment(JLabel.CENTER);
					grid[i][j].setOpaque(true);
					//Setto il colore del label a bianco
					grid[i][j].setBackground(Color.WHITE);
					//Aggiungo un bordo (creato prima del ciclo)
					grid[i][j].setBorder(border);
				}
				//Condizione per saltare la prima cella e identificare le altre
				if(j == 0 && i > 0 ) {
					grid[i][j] = new JLabel("" + i);
					grid[i][j].setPreferredSize(new Dimension(10, 10));
					//Setto l'orientamento orizzontale e verticale
					((JLabel) grid[i][j]).setHorizontalAlignment(JLabel.CENTER);
					((JLabel) grid[i][j]).setVerticalAlignment(JLabel.CENTER);
					grid[i][j].setOpaque(true);
					//Setto il colore del label a bianco
					grid[i][j].setBackground(Color.WHITE);
					//Aggiungo un bordo (creato prima del ciclo)
					grid[i][j].setBorder(border);
				}
				
				if(j == 0 && i == 0 ) {
					grid[i][j] = new JLabel("");
					grid[i][j].setPreferredSize(new Dimension(10, 10));
					//Setto l'orientamento orizzontale e verticale
					((JLabel) grid[i][j]).setHorizontalAlignment(JLabel.CENTER);
					((JLabel) grid[i][j]).setVerticalAlignment(JLabel.CENTER);
					grid[i][j].setOpaque(true);
					//Setto il colore del label a bianco
					grid[i][j].setBackground(Color.WHITE);
					//Aggiungo un bordo (creato prima del ciclo)
					grid[i][j].setBorder(border);
				}
				
				this.add(grid[i][j]);	
			}
		}
		
		this.updateView(m.getValue());
		
		
		this.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	private void updateView(int value) {
		// TODO Auto-generated method stub
		
	}


}

package upo.battleship;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;



public class View extends JFrame{
	
	public View(Model m, Controller c) {
		this.setSize(500, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));
		Border b = BorderFactory.createLineBorder(Color.WHITE, 10);
		
		//crea e aggiunge la griglia del giocatore
		PlayerGridView v1 = new PlayerGridView(m.getPlayer1GridModel(), c.getPlayer1GridController());
		this.add(v1);
		
		v1.setBorder(b);
		
		//crea e aggiunge la griglia della CPU
		//prima versione: tutto nello stesso frame
		PlayerGridView v2 = new PlayerGridView(m.getPlayer2GridModel(), c.getPlayer2GridController());
		this.add(v2);
		
		v2.setBorder(b);
		
		this.setVisible(true);
	}

}

package upo.battleship;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class View extends JFrame{
	
	public View(Model m, Controller c) {
		this.setSize(500, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));
		Border b = BorderFactory.createLineBorder(Color.WHITE, 10);
		
		PlayerGridView v1 = new PlayerGridView(m.getPlayerGridModel(), c.getPlayerGridController());
		this.add(v1);
		v1.setBorder(b);
		PlayerGridView v2 = new PlayerGridView(m.getPlayer2GridModel(), c.getPlayer2GridController());
		this.add(v2);
		v2.setBorder(b);
		
		this.setVisible(true);
	}
}

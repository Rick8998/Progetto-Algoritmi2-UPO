package upo.battleship;

public class Model {
	
	PlayerGridModel p1;
	PlayerGridModel p2;
	
	public Model() {
		p1 = new PlayerGridModel(0);
		p2 = new PlayerGridModel(0);
	}
	
	public PlayerGridModel getPlayerGridModel() {
		return p1;
	}
	
	public PlayerGridModel getPlayer2GridModel() {
		return p2;
		
	}
	
	

}

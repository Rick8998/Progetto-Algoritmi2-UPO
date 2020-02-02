package upo.battleship;

public class Controller {

	PlayerGridController c1;
	PlayerGridController c2;
	
	public Controller(Model m) {
		c1 = new PlayerGridController(m.getPlayerGridModel());
		c2 = new PlayerGridController(m.getPlayer2GridModel());
	}
	
	public PlayerGridController getPlayerGridController() {
		return c1;
	}
	
	public PlayerGridController getPlayer2GridController() {
		return c2;
	}

}

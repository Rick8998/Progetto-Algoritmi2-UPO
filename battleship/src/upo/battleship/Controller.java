package upo.battleship;

public class Controller {
	PlayerGridController p1;
	PlayerGridController p2;
	
	public Controller(Model m) {
		p1 = new PlayerGridController(m.getPlayer1GridModel());
		p2 = new PlayerGridController(m.getPlayer2GridModel());
	}

	public PlayerGridController getPlayer1GridController() {
		// TODO Auto-generated method stub
		return p1;
	}

	public PlayerGridController getPlayer2GridController() {
		// TODO Auto-generated method stub
		return p2;
	}

}

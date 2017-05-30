package GameState;

import java.awt.Rectangle;

import Units.Unit;

public class MovingController extends MainController{
	
	public MovingController(ArenaState arena, MainGameplayState game) {
		super(arena, game);
	}
	
	@Override
	public void keyPressed(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(Rectangle mouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		map.reset();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		Unit selectedUnit = game.getSelectedUnit();
		if(selectedUnit != null) {
			if(!selectedUnit.isOnWay()) {
				game.setController("popUpMenu");
			}
		}
	}

}

package GameState;

import java.awt.Rectangle;

import Units.Unit;

public class SelectController extends MainController{
	
	public SelectController(ArenaState arena, MainGameplayState game) {
		super(arena, game);
	}

	@Override
	public void keyPressed(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(Rectangle mouse) {
		units.get(game.getCurrentSide()).selectUnit(mouse);
		game.setSelectedUnit(units.get(game.getCurrentSide()).getSelectedUnit());
		Unit selectedUnit = game.getSelectedUnit();
		if(selectedUnit != null && selectedUnit.isAvailable()) {
			if(!selectedUnit.isOnWay()) {
				ui.setUnitContent(selectedUnit);
				game.setController("move");
			}
		}
	}

	@Override
	public void init() {
		ui.resetUnitContent();
		if(game.getSelectedUnit() != null) {
			game.getSelectedUnit().setSelected(false);
			game.setSelectedUnit(null);
		}
		try {
			if(game.getSteps() == units.get(game.getCurrentSide()).size()) {
				game.switchSide();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}

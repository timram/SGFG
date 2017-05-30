package GameState;

import java.awt.Rectangle;

import Sprite.Button;
import TileMap.Tile;
import Units.Unit;

public class LocateUnitsController extends InitController{
	
	private boolean isStartButtonPressed;
	
	public LocateUnitsController(ArenaState arena, InitGameplayState game) {
		super(arena, game);
		isStartButtonPressed = false;
	}
	
	@Override
	public void keyPressed(int key) {
	}

	@Override
	public void mouseClicked(Rectangle mouse) {
		Tile selectedTile = map.getTile(mouse);
		game.getAvailableUnits().selectUnit(mouse);
		Unit selectedUnit = game.getAvailableUnits().getSelectedUnit();
		if(selectedUnit != null) {
			if(selectedTile != null && map.isAvailableSpot(selectedTile)) {
				selectedUnit.takeTileWithoutMovement(selectedTile);
				if(!game.getSelectedUnits().contains(selectedUnit)) {
					game.getSelectedUnits().add(selectedUnit);
				}
			}
			else {
				Button btn = ui.getButton(mouse);
				if(btn != null){
					handleButtonClick(btn.getName(), selectedUnit);
					if(isStartButtonPressed) {
						return;
					}
				}
			}
			updateUserInterface(selectedUnit);
		}	
	}
	
	private void handleButtonClick(String name, Unit unit) {
		if(name == "remove") {
			game.getSelectedUnits().remove(unit);
			unit.setCoord(game.getCoords().get(unit.getName()));
			ui.hideButton("remove");
			ui.hideButton("ready");
		}
		if(name == "ready") {
			for(Unit _unit : game.getSelectedUnits()) {
				units.get(game.getSelectedSide()).addUnit(_unit);
			}
			units.get(game.getSelectedSide()).updateTakenTiles();
			ui.hideButton("remove");
			ui.hideButton("ready");
			ui.resetUnitContent();
			game.getAvailableUnits().getSelectedUnit().setSelected(false);
			game.setController("selectSide");
			isStartButtonPressed = true;
		}
		units.get(game.getSelectedSide()).updateTakenTiles();
	}
	
	private void updateUserInterface(Unit unit) {
		ui.setUnitContent(unit);
		if(!game.getSelectedUnits().contains(unit)) {
			ui.hideButton("remove");
		}
		else {
			ui.showButton("remove");
		}
		if(game.getSelectedUnits().size() == 5) {
			ui.showButton("ready");
		}
		else {
			ui.hideButton("ready");
		}
	}

	@Override
	public void init() {
		game.initSet(game.getSelectedSide());
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		for(Unit unit : game.getSelectedUnits()) {
			unit.getTakenTile().setTaken(true);
		}
		map.highlightBeginningSpot(game.getSelectedSide());
	}
}

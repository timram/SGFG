package GameState;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import TileMap.Tile;
import Units.Unit;

public class MoveController extends MainController{
	
	public MoveController(ArenaState arena, MainGameplayState game) {
		super(arena, game);
	}
	
	@Override
	public void init() {
		map.highlightAvailableTiles(game.getSelectedUnit());
	}

	@Override
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_BACK_SPACE) {
			game.setController("select");
		}
		if(key == KeyEvent.VK_ENTER) {
			game.setController("moving");
		}
	}
	
	private void reloadSelectedUnit(Unit unit) {
		map.reset();
		game.getSelectedUnit().setSelected(false);
		game.setSelectedUnit(unit);
		unit.setSelected(true);
		ui.setUnitContent(unit);
		game.setController("move");
	}

	@Override
	public void mouseClicked(Rectangle mouse) {
		Tile selectedTile = map.getTile(mouse);
		Unit selectedUnit = game.getSelectedUnit();
		Unit clickedUnit = units.get(game.getCurrentSide()).getUnit(mouse);
		if(clickedUnit != null && !clickedUnit.isSelected() && clickedUnit.isAvailable()) {
			reloadSelectedUnit(clickedUnit);
			return;
		}
		if(selectedTile != null) {
			if(map.routsCount() == 0) {
				ui.resetUnitContent();
				game.setController("select");
				return;
			}
			if(map.isAvailable(selectedTile)) {
				ArrayList<Tile> route = map.buildRoute(selectedUnit.getTakenTile(), selectedTile, selectedUnit.getStamina());
				if(route == null) {
					System.out.println("Can't to move");
				}
				else {
					selectedUnit.setRoute(route);
					game.setController("moving");
				}
			}
		}
		
	}

	@Override
	public void reset() {
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}

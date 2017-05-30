package GameState;

import java.awt.Rectangle;

import Sprite.Button;

public class SelectSideController extends InitController{
	
	public SelectSideController(ArenaState arena, InitGameplayState game) {
		super(arena, game);
	}
	
	@Override
	public void keyPressed(int key) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init() {
		map.resetSpotTiles();
		if(units.get("light").size() == 5 && units.get("dark").size() == 5) {
			game.getArena().setState("Main");
			return;
		}
		if(game.getSelectedSide() != null) {
			String selectedSide = game.getSelectedSide();
			selectedSide = (selectedSide == "light") ? "dark" : "light";
			game.setSelectedSide(selectedSide);
			game.setController("locateUnits");
			return;
		}
		ui.showButton("light");
		ui.showButton("dark");
	}

	@Override
	public void mouseClicked(Rectangle mouse) {
		Button btn = ui.getButton(mouse);
		if(btn != null) {
			game.setSelectedSide(btn.getName());
			ui.hideButton("light");
			ui.hideButton("dark");
			game.setController("locateUnits");
		}
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {	
	}
}

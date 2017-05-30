package GameState;

import java.awt.Rectangle;

public class WinningController extends MainController{


	public WinningController(ArenaState arena, MainGameplayState game) {
		super(arena, game);
		// TODO Auto-generated constructor stub
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
		ui.setWinningLabel(game.getCurrentSide());
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

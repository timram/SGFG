package GameState;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import Main.GameBoard;
import Units.Unit;
import ui.PopUpMenu;

public class PopUpController extends MainController{
	
	private PopUpMenu menu;
	
	public PopUpController(ArenaState arena, MainGameplayState game) {
		super(arena, game);
		menu = (PopUpMenu)ui.getPopUp("menu");
	}

	@Override
	public void init() {
		Unit selectedUnit = game.getSelectedUnit();
		menu.setVisible(true);
		int x, y;
		if(selectedUnit.getX() < GameBoard.BOARD_WIDTH / 2) {
			x = selectedUnit.getX() + 50;
		}
		else {
			x = selectedUnit.getX() - menu.getWidth() - 20;
		}
		if(selectedUnit.getY() < GameBoard.BOARD_HEIGHT / 2) {
			y = selectedUnit.getY();
		}
		else {
			y = selectedUnit.getY() - menu.getHeight();
		}
		menu.setLocation(x, y);
		units.get(game.getOppositeSide()).resetAttackableUnits();
		units.get(game.getOppositeSide()).showAttackableUnits(selectedUnit);
		menu.setAttackable(units.get(game.getOppositeSide()).isAttackAvailable());
	}

	@Override
	public void keyPressed(int key) {
		Unit selectedUnit = game.getSelectedUnit();
		if(key == KeyEvent.VK_DOWN) {
			menu.selectNextOption();
		}
		if(key == KeyEvent.VK_UP) {
			menu.selectPrevOption();
		}
		if(key == KeyEvent.VK_ENTER) {
			String option = menu.getSelectedOption();
			if(option == "Wait") {
				reset();
				game.resetStep();
				game.setController("select");
			}
			if(option == "Attack") {
				if(units.get(game.getOppositeSide()).isAttackAvailable()) {
					reset();
					game.setController("attack");
				}
			}
			if(option == "Use inventory") {
				reset();
				game.setController("inventory");
			}
		}
	}

	@Override
	public void mouseClicked(Rectangle mouse) {

	}

	@Override
	public void reset() {
		ui.getPopUp("menu").setVisible(false);
	}

	@Override
	public void update() {

	}
}

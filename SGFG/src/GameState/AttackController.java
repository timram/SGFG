package GameState;

import java.awt.Rectangle;

import com.sun.glass.events.KeyEvent;

import Sprite.Button;
import Units.Unit;

public class AttackController extends MainController{
	
	public AttackController(ArenaState arena, MainGameplayState game) {
		super(arena, game);
	}
	
	@Override
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_BACKSPACE) {
			game.setController("popUpMenu");
		}
	}
	
	@Override
	public void mouseClicked(Rectangle mouse) {
		Unit selectedUnit = game.getSelectedUnit();
		Unit currentSideUnit = units.get(game.getCurrentSide()).getUnit(mouse);
		if(currentSideUnit != null && currentSideUnit.isSelected()) {
			ui.setUnitContent(currentSideUnit);
			ui.hideButton("attack");
			return;
		}
		
		Unit oppositeUnit = units.get(game.getOppositeSide()).getUnit(mouse);
		if(oppositeUnit != null && units.get(game.getOppositeSide()).isAttackable(oppositeUnit)) {
			ui.setUnitContent(oppositeUnit);
			ui.showButton("attack");
			return;
		}
		
		Button btn = ui.getButton(mouse);
		if(btn != null && btn.getName().equals("attack")) {
			oppositeUnit = ui.getSelectedUnit();
			oppositeUnit.setHealth(oppositeUnit.getHealth() - selectedUnit.getAttack());
			ui.hideButton("attack");
			ui.getAttackLabel().setVisible(true);
			ui.getAttackLabel().initAttackScore(oppositeUnit.getX(), oppositeUnit.getY(), selectedUnit.getAttack());
			ui.getAttackLabel().initGif(oppositeUnit.getX() - 100, oppositeUnit.getY(), "WeaponSlash");
			if(oppositeUnit.getHealth() <= 0) {
				units.get(game.getOppositeSide()).removeUnit(oppositeUnit);
				map.addStaticObject(oppositeUnit.getTakenTile(), "Bones");
				game.setCurrentlyDeadUnit(oppositeUnit);
				game.setController("steal");
				return;
			}
			game.resetStep();
			game.setController("select");
		}
	}

	@Override
	public void init() {
		
	}

	@Override
	public void reset() {
		
	}

	@Override
	public void update() {
		
	}
}

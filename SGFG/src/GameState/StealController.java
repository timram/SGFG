package GameState;

import Items.Item;
import Items.Resource;
import Items.Weapon;
import Units.Unit;

public class StealController extends InventoryController{

	public StealController(ArenaState arena, MainGameplayState game) {
		super(arena, game);
	}
	
	@Override
	protected void applyItem(Item item, Unit unit) {
		if(item.getClass().getSimpleName().equals("Weapon")) {
			unit.addToWeapons((Weapon)item);
		} else {
			unit.addToInventory((Resource)item);
		}
		isItemUsed = true;
	}
	
	@Override
	public void init() {
		inventory.setVisible(true);
		inventory.init(game.getCurrentlyDeadUnit().getInventory(), game.getCurrentlyDeadUnit().getWeapons());
		game.getCurrentlyDeadUnit().getWeapon().setSelected(false);
	}
	
	@Override
	public void reset() {
		inventory.setVisible(false);
		inventory.reset();
		game.resetStep();
		game.setController("select");
	}

}

package GameState;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.sun.glass.events.KeyEvent;

import Items.Item;
import Items.Resource;
import Items.Weapon;
import Sprite.Button;
import Units.Unit;
import ui.Inventory;

public class InventoryController extends MainController {

	protected Inventory inventory;
	protected boolean isItemUsed;
	
	public InventoryController(ArenaState arena, MainGameplayState game) {
		super(arena, game);
		inventory = (Inventory)ui.getPopUp("inventory");
		isItemUsed = false;
	}

	@Override
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_BACKSPACE) {
			reset();
		}
	}
	
	protected void applyItem(Item item, Unit unit) {
		ui.hideButton("ok");
		if(item.getClass().getSimpleName().equals("Weapon")) {
			unit.resetWeapon();
			unit.setWeapon((Weapon)item);
		} else {
			item.effect(unit);
			int amount = ((Resource)item).getAmount() - 1;
			((Resource)item).setAmount(amount);
			if(amount <= 0) {
				unit.removeFromInventory((Resource)item);
			}
			isItemUsed = true;
		}
	}

	@Override
	public void mouseClicked(Rectangle mouse) {
		inventory.selectItem(mouse);
		Item selectedItem = inventory.getSelectedItem();
		Button button = ui.getButton(mouse);
		if(button != null) {
			if(button.getName().equals("close")) {
				reset();
			}
			if(button.getName().equals("ok")) {
				ui.hideButton("ok");
				applyItem(selectedItem, game.getSelectedUnit());
			}
			return;
		}
		if(selectedItem != null) {
			if(selectedItem.getClass().getSimpleName().equals("Resource") && !isItemUsed) {
				ui.showButton("ok");
			} else if(selectedItem.getClass().getSimpleName().equals("Weapon") && !((Weapon)selectedItem).isSelected()) {
				ui.showButton("ok");
			} else {
				ui.hideButton("ok");
			}
		} else {
			ui.hideButton("ok");
		}
	}

	@Override
	public void init() {
		inventory.setVisible(true);
		inventory.init(game.getSelectedUnit().getInventory(), game.getSelectedUnit().getWeapons());
	}

	@Override
	public void reset() {
		inventory.setVisible(false);
		inventory.reset();
		if(isItemUsed) {
			game.resetStep();
			game.setController("select");
		} else {
			game.setController("popUpMenu");
		}
		isItemUsed = false;
	}

	@Override
	public void update() {
		
	}
	

}

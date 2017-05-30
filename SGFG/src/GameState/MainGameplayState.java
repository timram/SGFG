package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import Items.ItemsFactory;
import Items.Resource;
import Main.GameBoard;
import Sprite.Button;
import TileMap.Loot;
import TileMap.Tile;
import Units.Unit;
import ui.InfoPopUp;
import ui.PopUpMenu;


public class MainGameplayState extends GameplayState{
	private Unit selectedUnit;
	private Unit currentlyDead;
	private String currentSide;
	private String oppositeSide;
	private int stepCounter;
	
	public MainGameplayState(ArenaState arena) {
		super(arena);
		controllers = new HashMap<String, Controller>();
		stepCounter = 0;
		controllers.put("select", new SelectController(arena, this));
		controllers.put("popUpMenu", new PopUpController(arena, this));
		controllers.put("move", new MoveController(arena, this));
		controllers.put("moving", new MovingController(arena, this));
		controllers.put("attack", new AttackController(arena, this));
		controllers.put("inventory", new InventoryController(arena, this));
		controllers.put("steal", new StealController(arena, this));
	}
	
	@Override
	public void init() {
		switchSide();
		setController("select");
	}
	
	@Override
	public void update() {
		try {
			controllers.get(currentController).update();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		arena.getMap().drawMap(g2d, null);
		arena.getSetOfUnits("light").drawUnits(g2d, null);
		arena.getSetOfUnits("dark").drawUnits(g2d, null);
		arena.getUserInterface().draw(g2d, null);
	}

	@Override
	public void keyPressed(int key) {
		controllers.get(currentController).keyPressed(key);
	}

	@Override
	public void keyReleased(int key) {
	}

	@Override
	public void mouseClicked(Rectangle mouse) {
		controllers.get(currentController).mouseClicked(mouse);
	}

	@Override
	public void mousePressed(Rectangle mouse) {
		Button btn = arena.getUserInterface().getButton(mouse);
		if(btn != null) {
			arena.getUserInterface().pressButton(btn.getName());
		}
	}

	@Override
	public void mouseReleased(Rectangle mouse) {
		arena.getUserInterface().unpressButton();
	}
	
	public void switchSide() {
		stepCounter = 0;
		currentSide = (currentSide == "light") ? "dark" : "light";
		oppositeSide = (currentSide == "light") ? "dark" : "light";
		if(currentSide == "light") {
			arena.getUserInterface().getGameLabel().initLightLabel();
		}
		else {
			arena.getUserInterface().getGameLabel().initDarkLabel();
		}
		arena.getSetOfUnits(currentSide).unlockUnits();
		arena.getMap().addRandomLoot();
	}
	
	public void resetStep() {
		incrementSteps();
		selectedUnit.setAvailable(false);
		arena.getSetOfUnits(oppositeSide).resetAttackableUnits();
		arena.getUserInterface().resetUnitContent();
		Loot loot = arena.getMap().getLoot(selectedUnit.getTakenTile());
		if(loot != null) {
			Resource res = (Resource)ItemsFactory.makeItem(loot.getResourceName());
			res.setAmount(1);
			selectedUnit.addToInventory(res);
			arena.getMap().removeLoot(loot);
			InfoPopUp info = (InfoPopUp)arena.getUserInterface().getPopUp("info");
			info.setVisible(true);
			info.setImage(res.getBigImage());
			info.setText("YOU GOT NEW " + res.getName() + " !!!");
			info.resetTimer();
		}
	}
	
	public Unit getSelectedUnit() {
		return selectedUnit;
	}
	
	public void setSelectedUnit(Unit unit) {
		selectedUnit = unit;
	}
	
	public Unit getCurrentlyDeadUnit() {
		return currentlyDead;
	}
	
	public void setCurrentlyDeadUnit(Unit unit) {
		currentlyDead = unit;
	}
	
	public int getSteps() {
		return stepCounter;
	}
	
	public void incrementSteps() {
		stepCounter++;
	}
	
	public String getCurrentSide() {
		return currentSide;
	}
	
	public String getOppositeSide() {
		return oppositeSide;
	}
}

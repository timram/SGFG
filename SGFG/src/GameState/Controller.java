package GameState;

import java.awt.Rectangle;
import java.util.HashMap;

import TileMap.Map;
import Units.SetOfUnits;
import ui.UserInterface;

public abstract class Controller {
	protected Map map;
	protected UserInterface ui;
	protected HashMap<String, SetOfUnits> units;
	
	public Controller(ArenaState arena) {
		map = arena.getMap();
		ui = arena.getUserInterface();
		units = new HashMap<String, SetOfUnits>();
		units.put("light", arena.getSetOfUnits("light"));
		units.put("dark", arena.getSetOfUnits("dark"));
	}
	
   	public abstract void keyPressed(int key);
	public abstract void mouseClicked(Rectangle mouse);
	public abstract void init();
	public abstract void reset();
	public abstract void update();
}

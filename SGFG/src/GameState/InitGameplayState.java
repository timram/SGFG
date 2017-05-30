package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

import Sprite.Button;
import TileMap.Tile;
import Units.SetOfUnits;
import Units.Unit;
import Units.UnitFactory;

public class InitGameplayState extends GameplayState{
	private SetOfUnits availableUnits;
	private ArrayList<Unit> selectedUnits;
	private final String[] names = {"Icarus", "Link", "Paluten", "Alucard", "Ragnaros"};
	private String selectedSide;
	private HashMap<String, Integer[]> coords;
	private int startX;
	private int startY;

	public InitGameplayState(ArenaState arena) {
		super(arena);
		selectedUnits = new ArrayList<Unit>();
		startX = arena.getUserInterface().getUnitPanelWidth() + 300;
		startY = arena.getUserInterface().getStartY() + 10;
		controllers = new HashMap<String, Controller>();
		controllers.put("selectSide", new SelectSideController(arena, this));
		controllers.put("locateUnits", new LocateUnitsController(arena, this));
		setController("selectSide");	
	}
	
	public void initSet(String type) {
		int x = startX;
		int y = startY;
		selectedSide = type;
		int tileWidth = arena.getMap().getTileWidth();
		
		selectedUnits.clear();
		if(availableUnits != null) {
			availableUnits.clear();
		}
		else {
			availableUnits = new SetOfUnits();
		}
		
		if(coords != null) {
			coords.clear();
		}
		else {
			coords = new HashMap<String, Integer[]>();
		}
		for(int i = 0; i < names.length; i++) {
			x += tileWidth;
			Unit newUnit = (type == "light") ? UnitFactory.makeUnit(x, y, names[i]) : UnitFactory.makeEvilUnit(x, y, names[i]); 
			coords.put(newUnit.getName(), new Integer[] {x, y});
			availableUnits.addUnit(newUnit);
		}
	}
	
	public SetOfUnits getAvailableUnits() {
		return availableUnits;
	}
	
	public ArrayList<Unit> getSelectedUnits() {
		return selectedUnits;
	}
	
	public String getSelectedSide() {
		return selectedSide;
	}
	
	public void setSelectedSide(String selectedSide) {
		this.selectedSide = selectedSide;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
	public HashMap<String, Integer[]> getCoords() {
		return coords;
	}
	
	public ArenaState getArena() {
		return arena;
	}
	
	@Override
	public void init() {
	}


	@Override
	public void draw(Graphics2D g2d) {
		arena.getMap().drawMap(g2d, null);
		arena.getUserInterface().draw(g2d, null);
		arena.getSetOfUnits("light").drawUnits(g2d, null);
		arena.getSetOfUnits("dark").drawUnits(g2d, null);
		if(availableUnits != null){ 
			availableUnits.drawUnits(g2d, null);
		}
	}

	@Override
	public void keyPressed(int key) {
	
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

	@Override
	public void update() {
		controllers.get(currentController).update();
		
	}
}

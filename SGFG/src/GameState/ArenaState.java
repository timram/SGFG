package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import Units.SetOfUnits;
import Units.UnitFactory;
import ui.UserInterface;
import TileMap.Map;
import Main.GameBoard;


public class ArenaState extends GameState{
	private HashMap<String, SetOfUnits> units;
	private Map map;
	private UserInterface userInterface;
	private HashMap<String, GameplayState> states;
	private String currentState;
	
	public ArenaState(GameStateManager gsm) {
		super(gsm);
	}
	
	private void initMockUnits() {
		units.get("light").addUnit(UnitFactory.makeUnit(map.getTile(3, 0), "Icarus"));
		units.get("light").addUnit(UnitFactory.makeUnit(map.getTile(3, 1), "Link"));
		units.get("light").addUnit(UnitFactory.makeUnit(map.getTile(3, 2), "Paluten"));
		units.get("light").addUnit(UnitFactory.makeUnit(map.getTile(4, 0), "Alucard"));
		units.get("light").addUnit(UnitFactory.makeUnit(map.getTile(4, 1), "Ragnaros"));
		units.get("dark").addUnit(UnitFactory.makeEvilUnit(map.getTile(3, 20), "Icarus"));
		units.get("dark").addUnit(UnitFactory.makeEvilUnit(map.getTile(3, 21), "Link"));
		units.get("dark").addUnit(UnitFactory.makeEvilUnit(map.getTile(3, 22), "Paluten"));
		units.get("dark").addUnit(UnitFactory.makeEvilUnit(map.getTile(4, 20), "Alucard"));
		units.get("dark").addUnit(UnitFactory.makeEvilUnit(map.getTile(4, 21), "Ragnaros"));
	}
	
	public void setState(String state) {
		currentState = state;
		states.get(currentState).init();
	}

	@Override
	public void init() {
		if(!initialized) {
			map = new Map(GameBoard.BOARD_WIDTH, GameBoard.BOARD_HEIGHT, "first");
			userInterface = new UserInterface(map.getRowsCount() * map.getTileHeight());
			units = new HashMap<String, SetOfUnits>();
			units.put("light", new SetOfUnits());
			units.put("dark", new SetOfUnits());
			//initMockUnits();
			states = new HashMap<String, GameplayState>();
			states.put("Init", new InitGameplayState(this));
			states.put("Main", new MainGameplayState(this));
			setState("Init");
			System.out.println(currentState);
			initialized = true;
		}
	}

	@Override
	public void update() {
		if(currentState == null) {
			return;
		}
		states.get(currentState).update();
	}

	@Override
	public void draw(Graphics2D g2d) {
		if(currentState == null) {
			return;
		}
		states.get(currentState).draw(g2d);
	}

	@Override
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_ESCAPE) {
			gsm.setState("Menu");
		}
		else {
			states.get(currentState).keyPressed(key);
		}
	}

	@Override
	public void keyReleased(int key) {
			
	}

	@Override
	public void mouseClicked(Rectangle mouse) {
		states.get(currentState).mouseClicked(mouse);
	}
	
	public Map getMap() {
		return map;
	}
	
	public SetOfUnits getSetOfUnits(String side) {
		return units.get(side);
	}
	
	public UserInterface getUserInterface() {
		return userInterface;
	}

	@Override
	public void mousePressed(Rectangle mouse) {
		states.get(currentState).mousePressed(mouse);
		
	}

	@Override
	public void mouseReleased(Rectangle mouse) {
		states.get(currentState).mouseReleased(mouse);
		
	}

	@Override
	public void reset() {
		userInterface.resetUnitContent();
		units.get("light").clear();
		units.get("dark").clear();
		setState("Init");
	}
}

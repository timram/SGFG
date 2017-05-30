package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;

public class GameStateManager {
	
	private HashMap<String, GameState> gameStates;
	private String currentState;
	
	public GameStateManager() {
		gameStates = new HashMap<String, GameState>();
		gameStates.put("Menu", new MenuState(this));
		gameStates.put("Arena", new ArenaState(this));
		setState("Menu");
	}
	
	public void setState(String state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(Graphics2D g2d) {
		gameStates.get(currentState).draw(g2d);
	}
	
	public void keyPressed(int key) {
		gameStates.get(currentState).keyPressed(key);
	}
	
	public void keyReleased(int key) {
		gameStates.get(currentState).keyReleased(key);
	}
	
	public void mouseClicked(Rectangle mouse) {
		gameStates.get(currentState).mouseClicked(mouse);
	}
	
	public void mousePressed(Rectangle mouse) {
		gameStates.get(currentState).mousePressed(mouse);
	}
	
	public void mouseReleased(Rectangle mouse) {
		gameStates.get(currentState).mouseReleased(mouse);
	}
}

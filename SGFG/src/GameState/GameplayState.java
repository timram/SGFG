package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;

public abstract class GameplayState {
	protected ArenaState arena;
	protected HashMap<String, Controller> controllers;
	protected String currentController;
	
	public GameplayState(ArenaState arena) {
		this.arena = arena;
	}
	
	public abstract void draw(Graphics2D g2d);
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);
	public abstract void mouseClicked(Rectangle mouse);
	public abstract void mousePressed(Rectangle mouse);
	public abstract void mouseReleased(Rectangle mouse);
	public abstract void update();
	public abstract void init();
	
	protected void setController(String controller) {
		currentController = controller;
		controllers.get(currentController).init();
	}
}

package GameState;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class GameState {
	protected GameStateManager gsm;
	protected boolean initialized;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		initialized = false;
	}
	
	public abstract void init();
	public abstract void reset();
	public abstract void update();
	public abstract void draw(Graphics2D g2d);
	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);
	public abstract void mouseClicked(Rectangle mouse);
	public abstract void mousePressed(Rectangle mouse);
	public abstract void mouseReleased(Rectangle mouse);
}

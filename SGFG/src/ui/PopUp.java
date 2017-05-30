package ui;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public abstract class PopUp {
	protected boolean visible;
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public abstract void draw(Graphics2D g2d, JPanel board);
}

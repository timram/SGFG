package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PopUpMenu extends PopUp{
	private int x;
	private int y;
	private final String[] bar = {"Attack", "Use inventory", "Wait"};
	private final int[] pos = {30, 60, 90, 120, 150};
	private BufferedImage panel;
	private BufferedImage cursor;
	private final int fontSize = 20;
	private int selected;
	private int cursorX;
	private int cursorY;
	private boolean attackable;	
	
	public PopUpMenu() {
		try {
			panel = ImageIO.read(this.getClass().getResourceAsStream("/UserInterface/popUpMenu.png"));
			cursor = ImageIO.read(this.getClass().getResourceAsStream("/UserInterface/arrow.png"));
			visible = false;
			attackable = false;
			selected = 0;
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		selected = 0;
		cursorX = x - 10;
	}
	
	public void setAttackable(boolean attackable) {
		this.attackable = attackable;
	}
	
	@Override
	public void draw(Graphics2D g2d, JPanel board) {
		g2d.drawImage(panel, x, y, board);
		g2d.drawImage(cursor, cursorX, y + pos[selected] - cursor.getHeight(), board);
		g2d.setFont(new Font("Arial", Font.PLAIN, fontSize));
		for(int i = 0; i < bar.length; i++) {
			g2d.setColor(new Color(0, 0, 0));
			if(bar[i].equals("Attack")) {
				if(!attackable) {
					g2d.setColor(new Color(100, 100, 100));
				}
			}
			g2d.drawString(bar[i], cursorX + 25, y + pos[i]);
		}
	}
	
	public void selectNextOption() {
		selected = (selected == bar.length - 1) ? 0 : ++selected;
	}
	
	public void selectPrevOption() {
		selected = (selected == 0) ? bar.length - 1 : --selected;
	}
	
	public String getSelectedOption() {
		return bar[selected];
	}
	
	public int getWidth() {
		return panel.getWidth();
	}
	
	public int getHeight() {
		return panel.getHeight();
	}
}

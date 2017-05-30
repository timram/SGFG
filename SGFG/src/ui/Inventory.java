package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Items.Item;
import Items.Resource;
import Items.Weapon;
import Main.GameBoard;
import Sprite.Button;

public class Inventory extends PopUp {
	private BufferedImage panel;
	private Item selectedItem;
	private HashMap<String, Button> buttons;
	private ArrayList<Item> resources;
	private ArrayList<Item> weapons;
	private int x;
	private int y;
	private final int viewWidth = 200;
	private int width;
	private int height;
	private UserInterface ui;
	private BufferedImage selectedWeapon;
	
	public Inventory(UserInterface ui) {
		try {
			panel = ImageIO.read(this.getClass().getResourceAsStream("/UserInterface/inventoryPanel.png"));
			width = panel.getWidth();
			height = panel.getHeight();
			selectedWeapon = ImageIO.read(this.getClass().getResourceAsStream("/Items/Weapons/Selected.png"));
			x = (GameBoard.BOARD_WIDTH / 2) - (width / 2);
			y = (GameBoard.BOARD_HEIGHT / 2) - (height / 2) - 60;
			initButtons();
			this.ui = ui;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initButtons() {
		buttons = new HashMap<String, Button>();
		Button close = new Button(0, 0, "close");
		close.setX(x + width - close.getWidth());
		close.setY(y);
		close.setVisible(true);
		buttons.put("close", close);
		Button ok = new Button(0, 0, "ok");
		ok.setX(x + viewWidth - ok.getWidth() - 20);
		ok.setY(y + panel.getHeight() - ok.getHeight() - 20);
		buttons.put("ok", ok);
	}
	
	public void init(ArrayList<Item> res, ArrayList<Item> weapons) {
		for(String name : buttons.keySet()) {
			ui.addButton(buttons.get(name));
		}
		this.resources = res;
		this.weapons = weapons;
		arrangeItems(y + 20, this.resources);
		arrangeItems(y + 170, this.weapons);
	}
	
	private void arrangeItems(int startY, ArrayList<Item> items) {
		int itemY = startY;
		int itemX = x + viewWidth;
		final int gap = 65;
		for(Item item : items) {
			item.setX(itemX);
			item.setY(itemY);
			itemX += gap;
			if(itemX >= x + width - gap) {
				itemX = x + viewWidth;
				itemY += gap;
			}
		}
	}
	
	public void reset() {
		ui.hideButton("ok");
		for(String name : buttons.keySet()) {
			ui.removeButton(name);
		}
		selectedItem = null;
		resources = null;
		weapons = null;
	}
	
	public void selectItem(Rectangle mouse) {
		for(Item res : resources) {
			if(res.getBounds().intersects(mouse)) {
				selectedItem = res;
				System.out.println(selectedItem.getClass().getSimpleName());
				return;
			}
		}
		for(Item weapon : weapons) {
			if(weapon.getBounds().intersects(mouse)) {
				selectedItem = weapon;
				System.out.println(selectedItem.getClass().getSimpleName());
			}
		}
	}
	
	public Item getSelectedItem() {
		return selectedItem;
	}
	
	@Override
	public void draw(Graphics2D g2d, JPanel board) {
		try {
			drawPanel(g2d, board);
			drawResources(g2d, board);
			drawWeapons(g2d, board);
			drawItemContent(g2d, board);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void drawItemContent(Graphics2D g2d, JPanel board) {
		int startY = y + 20;
		if(selectedItem != null) {
			BufferedImage itemIcon = selectedItem.getBigImage();
			g2d.drawImage(itemIcon, x + (viewWidth / 2) - (itemIcon.getWidth() / 2), startY, board);
			g2d.setColor(new Color(255, 255, 255));
			int descY = startY + itemIcon.getHeight() + 15;
			g2d.drawString(selectedItem.getName(), x + (viewWidth / 2) - (itemIcon.getWidth() / 2), descY);
			descY += 20;
			for(String str : selectedItem.getDescription()) {
				g2d.drawString(str, x + 5, descY);
				descY += 20;
			}
		}
	}
	
	private void drawPanel(Graphics2D g2d, JPanel board) {
		g2d.drawImage(panel, x, y, board);
	}
	
	private void drawResources(Graphics2D g2d, JPanel board) {
		for(Item res : resources) {
			g2d.drawImage(res.getImage(), res.getX(), res.getY(), board);
			int countX = res.getX() + res.getWidth();
			int countY = res.getY() + res.getHeight();
			g2d.setColor(new Color(255, 255, 255));
			g2d.drawString(Integer.toString(((Resource)res).getAmount()), countX, countY);
		}
	}
	
	private void drawWeapons(Graphics2D g2d, JPanel board) {
		for(Item weapon : weapons) {
			g2d.drawImage(weapon.getImage(), weapon.getX(), weapon.getY(), board);
			if(((Weapon)weapon).isSelected()) {
				g2d.drawImage(selectedWeapon, weapon.getX(), weapon.getY(), board);
			}
		}
	}
}

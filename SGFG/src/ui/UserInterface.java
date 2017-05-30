package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Main.GameBoard;
import Sprite.Button;
import Sprite.GameLabel;
import Units.Unit;

public class UserInterface {
	private int startY;
	private BufferedImage unitPanel;
	private BufferedImage gamePanel;
	private BufferedImage unitIcon;
	private BufferedImage winningLabel;
	private Unit selectedUnit;
	private HashMap<String, Button> buttons;
	private Button pressedButton;
	private HashMap<String, PopUp> popUps;
	private GameLabel gameLabel;
	private AttackLabel attackLabel;
	
	public UserInterface(int startY) {
		this.startY = startY;
		popUps = new HashMap<String, PopUp>();
		popUps.put("menu", new PopUpMenu());
		popUps.put("inventory", new Inventory(this));
		popUps.put("info", new InfoPopUp());
		gameLabel = new GameLabel(-50, 10);
		attackLabel = new AttackLabel();
		try {
			unitPanel = ImageIO.read(this.getClass().getResourceAsStream("/UserInterface/unitPanel.png"));
			gamePanel = ImageIO.read(this.getClass().getResourceAsStream("/UserInterface/gamePanel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initButtons();
	}

	private void initButtons() {
		buttons = new HashMap<String, Button>();
		buttons.put("remove", new Button(getUnitPanelWidth() - 200, startY + 35, "remove"));
		buttons.put("ready", new Button(GameBoard.BOARD_WIDTH / 2 - 50, 60, "ready"));
		buttons.put("light", new Button(getUnitPanelWidth() + 300, startY + 1, "light"));
		buttons.put("dark", new Button(getUnitPanelWidth() + 500, startY + 1, "dark"));
		buttons.put("attack", new Button(getUnitPanelWidth() - 200, startY + 35, "attack"));
		buttons.put("closeWinning", new Button(100, 100, "ok"));
	}
	
	public void draw(Graphics2D g2d, JPanel board) {
		drawUI(g2d, board);
		drawUnitContent(g2d, board);
		drawButtons(g2d, board);
		drawAttackLabel(g2d, board);
		drawWinningLabel(g2d, board);
	}
	
	private void drawWinningLabel(Graphics2D g2d, JPanel board) {
		if(winningLabel != null) {
			g2d.drawImage(winningLabel, 100, 100, board);
		}
	}
	
	private void drawAttackLabel(Graphics2D g2d, JPanel board) {
		if(attackLabel.isVisible()) {
			attackLabel.draw(g2d, board);
			attackLabel.update();
		}
	}
	
	private void drawUI(Graphics2D g2d, JPanel board) {
		g2d.drawImage(unitPanel, 0, startY, board);
		g2d.drawImage(gamePanel, unitPanel.getWidth(), startY, board);
		drawPopUp(g2d, board);
		if(gameLabel.isVisible()) {
			g2d.drawImage(gameLabel.getImage(), gameLabel.getX(), gameLabel.getY(), null);
			gameLabel.move();
		}
	}
	
	private void drawPopUp(Graphics2D g2d, JPanel board) {
		for(String name : popUps.keySet()) {
			PopUp popUp = popUps.get(name);
			if(popUp.isVisible()) {
				popUp.draw(g2d, board);
			}
		}
	}
	
	private void drawButtons(Graphics2D g2d, JPanel board) {
		for(String name : buttons.keySet()) {
			Button btn = buttons.get(name);
			if(btn.isVisible()) {
				g2d.drawImage(btn.getImage(), btn.getX(), btn.getY(), board);
			}
		}
	}
	
	private void drawUnitContent(Graphics2D g2d, JPanel board) {
		if(unitIcon != null && selectedUnit != null) {
			try {
				g2d.drawImage(unitIcon, 10, startY, board);
				g2d.setColor(new Color(0, 0, 0));
				g2d.setFont(new Font("Arial", Font.PLAIN, 34));
				g2d.drawString(selectedUnit.getName(), 20 + unitIcon.getWidth(), startY + 30);
				g2d.setFont(new Font("Arial", Font.PLAIN, 15));
				String hp = "HP: " + selectedUnit.getHealth() + "/" + selectedUnit.getMaxHealth();
				g2d.drawString(hp, unitPanel.getWidth() + 20, startY + 35);
				String stamina = "Stamina: " + selectedUnit.getStamina();
				g2d.drawString(stamina, unitPanel.getWidth() + 20, startY + 55);
				String attack = "Attack: " + selectedUnit.getAttack();
				g2d.drawString(attack, unitPanel.getWidth() + 20, startY + 75);
				String weapon = "Weapon: " + selectedUnit.getWeapon().getName();
				g2d.drawString(weapon, unitPanel.getWidth() + 20, startY + 95);
				String strength = "Strength: " + selectedUnit.getStrength();
				g2d.drawString(strength, unitPanel.getWidth() + 155, startY + 35);
				String intelegence = "Intelegence: " + selectedUnit.getIntelegence();
				g2d.drawString(intelegence, unitPanel.getWidth() + 155, startY + 55);
				String agility = "Agility: " + selectedUnit.getAgility();
				g2d.drawString(agility, unitPanel.getWidth() + 155, startY + 75);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getStartY() {
		return startY;
	}
	
	public int getGamePanelWidth() {
		return gamePanel.getWidth();
	}
	
	public int getUnitPanelWidth() {
		return unitPanel.getWidth();
	}
	
	public void setUnitContent(Unit unit) {
		try {
			unitIcon = ImageIO.read(this.getClass().getResourceAsStream("/Units/" + unit.getName() + "/big.png"));
			selectedUnit = unit;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void resetUnitContent() {
		unitIcon = null;
		selectedUnit = null;
	}
	
	public Button getButton(Rectangle mouse) {
		for(String name : buttons.keySet()) {
			Button btn = buttons.get(name);
			if(btn.isVisible() && btn.getBounds().intersects(mouse)) {
				return btn;
			}
		}
		return null;
	}
	
	public Button getButton(String name) {
		if(buttons.containsKey(name)) {
			return buttons.get(name);
		}
		return null;
	}
	
	public PopUp getPopUp(String name) {
		if(popUps.containsKey(name)) {
			return popUps.get(name); 
		}
		return null;
	}
	
	public GameLabel getGameLabel() {
		return gameLabel;
	}
	
	public void setWinningLabel(String name) {
		try {
			winningLabel = ImageIO.read(this.getClass().getResourceAsStream("/UserInterface/" + name + "_winning.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void resetWinningLabel() {
		winningLabel = null;
	}
	
	public void pressButton(String name) {
		if(buttons.containsKey(name)) {
			pressedButton = buttons.get(name);
			pressedButton.press();
		}
	}
	
	public void unpressButton() {
		if(pressedButton != null) {
			pressedButton.unpress();
			pressedButton = null;
		}
	}
	
	public void showButton(String name) {
		buttons.get(name).setVisible(true);
	}
	
	public void hideButton(String name) {
		buttons.get(name).setVisible(false);
	}
	
	public void addButton(Button button) {
		buttons.put(button.getName(), button);
	}
	
	public void removeButton(String name) {
		buttons.remove(name);
	}
	
	public Unit getSelectedUnit() {
		return selectedUnit;
	}

	public AttackLabel getAttackLabel() {
		return attackLabel;
	}
}

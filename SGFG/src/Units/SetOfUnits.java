package Units;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import TileMap.Map;

public class SetOfUnits {
	private ArrayList<Unit> units;
	private ArrayList<Unit> attackableUnits;
	private BufferedImage selectedImage;
	
	public SetOfUnits() {
		units = new ArrayList<Unit>();
		attackableUnits = new ArrayList<Unit>();
		try {
			selectedImage = ImageIO.read(this.getClass().getResourceAsStream("/UserInterface/cursorSword_gold.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addUnit(Unit unit) {
		units.add(unit);
	}
	
	public void removeUnit(Unit unit) {
		units.remove(unit);
	}
	
	public ArrayList<Unit> getUnits() {
		return units;
	}
	
	public Unit getSelectedUnit() {
		for(Unit unit : units) {
			if(unit.isSelected()) {
				return unit;
			}
		}
		return null;
	}
	
	public void selectUnit(Rectangle mouse) {
		for(Unit unit : units) {
			if(unit.getBounds().intersects(mouse) && unit.isAvailable()) {
				unit.setSelected(true);
				for(Unit otherUnit : units) {
					if(otherUnit != unit) {
						otherUnit.setSelected(false);
					}
				}
				break;
			}
		}
	}
	
	public Unit getUnit(Rectangle mouse) {
		for(Unit unit : units) {
			if(unit.getBounds().intersects(mouse)) {
				return unit;
			}
		}
		return null;
	}
	
	public void drawUnits(Graphics2D g2d, JPanel board) {
		for(int i = 0; i < units.size(); i++) {
			Unit unit = units.get(i);
			unit.move();
			g2d.drawImage(unit.getImage(), unit.getX(), unit.getY(), board);
			if(unit.isSelected()) {
				g2d.drawImage(selectedImage, unit.getX(), unit.getY() - selectedImage.getWidth(null), board);
			}
		}
	}
	
	public void updateTakenTiles() {
		for(Unit unit : units) {
			unit.getTakenTile().setTaken(true);
		}
	}
	
	public void unlockUnits() {
		for(Unit unit : units) {
			unit.setAvailable(true);
		}
	}
	
	public boolean isAttackable(Unit unit) {
		return attackableUnits.contains(unit);
	}
	
	public boolean isAttackAvailable() {
		return attackableUnits.size() != 0;
	}
	
	public void resetAttackableUnits() {
		for(Unit unit : attackableUnits) {
			unit.getTakenTile().reset();
		}
		attackableUnits.clear();
	}
	
	public void showAttackableUnits(Unit attackUnit) {
		int selectedRow = Map.getTileRow(attackUnit.getTakenTile());
		int selectedCol = Map.getTileColumns(attackUnit.getTakenTile());
		for(Unit unit : units) {
			int row = Map.getTileRow(unit.getTakenTile());
			int col = Map.getTileColumns(unit.getTakenTile());
			int distance = Math.abs(selectedRow - row) + Math.abs(selectedCol - col);
			if(distance == attackUnit.getAttackRange()) {
				attackableUnits.add(unit);
				unit.getTakenTile().pickAttack();
			}
		}
	}
	
	public void clear() {
		units.clear();
	}
	
	public int size() {
		return units.size();
	}
}

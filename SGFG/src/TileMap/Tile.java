package TileMap;

import Sprite.Sprite;

public class Tile extends Sprite {
	
	public static int INDEX = 0;
	private int id;
	private String name;
	private String defImage;
	private String pickImage;
	private String attackImage;
	private boolean taken;
	private int movementCost;
	
	public Tile(int x, int y, String name) {
		super(x, y);
		this.name = name;
		try {
			defImage = "/Tiles/" + this.name + ".png";
			pickImage = "/Tiles/" + this.name + "_picked.png";
			attackImage = "/Tiles/" + this.name + "_attack.png";
		} catch (Exception e) {
			e.printStackTrace();
		}
		init();
	}
	
	@Override
	protected void init() {
		setImage(defImage);
		taken = false;
		id = Tile.INDEX++;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(String name) {
		return name;
	}
	
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	
	public boolean isTaken() {
		return taken;
	}
	
	public void pick() {
		setImage(pickImage);
	}
	
	public void pickAttack() {
		setImage(attackImage);
	}
	
	public void reset() {
		setImage(defImage);
	}
	
	public void setMovementCost(int movementCost) {
		this.movementCost = movementCost;
	}
	
	public int getMovementCost() {
		return movementCost;
	}
	
	@Override
	public String toString() {
		return Integer.toString(id);
	}
}

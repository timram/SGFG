package Units;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import Items.Item;
import Items.Resource;
import Items.Weapon;
import Sprite.AbleToTakeTile;
import Sprite.Animated;
import Sprite.IMoveable;
import Sprite.Sprite;
import TileMap.Tile;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Unit extends Sprite implements AbleToTakeTile, IMoveable, Animated{
	private int destX, destY;
	private int stepPeriod;
	private String currentDirection;
	private ArrayList<BufferedImage> images;
	private Iterator<BufferedImage> imageSwitcher;
	private final int oneStepPeriod = 5;
	private final int SPEED = 3;
	private boolean selected;
	private Tile takenTile;
	private Iterator<Tile> route;
	private boolean available;
	
	protected String name;
	protected int maxHealth;
	protected int health;
	protected int stamina;
	protected int attack;
	protected int baseAttack;
	protected int agility;
	protected int intelegence;
	protected int strength;
	protected int attackRange;
	protected Weapon weapon;
	protected ArrayList<Item> inventory;
	protected ArrayList<Item> weapons;
	
	
	public Unit(int x, int y) {
		super(x, y);
		destX = x;
		destY = y;
		initStates();
		init();
	}
	
	public Unit(Tile tile) {
		this(0, 0);
		takeTile(tile);
		x = destX;
		y = destY;
	}
	
	@Override
	protected void init() {
		selected = false;
		available = true;
		stepPeriod = 0;
		route = new ArrayList<Tile>().iterator();
		images = new ArrayList<BufferedImage>();
		inventory = new ArrayList<Item>();
		weapons = new ArrayList<Item>();
		setDirection("forward");
		setImage(imageSwitcher.next());
	}
	
	private void setImage(BufferedImage image) {
		img = image;
		width = img.getWidth(null);
		height = img.getHeight(null);
	}
	
	private void setDirection(String direction) {
		if(direction.equals(currentDirection)) {
			return;
		}
		images.clear();
		currentDirection = direction;
		try {
			File[] files = (new File(getClass().getResource("/Units/"+ name + "/" + direction).toURI())).listFiles();
			for(File file : files) {	
				BufferedImage image = ImageIO.read(file);
				images.add(image);
			}
			imageSwitcher = images.iterator();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public boolean isOnWay() {
		return route.hasNext() || (x != destX || y != destY);
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
		if(!available) {	
			setImage("/Units/" + name + "/finished.png");
		}
		else {
			currentDirection = "";
			setDirection("forward");
			setImage(imageSwitcher.next());
		}
	}
	
	public void setCoord(Integer[] coord) {
		x = coord[0];
		y = coord[1];
		destX = x;
		destY = y;
		if(takenTile != null) {
			takenTile.setTaken(false);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public int getBaseAttack() {
		return baseAttack;
	}
	
	public void setBaseAttack(int baseAttack) {
		this.baseAttack = baseAttack;
	}
	
	public int getAgility() {
		return agility;
	}
	
	public void setAgility(int agility) {
		this.agility = agility;
	}
	
	public int getIntelegence() {
		return intelegence;
	}
	
	public void setIntelegence(int intelegence) {
		this.intelegence = intelegence;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public int getAttackRange() {
		return attackRange;
	}
	
	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
	public int getStamina() {
		return stamina;
	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		weapon.effect(this);
		weapon.setSelected(true);
	}
	
	public void resetWeapon() {
		if(weapon != null) {
			weapon.setSelected(false);
		}
		weapon = null;
		attack = baseAttack;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
	public ArrayList<Item> getWeapons() {
		return weapons;
	}
	
	private boolean isInInventory(ArrayList<Item> items, Item item) {
		for(Item it : items) {
			if(it.getName().equals(item.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public void addToWeapons(Weapon weapon) {
		if(!isInInventory(weapons, weapon)) {
			weapons.add(weapon);
		}
	}
	
	public void removeFromWeapons(Weapon weapon) {
		weapons.remove(weapon);
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void addToInventory(Resource item) {
		for(Item res : inventory) {
			if(res.getName().equals(item.getName())) {
				((Resource)res).increaseAmount();
				return;
			}
		}
		inventory.add(item);
	}
	
	public void removeFromInventory(Item item) {
		inventory.remove(item);
	}
	
	@Override
	public Tile getTakenTile() {
		return takenTile;
	}

	@Override
	public void takeTile(Tile tile) {
		if(takenTile != null) {
			takenTile.setTaken(false);
		}
		tile.setTaken(true);
		takenTile = tile;
		destX = tile.getX() + width / 4;
		destY = tile.getY() + height / 4;
	}
	
	public void takeTileWithoutMovement(Tile tile) {
		takeTile(tile);
		x = destX;
		y = destY;
	}
	
	public void setRoute(ArrayList<Tile> route) {
		this.route = route.iterator();
	}
	
	private void takeNextTile() {
		x = destX;
		y = destY;
		if(route.hasNext()) {
			takenTile.setTaken(false);
			takeTile(route.next());
		}
	}
	
	@Override
	public void move() {
		if(x < destX && Math.abs(x - destX) > SPEED) {
			x += SPEED;
			animateMovement("right");
		}
		else if(x > destX && Math.abs(x - destX) > SPEED) {
			x -= SPEED;
			animateMovement("left");
		}
		else if(y < destY && Math.abs(y - destY) > SPEED) {
			y += SPEED;
			animateMovement("forward");
		}
		else if(y > destY && Math.abs(y - destY) > SPEED) {
			y -= SPEED;
			animateMovement("backward");
		}
		else {
			takeNextTile();
		}
	}
	
	private void animateMovement(String direction) {
		setDirection(direction);
		animate();
	}
	
	@Override
	public void animate() {
		if(stepPeriod % oneStepPeriod == 0){
			if(imageSwitcher.hasNext()) {
				setImage(imageSwitcher.next());
			}
			else {
				imageSwitcher = images.iterator();
				setImage(imageSwitcher.next());
			}
		}
		stepPeriod++;
	}
	
	protected abstract void initStates();
}

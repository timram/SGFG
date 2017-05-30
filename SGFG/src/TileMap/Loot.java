package TileMap;

import java.util.Random;

import Items.ItemsFactory;

public class Loot extends StaticObject {
	private String resourceName;
	private Random rand;
	
	public Loot(int x, int y, String name) {
		super(x, y, name);
		initLoot();
	}
	
	public Loot(Tile tile, String name) {
		super(tile, name);
		initLoot();
	}
	
	private void initLoot() {
		rand = new Random();
		int count = ItemsFactory.resources.length;
		resourceName = ItemsFactory.resources[rand.nextInt(count)];
		takenTile.setTaken(false);
	}
	
	public String getResourceName() {
		return resourceName;
	}

}

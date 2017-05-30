package TileMap;

import Sprite.AbleToTakeTile;
import Sprite.Sprite;

public class StaticObject extends Sprite implements AbleToTakeTile{
	protected String name;
	protected Tile takenTile;
	
	public StaticObject(int x, int y, String name) {
		super(x, y);
		this.name = name;
		init();
	}
	
	public StaticObject(Tile tile, String name) {
		this(tile.getX(), tile.getY(), name);
		takeTile(tile);
	}

	@Override
	protected void init() {
		this.setImage("/Tiles/" + name + ".png");
	}

	@Override
	public void takeTile(Tile tile) {
		takenTile = tile;
		tile.setTaken(true);
		x = tile.getX() + width / 4;
		y = tile.getY() + height / 4;
	}

	@Override
	public Tile getTakenTile() {
		return takenTile;
	}

}

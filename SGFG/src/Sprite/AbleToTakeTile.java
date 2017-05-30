package Sprite;

import TileMap.Tile;

public interface AbleToTakeTile {
	public void takeTile(Tile tile);
	public Tile getTakenTile();
}

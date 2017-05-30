package TileMap;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

import FileReader.MapReader;
import PathFinder.PathFinder;
import Units.Unit;

public class Map {
	private int WIDTH;
	private int HEIGHT;
	private static int ROWS;
	private static int COLUMNS;
	private final int TILE_WIDTH = 50;
	private final int TILE_HEIGHT = 50;
	private Tile[][] tiles;
	private HashMap<Integer, ArrayList<Tile>> availableRouts;
	private ArrayList<Tile> availableSpotTiles;
	private ArrayList<StaticObject> objects;
	private ArrayList<Loot> loots;
	private HashMap<String, String> tileNames;
	private HashMap<String, Integer> tileMovementCosts;
	private	HashMap <String, HashMap<String, Integer>> spots;
	private String name;
	private PathFinder pathFinder;
	
	public Map(int width, int height, String name) {
		WIDTH = width;
		HEIGHT = height;
		this.name = name;
		availableRouts = new HashMap<Integer, ArrayList<Tile>>();
		availableSpotTiles = new ArrayList<Tile>();
		tileNames = new HashMap<String, String>();
		tileNames.put("s", "StoneTileSmall");
		tileNames.put("g", "Grass");
		tileMovementCosts = new HashMap<String, Integer>();
		tileMovementCosts.put("s", 1);
		tileMovementCosts.put("g", 2);
		objects = new ArrayList<StaticObject>();
		loots = new ArrayList<Loot>();
		loadMap();
		pathFinder = new PathFinder(this);
	}
	
	private void loadMap() {
		try {
			InputStream in = getClass().getResourceAsStream("/Map/" + name + ".map");
			MapReader reader = new MapReader(in);
			ROWS = reader.getRowsCount();
			COLUMNS = reader.getColsCount();
			Iterator<String> markup = reader.getMapMarkup().iterator();
			spots = reader.getSpots();
			tiles = new Tile[ROWS][COLUMNS];
			for(int i = 0; i < ROWS; i++) {
				for(int j = 0; j < COLUMNS; j++) {
					int y = i * TILE_HEIGHT;
					int x = j * TILE_WIDTH;
					if(markup.hasNext()) {
						String tileName = markup.next();
						tiles[i][j] = new Tile(x, y, tileNames.get(tileName));
						tiles[i][j].setMovementCost(tileMovementCosts.get(tileName));
					}
				}
			}
			HashMap<String, String> objs = reader.getObjects();
			for(String key : objs.keySet()) {
				String[] coords = key.split(" ", 0);
				objects.add(new StaticObject(getTile(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])),
						objs.get(key)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addStaticObject(Tile tile, String name) {
		objects.add(new StaticObject(tile, name));
	}
	
	public void addRandomLoot() {
		Random rand = new Random();
		int number = rand.nextInt(3);
		if(number == 0 && loots.size() < 4) {
			ArrayList<Tile> freeTiles = new ArrayList<Tile>();
			for(int i = 0; i < ROWS; i++) {
				for(int j = 0; j < COLUMNS; j++) {
					Tile tile = tiles[i][j];
					if(!tile.isTaken() && !isLootOnTile(tile)) {
						freeTiles.add(tile);
					}
				}
			}
			int count = freeTiles.size();
			Tile tile = freeTiles.get(rand.nextInt(count));
			loots.add(new Loot(tile, "Loot"));
		}
	}
	
	public boolean isLootOnTile(Tile tile) {
		for(Loot loot: loots) {
			if(loot.getTakenTile() == tile) {
				return true;
			}
		}
		return false;
	}
	
	public Loot getLoot(Tile tile) {
		for(Loot loot : loots) {
			if(loot.getTakenTile() == tile) {
				return loot;
			}
		}
		return null;
	}
	
	public void removeLoot(Loot loot) {
		if(loots.contains(loot)) {
			loots.remove(loot);
		}
	}
	
	public void drawMap(Graphics2D g2d, JPanel board) {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				Tile tile = tiles[i][j];
				g2d.drawImage(tile.getImage(), tile.getX(), tile.getY(), board);
			}
		}
		for(StaticObject obj : objects) {
			g2d.drawImage(obj.getImage(), obj.getX(), obj.getY(), board);
		}
		if(loots.size() > 0) {
			for(Loot loot: loots) {
				g2d.drawImage(loot.getImage(), loot.getX(), loot.getY(), board);
			}
		}
	}
	
	public static int getTileRow(Tile tile) {
		return tile.getId() / COLUMNS;
	}
	
	public static int getTileColumns(Tile tile) {
		return tile.getId() % COLUMNS;
	}
	
	public Tile getTile(int x, int y) {
		if(x >= 0 && x < ROWS && y >= 0 && y < COLUMNS) {
			return tiles[x][y];
		}
		return null;
	}
	
	public Tile getTile(int id) {
		if(id < 0 || id > ROWS * COLUMNS) {
			return null;
		}
		int row = id / COLUMNS;
		int col = id % COLUMNS;
		return tiles[row][col];
	}
	
	public Tile getTile(Rectangle rect) {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				Tile tile = tiles[i][j];
				if(tile.getBounds().intersects(rect)) {
					return tile;
				}
			}
		}
		return null;
	}
	
	public void highlightAvailableTiles(Unit unit) {
		int takenId = unit.getTakenTile().getId();
		int takenRow = takenId / COLUMNS;
		int takenCol = takenId % COLUMNS;
		int radius = unit.getStamina();
		for(int i = -radius; i <= radius; i++) {
			int startCol = takenCol - (radius - Math.abs(i));
			int endCol = takenCol + (radius - Math.abs(i));
			for(int col = startCol; col <= endCol; col++) {
				int row = takenRow + i;
				if(row >= 0 && row < ROWS && col >= 0 && col < COLUMNS) {
					Tile tile = tiles[row][col];
					if(!tile.isTaken()) {
						ArrayList<Tile> path = pathFinder.findPath(takenRow, takenCol, row, col, radius);
						if(path != null) {
							availableRouts.put(tile.getId(), path);
							tile.pick();
						}
					}
				}
			}
		}
	}
	
	public void highlightBeginningSpot(String side) {
		availableSpotTiles.clear();
		try {
			for(int x = spots.get(side).get("startRow"); x <= spots.get(side).get("endRow"); x++) {
				for(int y = spots.get(side).get("startCol"); y <= spots.get(side).get("endCol"); y++) {
					Tile tile = tiles[x][y];
					if(!tile.isTaken()) {
						availableSpotTiles.add(tile);
						tile.pick();
					}
					else {
						tile.reset();
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reset() {
		for(int id : availableRouts.keySet()) {
			getTile(id).reset();
		}
		availableRouts.clear();
		for(StaticObject obj: objects) {
			obj.getTakenTile().setTaken(true);
		}
		for(Tile tile : availableSpotTiles) {
			tile.reset();
		}
		availableSpotTiles.clear();
	}
	
	public void resetSpotTiles() {
		System.out.println("Reset Spot Tiles");
		for(int i = 0; i < availableSpotTiles.size(); i++) {
			availableSpotTiles.get(i).reset();
			System.out.println(i);
		}
		availableSpotTiles.clear();
	}
	
	public ArrayList<Tile> buildRoute(Tile dep, Tile dest, int availableDepth) {
		try {
			ArrayList<Tile> route = availableRouts.get(dest.getId());
			return route;
		} catch(Exception e) {
			return null;
		}
	}
	
	public int getRowsCount() {
		return ROWS;
	}
	
	public int getColumnsCount() {
		return COLUMNS;
	}
	
	public boolean isAvailable(Tile tile) {
		return availableRouts.containsKey(tile.getId());
	}
	
	public boolean isAvailableSpot(Tile tile) {
		return availableSpotTiles.contains(tile);
	}
	
	public int getTileHeight() {
		return TILE_HEIGHT;
	}
	
	public int getTileWidth() {
		return TILE_WIDTH;
	}
	
	public int routsCount() {
		return availableRouts.size();
	}
}

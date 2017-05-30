package PathFinder;

import java.util.ArrayList;

import TileMap.Map;
import TileMap.Tile;

public class PathFinder {
	private ArrayList<Node> closed;
	private SortedNodeList open;
	private Map map;
	private Node[][] nodes;
	
	public PathFinder(Map map) {
		this.map = map;
		nodes = new Node[map.getRowsCount()][map.getColumnsCount()];
		for(int x = 0; x < map.getRowsCount(); x++) {
			for(int y = 0; y < map.getColumnsCount(); y++) {
				nodes[x][y] = new Node(x, y);
			}
		}
		closed = new ArrayList<Node>();
		open = new SortedNodeList();
	}
	
	public ArrayList<Tile> findPath(int sx, int sy, int tx, int ty, int availableDepth) {
		nodes[sx][sy].setCost(0);
		nodes[sx][sy].setHeuristic(0);
		nodes[sx][sy].setDepth(0);
		closed.clear();
		open.clear();
		open.add(nodes[sx][sy]);
		nodes[tx][ty].resetParent();
		
		int maxDepth = 0;
		
		while(open.size() != 0 && maxDepth < availableDepth) {
			Node current = open.first();
			if(current == nodes[tx][ty]) {
				break;
			}
			open.remove(current);
			closed.add(current);
			
			for(int x = -1; x < 2; x++) {
				for(int y = -1; y < 2; y++) {
					if((x != 0 && y != 0) || (x == 0 && y == 0)) {
						continue;
					}
					int neighbourX = x + current.getX();
					int neighbourY = y + current.getY();
					if(isValidLocation(neighbourX, neighbourY)) {
						double nextStepCost = current.getCost() + getCost(neighbourX, neighbourY);
						Node neighbour = nodes[neighbourX][neighbourY];
						Tile neighbourTile = map.getTile(neighbourX, neighbourY);
						if(nextStepCost < neighbour.getCost()) {
							if(open.contains(neighbour)) {
								open.remove(neighbour);
							}
							if(closed.contains(neighbour)) {
								closed.remove(neighbour);
							}
						}
						if(!open.contains(neighbour) && !closed.contains(neighbour)) {
							neighbour.setCost(nextStepCost);
							neighbour.setHeuristic(getHeuristicCost(neighbourX, neighbourY, tx, ty));
							neighbour.setParent(current);
							neighbour.setDepth(current.getDepth() + neighbourTile.getMovementCost());
							maxDepth = Math.max(maxDepth, neighbour.getDepth());
							open.add(neighbour);
						}
					}
				}
			}
		}
		if(nodes[tx][ty].getParent() == null){
			return null;
		}	
		ArrayList<Tile> path = new ArrayList<Tile>();
		Node target = nodes[tx][ty];
		while(target != nodes[sx][sy]) {
			path.add(0, map.getTile(target.getX(), target.getY()));
			target = target.getParent();
		}
		path.add(0, map.getTile(sx, sy));
		return path;
	}
	
	private boolean isValidLocation(int x, int y) {
		boolean isValid = x >= 0 && y >=0 && x < map.getRowsCount() &&  y < map.getColumnsCount();
		if(isValid) {
			isValid = !map.getTile(x, y).isTaken();
		}
		return isValid;
	}
	
	private double getCost(int x, int y) {
		Tile tile = map.getTile(x, y);
		return tile.getMovementCost();
	}
	
	private double getHeuristicCost(int x, int y, int tx, int ty) {
		double dx = Math.abs(tx - x);
		double dy = Math.abs(ty - y);
		return dx + dy;
	}
}

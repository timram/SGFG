package FileReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MapReader {
	private Scanner scanner;
	private int rowsCount;
	private int colsCount;
	private HashMap<String, HashMap<String, Integer>> spots;
	private ArrayList<String> mapMarkup;
	private HashMap<String, String> objects;
	
	public MapReader(InputStream map) {
		scanner = new Scanner(map);
		readSize();
		readMapMarkup();
		readSpots();
		readObjects();
	}
	
	private String readCount(String label) {
		while(!scanner.hasNext(label)) {
			scanner.next();
		}
		scanner.next();
		return scanner.next();
	}
	
	private void readSize() {
		rowsCount = Integer.parseInt(readCount("rows:"));
		colsCount = Integer.parseInt(readCount("columns:"));
	}
	
	private void readMapMarkup() {
		mapMarkup = new ArrayList<String>();
		while(!scanner.hasNext("BEGIN")) {
			scanner.next();
		}
		scanner.next();
		while(!scanner.hasNext("END")) {
			mapMarkup.add(scanner.next());
		}
	}
	
	private void readSpots() {
		spots = new HashMap<String, HashMap<String, Integer>>();
		while(!scanner.hasNext("Spots:")) {
			scanner.next();
		}
		scanner.next();
		while(!scanner.hasNext("BEGIN")) {
			scanner.next();
		}
		while(!scanner.hasNext("Light:")) {
			scanner.next();
		}
		scanner.next();
		spots.put("light", new HashMap<String, Integer>());
		spots.get("light").put("startRow", scanner.nextInt());
		spots.get("light").put("endRow", scanner.nextInt());
		spots.get("light").put("startCol", scanner.nextInt());
		spots.get("light").put("endCol", scanner.nextInt());
	
		while(!scanner.hasNext("Dark:")) {
			scanner.next();
		}
		scanner.next();
		spots.put("dark", new HashMap<String, Integer>());
		spots.get("dark").put("startRow", scanner.nextInt());
		spots.get("dark").put("endRow", scanner.nextInt());
		spots.get("dark").put("startCol", scanner.nextInt());
		spots.get("dark").put("endCol", scanner.nextInt());
	
		while(!scanner.hasNext("END")) {
			scanner.next();
		}
	}
	
	private void readObjects() {
		objects = new HashMap<String, String>();
		while(!scanner.hasNext("Objects:")) {
			scanner.next();
		}
		scanner.next();
		while(!scanner.hasNext("BEGIN")) {
			scanner.next();
		}
		scanner.next();
		while(!scanner.hasNext("END")) {
			objects.put(scanner.nextInt() + " " + scanner.nextInt(), scanner.next());
		}
	}
	
	public int getRowsCount() {
		return rowsCount;
	}
	
	public int getColsCount() {
		return colsCount;
	}
	
	public ArrayList<String> getMapMarkup() {
		return mapMarkup;
	}
	
	public HashMap<String, String> getObjects() {
		return objects;
	}
	
	public HashMap<String, HashMap<String, Integer>> getSpots() {
		return spots;
	}
}

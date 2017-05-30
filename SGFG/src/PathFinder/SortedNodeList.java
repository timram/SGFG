package PathFinder;

import java.util.ArrayList;
import java.util.Collections;

public class SortedNodeList {
	private ArrayList<Node> list;
	
	public SortedNodeList() {
		list = new ArrayList<Node>();
	}
	
	public Node first() {
		return list.get(0);
	}
	
	public void clear() {
		list.clear();
	}
	
	@SuppressWarnings("unchecked")
	public void add(Node node) {
		list.add(node);
		Collections.sort(list);
	}
	
	public void remove(Node node) {
		list.remove(node);
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean contains(Node node) {
		return list.contains(node);
	}
}

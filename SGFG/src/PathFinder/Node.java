package PathFinder;

@SuppressWarnings("rawtypes")
public class Node implements Comparable{
	private int x;
	private int y;
	private double cost;
	private Node parent;
	private double heuristic;
	private int depth;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double getCost() {
		return cost;
	}
	
	public double getHeuristic() {
		return heuristic;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void resetParent() {
		parent = null;
	}
	
	@Override
	public int compareTo(Object other) {
		Node node = (Node) other;
		double value = heuristic + cost;
		double otherValue = node.heuristic + node.cost;
		return (value < otherValue) ? -1 : (value > otherValue) ? 1 :0;
	}
	
}

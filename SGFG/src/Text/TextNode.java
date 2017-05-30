package Text;

public abstract class TextNode {
	protected int x;
	protected int y;
	protected String message;
	protected boolean visible;
	
	public TextNode() {
		x = 0;
		y = 0;
		message = "";
		visible = false;
	}
	
	public TextNode(int x, int y) {
		this.x = x;
		this.y = y;
		message = "";
		visible = false;
	}
	
	public TextNode(int x, int y, String message) {
		this(x, y);
		this.message = message;
	}
	
	public TextNode(String message) {
		this(0, 0);
		this.message = message;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
}

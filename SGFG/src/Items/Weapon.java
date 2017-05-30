package Items;

public class Weapon extends Item{
	private boolean selected;
	
	public Weapon(int x, int y, String name) {
		super(x, y, name);
		selected = false;
	}
	
	@Override
	protected void initBasePath() {
		basePath = "Weapons";
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}

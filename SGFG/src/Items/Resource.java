package Items;

public class Resource extends Item{
	private int amount;

	public Resource(int x, int y, String name) {
		super(x, y, name);
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void increaseAmount() {
		++amount;
	}
	
	public void decreaseAmount() {
		--amount;
	}

	@Override
	protected void initBasePath() {
		basePath = "Resources";
	}
}

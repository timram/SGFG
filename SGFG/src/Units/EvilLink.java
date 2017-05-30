package Units;

public class EvilLink extends Link{
	public EvilLink(int x, int y) {
		super(x, y);
	}
	
	@Override
	protected void initStates() {
		super.initStates();
		this.name = "Evil Link";
	}
}

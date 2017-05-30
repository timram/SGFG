package Units;

public class EvilPaluten extends Paluten{
	public EvilPaluten(int x, int y) {
		super(x, y);
	}
	
	@Override
	protected void initStates() {
		super.initStates();
		this.name = "Evil Paluten";
	}
}

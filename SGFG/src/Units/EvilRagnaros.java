package Units;

public class EvilRagnaros extends Ragnaros{
	public EvilRagnaros(int x, int y) {
		super(x, y);
	}
	
	@Override
	protected void initStates() {
		super.initStates();
		this.name = "Evil Ragnaros";
	}
}

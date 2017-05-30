package Units;

public class EvilAlucard extends Alucard{

	public EvilAlucard(int x, int y) {
		super(x, y);
	}

	@Override
	protected void initStates() {
		super.initStates();
		this.name = "Evil Alucard";
	}

}

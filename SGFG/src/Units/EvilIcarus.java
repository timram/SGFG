package Units;

public class EvilIcarus extends Icarus{
	public EvilIcarus(int x, int y) {
		super(x, y); 
	}
	
	@Override
	protected void initStates() {
		super.initStates();
		this.name = "Evil Icarus";
	}
}

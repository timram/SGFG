package Units;

public class Ragnaros extends Unit{

	public Ragnaros(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initStates() {
		this.name = "Ragnaros";
		this.maxHealth = 35;
		this.health = 35;
		this.stamina = 2;
		this.baseAttack = 6;
		this.attackRange = 1;
		this.strength = 4;
		this.intelegence = 1;
		this.agility = 1;
	}
	

}

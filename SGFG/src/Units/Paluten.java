package Units;

public class Paluten extends Unit{

	public Paluten(int x, int y) {
		super(x, y);
	}

	@Override
	protected void initStates() {
		this.name = "Paluten";
		this.maxHealth = 20;
		this.health = 20;
		this.stamina = 4;
		this.baseAttack = 1;
		this.attackRange = 1;
		this.strength = 1;
		this.intelegence = 4;
		this.agility = 1;
	}
}

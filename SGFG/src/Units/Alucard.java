package Units;

public class Alucard extends Unit{
	public Alucard(int x, int y) {
		super(x, y);
	}

	@Override
	protected void initStates() {
		this.name = "Alucard";
		this.maxHealth = 22;
		this.health = 22;
		this.stamina = 30;
		this.baseAttack = 4;
		this.attackRange = 1;
		this.strength = 3;
		this.intelegence = 3;
		this.agility = 2;
	}
}

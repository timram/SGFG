package Units;

public class Icarus extends Unit{

	public Icarus(int x, int y) {
		super(x, y);
	}

	@Override
	protected void initStates() {
		this.name = "Icarus";
		this.maxHealth = 22;
		this.health = 22;
		this.stamina = 6;
		this.baseAttack = 2;
		this.attackRange = 1;
		this.strength = 2;
		this.intelegence = 2;
		this.agility = 4;
	}
}

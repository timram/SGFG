package Units;

public class Link extends Unit{

	public Link(int x, int y) {
		super(x, y);
	}

	@Override
	protected void initStates() {
		this.name = "Link";
		this.maxHealth = 24;
		this.health = 24;
		this.stamina = 3;
		this.baseAttack = 4;
		this.attackRange = 1;
		this.strength = 3;
		this.intelegence = 2;
		this.agility = 3;
	}
}

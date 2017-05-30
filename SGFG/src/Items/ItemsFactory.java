package Items;

import java.util.HashMap;

import Units.Unit;

public class ItemsFactory {
	
	private interface WeaponEffector {
		void effectAttack(int attackWeight, int attack, Unit unit);
	}
	
	protected static HashMap<String, Item> items;
	protected static HashMap<String, Effector> effectors;
	protected static HashMap<String, WeaponEffector> weaponEffectors;
	
	public static String[] resources = {"Butt_Buster", "Clobberin_Time", "Doctor_Strange", "Expired_Milk", "Its_Alive", 
			"Ivan_Water", "Pegasus_Boots", "Whale_Sperm"};
	
	static {
		initItems();
		initWeaponEffectors();
		initEffectors();
	}
	
	private static void initItems() {
		items = new HashMap<String, Item>();
		items.put("Butt_Buster", new Resource(0, 0, "Butt_Buster"));
		items.put("Clobberin_Time", new Resource(0, 0, "Clobberin_Time"));
		items.put("Doctor_Strange", new Resource(0, 0, "Doctor_Strange"));
		items.put("Expired_Milk", new Resource(0, 0, "Expired_Milk"));
		items.put("Its_Alive", new Resource(0, 0, "Its_Alive"));
		items.put("Ivan_Water", new Resource(0, 0, "Ivan_Water"));
		items.put("Pegasus_Boots", new Resource(0, 0, "Pegasus_Boots"));
		items.put("Whale_Sperm", new Resource(0, 0, "Whale_Sperm"));
		items.put("Axe", new Weapon(0, 0, "Axe"));
		items.put("Bow", new Weapon(0, 0, "Bow"));
		items.put("Magic", new Weapon(0, 0, "Magic"));	
		items.put("Spear", new Weapon(0, 0, "Spear"));
		items.put("Sword", new Weapon(0, 0, "Sword"));
	}
	
	private static void initWeaponEffectors() {
		weaponEffectors = new HashMap<String, WeaponEffector>();
		weaponEffectors.put("strength", (weight, attack, unit) -> {
			int unitStrength = unit.getStrength();
			int unitAttack = unit.getBaseAttack();
			int newAttack = (attack + unitAttack) - ((weight - unitStrength) * 2);
			unit.setAttack(newAttack);
		});
		
		weaponEffectors.put("intelegence", (inta, attack, unit) -> {
			int unitInta = unit.getIntelegence();
			int unitAttack = unit.getBaseAttack();
			int newAttack = (attack + unitAttack) - ((inta - unitInta) * 4);
			unit.setAttack(newAttack);
		});
		
		weaponEffectors.put("agile", (agile, attack, unit) -> {
			int unitAgile = unit.getAgility();
			int unitAttack = unit.getBaseAttack();
			int newAttack = (attack + unitAttack) - ((agile - unitAgile) * 2);
			unit.setAttack(newAttack);
		});
	}
	
	private static void initEffectors() {
		effectors = new HashMap<String, Effector>();
		effectors.put("Butt_Buster", (unit) -> {
			int health = unit.getHealth();
			unit.setHealth((health + 20 > unit.getMaxHealth()) ? unit.getMaxHealth() : health + 20);
		});
		effectors.put("Clobberin_Time", (unit) -> {
			int attack = unit.getAttack();
			unit.setAttack(attack + 10);
		});
		effectors.put("Doctor_Strange", (unit) -> {
			int health = unit.getHealth();
			unit.setHealth((health + 10 > unit.getMaxHealth()) ? unit.getMaxHealth() : health + 10);
		});
		effectors.put("Expired_Milk", (unit) -> {
			int attack = unit.getAttack();
			unit.setAttack(attack + 5);
		});
		effectors.put("Its_Alive", (unit) -> {
			
		});
		effectors.put("Ivan_Water", (unit) -> {
			int attack = unit.getAttack();
			unit.setAttack(attack + 15);
			int health = unit.getHealth();
			unit.setHealth((health - 5 <= 0) ? 1 : health - 5);
		});
		effectors.put("Pegasus_Boots", (unit) -> {
			int stamina = unit.getStamina();
			unit.setStamina(stamina + 4);
		});
		effectors.put("Whale_Sperm", (unit) -> {
			int attack = unit.getAttack();
			unit.setAttack(attack + 15);
			int health = unit.getHealth();
			unit.setHealth((health - 10 <= 0) ? 1 : health - 10);
		});
		effectors.put("Axe", (unit) -> {
			weaponEffectors.get("strength").effectAttack(4, 12, unit);
			unit.setAttackRange(1);
		});
		effectors.put("Bow", (unit) -> {
			weaponEffectors.get("agile").effectAttack(4, 13, unit);
			unit.setAttackRange(2);
		});
		effectors.put("Magic", (unit) -> {
			weaponEffectors.get("intelegence").effectAttack(4, 12, unit);
			unit.setAttackRange(2);
		});
		effectors.put("Sword", (unit) -> {
			weaponEffectors.get("strength").effectAttack(3, 8, unit);
			unit.setAttackRange(1);
		});
		effectors.put("Spear", (unit) -> {
			weaponEffectors.get("strength").effectAttack(2, 6, unit);
			unit.setAttackRange(1);
		});
	}
	
	public static Item makeItem(String name) {
		Item item;
		try {
			item = (Item)items.get(name).clone();
			item.setEffector(effectors.get(name));
			return item;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}

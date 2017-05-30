package Units;

import java.util.HashMap;

import Items.ItemsFactory;
import Items.Resource;
import Items.Weapon;
import TileMap.Tile;

public class UnitFactory {
	private static HashMap<String, String> defaultWeapons;
	
	static {
		defaultWeapons = new HashMap<String, String>();
		defaultWeapons.put("Alucard", "Sword");
		defaultWeapons.put("Paluten", "Magic");
		defaultWeapons.put("Link", "Spear");
		defaultWeapons.put("Icarus", "Bow");
		defaultWeapons.put("Ragnaros", "Axe");
	}
	
	public UnitFactory(){}
	
	public static void addItemsToUnit(Unit unit) {
		Resource docStrange = (Resource)ItemsFactory.makeItem("Doctor_Strange");
		docStrange.setAmount(3);
		unit.addToInventory(docStrange);
		Resource buttBuster = (Resource)ItemsFactory.makeItem("Butt_Buster");
		buttBuster.setAmount(5);
		unit.addToInventory(buttBuster);
		Resource expiredMilk = (Resource)ItemsFactory.makeItem("Expired_Milk");
		expiredMilk.setAmount(7);
		unit.addToInventory(expiredMilk);
		Resource itsAlive = (Resource)ItemsFactory.makeItem("Its_Alive");
		itsAlive.setAmount(7);
		unit.addToInventory(itsAlive);
		Resource clobberinTime = (Resource)ItemsFactory.makeItem("Clobberin_Time");
		clobberinTime.setAmount(4);
		unit.addToInventory(clobberinTime);
		Resource ivanWater = (Resource)ItemsFactory.makeItem("Ivan_Water");
		ivanWater.setAmount(8);
		unit.addToInventory(ivanWater);
		Resource pegasusBoots = (Resource)ItemsFactory.makeItem("Pegasus_Boots");
		pegasusBoots.setAmount(4);
		unit.addToInventory(pegasusBoots);
		Resource whaleSperm = (Resource)ItemsFactory.makeItem("Whale_Sperm");
		whaleSperm.setAmount(3);
		unit.addToInventory(whaleSperm);
	}
	
	@SuppressWarnings("unused")
	private static void setWeaponToUnit(String name, Unit unit) {
		Weapon weapon = (Weapon)ItemsFactory.makeItem(defaultWeapons.get(name));
		unit.setWeapon(weapon);
		unit.addToWeapons(weapon);
		if(!weapon.getName().equals("Sword")) {
			unit.addToWeapons((Weapon)ItemsFactory.makeItem("Sword"));
		}
	}
	
	public static Unit makeUnit(int x, int y, String name) {
		Unit unit = null;
		switch(name) {
		case "Paluten": unit = new Paluten(x, y); break;
		case "Icarus": unit = new Icarus(x, y); break;
		case "Link": unit = new Link(x, y); break;
		case "Alucard": unit = new Alucard(x, y); break;
		case "Ragnaros": unit = new Ragnaros(x, y); break;
		}
		addItemsToUnit(unit);
		setWeaponToUnit(name, unit);
		return unit;
	}
	
	public static Unit makeUnit(Tile tile, String name) {
		Unit unit = makeUnit(0, 0, name);
		unit.takeTileWithoutMovement(tile);
		return unit;
	}
	
	public static Unit makeEvilUnit(int x, int y, String name) {
		Unit unit = null;
		switch(name) {
		case "Paluten": unit = new EvilPaluten(x, y); break;
		case "Icarus": unit = new EvilIcarus(x, y); break;
		case "Link": unit = new EvilLink(x, y); break;
		case "Alucard": unit = new EvilAlucard(x, y); break;
		case "Ragnaros": unit = new EvilRagnaros(x, y); break;
		}
		addItemsToUnit(unit);
		setWeaponToUnit(name, unit);
		return unit;
	}
	
	public static Unit makeEvilUnit(Tile tile, String name) {
		Unit unit = makeEvilUnit(0, 0, name);
		unit.takeTileWithoutMovement(tile);
		return unit;
	}
}

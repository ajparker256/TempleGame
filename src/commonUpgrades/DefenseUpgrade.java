package commonUpgrades;

import grid.Tile;
import upgrades.Upgrade;

public class DefenseUpgrade extends Upgrade {

	public DefenseUpgrade(String explanation, int rarity) {
		super(explanation, rarity);
	}
	
	public void upgrade(Tile tile) {
		//tile.setDefense(tile.getDefense*1.2);
	}
}

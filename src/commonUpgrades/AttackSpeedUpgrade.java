package commonUpgrades;

import grid.Tile;
import upgrades.Upgrade;

public class AttackSpeedUpgrade extends Upgrade{
	
	public AttackSpeedUpgrade(String explanation, int rarity) {
		super(explanation, rarity);
	}
	
	@Override
	public void upgrade(Tile upgradedTrap) {
		//upgradedTrap.setAttackSpd(upgradedTrap.getAttackspd()*1.2); //Multiplies current by some constant
	}

}

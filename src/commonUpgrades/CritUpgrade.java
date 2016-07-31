package commonUpgrades;

import traps.Trap;
import upgrades.Upgrade;

public class CritUpgrade extends Upgrade{

private static double critChanceAdded = .08;
	
	public CritUpgrade() {
		super("Increase the chance of critical hit on each attack by "+(int)(critChanceAdded*100)+"%.", 10);
		id = 2;
	}
	
	@Override
	public void upgrade(Trap trapBeingUpgraded) {
		trapBeingUpgraded.setCritChance(trapBeingUpgraded.getCritChance()+critChanceAdded);
	}
}

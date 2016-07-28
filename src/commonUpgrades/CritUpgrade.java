package commonUpgrades;

import traps.Trap;
import upgrades.Upgrade;

public class CritUpgrade extends Upgrade{

private static double critChanceAdded = .08;
	
	public CritUpgrade() {
		super("This increases the chance of critical hit on each hit by "+(int)(critChanceAdded*100)+"%.", 10);
	}
	
	@Override
	public void upgrade(Trap trapBeingUpgraded) {
		trapBeingUpgraded.setCritChance(trapBeingUpgraded.getCritChance()+critChanceAdded);
	}
}

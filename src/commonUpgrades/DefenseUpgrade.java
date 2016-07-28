package commonUpgrades;

import traps.Trap;
import upgrades.Upgrade;

public class DefenseUpgrade extends Upgrade {

private static int defenseIncrease = 1;
	
	public DefenseUpgrade() {
		super("This increases the defense of the trap by "+defenseIncrease+".", 10);
	}
	
	@Override
	public void upgrade(Trap trapBeingUpgraded) {
		trapBeingUpgraded.setDefense(trapBeingUpgraded.getDefense()+defenseIncrease);
	}
}

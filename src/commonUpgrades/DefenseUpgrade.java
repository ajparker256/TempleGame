package commonUpgrades;

import traps.Trap;
import upgrades.Upgrade;

public class DefenseUpgrade extends Upgrade {

private static int defenseIncrease = 1;
	
	public DefenseUpgrade() {
		super("Increase the defense of this trap by "+defenseIncrease+".", 10);
		id = 4;
	}
	
	@Override
	public void upgrade(Trap trapBeingUpgraded) {
		trapBeingUpgraded.setDefense(trapBeingUpgraded.getDefense()+defenseIncrease);
	}
}

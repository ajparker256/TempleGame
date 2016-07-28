package commonUpgrades;

import traps.Trap;
import upgrades.Upgrade;

public class RangeUpgrade extends Upgrade{

	private static int rangeIncrease = 1;
	
	public RangeUpgrade() {
		super("This increases the range of the trap by "+rangeIncrease+".", 10);
	}
	
	@Override
	public void upgrade(Trap trapBeingUpgraded) {
		trapBeingUpgraded.setRange(trapBeingUpgraded.getRange()+rangeIncrease);
	}
}

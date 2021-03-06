package commonUpgrades;

import traps.Trap;
import upgrades.Upgrade;

public class RangeUpgrade extends Upgrade{

	private static int rangeIncrease = 1;
	
	public RangeUpgrade() {
		super("Increase the range of this trap by "+rangeIncrease+".", 10);
		id = 6;
	}
	
	@Override
	public void upgrade(Trap trapBeingUpgraded) {
		trapBeingUpgraded.setRange(trapBeingUpgraded.getRange()+rangeIncrease);
	}
}

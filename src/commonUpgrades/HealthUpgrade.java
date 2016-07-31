package commonUpgrades;

import traps.Trap;
import upgrades.Upgrade;

public class HealthUpgrade extends Upgrade{
	
	private static double multiplier = .2;
	
	public HealthUpgrade() {
		super("Increase the health of this trap by "+(int)(multiplier*100)+"%.", 10);
		id = 5;
	}
	
	@Override
	public void upgrade(Trap trapBeingUpgraded) {
		trapBeingUpgraded.setMaxHp((int)(trapBeingUpgraded.getMaxHp()*(1+multiplier)));
	}
}

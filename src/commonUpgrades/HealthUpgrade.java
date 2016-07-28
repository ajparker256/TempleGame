package commonUpgrades;

import traps.Trap;
import upgrades.Upgrade;

public class HealthUpgrade extends Upgrade{
	
	private static double multiplier = .2;
	
	public HealthUpgrade() {
		super("This increases the health of the trap by "+(int)(multiplier*100)+"%.", 10);
	}
	
	@Override
	public void upgrade(Trap trapBeingUpgraded) {
		trapBeingUpgraded.setMaxHp((int)(trapBeingUpgraded.getMaxHp()*(1+multiplier)));
	}
}

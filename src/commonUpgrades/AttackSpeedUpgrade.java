package commonUpgrades;

import traps.Trap;
import upgrades.Upgrade;

public class AttackSpeedUpgrade extends Upgrade{
	
private static double attackSpeedIncrease = .2;
	
	public AttackSpeedUpgrade() {
		super("This increases the attack speed of the trap by "+(int)(attackSpeedIncrease*100)+"%.", 10);
	}
	
	@Override
	public void upgrade(Trap trapBeingUpgraded) {
		trapBeingUpgraded.setMaxCd(trapBeingUpgraded.getMaxCd()/(1+attackSpeedIncrease));
	}

}

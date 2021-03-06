package commonUpgrades;

import traps.Trap;
import upgrades.Upgrade;

public class AttackSpeedUpgrade extends Upgrade{
	
private static double attackSpeedIncrease = .2;
	
	public AttackSpeedUpgrade() {
		super("Increase the attack speed of this trap by "+(int)(attackSpeedIncrease*100)+"%.", 10);
		id = 1;
	}
	
	@Override
	public void upgrade(Trap trapBeingUpgraded) {
		trapBeingUpgraded.setMaxCd(trapBeingUpgraded.getMaxCd()/(1+attackSpeedIncrease));
	}

}

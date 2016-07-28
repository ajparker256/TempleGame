package commonUpgrades;

import traps.Trap;
import upgrades.Upgrade;

public class DamageUpgrade extends Upgrade{

private static double damageIncrease = .2;
	
	public DamageUpgrade() {
		super("This increases the damage of the trap by "+(int)(100*damageIncrease)+"%.", 10);
		id = 3;
	}
	
	@Override
	public void upgrade(Trap trapBeingUpgraded) {
		trapBeingUpgraded.setDamage((int)(trapBeingUpgraded.getDamage()*(1+damageIncrease)));
	}
	
}

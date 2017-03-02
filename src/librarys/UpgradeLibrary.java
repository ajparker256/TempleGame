package librarys;

import java.util.ArrayList;

import commonUpgrades.*;
import upgrades.Upgrade;

public class UpgradeLibrary {
	
	//Upgrades
	public static AttackSpeedUpgrade attackSpeedRef;
	
	public static HealthUpgrade hpRef;
	
	public static CritUpgrade critRef;
	
	public static DamageUpgrade damageRef;
	
	public static RangeUpgrade rangeRef;
	
	public static DefenseUpgrade defenseRef;
	
	//Trap Upgrade Pools
	public static ArrayList<Upgrade> arrowTrapUpgrades;
	
	public static ArrayList<Upgrade> tikiTrapUpgrades;
	
	public static ArrayList<Upgrade> treasureTrapUpgrades;
	
	public static ArrayList<Upgrade> cursedIdolUpgrades;
	
	public static void init() {
		//Initializes example upgrades for reference in the arraylists
		attackSpeedRef = new AttackSpeedUpgrade();
		hpRef = new HealthUpgrade();
		critRef = new CritUpgrade();
		damageRef = new DamageUpgrade();
		rangeRef = new RangeUpgrade();	
		defenseRef = new DefenseUpgrade();
		
		arrowTrapUpgrades = new ArrayList<Upgrade>();
		arrowTrapUpgrades.add(hpRef);
		arrowTrapUpgrades.add(damageRef);
		arrowTrapUpgrades.add(critRef);
		arrowTrapUpgrades.add(attackSpeedRef);
		arrowTrapUpgrades.add(rangeRef);
		arrowTrapUpgrades.add(defenseRef);
		
		tikiTrapUpgrades = new ArrayList<Upgrade>();
		tikiTrapUpgrades.add(hpRef);
		tikiTrapUpgrades.add(damageRef);
		tikiTrapUpgrades.add(critRef);
		tikiTrapUpgrades.add(attackSpeedRef);
		tikiTrapUpgrades.add(defenseRef);
		//tikiTrapUpgrades.add(rangeRef);
		
		treasureTrapUpgrades = new ArrayList<Upgrade>();
		treasureTrapUpgrades.add(hpRef);
		treasureTrapUpgrades.add(defenseRef);
		
		cursedIdolUpgrades = new ArrayList<Upgrade>();
		cursedIdolUpgrades.add(hpRef);
		cursedIdolUpgrades.add(defenseRef);
	}
	
	public static Upgrade getUpgradeOfType(int upgradeId) {
		if(upgradeId == hpRef.getId())
			return new HealthUpgrade();
		if(upgradeId == attackSpeedRef.getId())
			return new AttackSpeedUpgrade();
		if(upgradeId == defenseRef.getId())
			return new DefenseUpgrade();
		if(upgradeId == critRef.getId())
			return new CritUpgrade();
		if(upgradeId == rangeRef.getId())
			return new RangeUpgrade();
		if(upgradeId == damageRef.getId()) {
			return new DamageUpgrade();
		}
		System.out.println("Error in Upgrade getUpgradeOfType Method");
		return null;
	}
	
	public static Upgrade getUpgrade(int trapId) {
		ArrayList<Upgrade> pool = new ArrayList<Upgrade>();
		//Identification of pool
		if(trapId == 2)
			pool = cursedIdolUpgrades;
		if(trapId == 4)
			pool = treasureTrapUpgrades;
		if(trapId == 5) 
			pool = arrowTrapUpgrades;
		if(trapId == 9)
			pool = tikiTrapUpgrades;
		
		//Randomization calculation
		int total = 0;
		for(Upgrade u: pool) {
			total+=u.getNumOfEntries();
		}
		int num = (int)(Math.random()*total);
		for(Upgrade u: pool) {
			num -= u.getNumOfEntries();
			if(num<=0) {
				return getUpgradeOfType(u.getId());
			}
		}
		
		System.out.println("Error case in Upgrade Library retrieval of upgrade");
		return null;
		
	}

}

package trapPaths;

import java.awt.Point;

import main.Main;
import traps.Fire;
import traps.TikiTrap;
import upgrades.Upgrade;

public class TikiRing extends TikiTrap{

	public TikiRing(int x, int y, float size, int floor) {
		super(x, y, size, floor);
		damage *= 1.5;
		hp *= 1.5;
		defense *= 1.5;
		//guiTexture = insert the new texture here, or fragments of it based on level here.
	}
	
	@Override
	public void setTriggers(){
		
		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++) {
				if(i+x > 0 && i+x<Main.gridsReadOnly.get(floor).getWidth() && j+y > 0 && j+y <Main.gridsReadOnly.get(floor).getWidth()) {
					if(j != 0 && i != 0) {
						Main.gridsReadOnly.get(floor).getTile(x+i, y+j).addTrapRef(new Point(this.x, this.y));
					}
				}
			}
		}
	}
	
	@Override
	public void whenTriggered(Point p){
		for(Upgrade u : onTrigger) {
			u.upgrade(this);
		}
		if(cooldown<=0){
			boolean fire = true;
			//Up
			double tempDamage = getRealDamage();
		
			if(fire) {

				for(int i = -1; i<2; i++) {
					for(int j = -1; j<2; j++) {
						if(i+x > 0 && i+x<Main.gridsReadOnly.get(floor).getWidth() && j+y > 0 && j+y <Main.gridsReadOnly.get(floor).getWidth()) {
							if(j != 0 && i != 0) {
								Main.projectiles.add(new Fire(1, x+i, y+j, super.floor, tempDamage));
							}
						}
					}
				}
				isFiring = true;
				for(Upgrade u : onFire) {
					u.upgrade(this);
				}
				cooldown=maxCd; 
			}
		}
	}
	
	public void claimUpgrades(TikiTrap trapBeingUpgraded) {
		for(Upgrade u : trapBeingUpgraded.getAllUpgrades()) {
			this.upgrade(u);
		}
	}
}

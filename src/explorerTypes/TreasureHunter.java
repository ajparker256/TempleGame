package explorerTypes;

import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import pathing.Group;

public class TreasureHunter extends Explorer{

	
	public TreasureHunter(Group group) {
		super(group);
		super.idle=new GuiTexture(GuiLibrary.treasureFinderStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.treasureFinderRunning,location,unitSize);
		super.setDelay(25);

		this.id = 3;
	}
	
	@Override
	public void damage(double damageTaken) {
		super.hp-=damageTaken;
		if(hp<=0){
			super.kill=true;
		}
		
	}
}

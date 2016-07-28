package explorerTypes;

import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import pathing.Group;

public class TreasureHunter extends Explorer{

	//IMPORTANT!!! SQUADS SHOULD BE LIMITED TO 1 TREASUREHUNTER!!! (ONLY ONE WILL AFFECT PATHFINDING)
	
	public TreasureHunter(Group group) {
		super(group);
		super.idle=new GuiTexture(GuiLibrary.explorerStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.explorer,location,unitSize);
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

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
		super.animation=new Animation(AnimationLibrary.explorerX,location,unitSize);
		super.animation.setDelay(25);
		this.idley=GuiLibrary.explorerStanding;
		this.idlex=GuiLibrary.explorerStanding1;
		this.animationx=AnimationLibrary.explorerX;
		this.animationy=AnimationLibrary.explorerY;
		this.id = 3;
	}
}

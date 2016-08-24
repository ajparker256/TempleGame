package explorerTypes;

import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import pathing.Group;

public class Miner extends Explorer{

	public Miner(Group group) {
		super(group);
		super.idle=new GuiTexture(GuiLibrary.minerStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.minerRunning,location,unitSize);
		super.animation.setDelay(25);

		this.id = 1;
	}

}

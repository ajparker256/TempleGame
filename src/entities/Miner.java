package entities;

import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;

public class Miner extends Explorer{

	public Miner(Group group) {
		super(group);
		super.idle=new GuiTexture(GuiLibrary.minerStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.minerX,location,unitSize);
		super.animation.setDelay(25);
		this.idley=GuiLibrary.minerStanding;
		this.idlex=GuiLibrary.minerStanding1;
		this.animationx=AnimationLibrary.minerX;
		this.animationy=AnimationLibrary.minerY;
		this.id = 1;
	}

}

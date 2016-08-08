package explorerTypes;

import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import pathing.Group;

public class Exploder extends Explorer{

	private int explosiveDamage;

	public Exploder(Group group) {
		super(group);
		this.explosiveDamage=1000;
		super.idle=new GuiTexture(GuiLibrary.minerStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.miner,location,unitSize);
		super.animation.setDelay(25);
		super.damage=1;
	}

	public int getExplosive() {
		return explosiveDamage;
		
	}

}

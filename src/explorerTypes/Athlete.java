package explorerTypes;

import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import pathing.Group;

public class Athlete extends Explorer{

	public Athlete(Group group) {
		super(group);
		super.idle=new GuiTexture(GuiLibrary.athleteStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.athleteRunning,location,unitSize);
		super.animation.setDelay(25);
		this.id = 1;
		super.providedSpeedBonus=0.5f;
	}

}

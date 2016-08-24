package explorerTypes;

import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import pathing.Group;

public class Exploder extends Explorer{

	private int explosiveDamage;
	private int maxCd;
	private int cd;

	public Exploder(Group group) {
		super(group);
		this.explosiveDamage=1000;
		super.idle=new GuiTexture(GuiLibrary.tntManStanding,location,unitSize);
		super.animation=new Animation(AnimationLibrary.tntManRunning,location,unitSize);
		super.animation.setDelay(25);
		super.damage=1;
		this.maxCd=10000;
		this.cd=maxCd;
	}

	public int getExplosive() {
		if(cd<=0){
			cd=maxCd;
		return explosiveDamage;
		}
		return 0;
		
		
	}
	@Override 
	public void tick(int milli){
		cd-=milli;
	}

}

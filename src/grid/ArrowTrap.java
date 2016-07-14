package grid;

import org.lwjgl.util.vector.Vector2f;

import entities.Projectile;
import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import main.Main;
import renderEngine.DisplayManager;

public class ArrowTrap extends Tile{
	
	protected int hp;
	protected int level;
	protected int direction;
	protected Animation firing;
	protected boolean isFiring;
	
	public ArrowTrap(int x, int y, float size,int direction) {
		super(x, y, size, Main.grid.getLoc());
		super.passable=false;
		super.canInteract=true;
		this.direction=direction;
		this.hp=100;
		this.texture=1;
		this.guiTexture=(new GuiTexture(GuiLibrary.arrowTrap1,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Arrow Trap";
		id = 5;
		isFiring = false;
		firing = new Animation(AnimationLibrary.crossBowFiring, position, new Vector2f(size, (float)(size*DisplayManager.getAspectratio())));
		firing.setDelay(25);
	}
	
	public void trigger(){
		Main.projectiles.add(new Projectile(direction,x,y,super.floor));
		isFiring = true;
	}
	
	@Override
	public GuiTexture drawTile() {
		if(isFiring) {
			GuiTexture temp = firing.getFrameNoLoop();
			if(temp == null) {
				return guiTexture;
			} else {
				isFiring = false;
				return temp;
			}
		} 
		return guiTexture;
	}
}
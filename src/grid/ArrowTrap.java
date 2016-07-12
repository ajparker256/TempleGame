package grid;

import org.lwjgl.util.vector.Vector2f;

import entities.Projectile;
import gui.GuiTexture;
import librarys.TextureLibrary;
import main.Main;
import renderEngine.DisplayManager;

public class ArrowTrap extends Tile{
	
	protected int hp;
	protected int level;
	protected int direction;
	
	public ArrowTrap(int x, int y, float size,int direction) {
		super(x, y, size, Main.grid.getLoc());
		super.passable=false;
		super.canInteract=true;
		this.direction=direction;
		this.hp=100;
		this.texture=1;
		this.guiTexture=(new GuiTexture(TextureLibrary.getTile(texture),position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Arrow Trap";
		id = 1;
	}
	public void trigger(){
		Main.projectiles.add(new Projectile(direction,x,y,super.floor));
	}
}
package grid;

import gui.GuiTexture;
import librarys.GuiLibrary;
import main.Main;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.DisplayManager;

public class Dirt extends Tile{
	
	protected int level;
	
	public Dirt(int x, int y, float size) {
		super(x, y, size, Main.grid.getLoc());
		super.passable=false;
		super.canInteract=true;
		this.texture=1;
		this.guiTexture=(new GuiTexture(GuiLibrary.dirt0,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Dirt!";
		id = 1;
	}
	
	
	public Dirt(int x, int y, float size, Vector2f location) {
		super(x, y, size, location);
		super.passable=false;
		super.canInteract=true;
		this.hp=3000;
		this.texture=1;
		this.guiTexture=(new GuiTexture(GuiLibrary.dirt0,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Dirt!";
		id = 1;
	}

	
	@Override
	public Tile copy() {
		Tile t = new Dirt(x, y, size, location);
		t.setTrapRefs(trapRefs);
		t.upgrade(level);
		return t;
	}
	
	public void upgrade(int newLevel) {
		level = newLevel;
		if(level == 0)
			guiTexture=new GuiTexture(GuiLibrary.dirt0,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio())));	
		else if(level == 1)
			guiTexture = new GuiTexture(GuiLibrary.rock1, position, new Vector2f(size, (float)(size*DisplayManager.getAspectratio())));
		else if(level == 2)
			guiTexture = new GuiTexture(GuiLibrary.rock2, position, new Vector2f(size, size*(float)DisplayManager.getAspectratio()));
		else if(level == 3)
			guiTexture = new GuiTexture(GuiLibrary.rock3, position, new Vector2f(size, size*(float)DisplayManager.getAspectratio()));
		else if(level == 4)
			guiTexture = new GuiTexture(GuiLibrary.rock4, position, new Vector2f(size, size*(float)DisplayManager.getAspectratio()));
		else if(level == 5)
			guiTexture = new GuiTexture(GuiLibrary.rock5, position, new Vector2f(size, size*(float)DisplayManager.getAspectratio()));
		else if(level == 6)
			guiTexture = new GuiTexture(GuiLibrary.rock6, position, new Vector2f(size, size*(float)DisplayManager.getAspectratio()));
		else if(level == 7)
			guiTexture = new GuiTexture(GuiLibrary.rock7, position, new Vector2f(size, size*(float)DisplayManager.getAspectratio()));
		hp = 1000*level;
		if(hp == 0) {
			hp += 100;
		}
	}
	
	
	

}

package grid;

import gui.GuiTexture;
import librarys.GuiLibrary;
import main.Main;

import org.lwjgl.util.vector.Vector2f;

import entities.Explorer;
import entities.Group;
import renderEngine.DisplayManager;

public class Dirt extends Tile{
	
	protected int hp;
	protected int level;
	
	public Dirt(int x, int y, float size) {
		super(x, y, size, Main.grid.getLoc());
		super.passable=false;
		super.canInteract=true;
		this.hp=100;
		this.texture=1;
		this.guiTexture=(new GuiTexture(GuiLibrary.dirt0,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Dirt!";
		id = 1;
	}
	
	
	public Dirt(int x, int y, float size, Vector2f location) {
		super(x, y, size, location);
		super.passable=false;
		super.canInteract=true;
		this.hp=100;
		this.texture=1;
		this.guiTexture=(new GuiTexture(GuiLibrary.dirt0,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Dirt!";
		id = 1;
	}
	@Override
	public void interact(Group g){
		int bonusDamage = 0;
		for(Explorer e : g.getGroup()) {
			if(e.getId() == 2) {
				bonusDamage += 5*e.getDamage();
			} else {
				bonusDamage += e.getDamage();
			}
		}
		hp-=(bonusDamage);
		if(hp<=0){
			Blank blank = new Blank(super.x, super.y, super.size, Main.grid.getLoc());
			blank.setTrapRefs(trapRefs);
			Main.grids.get(g.getFloor()).setTile(super.x, super.y, blank);
		}
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

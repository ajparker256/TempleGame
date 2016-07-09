package grid;

import gui.GuiTexture;
import librarys.TextureLibrary;
import main.Main;

import org.lwjgl.util.vector.Vector2f;

import entities.Group;
import renderEngine.DisplayManager;

public class Dirt extends Tile{
	
	private int hp;
	
	
	public Dirt(int x, int y, float size) {
		super(x, y, size, Main.grid.getLoc());
		System.out.println(size);
		super.passable=false;
		super.canInteract=true;
		this.hp=100;
		this.texture=1;
		this.guiTexture=(new GuiTexture(TextureLibrary.getTile(texture),position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Dirt!";
		id = 1;
	}
	
	
	public Dirt(int x, int y, float size, Vector2f location) {
		super(x, y, size, location);
		super.passable=false;
		super.canInteract=true;
		this.hp=100;
		this.texture=1;
		this.guiTexture=(new GuiTexture(TextureLibrary.getTile(texture),position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Dirt!";
		id = 1;
	}
	@Override
	public void interact(Group g){
		hp-=1;
		if(hp<=0){
			Main.grid.setTile(super.x, super.y, new Blank(super.x, super.y, super.size, Main.grid.getLoc()));
		}
	}
	
	@Override
	public Tile copy() {
		return new Dirt(x, y, size);
	}
	
	

}

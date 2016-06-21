package grid;

import gui.GuiTexture;
import librarys.TextureLibrary;
import main.Main;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.DisplayManager;

public class Dirt extends Tile{
	
	private int hp;
	public Dirt(int x, int y, float size, Vector2f location) {
		super(x, y, size, location);
		super.passable=false;
		super.canInteract=true;
		this.hp=1000;
		this.texture=1;
		this.guiTexture=(new GuiTexture(TextureLibrary.getTile(texture),position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Dirt!";
	}
	@Override
	public void interact(){
		hp-=1;
		if(hp<=0){
			Main.grid.setTile(super.x, super.y, new Blank(x,y,super.size,super.location));
		}
	}
	
	

}

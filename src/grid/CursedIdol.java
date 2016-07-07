package grid;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.TextureLibrary;
import main.Main;
import renderEngine.DisplayManager;

public class CursedIdol extends Tile{

	//This is the upgrading power given when picked up by the explorers
	private int value;
	
	
	
	public CursedIdol(int x, int y, float size) {
		super(x, y, size, Main.grid.getLoc());
		super.passable=false;
		super.canInteract=true;
		this.value = 100;
		this.texture=1;
		this.guiTexture=(new GuiTexture(GuiLibrary.idolOnBlank,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Idol!";
		id = 2;
	}
	
	@Override
	public void interact(){
		value--;
		if(value%5 == 0)
			Main.money++;
		if(value<=0){
			Main.grid.setTile(super.x, super.y, new Blank(super.x, super.y, super.size, Main.grid.getLoc()));
		}
		
	}
}

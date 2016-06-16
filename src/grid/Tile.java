package grid;

import entities.Unit;
import gui.GuiTexture;

import java.util.ArrayList;

import librarys.TextureLibrary;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.DisplayManager;

// This is the superclass for all tiles contained in a grid

public class Tile {
	
	//the location of this tile in the grid, stores column then row (x then y)
	//the units contained within this tile
		
	protected Vector2f position;
	private int texture;
	private GuiTexture guiTexture;
	
	//creates a tile in location loc, give location in column then row
	public Tile(int x, int y, float size, Vector2f location){
		this.position=new Vector2f (location.x+((x-size/2)*size*2),(float) (location.y+((y-size/2)*(size*2*DisplayManager.getAspectratio()))));
		this.texture=1;
		this.guiTexture=(new GuiTexture(TextureLibrary.getTile(texture),position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		}
	public GuiTexture drawTile(){
		return guiTexture;
	}
	




}

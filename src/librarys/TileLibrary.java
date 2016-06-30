package librarys;

import grid.*;
import main.Main;

public class TileLibrary {

	//This is a library that creates tiles based off of id's
	
	public static Tile getTile(int x, int y, float size, int id) {
		if(id == 0)
			return new Blank(x, y, size, Main.grid.getLoc());
		if(id == 1) 
			return new Dirt(x, y, size, Main.grid.getLoc());
		return null;
	}
}

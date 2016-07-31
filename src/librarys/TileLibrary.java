package librarys;

import grid.*;
import main.Main;
import traps.*;

public class TileLibrary {

	//This is a library that creates tiles based off of id's
	
	public static Tile getTile(int x, int y, float size, int id) {
		if(id == 0)
			return new Blank(x, y, size, Main.grids.get(Main.grid.getFloor()).getLoc(), Main.grid.getFloor());
		if(id == 1) 
			return new Dirt(x, y, size, Main.grids.get(Main.grid.getFloor()).getLoc(), Main.grid.getFloor());
		if(id == 2)
			return new CursedIdol(x, y, size, Main.grid.getFloor());
		if(id == 4) 
			return new TreasureTrap(x, y, size, Main.grid.getFloor());
		//Arrow trap rotations (Arrow trap id = 5 + direction - 1)
		if(id == 5) 
			return new ArrowTrap(x, y, size, 1, Main.grid.getFloor());
		if(id == 6) 
			return new ArrowTrap(x, y, size, 3, Main.grid.getFloor());
		if(id == 7) 
			return new ArrowTrap(x, y, size, 2, Main.grid.getFloor());
		if(id == 8) 
			return new ArrowTrap(x, y, size, 4, Main.grid.getFloor());
		if(id == 9) 
			return new TikiTrap(x, y, size, Main.grid.getFloor());
		
		if(id == -2)
			return new Exit(x, y, size, Main.grid.getFloor());
		
		return null;
	}
}

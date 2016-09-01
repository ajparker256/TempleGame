package librarys;

import java.util.HashMap;

import grid.*;
import main.Main;
import traps.*;

public class TileLibrary {

	//This is a library that creates tiles based off of id's
	
	private static final int FIRST_TRAP_ID = 2;
	private static final float SIZE = Grid.getTileSize();
	
	public static Tile getTile(int x, int y, int id, int floor) {
		if(id == 0)
			return new Blank(x, y, SIZE, floor);
		if(id == 1) 
			return new Dirt(x, y, SIZE, floor);
		if(id == 2)
			return new CursedIdol(x, y, SIZE, floor);
		if(id == 4) 
			return new TreasureTrap(x, y, SIZE, floor);
		//Arrow trap rotations (Arrow trap id = 5 + direction - 1)
		if(id == 5) 
			return new ArrowTrap(x, y, SIZE, 1, floor);
		if(id == 6) 
			return new ArrowTrap(x, y, SIZE, 3, floor);
		if(id == 7) 
			return new ArrowTrap(x, y, SIZE, 2, floor);
		if(id == 8) 
			return new ArrowTrap(x, y, SIZE, 4, floor);
		if(id == 9) 
			return new TikiTrap(x, y, SIZE, floor);
		if(id == 10)
			return new PatrolArrow(x, y, SIZE, floor);
		
		if(id == -2)
			return new Exit(x, y, SIZE, floor);
		
		return null;
	}
	
	public static boolean isIdTrap(int id) {
		//TODO
		return false;
	}
	
	public static int getFirstTrapId() {
		return FIRST_TRAP_ID;
	}
}

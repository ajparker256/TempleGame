package traps;

import java.awt.Point;

import grid.Grid;


public enum TrapNames {

	EXIT(-2),
	BLANK(0),
	DIRT(1),
	CURSED_IDOL(2),
	TREASURE_TRAP(4),
	ARROW_TRAP_RIGHT(5),
	ARROW_TRAP_UP(6),
	ARROW_TRAP_LEFT(7),
	ARROW_TRAP_DOWN(8),
	TIKI_TRAP(9),
	PATROL_ARROW(10);
	
	public final int index;
	private static final float SIZE = Grid.getTileSize();
		
	TrapNames(int id) {
		index = id;
	}
	
}

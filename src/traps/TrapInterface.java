package traps;

import java.awt.Point;

import grid.Tile;
import gui.GuiTexture;

public interface TrapInterface {
	
			public void whenTriggered(Point p); 

			public GuiTexture drawTile();

			public Tile copy();

			public void setTriggers();

			public void tick(double milli);
		
}

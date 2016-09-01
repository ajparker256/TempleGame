package traps;

import java.awt.Point;
import java.util.ArrayList;

import grid.Tile;
import gui.GuiTexture;

public interface TrapInterface {
	
			public void whenTriggered(Point p); 

			public GuiTexture drawTile();

			public Tile copy();

			public ArrayList<Point> getTriggerLocations();

			public void tick(double milli);
		
}

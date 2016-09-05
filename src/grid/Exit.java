package grid;

import org.lwjgl.util.vector.Vector2f;

import explorerTypes.Explorer;
import gui.GuiTexture;
import librarys.GuiLibrary;
import main.Main;
import pathing.Group;
import renderEngine.DisplayManager;

public class Exit extends Tile{
	
	public Exit(int x, int y, float size, int floor) {
		super(x, y, size, floor);
		this.guiTexture=(new GuiTexture(GuiLibrary.ladder,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));
		passable = true;
		canInteract = true;
		name = "Exit!";
		id = -2;
	}
	
	@Override
	public void interact(Group g, Grid currentFloor) {
		g.setFloor(g.getFloor()+1);
		for(Explorer e : g.getGroup()) {
			e.setFloor(e.getFloor()+1);
		}
	}
	
	@Override
	public void damage(double damage) {
		
	}
	
	@Override
	public Tile copy() {
		Tile newExit = new Exit(x, y, size, floor);
		newExit.setTrapRefs(trapRefs);
		return newExit;
	}
}

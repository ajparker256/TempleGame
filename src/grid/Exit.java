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
		super(x, y, size, Main.grids.get(floor).getLoc(), floor);
		this.guiTexture=(new GuiTexture(GuiLibrary.ladder,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));
		passable = true;
		canInteract = true;
		name = "Exit!";
		id = -2;
	}
	
	public void interact(Group g) {
		g.setFloor(g.getFloor()+1);
		for(Explorer e : g.getGroup()) {
			e.setFloor(e.getFloor()+1);
		}
		if(Main.squads.get(g.getSquadId()).getPathMods().get(4) != null) {
			Main.squads.get(g.getSquadId()).getPathMods().remove(4);//Cursed idol path modifier
			System.out.println("HEY");
		}
		Main.grids.get(g.getFloor()).setTile(x, y, new ExitTop(x, y, size, floor+1));
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

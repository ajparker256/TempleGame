package grid;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import entities.Projectile;
import gui.GuiTexture;
import librarys.TileLibrary;
import pathing.Squad;
import traps.Trap;
import traps.TrapInterface;

public abstract class Temple {
	
	protected ArrayList<Squad> squads;
	protected ArrayList<Projectile> projectiles;
	protected FloorCollection allFloors;
	
	public Temple() {
		squads = new ArrayList<Squad>();
		projectiles = new ArrayList<Projectile>();
		allFloors = new FloorCollection();
	}

	public FloorCollection getFloorCollection() {
		return allFloors;
	}
	
	public void setFloor(int newFloor) {
		allFloors.setCurrentlyViewedFloor(newFloor);
	}
	
	public Grid getFloor(int floorId) {
		return allFloors.getFloor(floorId);
	}
	
	public Grid getCurrentFloor() {
		return allFloors.getFloorToBeRendered();
	}
	
	public void changeEditState() {
		allFloors.switchEditState();
	}
	
	public void addFloor() {
		allFloors.addFloor();
	}
	
	public boolean isEditState() {
		return allFloors.isEditState();
	}
	
	public void addTrapToCurrentFloor(Point coordinates, int trapId) {
		Grid currentFloor = allFloors.getFloorToBeRendered();
		ArrayList<Point> oldTrapRefs = currentFloor.getTile(coordinates.x, coordinates.y).getTrapRefs();
		Tile newTile = TileLibrary.getTile(coordinates.x, coordinates.y, trapId, currentFloor.getFloor());
		if(newTile instanceof TrapInterface) { 
			Trap newTrap = (Trap)TileLibrary.getTile(coordinates.x, coordinates.y, trapId, currentFloor.getFloor());
			currentFloor.addTrapRefsForTrap(newTile.getTrapRefs(),  coordinates);
			newTrap.setTrapRefs(oldTrapRefs);
			currentFloor.setTile(coordinates.x, coordinates.y, newTrap);
		} else {
			newTile.setTrapRefs(oldTrapRefs);
			currentFloor.setTile(coordinates.x, coordinates.y, newTile);
		}
	}
	
	protected abstract void assignSquadStartPosition(Vector2f locationOnScreen);
	
	public void tick(long milli) {
		if(!allFloors.isEditState()) {
			tickSquads(milli);
			tickProjectiles(milli);
			tickFloors(milli);
		}
	}
	
	protected abstract void tickSquads(long milli);
	protected abstract void tickProjectiles(long milli);
	protected abstract void tickFloors(long milli);

	public void render(ArrayList<GuiTexture> dynamicGuis) {
		allFloors.render(dynamicGuis);
		renderSquads(dynamicGuis);
		renderProjectiles(dynamicGuis);
	}
	
	protected abstract void renderSquads(ArrayList<GuiTexture> dynamicGuis);
	protected abstract void renderProjectiles(ArrayList<GuiTexture> dynamicGuis);
	
}

package grid;

import java.util.ArrayList;
import java.util.HashMap;

import gui.GuiTexture;

public class FloorCollection {
	
	private static final float TILE_SIZE = .05f;
	private static final int BASE_NUMBER_OF_ROWS = 5;
	private static final int FIRST_FLOOR = 0;
	
	private HashMap<Integer, Grid> floorsReadOnly;
	private HashMap<Integer, Grid> floorsWritable;
	private int currentlyViewedFloor;
	private int nextFloorNumber;
	private boolean isEditState;
	
	public FloorCollection() {
		floorsReadOnly = new HashMap<Integer, Grid>();
		floorsWritable = new HashMap<Integer, Grid>();
		currentlyViewedFloor = FIRST_FLOOR;
		nextFloorNumber = FIRST_FLOOR;
		isEditState = true;
		addFloor();
	}
	
	public float getTileSize() {
		return TILE_SIZE;
	}
	
	public int getBaseAmountOfTerrain() {
		return BASE_NUMBER_OF_ROWS;
	}
	
	public boolean isEditState() {
		return isEditState;
	}
	
	public void setCurrentlyViewedFloor(int newFloor) {
		currentlyViewedFloor = newFloor;
	}
	
	public void addFloor() {
		int numberOfRows = BASE_NUMBER_OF_ROWS+nextFloorNumber;
		if(numberOfRows > 9) {
			numberOfRows = 9;
		}
		Grid g = new Grid(TILE_SIZE, numberOfRows, nextFloorNumber);
		floorsReadOnly.put(nextFloorNumber, g);
		nextFloorNumber++;
	} 
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
		getFloorToBeRendered().render(dynamicGuis);
	}

	public Grid getFloorToBeRendered() {
		return getFloor(currentlyViewedFloor);
	}
	
	public Grid getFloor(int floorNumber) {
		if(isEditState) {
			return floorsReadOnly.get(floorNumber);
		} else {
			return floorsWritable.get(floorNumber);
		}
	}
	
	public void switchEditState() {
		if(isEditState) {
			isEditState = false;
			for(int i = FIRST_FLOOR; i<nextFloorNumber; i++) {
				floorsWritable.put(i, floorsReadOnly.get(i).copy());
			}
		} else {
			isEditState = true;
		}
		System.out.println("STATES HAVE SWITCHED");
	}
	
	public void tick(long milli) {
		for(int currentFloor = 0; currentFloor<floorsWritable.size(); currentFloor++) {
			iterateOverAllTiles(currentFloor, milli);
		}
	}
	
	private void iterateOverAllTiles(int currentFloor, long milli) {
		for(int row = 0; row<floorsWritable.get(currentFloor).getWidth(); row++) {
			for(int column = 0; column<floorsWritable.get(currentFloor).getWidth(); column++) {
				floorsWritable.get(currentFloor).getTile(row, column).tick(milli);
			}
		}
	}
}

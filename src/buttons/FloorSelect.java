package buttons;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;

import grid.FloorCollection;
import grid.Grid;
import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.StringLibrary;
import renderEngine.DisplayManager;

public class FloorSelect {
	
	private static final int LOCKED_ID = -1;
	
	private int currentFloorId;
	private int numberOfFloors;
	private int baseCost;
	private int[] gridsAssignedToHitboxes;
	private Vector2f location;
	private Vector2f sizeOfLevelIcon; //AKA text size currently 8/20/16
	private Vector2f maxSize;
	private float extraSpace;
	private Button[] hitBoxes;
	private GuiTexture[] icons;
	private boolean isOn;
	private int debt;
	private boolean exitShop;
	
	private int visibilityRange[];
	private int availableDynamicSpace;
	
	private double lastInteractionTimeMillis;
	private double interactionDelayMillis;
	
	public FloorSelect(Vector2f loc, Vector2f sizeOfEntireBar) {
		numberOfFloors = 1;
		
		location = loc;
		maxSize = sizeOfEntireBar;
		extraSpace = sizeOfEntireBar.x%sizeOfEntireBar.y;	
		
		int availableIconSpots = (int)(sizeOfEntireBar.x/sizeOfEntireBar.y);		
		int numberOfConstantIcons = 3; // 1 for the "Add Floor" button, 2 for one scroll icon on each of the sides
		
		availableDynamicSpace = availableIconSpots-numberOfConstantIcons;
		gridsAssignedToHitboxes = new int[availableDynamicSpace]; 		

		hitBoxes = new Button[(int)(sizeOfEntireBar.x/sizeOfEntireBar.y)];		
		visibilityRange = new int[2];
		visibilityRange[1] = gridsAssignedToHitboxes.length;		

		baseCost = 200;
		
		sizeOfLevelIcon = new Vector2f(sizeOfEntireBar.y*4/5/(float)DisplayManager.getAspectratio(), sizeOfEntireBar.y*4/5);

		icons = new GuiTexture[hitBoxes.length];
		interactionDelayMillis = 300; 

		assignButtons();
		setHitboxes();
		isOn = true;
		
	}
	
	
	private void setHitboxes() {
		for(int i = 0; i<hitBoxes.length; i++) {
			hitBoxes[i] = Button.makeButtonWithCenterLoc(icons[i].getPosition(), icons[i].getScale());
			
		}
	}
	
	private void assignButtons() {
		for(int i = visibilityRange[0]; i<visibilityRange[1]; i++) {
			if(i<numberOfFloors)
				gridsAssignedToHitboxes[i-visibilityRange[0]] = i; //Puts the displayed floors there
			else 
				gridsAssignedToHitboxes[i-visibilityRange[0]] = LOCKED_ID;
		}	
		setIcons();
	}
	
	private void setIcons() {
		setFloorIcons();
		setLeftScrollIcon();
		setRightScrollIcon();
		setAddFloorIcon();
	}
	
	private void setFloorIcons() {
		int offsetRight = 1;
		for(int i = offsetRight; i<icons.length-2; i++) {
			if(gridsAssignedToHitboxes[i-offsetRight] == LOCKED_ID) {
				icons[i] = new GuiTexture(GuiLibrary.lockedIcon, new Vector2f(location.x+(i+.5f)*maxSize.y+extraSpace/2, 
						location.y+maxSize.y/2), sizeOfLevelIcon);
			} else {
				icons[i] = new GuiTexture(StringLibrary.getLetter(getFloorChar(gridsAssignedToHitboxes[i-offsetRight])), new Vector2f(location.x+(i+.5f)*maxSize.y+extraSpace/2, 
							location.y + maxSize.y/2), sizeOfLevelIcon);
			}
		}
	}
	
	private char getFloorChar(int floorId) {
		return (""+floorId).charAt(0);
	}
	
	private void setLeftScrollIcon() {
		if(visibilityRange[0] > 0) {
			icons[0] =  new GuiTexture(GuiLibrary.scrollLeftSelectable,new Vector2f(location.x+(.5f)*maxSize.y/(float)DisplayManager.getAspectratio()+extraSpace/2, 
					location.y+maxSize.y/2), sizeOfLevelIcon);
		} else {
			icons[0] = new GuiTexture(GuiLibrary.scrollLeft,new Vector2f(location.x+(.5f)*maxSize.y/(float)DisplayManager.getAspectratio()+extraSpace/2, 
					location.y+maxSize.y/2), sizeOfLevelIcon);
		}
	}
	
	private void setRightScrollIcon() {
		int indexInHitboxes = icons.length-2;
		if(visibilityRange[1] < numberOfFloors) {
			icons[icons.length-2] = new GuiTexture(GuiLibrary.scrollRightSelectable, new Vector2f(location.x+(indexInHitboxes+.5f)*maxSize.y+extraSpace/2, 
					location.y+maxSize.y/2), sizeOfLevelIcon);	
		} else {
			icons[icons.length-2] = new GuiTexture(GuiLibrary.scrollRight, new Vector2f(location.x+(indexInHitboxes+.5f)*maxSize.y+extraSpace/2, 
				location.y+maxSize.y/2), sizeOfLevelIcon);
		}
	}

	private void setAddFloorIcon() {
		icons[icons.length-1] = new GuiTexture(GuiLibrary.addFloorIcon, new Vector2f(location.x+(icons.length-1+.5f)*maxSize.y+extraSpace/2, 
				location.y+maxSize.y/2), sizeOfLevelIcon);
	}
	
	private boolean isLocked(int id) {
		return id == LOCKED_ID;
	}
	
	public boolean canInteract() {
		return lastInteractionTimeMillis+interactionDelayMillis <= System.currentTimeMillis();
	}

	public void scrollIfClicked(float mouseX, float mouseY) {
		int scrollDistance = gridsAssignedToHitboxes.length;
		int remainingGridsLength = numberOfFloors-visibilityRange[0];
		int locationOfRightScrollIcon = availableDynamicSpace+1;
		if(remainingGridsLength>availableDynamicSpace && hitBoxes[locationOfRightScrollIcon].isClicked(mouseX, mouseY)) {
			visibilityRange[0] += scrollDistance;
			visibilityRange[1] += scrollDistance;
			assignButtons();
		}
		if(visibilityRange[0] > 0 && hitBoxes[0].isClicked(mouseX, mouseY)) {
			if(visibilityRange[0]<scrollDistance) {
				visibilityRange[1] -= visibilityRange[0];
				visibilityRange[0] = 0;
			} else {
				visibilityRange[0] -= scrollDistance;
				visibilityRange[1] -= scrollDistance;
			}
			assignButtons();
		}
	}
		
	
	public int tryToBuyFloor(float mouseX, float mouseY, int money) {
		if(hitBoxes[hitBoxes.length-1].isClicked(mouseX, mouseY)) {
			int cost = numberOfFloors*baseCost;
			if(money>=cost) {
				numberOfFloors++;
				assignButtons();
				return cost;
			} 
		}
		return 0;
	}
	
	public int changeFloor(float mouseX, float mouseY) {
		int clickedFloorId = getFloorClicked(mouseX, mouseY);
		if(isValidFloor(clickedFloorId)) {
			currentFloorId = clickedFloorId;
			exitShop = true;
		}
		return currentFloorId;
	}
	
	public boolean isValidFloor(int id) {
		return id >= 0;
	}
	

	public void setLastInteractTimeToNow() {
		lastInteractionTimeMillis = System.currentTimeMillis();
	}
	
	//Potential optimization if locked is the start of a non-working set always
	private int getFloorClicked(float mouseX, float mouseY) {
		int offsetRight = 1;
		for(int i = 0; i<gridsAssignedToHitboxes.length; i++) {
			if(!isLocked(gridsAssignedToHitboxes[i]) && hitBoxes[i+offsetRight].isClicked(mouseX, mouseY))
				return gridsAssignedToHitboxes[i];
		}
		return -1;
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
		if(isOn) {
			for(GuiTexture currentIcon : icons) {
				dynamicGuis.add(currentIcon);
			}
		}
	}
	
	public void setOn(boolean b) {
		isOn = b;
	}
	
	public int getCurrentFloor() {
		return currentFloorId;
	}
	
	public boolean shouldExitShop() {
		if(exitShop) {
			exitShop = false;
			return true;
		} else {
			return false;
		}
	}
	
}
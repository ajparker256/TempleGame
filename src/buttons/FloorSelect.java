package buttons;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;

import grid.Grid;
import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.StringLibrary;
import renderEngine.DisplayManager;

public class FloorSelect {
	
	//The last spot is always + floor icon
	//The second to last spot is always scroll, but may not be highlighted as usable
	//The first position is also always scroll same as above
	private static int LOCKED_ID = -1;
	private int nextGridNumber;
	private int renderedGridId;
	private HashMap<Integer, Grid> floorsReadOnly;
	private HashMap<Integer, Grid> floorsWritable;
	private boolean isEditState;
	private int baseCost;
	private int[] gridsAssignedToHitboxes;
	private Vector2f location;
	private Vector2f sizeOfLevelIcon; //AKA text size currently 8/20/16
	private Vector2f maxSize;
	private float extraSpace;
	private Button[] hitBoxes;
	private GuiTexture[] icons;
	
	private int visibilityRange[];
	private int availableDynamicSpace;
	
	private double lastInteractionTimeMillis;
	private double interactionDelayMillis;
	
	public FloorSelect(Vector2f loc, Vector2f sizeOfEntireBar) {
		floorsReadOnly = new HashMap<Integer, Grid>();
		floorsWritable = new HashMap<Integer, Grid>();
		isEditState = true;
		
		int availableIconSpots = (int)(sizeOfEntireBar.x/sizeOfEntireBar.y);		
		int numberOfConstantIcons = 3; // 1 for the "Add Floor" button, 2 for one scroll icon on each of the sides		
		availableDynamicSpace = availableIconSpots-numberOfConstantIcons;
		gridsAssignedToHitboxes = new int[availableDynamicSpace]; 		
		extraSpace = sizeOfEntireBar.x%sizeOfEntireBar.y;	
		hitBoxes = new Button[(int)(sizeOfEntireBar.x/sizeOfEntireBar.y)];		
		visibilityRange = new int[2];
		visibilityRange[1] = gridsAssignedToHitboxes.length;		
		nextGridNumber = 0;
		renderedGridId = 0;
		baseCost = 200;
		location = loc;
		sizeOfLevelIcon = new Vector2f(sizeOfEntireBar.y*4/5/(float)DisplayManager.getAspectratio(), sizeOfEntireBar.y*4/5);
		maxSize = sizeOfEntireBar;
		icons = new GuiTexture[hitBoxes.length];
		interactionDelayMillis = 300; 
		assignButtons();
		setHitboxes();
	}
	
	
	private void setHitboxes() {
		for(int i = 0; i<hitBoxes.length; i++) {
			hitBoxes[i] = Button.makeButtonWithCenterLoc(icons[i].getPosition(), icons[i].getScale());
			
		}
	}
	
	private void assignButtons() {
		for(int i = visibilityRange[0]; i<visibilityRange[1]; i++) {
			if(i<floorsReadOnly.size())
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
		if(visibilityRange[1] < floorsReadOnly.size()) {
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
	

	@SuppressWarnings("unused") //TODO
	private void changeBetweenEditAndPlayState() {
		if(isEditState) {
			for(int i = 0; i<floorsReadOnly.size(); i++) {
				floorsWritable.put(i, floorsReadOnly.get(i).copy());
			}
		}
	}
	
	public void doMouseEvents(float mouseX, float mouseY) {
		if(lastInteractionTimeMillis + interactionDelayMillis <= System.currentTimeMillis()) {
			scrollIfClicked(mouseX, mouseY);
			if(hitBoxes[hitBoxes.length-1].isClicked(mouseX, mouseY)) {
				addFloor();
			}
			int clickedFloorId = getFloorClicked(mouseX, mouseY);
			if(isValidFloor(clickedFloorId)) {
				renderedGridId = clickedFloorId;
				System.out.println(clickedFloorId);
			}
			lastInteractionTimeMillis = System.currentTimeMillis();
		}
	}
	
	private void scrollIfClicked(float mouseX, float mouseY) {
		int scrollDistance = gridsAssignedToHitboxes.length;
		int remainingGridsLength = floorsReadOnly.size()-visibilityRange[0];
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
	
	public int addFloor() {
		int cost = nextGridNumber*nextGridNumber*baseCost; //Quadratic growth
		Grid g = new Grid(0.05f,5+nextGridNumber, nextGridNumber);
		floorsReadOnly.put(nextGridNumber, g);
		nextGridNumber++;
		assignButtons();
		return cost;
	}
	
	private boolean isValidFloor(int id) {
		return id >= 0;
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
		for(GuiTexture currentIcon : icons) {
			dynamicGuis.add(currentIcon);
		}
		if(getGridToBeRendered() != null) {
			getGridToBeRendered().render();
		}
	}
	
	public Grid getGridToBeRendered() {
		if(isEditState) {
			return floorsReadOnly.get(renderedGridId);
		} else {
			return floorsWritable.get(renderedGridId);
		}
	}
	
}
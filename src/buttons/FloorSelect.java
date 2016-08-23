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
		sizeOfLevelIcon = new Vector2f(sizeOfEntireBar.y*2/3/(float)DisplayManager.getAspectratio(), sizeOfEntireBar.y*2/3);
		maxSize = sizeOfEntireBar;
		icons = new GuiTexture[hitBoxes.length];
		interactionDelayMillis = 300; 
		setHitboxes();
		assignButtons();
		renderedGridId = 0;
		System.out.println(extraSpace);
	}
	
	public int addFloor() {
		int cost = nextGridNumber*nextGridNumber*baseCost; //Quadratic growth
		Grid g = new Grid(0.05f,5+nextGridNumber, nextGridNumber);
		floorsReadOnly.put(nextGridNumber, g);
		assignButtons();
		return cost;
	}
	
	public void changeBetweenEditAndPlayState() {
		if(isEditState) {
			for(int i = 0; i<floorsReadOnly.size(); i++) {
				floorsWritable.put(i, floorsReadOnly.get(i).copy());
			}
		}
	}
	
	public Grid getGridToBeRendered() {
		return floorsWritable.get(renderedGridId);
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
		for(GuiTexture g : icons) {
			dynamicGuis.add(g);
		}
	}
	
	private void scrollIfClicked(float mouseX, float mouseY) {
		int scrollDistance = 3;
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
	
	public void mouseEvents(float mouseX, float mouseY) {
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
	
	private boolean isValidFloor(int id) {
		return id < 0;
	}
	
	//Potential optimization if locked is the start of a non-working set always
	private int getFloorClicked(float mouseX, float mouseY) {
		for(int i = 0; i<gridsAssignedToHitboxes.length; i++) {
			if(!isLocked(gridsAssignedToHitboxes[i]) && hitBoxes[i].isClicked(mouseX, mouseY))
				return i;
		}
		return -1;
	}
	
	private boolean isLocked(int id) {
		return id == -1;
	}
	
	
	private void assignButtons() {
		for(int i = visibilityRange[0]; i<visibilityRange[1]; i++) {
			if(i<floorsReadOnly.size())
				gridsAssignedToHitboxes[i] = i; //Puts the displayed floors there
			else 
				gridsAssignedToHitboxes[i] = -1; //Locked Icon
		}	
		setIcons();
	}
	
	private void setIcons() {
		setFloorIcons();
		setLeftScrollIcon();
		setRightScrollIcon();
		setAddFloorIcon();
	}
	
	private void setAddFloorIcon() {
		icons[icons.length-1] = new GuiTexture(GuiLibrary.addFloorIcon, new Vector2f(location.x+(icons.length-1+.5f)*maxSize.y/(float)DisplayManager.getAspectratio()+extraSpace/2, 
				location.y+maxSize.y/2), sizeOfLevelIcon);
	}
	
	private void setFloorIcons() {
		for(int i = 1; i<icons.length-2; i++) {
			if(gridsAssignedToHitboxes[i-1] == -1) {
				icons[i] = new GuiTexture(GuiLibrary.lockedIcon, new Vector2f(location.x+(i+.5f)*maxSize.y/(float)DisplayManager.getAspectratio()+extraSpace/2, 
						location.y+maxSize.y/2), sizeOfLevelIcon);
			} else {
				icons[i] = new GuiTexture(StringLibrary.getLetter((char)gridsAssignedToHitboxes[i-1]), new Vector2f(location.x+(i+.5f)*maxSize.y/(float)DisplayManager.getAspectratio()+extraSpace/2, 
							location.y + maxSize.y/2), sizeOfLevelIcon);
			}
		}
	}
	
	private void setRightScrollIcon() {
		if(visibilityRange[1] < floorsReadOnly.size()) {
			icons[icons.length-2] = new GuiTexture(GuiLibrary.scrollRightSelectable, new Vector2f(location.x+(icons.length-2+.5f)*maxSize.y/(float)DisplayManager.getAspectratio()+extraSpace/2, 
					location.y+maxSize.y/2), sizeOfLevelIcon);	
		} else {
			icons[icons.length-2] = new GuiTexture(GuiLibrary.scrollRight, new Vector2f(location.x+(icons.length-2+.5f)*maxSize.y/(float)DisplayManager.getAspectratio()+extraSpace/2, 
				location.y+maxSize.y/2), sizeOfLevelIcon);
		}
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
	
	private void setHitboxes() {
		for(int i = 0; i<hitBoxes.length; i++) {
			hitBoxes[i] = (new Button(new Vector2f(location.x+extraSpace/2+i*sizeOfLevelIcon.x, location.y+maxSize.y/2+sizeOfLevelIcon.y/2),
				new Vector2f(location.x+extraSpace/2+(i+1)*sizeOfLevelIcon.x, location.y+maxSize.y/2-sizeOfLevelIcon.y/2)));
		}
	}
}
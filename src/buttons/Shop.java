package buttons;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import grid.Tile;
import gui.GuiRenderer;
import gui.GuiTexture;
import librarys.StringLibrary;

public class Shop {
	
	//This is the tile clicked on to initialize the shop. It is where the traps are placed on the grid
	private Vector2f locationOfTrapPlacement;
	
	//This is the bottom left corner of the shop
	private Vector2f location;
	
	//This is the size of the shop from top left corner to bottom right
	private Vector2f size;
	
	//Button array to determine which spot is being interacted with.
	private Button[][] buttons;
	
	//Tile array containing the available traps for sale
	private Tile[][] traps;
	
	//0 = Row start, 1 = Row end, 2 = Col start 3 = Col end. This is the range shown on screen at any time. Subject to scrolling
	private int[] visibilityRange;
	
	//Checks if the shop has been clicked at all
	private Button shopHitbox;
	
	//This determines if the shop is in use or not
	private boolean isOn;
	
	public Shop(int numberOfRows, int numberOfColumns, Vector2f location, Vector2f size, Vector2f locationOfTrapPlacement, Tile[][] traps) {
		this.location = location;
		this.size = size;
		buttons = new Button[numberOfRows][numberOfColumns];
		this.traps = traps;
		this.locationOfTrapPlacement = locationOfTrapPlacement;
		visibilityRange = new int[4];
		visibilityRange[1] = 4;
		visibilityRange[2] = 2;
		isOn = false;
		for(int i = 0; i<numberOfRows; i++) {
			for(int j = 0; j<numberOfColumns; j++) {
				
				//Y position then X position
				buttons[i][j] = new Button(new Vector2f(location.x+i*location.x/size.x, location.y+j*location.y/size.y), 
						new Vector2f(location.x*(i+1)*location.x/size.x, location.y*(j+1)*location.y/size.y));
				
				Vector2f shopItemPosition = new Vector2f(location.x+(i+.5f)*(size.x/traps[0].length), location.y+(j+.5f)*(size.y/traps.length));
				
				traps[i][j].drawTile().setPosition(shopItemPosition);
			}
		}
		
		shopHitbox = new Button(location, new Vector2f(location.x+size.x, location.y+size.y));
	}
	
	//This renders all traps in the shop at their given locations.
	public void render(ArrayList<GuiTexture> guis) {
		for(int i = visibilityRange[2]; i<visibilityRange[3]; i++) {
			for(int j = visibilityRange[0]; j<visibilityRange[1]; j++) {
				//TODO Add Tile Naming system instead of toString Below!!!
				guis.add(traps[j][i].drawTile());
				StringLibrary.makeItFit(traps[j][i].getName(), new Vector2f(location.x+(j+.5f)*(size.x/traps[0].length), location.y+(i+.8f)*(size.y/traps.length)), size.x/(traps[0].length));
			}
		}
	}
	
	//Returns true if the mouse is on top of the shop when called.
	public boolean shopIsClicked(int mouseX, int mouseY) {
		return shopHitbox.isClicked(mouseX, mouseY);
	}
	
	public Vector2f getPlacementLoc() {
		return locationOfTrapPlacement;
	}
	
	public Tile getShopItem(int mouseX, int mouseY) {
		//Goes through the buttons in the shop until it finds where it is clicked, then returns the item.
		for(int i = 0; i<buttons.length; i++) {
			for(int j = 0; j<buttons[0].length; j++) {
				if(buttons[i][j].isClicked(mouseX, mouseY)) {
					return traps[i][j];
				}
			}
		}
		//Error Case
		return null;
	}	
	
	public void setOn(boolean b) {
		isOn = b;
	}
	
	public boolean isOn() {
		return isOn;
	}
	
	public void setGridLoc(Vector2f locationOnGrid) {
		locationOfTrapPlacement = locationOnGrid;
	}

}

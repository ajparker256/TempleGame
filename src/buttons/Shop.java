package buttons;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import grid.Tile;
import gui.GuiRenderer;
import gui.GuiTexture;
import librarys.StringLibrary;

public class Shop {
	
	//This is the tile clicked on to initialize the shop. It is where the traps are placed on the grid
	Vector2f locationOfTrapPlacement;
	
	//This is the bottom left corner of the shop
	Vector2f location;
	
	//This is the size of the shop from top left corner to bottom right
	Vector2f size;
	
	//Button array to determine which spot is being interacted with.
	Button[][] buttons;
	
	//Tile array containing the available traps for sale
	Tile[][] traps;
	
	ArrayList<GuiTexture> renderedTraps;
	
	Button shopHitbox;
	
	public Shop(int numberOfRows, int numberOfColumns, Vector2f location, Vector2f size, Vector2f locationOfTrapPlacement, Tile[][] traps) {
		this.location = location;
		renderedTraps = new ArrayList<GuiTexture>();
		this.size = size;
		buttons = new Button[numberOfRows][numberOfColumns];
		this.traps = traps;
		this.locationOfTrapPlacement = locationOfTrapPlacement;
		for(int i = 0; i<numberOfRows; i++) {
			for(int j = 0; j<numberOfColumns; j++) {
				//Y position then X position
				buttons[i][j] = new Button(new Vector2f(location.x+i*location.x/size.x, location.y+j*location.y/size.y), 
						new Vector2f(location.x*(i+1)*location.x/size.x, location.y*(j+1)*location.y/size.y));
				//This is the desired output, can be done here or in the Tile constructor. TODO
				//trapTexture, new Vector2f(location.x+(i+.5f)*(size.x/traps[0].length), location.y+(j+.5f)*(size.y/traps.length)), 
				//new Vector2f(size.x/traps[0].length, size.y/traps.length)
				Vector2f shopItemPosition = new Vector2f(location.x+(i+.5f)*(size.x/traps[0].length), location.y+(j+.5f)*(size.y/traps.length));
				traps[i][j].drawTile().setPosition(shopItemPosition);
				renderedTraps.add(traps[i][j].drawTile());
			}
		}
		
		shopHitbox = new Button(location, new Vector2f(location.x+size.x, location.y+size.y));
	}
	
	//This renders all traps in the shop at their given locations.
	public void render(GuiRenderer g) {
		for(int i = 0; i<traps[0].length; i++) {
			for(int j = 0; j<traps.length; j++) {
				g.render(renderedTraps);
				//TODO Add Tile Naming system instead of toString Below!!!
				StringLibrary.drawString(traps[j][i].toString(), new Vector2f(location.x+(j+.5f)*(size.x/traps[0].length), location.y+(i+.8f)*(size.y/traps.length)));
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

}

package buttons;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import grid.Tile;
import gui.GuiRenderer;
import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.StringLibrary;
import renderEngine.DisplayManager;

public class Shop {
	
	//This is the tile clicked on to initialize the shop. It is where the traps are placed on the grid
	private Vector2f locationOfTrapPlacement;
	
	//This is the bottom left corner of the shop
	private Vector2f location;
	
	//This is the size of the shop from top left corner to bottom right
	private Vector2f size;
	
	//Tile array containing the available traps for sale
	private Tile[][] traps;
	
	private GuiTexture downArrow;
	
	private Button downArrowHitbox;
	
	private GuiTexture upArrow;
	
	private Button upArrowHitbox;
	
	//0 = Row start, 1 = Row end, 2 = Col start 3 = Col end. This is the range shown on screen at any time. Subject to scrolling
	private int[] visibilityRange;
	
	//Checks if the shop has been clicked at all
	private Button shopHitbox;
	
	private GuiTexture[][] frames;
	
	//This determines if the shop is in use or not
	private boolean isOn;
	
	private Button exitShop;
	
	public Shop(Vector2f location, Vector2f size, Tile[][] traps) {
		this.location = location;
		this.size = size;
		//buttons = new Button[numberOfRows][numberOfColumns];
		this.traps = traps;
		this.frames = new GuiTexture[traps.length][traps[0].length];
		this.locationOfTrapPlacement = new Vector2f(0,0);
		visibilityRange = new int[4];
		if(traps.length<4) {
			visibilityRange[1] = traps.length;
		} else {
			visibilityRange[1] = 4;
		}
		if(traps[0].length<3) {
			visibilityRange[3] = traps[0].length;
		} else {
			visibilityRange[3] = traps[0].length;
			visibilityRange[2] = traps[0].length-3;
		}
		isOn = false;
		for(int i = 0; i<traps.length; i++) {
			for(int j = 0; j<traps[0].length; j++) {
				Vector2f shopItemPosition = new Vector2f(location.x+(i-visibilityRange[0]+.5f)*(2*size.x/traps.length), location.y+(j-visibilityRange[2]+.5f)*(size.y/traps[0].length));
				frames[i][j] = new GuiTexture(GuiLibrary.frame, shopItemPosition, new Vector2f(.05f, .05f*(float)DisplayManager.getAspectratio()));
			}
		}
		updateShopPosition();
		upArrow = new GuiTexture(GuiLibrary.upArrow, new Vector2f(location.x+size.x/2, location.y+size.y*.5f*(visibilityRange[3]-visibilityRange[2])+.025f), new Vector2f(.05f, .05f));
		
		upArrowHitbox = new Button(new Vector2f(upArrow.getPosition().x-upArrow.getScale().x/2, upArrow.getPosition().y+upArrow.getScale().y/2),
				new Vector2f(upArrow.getPosition().x+upArrow.getScale().x/2, upArrow.getPosition().y-upArrow.getScale().y/2));
		
		
		downArrow = new GuiTexture(GuiLibrary.downArrow, new Vector2f(location.x+size.x/2, location.y-.1f), new Vector2f(.05f, .05f));
		
		downArrowHitbox = new Button(new Vector2f(downArrow.getPosition().x-downArrow.getScale().x/2, downArrow.getPosition().y+downArrow.getScale().y/2),
				new Vector2f(downArrow.getPosition().x+downArrow.getScale().x/2, downArrow.getPosition().y-downArrow.getScale().y/2));
		
		exitShop = new Button(new Vector2f(location.x+size.x*(.5f * (visibilityRange[1]-visibilityRange[0]))-StringLibrary.getWidth('X'), 
				location.y+size.y*(.5f * (visibilityRange[3]-visibilityRange[2])+StringLibrary.getSize().y)), 
				new Vector2f(location.x+size.x*(.5f * (visibilityRange[1]-visibilityRange[0])), 
				location.y+size.y*(.5f * (visibilityRange[3]-visibilityRange[2]))));
		//System.out.println(exitShop.getTL()+" "+exitShop.getBR());
		shopHitbox = new Button(new Vector2f(location.x, 
				location.y+size.y*(.5f * (visibilityRange[3]-visibilityRange[2]))), 
				new Vector2f(location.x+size.x*(.5f * (visibilityRange[1]-visibilityRange[0])),
				location.y));
		//next = new Button(new Vector2f(location.x))
	}
	
	public boolean isUpArrowClicked(float mouseX, float mouseY) {
		if(visibilityRange[2] != traps[0].length-(visibilityRange[3]-visibilityRange[2])) 	
			return upArrowHitbox.isClicked(mouseX, mouseY);
		else return false;
	}
	
	public boolean isDownArrowClicked(float mouseX, float mouseY) {
		if(visibilityRange[2] != 0)
			return downArrowHitbox.isClicked(mouseX, mouseY);
		else return false;
	}
	
	public boolean isExitClicked(float mouseX, float mouseY) {
		return exitShop.isClicked(mouseX, mouseY);
	}
	
	public void scrollDown() {
		int rangeY = visibilityRange[3]-visibilityRange[2];
		if(visibilityRange[2]-rangeY < 0) {
			visibilityRange[2] = 0;
			visibilityRange[3] = rangeY;
		} else {
			visibilityRange[2] -= rangeY;
			visibilityRange[3] -= rangeY;
		}
		updateShopPosition();
	}
	
	public void scrollUp() {
		int rangeY = visibilityRange[3]-visibilityRange[2];
		if(visibilityRange[2]+rangeY > traps.length) {
			visibilityRange[2] = traps[0].length - rangeY;
			visibilityRange[3] = traps[0].length;
		} else {
			visibilityRange[2] += rangeY;
			visibilityRange[3] += rangeY;
		}
		updateShopPosition();
	}
	
	
	//This renders all traps in the shop at their given locations.
	public void render(ArrayList<GuiTexture> guis) {
		if(visibilityRange[2] != 0) {
			guis.add(downArrow);
		}
		if(visibilityRange[2] != traps[0].length-(visibilityRange[3]-visibilityRange[2])) {
			guis.add(upArrow);
		} 
		for(int i = visibilityRange[2]; i<visibilityRange[3]; i++) {
			for(int j = visibilityRange[0]; j<visibilityRange[1]; j++) {
				
				guis.add(frames[j][i]);
				guis.add(traps[j][i].drawTile());
				
				guis.addAll(StringLibrary.makeItFitC(traps[j][i].toString(), new Vector2f(traps[j][i].drawTile().getPosition().x, 
						traps[j][i].drawTile().getPosition().y-traps[j][i].drawTile().getScale().y+StringLibrary.getSize().y), size.x/traps[0].length));
			}
		}
		guis.addAll(StringLibrary.drawString("X", new Vector2f(location.x+size.x*(.5f * (visibilityRange[1]-visibilityRange[0]))-StringLibrary.getWidth('X'),
				location.y+size.y*(.5f * (visibilityRange[3]-visibilityRange[2]))+StringLibrary.getSize().y)));
	}
	
	public Vector2f getGridLoc() {
		return locationOfTrapPlacement;
	}
	
	public void updateShopPosition() {
		for(int i = visibilityRange[0]; i<visibilityRange[1]; i++) {
			for(int j = visibilityRange[2]; j<visibilityRange[3]; j++) {
				Vector2f shopItemPosition = new Vector2f(location.x+(i-visibilityRange[0]+.5f)*(2*size.x/traps[0].length), location.y+(j-visibilityRange[2]+.5f)*(size.y/traps.length));
				traps[i][j].setPosition(shopItemPosition);
				traps[i][j].drawTile().setPosition(shopItemPosition);
				frames[i][j] = new GuiTexture(GuiLibrary.frame, shopItemPosition, new Vector2f(.05f, .05f*(float)DisplayManager.getAspectratio()));
			}
		}
	}
	
	//Returns true if the mouse is on top of the shop when called.
	public boolean shopIsClicked(float mouseX, float mouseY) {
		return shopHitbox.isClicked(mouseX, mouseY);
	}
	
	public Vector2f getLoc() {
		return location;
	}
	
	public Vector2f getSize() {
		return size;
	}
	
	public int getShopItem(float mouseX, float mouseY) {
		int x = (Mouse.getX()-(int)((location.x+1f)*DisplayManager.WIDTH/2))/(int)(size.x*2/(visibilityRange[3]-visibilityRange[2])*DisplayManager.WIDTH/2);
		int y = (Mouse.getY()-(int)((location.y+1f)*DisplayManager.HEIGHT/2))/(int)(size.y/(visibilityRange[1]-visibilityRange[0])*DisplayManager.HEIGHT/2);
		System.out.println(x+" "+y);
		return traps[x+visibilityRange[0]][y+visibilityRange[2]].getId();
		/*
		//Goes through the buttons in the shop until it finds where it is clicked, then returns the item.
		for(int i = 0; i<buttons.length; i++) {
			for(int j = 0; j<buttons[0].length; j++) {
				if(buttons[i][j].isClicked(mouseX, mouseY)) {
					System.out.println(i+" "+j);
					return traps[i][j].copy();
				}
			}
		}
		//Error Case
		System.out.println("Error case in getShopItem");
		return null;*/
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

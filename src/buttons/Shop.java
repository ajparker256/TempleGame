package buttons;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import grid.Tile;
import gui.GuiRenderer;
import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.ShopItemLibrary;
import librarys.StringLibrary;
import renderEngine.DisplayManager;
import shopItems.ShopItem;

public class Shop {
	
	//This is the tile clicked on to initialize the shop. It is where the traps are placed on the grid
	private Vector2f locationOfTrapPlacement;
	
	//This is the bottom left corner of the shop
	protected Vector2f location;
	
	//This is the size of the shop from top left corner to bottom right
	protected Vector2f size;
	
	//Tile array containing the available traps for sale
	protected Tile[][] traps;
	
	private GuiTexture downArrow;
	
	private Button downArrowHitbox;
	
	private GuiTexture upArrow;
	
	private Button upArrowHitbox;
	
	//0 = Row start, 1 = Row end, 2 = Col start 3 = Col end. This is the range shown on screen at any time. Subject to scrolling
	protected int[] visibilityRange;
	
	//Checks if the shop has been clicked at all
	private Button shopHitbox;
	
	//private GuiTexture[][] frames;
	
	private Vector2f textSize;
	
	//This determines if the shop is in use or not
	protected boolean isOn;
	
	private Button exitShop;
	
	protected ShopItem[][] items;
	
	protected Button[][] shopHitboxes;
	
	public Shop() {
		
	}
	
	public Shop(Vector2f location, Vector2f size, Tile[][] traps) {
		items = new ShopItem[ShopItemLibrary.getItems().size()/2][2];
		this.location = location;
		this.size = size;
		//buttons = new Button[numberOfRows][numberOfColumns];
		this.traps = traps;
		//this.frames = new GuiTexture[traps.length][traps[0].length];
		this.locationOfTrapPlacement = new Vector2f(0,0);
		visibilityRange = new int[4];
		if(traps.length<2) {
			visibilityRange[1] = traps.length;
		} else {
			visibilityRange[1] = 2;
		}
		if(traps[0].length<3) {
			visibilityRange[3] = traps[0].length;
		} else {
			visibilityRange[3] = traps[0].length;
			visibilityRange[2] = traps[0].length-3;
		}
		isOn = false;
		textSize = new Vector2f(.01f, .02f);
		int rX = visibilityRange[1]-visibilityRange[0];
		int rY = visibilityRange[3]-visibilityRange[2];
		shopHitboxes = new Button[rY][rX];
		for(int i = 0; i<rX; i++) {
			for(int j = 0; j<rY; j++) {
		//		Vector2f shopItemPosition = new Vector2f(location.x+(i-visibilityRange[0]+.5f)*(2*size.x/traps.length), location.y+(j-visibilityRange[2]+.5f)*(size.y/traps[0].length));
		//		frames[i][j] = new GuiTexture(GuiLibrary.frame, shopItemPosition, new Vector2f(.05f, .05f*(float)DisplayManager.getAspectratio()));
				Vector2f imageSize = traps[visibilityRange[0]+i][visibilityRange[2]+j].drawTile().getScale();
				//THIS PART WORKS
				shopHitboxes[j][i] = new Button(new Vector2f(location.x+size.x/rX/2-imageSize.x+i*(size.x/rX),
						location.y+size.y/rY/2+(imageSize.y+textSize.y)+j*(size.y/rY)),
						new Vector2f(location.x+size.x/rX/2+imageSize.x+i*(size.x/rX),
						location.y+size.y/rY/2-(imageSize.y+textSize.y)+j*(size.y/rY)));
				//System.out.println("TL = "+shopHitboxes[j][i].getTL());
				//System.out.println("BR = "+shopHitboxes[j][i].getBR());
				//System.out.println();
			}
		}
		updateShopPosition();
		upArrow = new GuiTexture(GuiLibrary.upArrow, new Vector2f(location.x+size.x/2, location.y+size.y+.05f*(float)DisplayManager.getAspectratio()), new Vector2f(.05f, .05f*(float)DisplayManager.getAspectratio()));
		
		upArrowHitbox = new Button(new Vector2f(upArrow.getPosition().x-upArrow.getScale().x/2, upArrow.getPosition().y+upArrow.getScale().y/2),
				new Vector2f(upArrow.getPosition().x+upArrow.getScale().x/2, upArrow.getPosition().y-upArrow.getScale().y/2));
		
		
		downArrow = new GuiTexture(GuiLibrary.downArrow, new Vector2f(location.x+size.x/2, location.y-.05f*(float)DisplayManager.getAspectratio()), new Vector2f(.05f, .05f*(float)DisplayManager.getAspectratio()));
		
		downArrowHitbox = new Button(new Vector2f(downArrow.getPosition().x-downArrow.getScale().x/2, downArrow.getPosition().y+downArrow.getScale().y/2),
				new Vector2f(downArrow.getPosition().x+downArrow.getScale().x/2, downArrow.getPosition().y-downArrow.getScale().y/2));
		
		exitShop = new Button(new Vector2f(location.x+size.x-StringLibrary.getWidth('X'), 
				location.y+size.y), 
				new Vector2f(location.x+size.x, 
				location.y+size.y-StringLibrary.getSize().y));
		shopHitbox = new Button(new Vector2f(location.x, 
											location.y+size.y), 
								new Vector2f(location.x+size.x,
											location.y));
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
		StringLibrary.setSize(textSize);
		for(int i = visibilityRange[2]; i<visibilityRange[3]; i++) {
			for(int j = visibilityRange[0]; j<visibilityRange[1]; j++) {
				
			//	guis.add(frames[j][i]);
				guis.add(traps[j][i].drawTile());
				//Currently possible fixed solution, does not scale optimally
				guis.addAll(StringLibrary.makeItFitC(traps[j][i].toString(), new Vector2f(location.x+StringLibrary.getWidth(' ')+(j-visibilityRange[0])*size.x/(visibilityRange[1]-visibilityRange[0]), 
						traps[j][i].drawTile().getPosition().y-traps[j][i].drawTile().getScale().y-textSize.y/2), size.x/(visibilityRange[1]-visibilityRange[0])));
			}
		}
		StringLibrary.setSize(new Vector2f(.02f, .04f));
		guis.addAll(StringLibrary.drawString("X", new Vector2f(location.x+size.x-StringLibrary.getWidth('X'),
				location.y+size.y)));
	}
	
	public Vector2f getGridLoc() {
		return locationOfTrapPlacement;
	}
	
	public void updateShopPosition() {
		int rX = visibilityRange[1]-visibilityRange[0];
		int rY = visibilityRange[3]-visibilityRange[2];
		
		for(int i = visibilityRange[0]; i<visibilityRange[1]; i++) {
			for(int j = visibilityRange[2]; j<visibilityRange[3]; j++) {
				Vector2f itemPosition = new Vector2f(location.x+size.x/rX/2+(i-visibilityRange[0])*size.x/rX,
													location.y+size.y/rY/2+textSize.y+(j-visibilityRange[2])*size.y/rY);
				traps[i][j].setPosition(itemPosition);
				traps[i][j].drawTile().setPosition(itemPosition);
				//frames[i][j] = new GuiTexture(GuiLibrary.frame, shopItemPosition, new Vector2f(.05f, .05f*(float)DisplayManager.getAspectratio()));
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
		//int x = (Mouse.getX()-(int)((location.x+1f)*DisplayManager.WIDTH/2))/(int)(size.x*2/(visibilityRange[3]-visibilityRange[2])*DisplayManager.WIDTH/2);
		//int y = (Mouse.getY()-(int)((location.y+1f)*DisplayManager.HEIGHT/2))/(int)(size.y/(visibilityRange[1]-visibilityRange[0])*DisplayManager.HEIGHT/2);
		//return traps[x+visibilityRange[0]][y+visibilityRange[2]].getId();
		for(int i = 0; i<shopHitboxes.length; i++) {
			for(int j = 0; j<shopHitboxes[0].length; j++) {
				if(shopHitboxes[i][j].isClicked(mouseX, mouseY)) {
					return traps[j+visibilityRange[0]][i+visibilityRange[2]].getId();
				}
			}
		}
		return -1;
		
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

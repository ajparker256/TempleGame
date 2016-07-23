package main;

import grid.Blank;
import grid.Dirt;
import grid.Exit;
import grid.Grid;
import grid.Tile;
import gui.GuiRenderer;
import gui.GuiTexture;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import librarys.SoundLibrary;
import librarys.StringLibrary;
import librarys.TileLibrary;
import pathing.Group;
import pathing.Squad;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import buttons.Button;
import buttons.RotationDialogueBox;
import buttons.Shop;
import entities.Flame;
import entities.Projectile;
import explorerTypes.Explorer;
import explorerTypes.Miner;
import explorerTypes.TreasureHunter;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import sound.Sound;
import tools.MathM;
import traps.ArrowTrap;
import traps.CursedIdol;
import traps.TreasureTrap;
//Main game loop
public class Main {
	//Amount of ambient light
public static final float ambient = 1f;
public static int money;
public static ArrayList<Explorer> group;
public static ArrayList<Button> buttons;
public static long milli;
public static ArrayList<Grid> grids;
public static Grid grid;
public static Shop epicShopofEpicness;
public static boolean wasJustDown = false;
public static ArrayList<Squad> squads;
public static GuiTexture background;
public static int round;
public static RotationDialogueBox rotationDialogueBox;
public static ArrayList<Projectile> projectiles;
public static double timeInRound;
public static ArrayList<Grid> gridsReadOnly;
public static void main(String[] args) throws FileNotFoundException {
	gridsReadOnly = new ArrayList<Grid>();
	grids = new ArrayList<Grid>();
	squads = new ArrayList<Squad>();
	money=2000;
	round = 0;
	buttons = new ArrayList<Button>();
	group = new ArrayList<Explorer>();
	DisplayManager.createDisplay();
	Loader loader = new Loader();
	StringLibrary.init();
	//Aspect Ratio should be 1 to 2
	StringLibrary.setSize(new Vector2f(.02f, .04f));
	GuiLibrary.init(loader);

	SoundLibrary.init();
	
	for(int i = 0; i<8; i++) {
		grids.add(new Grid(new Vector2f(-.5f,-.8f),0.05f,10, i));
		gridsReadOnly.add(grids.get(i).copy());
	}
	grid = grids.get(0);
	rotationDialogueBox = new RotationDialogueBox();
	
	ArrayList<GuiTexture> guis = new ArrayList<GuiTexture>();
	ArrayList<GuiTexture> dynamicGuis =  new ArrayList<GuiTexture>();
	projectiles= new ArrayList<Projectile>();
	
	GuiLibrary.background = loader.loadTexture("background");

	GuiRenderer guiRenderer = new GuiRenderer(loader);
	Sound.loopSound(SoundLibrary.music);	
	AnimationLibrary.init(loader);
	
	guis.add(new GuiTexture(GuiLibrary.backgroundDraft1, new Vector2f(-1f, -1f), new Vector2f(1.8f, 1.8f)));
/*	Group group1 = new Group(0);
	group1.add(new Explorer (group1));
	group1.add(new Miner (group1));
	group1.add(new Explorer (group1));
	group1.add(new Miner (group1));
	
	Group group2 = new Group(0);
	group2.add(new Explorer (group2));
	group2.add(new Miner (group2));
	group2.add(new Explorer (group2));
	group2.add(new Miner (group2));
	
	Group group3 = new Group(0);
	group3.add(new Explorer (group3));
	group3.add(new Miner (group3));
	group3.add(new Explorer (group3));
	group3.add(new Miner (group3));
	
	Group group4 = new Group(0);
	group4.add(new Explorer (group4));
	group4.add(new Miner (group4));
	group4.add(new Explorer (group4));
	group4.add(new Miner (group4));*/
	
	Group group1 = new Group(0);
	Group group2 = new Group(0);
	Group group3 = new Group(0);	
	Group group4 = new Group(0);

	
	ArrayList<Group> squad1List=new ArrayList<Group>();
	squad1List.add(group1);
	squad1List.add(group2);
	squad1List.add(group3);
	squad1List.add(group4);
	for(Group g : squad1List) {
		g.add(new Miner (g));
		g.add(new Miner (g));
		g.add(new Explorer (g));
		g.add(new TreasureHunter(g));

	}
	Squad squad1=new Squad(squad1List, 0);
	squads.add(squad1);
	
	int x = 0;
	for(Squad sq : squads) {
		for(Group gr : sq.getGroups()) {
			gr.setInitialLocation(new Vector2f(gr.getInitialLocation().x-.1f*x, gr.getInitialLocation().y));
		}
		x++;
	}
	
	
	
	Tile[][] traps = new Tile[2][4];
	for(int i = 0; i<traps.length; i++) {
		for(int j = 0; j<traps[0].length; j++) {
			traps[i][j] = new Dirt( i, j, .03f, new Vector2f(-.9f+j*.03f, -.5f+i*.03f));
			//System.out.println(traps[i][j].getLocation());
		}
	}
	traps[1][2] = new ArrowTrap(1,1,.03f, 1);
	traps[1][1] = new Blank(-20,-20, .03f, new Vector2f(-.87f, -.47f));
	traps[0][1] = new CursedIdol(-20,-20, .03f);
	traps[0][0] = new Exit(-20,-20, .03f);
	traps[1][3] = new TreasureTrap(.03f, Main.grids.indexOf(Main.grid));
	epicShopofEpicness = new Shop(new Vector2f(.5f, -.1f), new Vector2f(.25f, .5f), traps);
	Flame testFlame = new Flame(1, 1, new Vector2f(.1f, .1f*(float)DisplayManager.getAspectratio()), Main.grid.getFloor());
	projectiles.add(testFlame);
	//Makes the background white
	guis.add(new GuiTexture(loader.loadTexture("White"), new Vector2f(.9f,-.9f), new Vector2f(2f, 2f)));

	
	//projectiles.add(new Projectile(3, 1, 9, 0));
	//projectiles.add(new Projectile(3, 1, 8, 0));
	int counter = 0;
	milli = System.currentTimeMillis();
	while(!Display.isCloseRequested()){
	//	guis.add(background);
		milli = System.currentTimeMillis() - milli;
		update(dynamicGuis, milli);
		
		
		if(epicShopofEpicness.isOn()) {
			epicShopofEpicness.render(dynamicGuis);
		}
		dynamicGuis.addAll(MathM.printNumber(money,new Vector2f(0.6f,-0.9f),0.05f));
		
		//enemy update stuff
		//Renders from TOP TO BOTTOM!
		//RENDERS FROM CENTER OF IMAGE! (90% certain)
		//The screen is -1 to 1 for x and 0 to -1 for y in floats
		dynamicGuis.addAll(grid.render());
		
		guiRenderer.render(guis);
		
		//TODO add for each grid, and inside only do the tihngs that are on the right grid
		squad1.tick((int)milli,grids.get(squad1.getGroups().get(squad1.getGroups().size()-1).getFloor()));
	for(Grid tempgrid:grids){
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				tempgrid.getTile(i, j).tick(milli);
			}
		}
	}
		for(Grid g: grids) {
			dynamicGuis.addAll(g.renderFloorSelect());
		}
		for(Squad squad : squads) {
			dynamicGuis.addAll(squad.render());
		}
		for(int i=0;i<projectiles.size();i++){
			Projectile projectile=projectiles.get(i);
			projectile.tick(milli);
			if(projectile.isKill())
				projectiles.remove(projectile);
			if(projectile.canRender())
				dynamicGuis.add(projectile.render());
			
		}
		if(rotationDialogueBox != null && rotationDialogueBox.isOn()) {
			rotationDialogueBox.render(dynamicGuis);
		}
		
		if(counter < 250) {
			//dynamicGuis.add(new GuiTexture(GuiLibrary.backgroundDraft1, new Vector2f(0.5f, 0f), new Vector2f(1.55f, 1.2f)));
			counter++;
		}
		//Reinitialize milli after all methods that call it are done. Then render and do other stuff.
		milli = System.currentTimeMillis();
		guiRenderer.render(dynamicGuis);
		DisplayManager.updateDisplay();
		dynamicGuis.clear();
		
		
	}
	
	guiRenderer.cleanUp();
	loader.cleanUp();
	DisplayManager.closeDisplay();
		
	}
	
	public static void update(ArrayList<GuiTexture> dynamicGuis, double milli) {
		//TODO find a way to make this only on click, currently non-func. Works always when mouse is down regardless of event
		if(!Mouse.getEventButtonState() && Mouse.isButtonDown(0)) {
			updateMouse(dynamicGuis);
			wasJustDown = true;
		} else {
			wasJustDown = false;
		}
		//10s per round atm
		timeInRound += milli;
			if(timeInRound/1000 > 10) {
				round++;
				for(Grid g : grids) {
					for(Point p : g.getTreasureLocs()) {
						g.getTile(p.x, p.y).getIncome();
					}
				}
				//Recall units or push them all out then stall somehow
				timeInRound = 0;
			}
		//TODO Test traps
		//TODO Update Explorer AI
		//TODO Update player input such as placing traps
		//TODO Update ArrayLists of explorers, traps, Tiles, etc. if not already done so.
	}
	
	public static void updateMouse(ArrayList<GuiTexture> dynamicGuis) {
		//This scales mouseX to be in the range of 1 to -1;
		float mouseX = (float)Mouse.getX()*2/DisplayManager.WIDTH - 1;
		//This scales mouseY to be in the range of 1 at the top and -1 at the bottom
		float mouseY = (float)Mouse.getY()*2/DisplayManager.HEIGHT -1;
		if(epicShopofEpicness.isExitClicked(mouseX, mouseY)) {
			epicShopofEpicness.setOn(false);
			rotationDialogueBox.setOn(false);
		}
		if(grid.getGridButton().isClicked(mouseX, mouseY) && !rotationDialogueBox.isOn()) {
			int x = (Mouse.getX()-(int)((grid.getLoc().x-grid.getSize()+1f)*DisplayManager.WIDTH/2))/(int)(grid.getSize()*DisplayManager.WIDTH);
			int y = (Mouse.getY()-(int)((grid.getLoc().y-grid.getSize()+1f)*DisplayManager.HEIGHT/2))/(int)(grid.getSize()*DisplayManager.HEIGHT*DisplayManager.getAspectratio());
			epicShopofEpicness.setGridLoc(new Vector2f((float)x, (float)y));
			epicShopofEpicness.setOn(true);
		}
		if(epicShopofEpicness.isOn() && epicShopofEpicness.shopIsClicked(mouseX, mouseY)) {
			Tile oldTile=grid.getTile((int)epicShopofEpicness.getGridLoc().x, (int)epicShopofEpicness.getGridLoc().y);
			int selection = epicShopofEpicness.getShopItem(mouseX, mouseY);
			if(selection != -1) {
				Tile selectedTrap = TileLibrary.getTile(oldTile.getX(), oldTile.getY(), oldTile.getSize(), selection);
				if(selectedTrap.getPrice()<=money && oldTile.getId() != selectedTrap.getId() && !selectedTrap.isRotatable()) {
					selectedTrap.setTrapRefs(oldTile.getTrapRefs());
					grid.setTile(oldTile.getX(), oldTile.getY(), selectedTrap);
					gridsReadOnly.get(grid.getFloor()).setTile(oldTile.getX(), oldTile.getY(), selectedTrap);
					epicShopofEpicness.setOn(false);
					money -= selectedTrap.getPrice();
				} else if(selectedTrap.getPrice()>money) {
					dynamicGuis.addAll(StringLibrary.makeItFit("Insufficient Funds", new Vector2f(epicShopofEpicness.getLoc().getX(), epicShopofEpicness.getLoc().y-StringLibrary.getSize().y*2), epicShopofEpicness.getSize().x*1.6f));
				} else if(oldTile.getId() == selectedTrap.getId()) {
					dynamicGuis.addAll(StringLibrary.makeItFitC("That trap is already there!", new Vector2f(epicShopofEpicness.getLoc().getX(), epicShopofEpicness.getLoc().y-StringLibrary.getSize().y*6), epicShopofEpicness.getSize().x*1.6f));
				} else if(selectedTrap.isRotatable()) {
					Tile[][] rotations = new Tile[2][2];
					int k = 1;
					for(int i = 0; i<2; i++) {
						for(int j = 0; j<2; j++) {
							rotations[i][j] = TileLibrary.getTile(oldTile.getX(), oldTile.getY(), .08f, selectedTrap.getId());
							rotations[i][j].setName(k+"");
							k++;
						}
					}
					Vector2f size1 = new Vector2f(.8f, .8f);
					rotationDialogueBox = new RotationDialogueBox(new Vector2f(-size1.x/2, -size1.y/2), size1, rotations, "Which way should it point?");
				}
			}
		}
		if(rotationDialogueBox.isOn()) {
			int selected = rotationDialogueBox.getShopItem(mouseX, mouseY);
			if(selected != 0) {
				rotationDialogueBox.setSelection(selected);
			}
			if(rotationDialogueBox.getSelection() != 0 /*&& rotationDialogueBox.isConfirmed(mouseX, mouseY)*/) {
				grid.setTile((int)epicShopofEpicness.getGridLoc().x, (int)epicShopofEpicness.getGridLoc().y, TileLibrary.getTile((int)epicShopofEpicness.getGridLoc().x, (int)epicShopofEpicness.getGridLoc().y, .05f, rotationDialogueBox.getGivenTile().getId()+rotationDialogueBox.getSelection()-1));
				rotationDialogueBox.setSelection(0);
				rotationDialogueBox.setOn(false);
				epicShopofEpicness.setOn(false);
			}
			if(rotationDialogueBox.isCanceled(mouseX, mouseY)) {
				rotationDialogueBox.setOn(false);
			}
		}
		if(epicShopofEpicness.isOn()) {
			if(epicShopofEpicness.isUpArrowClicked(mouseX, mouseY))
				epicShopofEpicness.scrollUp();
			if(epicShopofEpicness.isDownArrowClicked(mouseX, mouseY))
				epicShopofEpicness.scrollDown();
		}
		for(Grid g : grids) {
			if(g.isLevelSelected(mouseX, mouseY) && Main.grid.getFloor() != g.getFloor()) {
				Main.grid = g;
				epicShopofEpicness.setOn(false);
				break;
			}
		}
		//Cursors are here http://www.flaticon.com/packs/cursors-and-pointers for changing the native cursor icon
		//Test each possible button individually here. Tried to make classes and use a for loop, but they were too individualized.
	}
}


	


package librarys;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import renderEngine.Loader;

public class GuiLibrary {
public static GuiTexture button1;
public static GuiTexture gun;
public static GuiTexture crosshair;
public static int bulletTexture;
public static int background;
public static int shop;
public static int nextWave;
public static int health;

//Explorer's basic images for standing and walking
public static int explorerStanding;
public static int explorerWalkingL;
public static int explorerWalkingR;
public static int explorerStanding1;
public static int explorerWalkingL1;
public static int explorerWalkingR1;

public static int minerStanding;
public static int minerWalkingL;
public static int minerWalkingR;
public static int minerStanding1;
public static int minerWalkingL1;
public static int minerWalkingR1;



//Dart Trap Arrow Animation
public static int arrow1;
public static int arrow2;
public static int arrow3;
public static int arrow4;
public static int arrow5;
public static int arrow6;
public static int arrow7;
public static int arrow8;
public static int arrow9;
public static int arrow10;

//Images for the shop
public static int frame;
public static int upArrow;
public static int downArrow;

public static int idol1;
public static int idolOnBlank;

public static int ladder;
public static int ladderTop;


	public static void init(Loader loader) {
		GuiLibrary.explorerStanding = loader.loadTexture("BasicExplorer");
		GuiLibrary.explorerWalkingL = loader.loadTexture("BasicExplorer Walking1");
		GuiLibrary.explorerWalkingR = loader.loadTexture("BasicExplorer Walking2");
		GuiLibrary.explorerStanding1 = loader.loadTexture("BasicExplorerR");
		GuiLibrary.explorerWalkingL1 = loader.loadTexture("BasicExplorer WalkingR1");
		GuiLibrary.explorerWalkingR1 = loader.loadTexture("BasicExplorer WalkingR2");
		
		GuiLibrary.minerStanding = loader.loadTexture("Explorers/Miner/Miner");
		GuiLibrary.minerWalkingL = loader.loadTexture("Explorers/Miner/MinerWalking1");
		GuiLibrary.minerWalkingR = loader.loadTexture("Explorers/Miner/MinerWalking2");
		GuiLibrary.minerStanding1 = loader.loadTexture("Explorers/Miner/MinerR");
		GuiLibrary.minerWalkingL1 = loader.loadTexture("Explorers/Miner/MinerWalkingR1");
		GuiLibrary.minerWalkingR1 = loader.loadTexture("Explorers/Miner/MinerWalkingR2");
		
		GuiLibrary.arrow1 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows1");
		GuiLibrary.arrow2 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows2");
		GuiLibrary.arrow3 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows3");
		GuiLibrary.arrow4 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows4");
		GuiLibrary.arrow5 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows5");
		GuiLibrary.arrow6 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows6");
		GuiLibrary.arrow7 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows7");
		GuiLibrary.arrow8 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows8");
		GuiLibrary.arrow9 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows9");
		GuiLibrary.arrow10 = loader.loadTexture("/Trap Animations/Dart Trap/Arrows10");
		
		GuiLibrary.frame = loader.loadTexture("Frame");
		GuiLibrary.upArrow = loader.loadTexture("UpArrow");
		GuiLibrary.downArrow = loader.loadTexture("DownArrow");
		
		GuiLibrary.idol1 = loader.loadTexture("Idol");
		GuiLibrary.idolOnBlank = loader.loadTexture("/Tiles/IdolOnBlank");

		GuiLibrary.ladder = loader.loadTexture("ladder");
		GuiLibrary.ladderTop = loader.loadTexture("ladderTop");
	}
	

}

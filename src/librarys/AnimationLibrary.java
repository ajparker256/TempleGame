package librarys;

import java.util.ArrayList;

import renderEngine.Loader;

public class AnimationLibrary {

	
	
	public static ArrayList<Integer> explorerRunning;
	public static ArrayList<Integer> minerRunning;
	public static ArrayList<Integer> tntManRunning;
	public static ArrayList<Integer> athleteRunning;
	public static ArrayList<Integer> treasureFinderRunning;
	public static ArrayList<Integer> flame;
	public static ArrayList<Integer> crossBowFiring;
	public static ArrayList<Integer> test;
	public static void init(Loader loader){
		explorerRunning = new ArrayList<Integer>();
		explorerRunning.add(GuiLibrary.explorerStanding);
		explorerRunning.add(GuiLibrary.explorerWalkingL);
		explorerRunning.add(GuiLibrary.explorerStanding);
		explorerRunning.add(GuiLibrary.explorerWalkingR);

		minerRunning=new ArrayList<Integer>();
		minerRunning.add(GuiLibrary.minerStanding);
		minerRunning.add(GuiLibrary.minerWalkingL);
		minerRunning.add(GuiLibrary.minerStanding);
		minerRunning.add(GuiLibrary.minerWalkingR);
		
		tntManRunning = new ArrayList<Integer>();
		tntManRunning.add(GuiLibrary.tntManStanding);
		
		athleteRunning = new ArrayList<Integer>();
		athleteRunning.add(GuiLibrary.athleteStanding);
		
		treasureFinderRunning = new ArrayList<Integer>();
		treasureFinderRunning.add(GuiLibrary.treasureFinderStanding);
		
		
		flame = new ArrayList<Integer>();
		flame.add(GuiLibrary.flame1);
		flame.add(GuiLibrary.flame2);
		flame.add(GuiLibrary.flame3);
		flame.add(GuiLibrary.flame4);
		
		crossBowFiring = new ArrayList<Integer>();
		crossBowFiring.add(GuiLibrary.arrowTrap1);
		crossBowFiring.add(GuiLibrary.arrowTrap2);
		crossBowFiring.add(GuiLibrary.arrowTrap3);
		crossBowFiring.add(GuiLibrary.arrowTrap4);
		
		test=new ArrayList<Integer>();
		test.add(GuiLibrary.test1);
		test.add(GuiLibrary.test2);
		test.add(GuiLibrary.test3);
		test.add(GuiLibrary.test4);
		test.add(GuiLibrary.test3);
		test.add(GuiLibrary.test2);
		test.add(GuiLibrary.test1);
		test.add(GuiLibrary.test5);
		test.add(GuiLibrary.test6);
		test.add(GuiLibrary.test7);
		test.add(GuiLibrary.test6);
		test.add(GuiLibrary.test5);
	}


	
}

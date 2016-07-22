package librarys;

import java.util.ArrayList;

import renderEngine.Loader;

public class AnimationLibrary {

	
	
	public static ArrayList<Integer> explorer;
	public static ArrayList<Integer> miner;
	public static ArrayList<Integer> flame;
	public static ArrayList<Integer> crossBowFiring;
	public static ArrayList<Integer> test;
	public static void init(Loader loader){
		explorer=new ArrayList<Integer>();
		explorer.add(GuiLibrary.explorerStanding);
		explorer.add(GuiLibrary.explorerWalkingL);
		explorer.add(GuiLibrary.explorerStanding);
		explorer.add(GuiLibrary.explorerWalkingR);

		miner=new ArrayList<Integer>();
		miner.add(GuiLibrary.minerStanding);
		miner.add(GuiLibrary.minerWalkingL);
		miner.add(GuiLibrary.minerStanding);
		miner.add(GuiLibrary.minerWalkingR);
		
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

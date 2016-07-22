package librarys;

import java.util.ArrayList;

import renderEngine.Loader;

public class AnimationLibrary {

	
	
	public static ArrayList<Integer> explorer;
	public static ArrayList<Integer> miner;
	public static ArrayList<Integer> flame;
	public static ArrayList<Integer> crossBowFiring;
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
	}


	
}

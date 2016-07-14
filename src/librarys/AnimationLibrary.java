package librarys;

import java.util.ArrayList;

import renderEngine.Loader;

public class AnimationLibrary {

	
	
	public static ArrayList<Integer> explorerX;
	public static ArrayList<Integer> explorerY;
	public static ArrayList<Integer> minerY;
	public static ArrayList<Integer> minerX;
	public static ArrayList<Integer> flame;
	public static ArrayList<Integer> crossBowFiring;
	public static void init(Loader loader){
		explorerY=new ArrayList<Integer>();
		explorerY.add(GuiLibrary.explorerStanding);
		explorerY.add(GuiLibrary.explorerWalkingL);
		explorerY.add(GuiLibrary.explorerStanding);
		explorerY.add(GuiLibrary.explorerWalkingR);
		explorerX=new ArrayList<Integer>();
		explorerX.add(GuiLibrary.explorerStanding1);
		explorerX.add(GuiLibrary.explorerWalkingL1);
		explorerX.add(GuiLibrary.explorerStanding1);
		explorerX.add(GuiLibrary.explorerWalkingR1);
		minerY=new ArrayList<Integer>();
		minerY.add(GuiLibrary.minerStanding);
		minerY.add(GuiLibrary.minerWalkingL);
		minerY.add(GuiLibrary.minerStanding);
		minerY.add(GuiLibrary.minerWalkingR);
		minerX=new ArrayList<Integer>();
		minerX.add(GuiLibrary.minerStanding1);
		minerX.add(GuiLibrary.minerWalkingL1);
		minerX.add(GuiLibrary.minerStanding1);
		minerX.add(GuiLibrary.minerWalkingR1);
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

package librarys;

import java.util.ArrayList;

import renderEngine.Loader;

public class AnimationLibrary {

	
	
	public static ArrayList<Integer> explorerX;
	public static ArrayList<Integer> explorerY;
	public static void init(Loader loader){
		explorerY=new ArrayList<Integer>();
		explorerY.add(GuiLibrary.explorerStanding);
		explorerY.add(GuiLibrary.explorerWalkingL);
		explorerY.add(GuiLibrary.explorerStanding);
		explorerY.add(GuiLibrary.explorerWalkingR);
		explorerX=new ArrayList<Integer>();
		explorerX.add(GuiLibrary.explorerStanding);
		explorerX.add(GuiLibrary.explorerWalkingL);
		explorerX.add(GuiLibrary.explorerStanding);
		explorerX.add(GuiLibrary.explorerWalkingR);

	}


	
}

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
		explorerX.add(GuiLibrary.explorerStanding1);
		explorerX.add(GuiLibrary.explorerWalkingL1);
		explorerX.add(GuiLibrary.explorerStanding1);
		explorerX.add(GuiLibrary.explorerWalkingR1);

	}


	
}

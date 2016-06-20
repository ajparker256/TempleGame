package librarys;

import java.util.ArrayList;

import renderEngine.Loader;

public class AnimationLibrary {

	
	
	
	public static ArrayList<Integer> explorer;
	public static void init(Loader loader){
		explorer=new ArrayList<Integer>();
		explorer.add(GuiLibrary.explorerStanding);
		explorer.add(GuiLibrary.explorerWalkingL);
		explorer.add(GuiLibrary.explorerStanding);
		explorer.add(GuiLibrary.explorerWalkingR);

	}


	
}

package librarys;

import org.lwjgl.util.vector.Vector2f;

import renderEngine.Loader;
import gui.Animation;
import gui.GuiTexture;

public class AnimationLibrary {

	public static Animation explorer;
	
	
	public static void init(Loader loader){
		explorer=makeExplorer(loader,30);

	}
	public static Animation makeExplorer(Loader loader, int delay) {
		Animation explorerWalk= new Animation();
		explorerWalk.addFrame(new GuiTexture(GuiLibrary.explorerStanding, explorerWalk.getLoc(), new Vector2f(.05f, .05f)));
		explorerWalk.addFrame(new GuiTexture(GuiLibrary.explorerWalkingL, explorerWalk.getLoc(), new Vector2f(.05f, .05f)));
		explorerWalk.addFrame(new GuiTexture(GuiLibrary.explorerStanding, explorerWalk.getLoc(), new Vector2f(.05f, .05f)));
		explorerWalk.addFrame(new GuiTexture(GuiLibrary.explorerWalkingR, explorerWalk.getLoc(), new Vector2f(.05f, .05f)));
		explorerWalk.setDelay(delay);
		return explorerWalk;
	}

	
}

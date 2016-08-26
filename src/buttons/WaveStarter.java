package buttons;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import gui.GuiTexture;
import librarys.GuiLibrary;

public class WaveStarter {

	private Button start;
	private GuiTexture startIconNotClicked;
	private GuiTexture startIconClicked;
	private double durationOfClickedIconMilli;
	private double timeClickedMilli;
	private boolean wasClicked;
	
	private boolean isOn;
	
	
	public WaveStarter(Vector2f locationBL, Vector2f totalSize) {
		start = new Button(new Vector2f(locationBL.x, locationBL.y+totalSize.y), new Vector2f(locationBL.x+totalSize.x, locationBL.y));
		Vector2f center = new Vector2f(locationBL.x+totalSize.x, locationBL.y+totalSize.y);
		startIconNotClicked = new GuiTexture(GuiLibrary.exclamationPoint, center, totalSize);
		startIconClicked = new GuiTexture(GuiLibrary.nextWave, center, totalSize);
		durationOfClickedIconMilli = 100;
		wasClicked = false;
		
		isOn = false;
	}
	
	public boolean isStartWaveClicked(float mouseX, float mouseY) {
		if(start.isClicked(mouseX, mouseY) && isOn) {
			wasClicked = true;
			timeClickedMilli = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
		if(isOn) {
			checkTime();
			if(wasClicked) {
				dynamicGuis.add(startIconClicked);
			} else {
				dynamicGuis.add(startIconNotClicked);
			}
		}
	}
	
	public void setOn(boolean newOn) {
		isOn = newOn;
	}
	
	private void checkTime() {
		if(timeClickedMilli+durationOfClickedIconMilli<=System.currentTimeMillis()) {
			wasClicked = false;
		}
	}
}

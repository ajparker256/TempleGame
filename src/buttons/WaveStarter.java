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
		Vector2f center = new Vector2f(locationBL.x+totalSize.x/2, locationBL.y+totalSize.y/2);
		startIconNotClicked = new GuiTexture(GuiLibrary.nextWaveUnclicked, center, new Vector2f(totalSize.x/2, totalSize.y/2));
		startIconClicked = new GuiTexture(GuiLibrary.nextWaveClicked, center, new Vector2f(totalSize.x/2, totalSize.y/2));
		durationOfClickedIconMilli = 300;
		wasClicked = false;
		
		isOn = true;
	}
	
	public boolean isStartWaveClicked(float mouseX, float mouseY) {
		checkIfStillClicked(mouseX, mouseY);
		if(start.isClicked(mouseX, mouseY) && isOn && !wasClicked) {
			wasClicked = true;
			timeClickedMilli = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	private void checkIfStillClicked(float mouseX, float mouseY) {
		checkTime();
		if(wasClicked && start.isClicked(mouseX, mouseY)) {
			timeClickedMilli = System.currentTimeMillis();
		}
	}
	
	public void render(ArrayList<GuiTexture> dynamicGuis) {
		if(isOn) {
			if(wasClicked) {
				checkTime();
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

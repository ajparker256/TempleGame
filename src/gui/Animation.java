package gui;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

//This class is designed to run through a list of textures in order to create animations. Internal Delays are done by using count%frameDelay == 0 
//Implied default of %1 == 0 but can be raised.
public class Animation {
	
	//This keeps track of the current animation image
	private int count;
	
	//This is the delay between frame changes
	private int frameDelay;
	
	//This holds all of the textures for the animation cycle
	private ArrayList<GuiTexture> frames;
	
	private Vector2f location;
	
	public Animation() {
		frameDelay = 1;
		count = 0;
		location = new Vector2f(0,0);
		frames = new ArrayList<GuiTexture>();
	}
	
	private void update() {
		for(GuiTexture g : frames) {
			g.setPosition(location);
		}
	}
	
	public void setLoc(Vector2f loc) {
		location = loc;
		update();
	}
	
	public Vector2f getLoc() {
		return location;
	}
	
	//Adds frame to the end of the list
	public void addFrame(GuiTexture g) {
		frames.add(g);
	}
	
	//This unites animations
	public void addAll(Animation addOn) {
		frames.addAll(addOn.getFrames());
	}
	
	//This adds a frame at the given location
	public void addFrame(GuiTexture g, int i) {
		frames.add(i, g);
	}
	
	//Removes a frame at the given location
	public void removeFrame(int i) {
		frames.remove(i);
	}
	
	//This returns the arraylist of frames
	public ArrayList<GuiTexture> getFrames() {
		return frames;
	}
	
	public void setDelay(int i) {
		frameDelay = i;
	}
	
	public GuiTexture getFrame() {
		GuiTexture currentFrame = frames.get(count/frameDelay);
		run();
		return currentFrame;
	}
	
	public void run() {
		
		//This moves to the next frame or loops
		if(count>frames.size()*frameDelay-2) {
			count = 0;
		} else {
			count++;
		}
	}
}

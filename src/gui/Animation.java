package gui;

import java.util.ArrayList;

//This class is designed to run through a list of textures in order to create animations. Internal Delays are done by using count%frameDelay == 0 
//Implied default of %1 == 0 but can be raised.
public class Animation {
	
	//This keeps track of the current animation image
	private int count;
	
	//This is the delay between frame changes
	private int frameDelay;
	
	//This holds all of the textures for the animation cycle
	private ArrayList<GuiTexture> frames;
	
	private ArrayList<GuiTexture> currentFrame;
	
	public Animation() {
		frameDelay = 1;
		count = 0;
		frames = new ArrayList<GuiTexture>();
		currentFrame = new ArrayList<GuiTexture>();
	}
	
	//Adds frame to the end of the list
	public void addFrame(GuiTexture g) {
		frames.add(g);
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
	
	//This displays the current frame
	public void run(GuiRenderer g) {
		currentFrame.clear();
		currentFrame.add(frames.get(count));
		g.render(currentFrame);
		count++;
	}
}

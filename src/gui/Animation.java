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
	
	private ArrayList<GuiTexture> currentFrame;
	
	private Vector2f location;
	
	public Animation(Vector2f loc) {
		frameDelay = 1;
		count = 0;
		location = loc;
		frames = new ArrayList<GuiTexture>();
		currentFrame = new ArrayList<GuiTexture>();
	}
	
	public Animation(Vector2f loc, ArrayList<GuiTexture> fs) {
		frameDelay = 1;
		count = 0;
		location = loc;
		frames = new ArrayList<GuiTexture>();
		currentFrame = fs;
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
		//This makes it so that only the current frame displays
		currentFrame.clear();
		currentFrame.add(frames.get(count));
		
		//This renders the frame
		g.render(currentFrame);
		
		//This moves to the next frame or loops
		if(count>frames.size()*frameDelay) {
			count = 0;
		} else {
			count++;
		}
	}
}

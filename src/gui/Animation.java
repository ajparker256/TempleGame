package gui;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

//This class is designed to run through a list of textures in order to create animations. Internal Delays are done by using count%frameDelay == 0 
//Implied default of %1 == 0 but can be raised.
public class Animation {
	
	private boolean freezeFrame;
	
	//This keeps track of the current animation image
	private int count;
	
	//This is the delay between frame changes
	private int frameDelay;
	
	//This holds all of the textures for the animation cycle
	private ArrayList<GuiTexture> frames;
	
	private Vector2f location;
	
	public Animation(ArrayList<Integer> list,Vector2f location,Vector2f size) {
		frameDelay = 60;
		count = 0;
		location = new Vector2f(0,0);
		frames = new ArrayList<GuiTexture>();
		for(Integer i:list){
			frames.add(new GuiTexture(i,location,size));
		}
		freezeFrame = false;
	}
	
	public boolean getIfFrozen() {
		return freezeFrame;
	}
	
	public void setIfFrozen(boolean b) {
		freezeFrame = b;
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
	
	//This only runs once.
	public GuiTexture getFrameNoLoop() {
		if(!(count>frames.size()*frameDelay-3)){
			GuiTexture currentFrame = frames.get(count/frameDelay);
			run();
			return currentFrame;
		}
		else return frames.get(0);
	}
	
	public void run() {
		
		//This moves to the next frame or loops
		if(count>frames.size()*frameDelay-2) {
			count = 0;
		} else if(!freezeFrame){
			count++;
		}
	}
	
	public void resetCount() {
		count = 0;
	}

	public void setRotation(double rotation) {
		for(GuiTexture frame:frames){
			frame.setRotation(rotation);
		}
		
	}
}

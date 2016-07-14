package entities;

import org.lwjgl.util.vector.Vector2f;

import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;

public class Flame extends Projectile{
	
	private Animation flames;
	
	public Flame(int x, int y, Vector2f size, int floor) {
		super(x, y, 1, floor);
		velocity = new Vector2f(0,0);
		flames = new Animation(AnimationLibrary.flame, new Vector2f(0,0), size);
		flames.setDelay(20);
	}
	
	public GuiTexture render() {
		return flames.getFrame();
	}
}

package entities;


import java.awt.Point;

import org.lwjgl.util.vector.Vector2f;

import grid.Grid;
import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;

public class Flame extends Projectile{
	
	private Animation flames;
	
	public Flame(int x, int y, Vector2f size, int floor, int damage) {
		super(1, new Point(x, y), floor, (double)damage);
		velocity = new Vector2f(0,0);
		flames = new Animation(AnimationLibrary.flame, new Vector2f(0,0), size);
		flames.setDelay(20);
	}
	
	public GuiTexture render() {
		return flames.getFrame();
	}
}

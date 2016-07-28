package traps;

import java.awt.Point;

import librarys.GuiLibrary;
import main.Main;

import org.lwjgl.util.vector.Vector2f;

import pathing.Group;
import pathing.Squad;
import entities.Projectile;
import gui.GuiTexture;

public class Fire extends Projectile {

	private int life;
	public Fire(int direction, int x, int y, int floor) {
		super(direction, x, y, floor);
		super.texture=GuiLibrary.flame1;
		super.image = new GuiTexture(texture, location, size);
		life=1000;

	}
	@Override
	public void move(long milli){
		
	}
	@Override
	public boolean tick(long milli){
		life-=milli;
		if(life<=0){
			kill=true;
		}
		if(kill){
			return false;
		}


		for(Squad squad:Main.squads){
			for(Group group: squad.getGroups()){
				if(group.getRealLoc().equals(new Point(x,y))){
					group.damage(new boolean[]{true,true,true,true},0.01);
				}
			}
		}
	
			
			return true;
		}
		
			
	}


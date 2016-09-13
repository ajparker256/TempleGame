package traps;

import java.awt.Point;
import java.util.ArrayList;

import librarys.GuiLibrary;

import pathing.Group;
import pathing.Squad;
import entities.Projectile;
import grid.Grid;
import gui.GuiTexture;

public class Fire extends Projectile {

	private int life;
	public Fire(int direction, Point coordinates, Grid floor, double damage) {
		super(direction, coordinates, floor, damage);
		super.texture=GuiLibrary.flame1;
		super.image = new GuiTexture(texture, location, size);
		life=1000;

	}
	@Override
	public void move(long milli){
		
	}
	@Override
	public boolean tick(long milli, ArrayList<Squad> squads){
		life-=milli;
		if(life<=0){
			kill=true;
		}
		if(kill){
			return false;
		}


		for(Squad squad: squads){
			for(Group group: squad.getGroups()){
				if(group.getRealLoc().equals(new Point(x,y))){
					group.damage(new boolean[]{true,true,true,true},0.01);
				}
			}
		}
	
			
			return true;
		}
		
			
	}


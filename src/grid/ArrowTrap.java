package grid;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import entities.Projectile;
import gui.GuiTexture;
import librarys.GuiLibrary;
import main.Main;
import renderEngine.DisplayManager;

public class ArrowTrap extends Tile{
	
	protected int hp;
	protected int level;
	protected int direction;
	
	public ArrowTrap(int x, int y, float size,int direction) {
		super(x, y, size, Main.grid.getLoc());
		super.passable=false;
		super.canInteract=true;
		this.direction=direction;
		this.hp=100;
		this.texture=1;
		this.guiTexture=(new GuiTexture(GuiLibrary.desertBackdrop,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Arrow Trap";
		id = 5;
		setTriggers();
	}
	@Override
	public void whenTriggered(){
		System.out.println("adsasdasd");
		Main.projectiles.add(new Projectile(direction,x,y,super.floor));
	}
	private void setTriggers(){
		
		int i=1;
		switch(direction){
		case 1:while(y+i<=9){
			Main.grids.get(floor).getTile(x, y+1).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
		case 2:while(x+i<=9){
			Main.grids.get(floor).getTile(x+i, y).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
		case 3:while(y-i>=0){
			Main.grids.get(floor).getTile(x, y-i).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
		case 4:while(x-i>=0){
			Main.grids.get(floor).getTile(x-i, y).addTrapRef(new Point(this.x,this.y));
			i++;
		}
		break;
	}
	}
}
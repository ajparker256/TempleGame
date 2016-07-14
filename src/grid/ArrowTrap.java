package grid;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import entities.Projectile;
import gui.Animation;
import gui.GuiTexture;
import librarys.AnimationLibrary;
import librarys.GuiLibrary;
import main.Main;
import renderEngine.DisplayManager;

public class ArrowTrap extends Tile{
	
	protected int level;
	protected int direction;
	protected Animation firing;
	protected boolean isFiring;
	private long cooldown;
	private long maxCd;
	private int range;
	
	public ArrowTrap(int x, int y, float size,int direction) {
		super(x, y, size, Main.grid.getLoc());
		super.passable=false;
		super.canInteract=true;
		this.cooldown=0;
		this.direction=direction;
		this.texture=1;
		this.guiTexture=(new GuiTexture(GuiLibrary.arrowTrap1,position,new Vector2f(size,(float) (size*DisplayManager.getAspectratio()))));	
		this.name = "Arrow Trap";
		this.maxCd=1000;
		id = 5;
		range = 5;
		firing = new Animation(AnimationLibrary.crossBowFiring, position, new Vector2f(size, (float)(size*DisplayManager.getAspectratio())));
		firing.setDelay(25);
		setTriggers();
	}
	@Override
	public void whenTriggered(Point p){
		if(cooldown<=0){
			boolean fire = false;
			//Up
			if(direction == 1 && p.y-y<range) {
				fire = true;
				for(int i = y+1; i<p.y; i++) {
					if(Main.grids.get(floor).getTile(x, i).getId() != 0) {
						fire = false;
						break;
					}
				}
			} else if(direction == 2 && p.x-x<range) {
				fire = true;
				for(int i = x+1; i<p.x; i++) {
					if(Main.grids.get(floor).getTile(i, y).getId() != 0) {
						fire = false;
						break;
					}
				}
			} else if(direction == 3 && y-p.y <range) {
				fire = true;
				for(int i = y-1; i>p.y; i--) {
					if(Main.grids.get(floor).getTile(x, i).getId() != 0) {
						fire = false;
						break;
					}
				}
			} else if(direction == 4 && x-p.x<range) {
				fire = true;
				for(int i = x-1; i>p.y; i--) {
					if(Main.grids.get(floor).getTile(x, i).getId() != 0) {
						fire = false;
						break;
					}
				}
			} 
			if(fire) {
				Main.projectiles.add(new Projectile(direction,x,y,super.floor));
				System.out.println("shooting");
				isFiring = true;
				cooldown=maxCd; 
			}
		}
	}
	
	public void trigger(){
	
	}
	
	@Override
	public GuiTexture drawTile() {
		if(isFiring) {
			GuiTexture temp = firing.getFrameNoLoop();
			if(temp == null) {
				return guiTexture;
			} else {
				isFiring = false;
				return temp;
			}
		} 
		return guiTexture;
	}
	private void setTriggers(){
		
		int i=1;
		switch(direction){
		case 1:while(y+i<=9){
			Main.grids.get(floor).getTile(x, y+i).addTrapRef(new Point(this.x,this.y));
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
	@Override
	public void tick(long milli){
		cooldown-=milli;
	}
}
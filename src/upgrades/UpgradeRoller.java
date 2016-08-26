package upgrades;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import buttons.Button;
import grid.Tile;
import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.StringLibrary;
import librarys.TileLibrary;
import librarys.UpgradeLibrary;
import traps.Trap;

public class UpgradeRoller {
	
		GuiTexture background;
		
		Button clickToRoll;
		
		Button[] options;
		
		Upgrade[] givenOptions;
		
		ArrayList<GuiTexture> optionsTextures;
		
		Vector2f location;
		
		Vector2f size;
		
		Trap trap;
		
		boolean isOn;
		
		double sinceOpened;
		
		public UpgradeRoller() {
			isOn = false;
		}
		
		public UpgradeRoller(Vector2f loc, Vector2f size, Trap trapBeingUpgraded) {
			//background = new GuiTexture(GuiLibrary.commonBackground, new Vector2f(loc.x+size.x/2, loc.y+size.y/2), size);
			options = new Button[3];
			givenOptions = new Upgrade[3];
			optionsTextures = new ArrayList<GuiTexture>();
			location = loc;
			this.size = size;
			trap = trapBeingUpgraded;
			for(int i = 0; i<options.length; i++) {
				//location.x+i*(size.x/options.length*2), location.y+size.y/2
				options[i] = new Button(new Vector2f(location.x-size.x/options.length+(i)*(2*size.x/options.length),
						location.y+size.y),
						new Vector2f(location.x-size.x/options.length+(i+1)*(2*size.x/options.length),
						location.y));
			}
			roll();
			isOn = true;
			sinceOpened	= System.currentTimeMillis();
		}
		
		public double getTimeOpened() {
			return sinceOpened;
		}
		
		public Trap getTrap() {
			return trap;
		}
		
		public boolean isOn() {
			return isOn;
		}
		
		public void setOn(boolean b) {
			isOn = b;
		}
		
		public void render(ArrayList<GuiTexture> dynamicGuis) {
			//dynamicGuis.add(background);
			if(isOn)
				dynamicGuis.addAll(optionsTextures);
		}
		
		public boolean isUpgradable(Tile currentlySelected) {
			return currentlySelected.getId() >= TileLibrary.getFirstTrapId();
		}
		
		public void roll() {
			optionsTextures.clear();
			StringLibrary.setSize(new Vector2f(.015f, .03f));
			for(int i = 0; i<givenOptions.length; i++) {
				givenOptions[i] = UpgradeLibrary.getUpgrade(trap.getId());
				givenOptions[i].getTexture().setScale(new Vector2f(size.x/options.length, size.y/2));
				optionsTextures.addAll(givenOptions[i].render(new Vector2f(location.x+i*(size.x/options.length*2), location.y+size.y/2)));
			}
			StringLibrary.setSize(new Vector2f(.02f, .04f));
		}
		
		public boolean itemIsClicked(float mouseX, float mouseY) {
			for(Button b : options) {
				if(b.isClicked(mouseX, mouseY)) {
					return true;
				}
			}
			
			return false;
		}
		
		public Upgrade getClickedUpgrade(float mouseX, float mouseY) {
			int i = 0;
			for(Button b : options) {
				if(b.isClicked(mouseX, mouseY)) {
					return givenOptions[i];
				}
				i++;
			}
			System.out.println("Error Case in Upgrade Roller clicking logic");
			return null;
		}

}

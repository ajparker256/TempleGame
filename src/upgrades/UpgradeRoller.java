package upgrades;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import buttons.Button;
import grid.Tile;
import gui.GuiTexture;
import librarys.GuiLibrary;
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
		
		public UpgradeRoller(Vector2f loc, Vector2f size, Trap trapBeingUpgraded) {
			background = new GuiTexture(GuiLibrary.commonBackground, loc, size);
			options = new Button[3];
			givenOptions = new Upgrade[3];
			optionsTextures = new ArrayList<GuiTexture>();
			location = loc;
			this.size = size;
			trap = trapBeingUpgraded;
			for(int i = 0; i<options.length; i++) {
				options[i] = new Button(new Vector2f(location.x+size.x/options.length/2-size.x/4+i*(size.x/options.length),
						location.y+size.y/6*5),
						new Vector2f(location.x+size.x/options.length/2+size.x/4+i*(size.x/options.length),
						location.y+size.y/6));
			}
			roll();
			isOn = false;
		}
		
		public ArrayList<GuiTexture> render() {
			ArrayList<GuiTexture> toBeRendered = new ArrayList<GuiTexture>();
			toBeRendered.add(background);
			toBeRendered.addAll(optionsTextures);
			return toBeRendered;
		}
		
		public void roll() {
			optionsTextures.clear();
			for(int i = 0; i<givenOptions.length; i++) {
				givenOptions[i] = UpgradeLibrary.getUpgrade(trap.getId());
				optionsTextures.addAll(givenOptions[i].render());
			}
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

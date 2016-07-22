package upgrades;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import buttons.Button;
import grid.Tile;
import gui.GuiTexture;
import librarys.GuiLibrary;

public class UpgradeRoller {
	
		GuiTexture background;
		
		Button clickToRoll;
		
		Button[] options;
		
		ArrayList<ArrayList<GuiTexture>> optionsTextures;
		
		Vector2f location;
		
		Vector2f size;
		
		Tile trap;
		
		boolean isOn;
		
		public UpgradeRoller(Vector2f loc, Vector2f size, Tile trapBeingUpgraded) {
			background = new GuiTexture(GuiLibrary.commonBackground, loc, size);
			options = new Button[3];
			optionsTextures = new ArrayList<ArrayList<GuiTexture>>();
			location = loc;
			this.size = size;
			trap = trapBeingUpgraded;
			for(int i = 0; i<options.length; i++) {
				options[i] = new Button(new Vector2f(location.x+size.x/options.length/2-size.x/4+i*(size.x/options.length),
						location.y+size.y/6*5),
						new Vector2f(location.x+size.x/options.length/2+size.x/4+i*(size.x/options.length),
						location.y+size.y/6));
			}
			isOn = false;
		}
		
		public ArrayList<GuiTexture> render() {
			ArrayList<GuiTexture> toBeRendered = new ArrayList<GuiTexture>();
			toBeRendered.add(background);
			for(int i = 0; i<optionsTextures.size();i++) {
				toBeRendered.addAll(optionsTextures.get(i));
			}
			return toBeRendered;
		}
		
		public void roll() {
			//TODO after Upgrade Library
		}

}

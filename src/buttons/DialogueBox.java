package buttons;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import grid.Tile;
import gui.GuiTexture;
import librarys.StringLibrary;
import renderEngine.DisplayManager;

public class DialogueBox extends Shop{

	private Button confirm;
	
	private Button cancel;
	
	private String message;
	
	public DialogueBox(Vector2f location, Vector2f size, Tile[][] options, String message) {
		super(location, size, options);
		this.message = message;
		visibilityRange[1] = 2;
		visibilityRange[3] = 2;
		if(options.length<2) {
			visibilityRange[1] = options.length;
		} else {
			visibilityRange[1] = 2;
		}
		if(options[0].length<2) {
			visibilityRange[3] = options[0].length;
		} else {
			visibilityRange[3] = options[0].length;
			visibilityRange[2] = options[0].length-2;
		}
		isOn = true;
		
		
	}
	
	public void render(ArrayList<GuiTexture> guis) {
		
		for(int i = visibilityRange[2]; i<visibilityRange[3]; i++) {
			for(int j = visibilityRange[0]; j<visibilityRange[1]; j++) {
				
				guis.add(traps[j][i].drawTile());
				
				guis.addAll(StringLibrary.makeItFitC(traps[j][i].toString(), new Vector2f(traps[j][i].drawTile().getPosition().x, 
						traps[j][i].drawTile().getPosition().y-traps[j][i].drawTile().getScale().y+StringLibrary.getSize().y), size.x/traps[0].length));
			}
		}
		guis.addAll(StringLibrary.makeItFitC(message, new Vector2f(location.x, location.y+size.y+.1f), size.x));
		
		guis.addAll(StringLibrary.drawString("X", new Vector2f(location.x+size.x*(.5f * (visibilityRange[1]-visibilityRange[0]))-StringLibrary.getWidth('X'),
				location.y+size.y*(.5f * (visibilityRange[3]-visibilityRange[2]))+StringLibrary.getSize().y)));
		guis.addAll(StringLibrary.drawString("X", new Vector2f(location.x+size.x*(.5f * (visibilityRange[1]-visibilityRange[0]))-StringLibrary.getWidth('X'),
				location.y+size.y*(.5f * (visibilityRange[3]-visibilityRange[2]))+StringLibrary.getSize().y)));
		guis.addAll(StringLibrary.drawString("X", new Vector2f(location.x+size.x*(.5f * (visibilityRange[1]-visibilityRange[0]))-StringLibrary.getWidth('X'),
				location.y+size.y*(.5f * (visibilityRange[3]-visibilityRange[2]))+StringLibrary.getSize().y)));
	}

	
	
}

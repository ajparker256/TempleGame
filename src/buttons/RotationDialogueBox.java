package buttons;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import grid.Tile;
import gui.GuiTexture;
import librarys.GuiLibrary;
import librarys.ShopItemLibrary;
import librarys.StringLibrary;
import renderEngine.DisplayManager;
import shopItems.ShopItem;
import tools.MathM;

public class RotationDialogueBox extends Shop{

	private Button confirm;
	
	private Button cancel;
	
	private String message;
	
	private GuiTexture background;
	
	private int currentSelection;
	
	public RotationDialogueBox() {
		isOn = false;
	}
	
	public RotationDialogueBox(Vector2f location, Vector2f size, ShopItem[][] items, String message) {
		super(location, size, items);
		this.message = message;
		confirm = new Button(new Vector2f(location.x,
				location.y+StringLibrary.getSize().y),
				new Vector2f(location.x+StringLibrary.getWidth("Confirm"),
						location.y));
		cancel = new Button(new Vector2f(location.x+size.x*(.5f * (visibilityRange[1]-visibilityRange[0]))-StringLibrary.getWidth("Cancel"),
				location.y+StringLibrary.getSize().y), 
				new Vector2f(location.x+size.x*(.5f * (visibilityRange[1] - visibilityRange[0])),
					location.y));
		visibilityRange[1] = 2;
		visibilityRange[3] = 2;
		
		if(traps.length<2) {
			visibilityRange[1] = traps.length;
		} else {
			visibilityRange[1] = 2;
		}
		if(traps[0].length<2) {
			visibilityRange[3] = traps[0].length;
		} else {
			visibilityRange[3] = traps[0].length;
			visibilityRange[2] = traps[0].length-2;
		}
		int k = 1;
		for(int i = visibilityRange[2]; i<visibilityRange[3]; i++) {
			for(int j = visibilityRange[0]; j<visibilityRange[1]; j++) {
				traps[j][i].drawTile().setRotation(MathM.toDegrees(k));
				k++;
			}
		}
		isOn = true;
		currentSelection = 0;
		background = new GuiTexture(GuiLibrary.dirt0, new Vector2f(location.x+size.x/2, location.y+size.y/2), new Vector2f(size.x/2,size.y/2));
	}
	
	public ShopItem getGivenTile() {
		return traps[0][0];
	}
	
	public boolean isCanceled(float mouseX, float mouseY) {
		return cancel.isClicked(mouseX, mouseY);
	}
	
	public void setSelection(int i) {
		currentSelection = i;
	}
	
	public int getSelection() {
		return currentSelection;
	}
	
	public boolean isConfirmed(float mouseX, float mouseY) {
		return confirm.isClicked(mouseX, mouseY);
	}
	
	public void render(ArrayList<GuiTexture> guis) {
		if(isOn) {
			int k = 1;
			guis.add(background);
			for(int i = visibilityRange[2]; i<visibilityRange[3]; i++) {
				for(int j = visibilityRange[0]; j<visibilityRange[1]; j++) {
					
					guis.add(traps[j][i].drawTile());
					
					guis.addAll(StringLibrary.makeItFitC(k+"", new Vector2f(traps[j][i].drawTile().getPosition().x, 
							traps[j][i].drawTile().getPosition().y-traps[j][i].drawTile().getScale().y+StringLibrary.getSize().y), size.x/traps[0].length));
					k++;
				}
			}
			guis.addAll(StringLibrary.makeItFitC(message, new Vector2f(location.x, location.y+size.y+.1f), size.x));
			
			guis.addAll(StringLibrary.drawString("Confirm", new Vector2f(location.x,
					location.y+StringLibrary.getSize().y/2)));
			guis.addAll(StringLibrary.drawString("Cancel", new Vector2f(location.x+size.x*(.5f * (visibilityRange[1]-visibilityRange[0]))-StringLibrary.getWidth("Cancel"),
					location.y+StringLibrary.getSize().y/2)));
		}
	}
	
	@Override
	public int getShopItem(float mouseX, float mouseY) {
		int k = 1;
		for(int i = visibilityRange[2]; i<visibilityRange[3]; i++) {
			for(int j = visibilityRange[0]; j<visibilityRange[1]; j++) {
				if(shopHitboxes[j][i].isClicked(mouseX, mouseY)) {
					return k;
				}
				k++;
			}
		}
		return 0;
	}

	
	
}

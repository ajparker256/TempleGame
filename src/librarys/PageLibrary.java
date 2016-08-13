package librarys;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import buttons.Page;

public class PageLibrary {
	
	public static ArrayList<Page> traps;
	public static ArrayList<Page> explorers;
	
	public static Page tikiTrap;
	public static Page arrowTrap;
	public static Page treasureTrap;
	public static Page cursedIdolTrap;
	//All trap info pages
	
	public static Page basicExplorer;
	public static Page athlete;
	public static Page miner;
	public static Page treasureFinder;
	public static Page exploder;
	//All explorer info pages
	
	//Location of menu/page area and size of it
	public static void init(Vector2f location, Vector2f size) {
		tikiTrap = new Page(location, size, "Tiki Trap", "This trap releases intense bursts of heat on unwary explorers."
				+ " It sets fire to every tile around it in its basic form. Strong area of effect unit.", GuiLibrary.tikiTrap);
		arrowTrap = new Page(location, size, "Arrow Trap", "Death, destruction, and devastation is the motto of this trap's creator."
				+ " This trap fires at long ranges dealing moderate amounts of damage. It cannot fire through other things though.", GuiLibrary.arrowTrap1);
		treasureTrap = new Page(location, size, "Treasure Chest", "This sealed vault of money will generate more over time. If an explorer" 
				+ " reaches it though, they will have more funding for their expiditions. Treasure finders are attracted to this trap.", GuiLibrary.treasureClosed);
		cursedIdolTrap = new Page(location, size, "Cursed Idol", "As greedy explorers pick up this trap, a curse fills their minds. This trap"
				+ " causes explorers to bend to your will in exchange for funding their expidition a small amount.", GuiLibrary.idolOnBlank);
		
		traps.add(tikiTrap);
		traps.add(arrowTrap);
		traps.add(treasureTrap);
		traps.add(cursedIdolTrap);
	}
	
	

}

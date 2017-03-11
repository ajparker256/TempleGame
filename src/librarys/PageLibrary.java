package librarys;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;

import buttons.*;


public class PageLibrary {
	
	public static ArrayList<Linkable> traps;
	public static ArrayList<Linkable> explorers;
	public static ArrayList<Linkable> categories;
	public static ArrayList<Linkable> instructions;
	
	private static HashMap<String, Linkable> allPages;
	
	public static Menu trapMenu;
	public static Menu categoriesMenu;
	public static Menu explorerMenu;
	public static Menu instructMenu;
	public static Page credits;
	//category area
	
	private static Page tikiTrap;
	private static Page arrowTrap;
	private static Page treasureTrap;
	private static Page cursedIdolTrap;
	//All trap info pages
	
	private static Page basicExplorer;
	private static Page athlete;
	private static Page miner;
	private static Page treasureFinder;
	private static Page exploder;
	//All explorer info pages
	
	private static Page goal;
	private static Page shop;
	private static Page upgrades;
	private static Page floors;
	private static Page waves;
	//All instruction info pages
	
	//Location of menu/page area and size of it
	public static void init(Vector2f location, Vector2f size) {
		
		allPages = new HashMap<String, Linkable>();
		traps = new ArrayList<Linkable>();
		explorers = new ArrayList<Linkable>();
		categories = new ArrayList<Linkable>();
		instructions = new ArrayList<Linkable>();
		
		//Menus with info pages displaying on them
		initializeTraps(location, size);
		initializeExplorers(location, size);
		initializeInstructions(location, size);
		
		initializeCredits(location, size);
		
		//A wrapper menu, leading to the other menus
		initializeCategories(location, size);
		
		
		
	}
	
	public static Linkable getLinkable(String key) {
		return allPages.get(key);
	}
	
	private static void initializeCategories(Vector2f location, Vector2f size) {
		categories.add(credits);
		categories.add(instructMenu);
		categories.add(trapMenu);
		categories.add(explorerMenu);
		categoriesMenu = createMenu(location, size, "Infopedia", categories);
	}
	
	private static void initializeExplorers(Vector2f location, Vector2f size) {
		basicExplorer = new Page(location, size, "Explorer", "Having had an unfulfilling life so far, this person is on a quest for greatness."
				+ " With no exceptional abilities of any kind, they are just there to steal the glory. As time passes though, they develop" 
				+ " useful skills.", GuiLibrary.explorerStanding, true);
		athlete = new Page(location, size, "Athlete", "After years of hard work, this person has learned how to get moving."
				+ " They are the drill sergeants of the squad. The speed of everyone is increased when they join the party.", 
				GuiLibrary.athleteStanding, true);
		miner = new Page(location, size, "Miner", "This person excels at breaking down dirt. That's it.", 
				GuiLibrary.minerStanding, true);
		treasureFinder = new Page(location, size, "Treasure Finder", "Born with a passion for money, the treasure finder is a person dedicated to greed"
				+ " Whether the treasure is behind a wall of spikes, or at the bottom of a cliff, they will pursue it no matter what.", 
				GuiLibrary.treasureFinderStanding, true);
		exploder = new Page(location, size, "Demolition Expert", "If you thought deadly traps weren't enough, this guy enjoys firing explosives" 
				+ " at close proximity to his team. Explosives deal damage to adjacent tiles dealing heavy damage, destroying anything too flimsy like treasure",
				GuiLibrary.tntManStanding, true);
		
		explorers.add(basicExplorer);
		explorers.add(athlete);
		explorers.add(miner);
		explorers.add(treasureFinder);
		explorers.add(exploder);
		
		explorerMenu = createMenu(location, size, "Explorers", explorers);
	}
	
	private static void initializeTraps(Vector2f location, Vector2f size) {
		tikiTrap = new Page(location, size, "Tiki Trap", "This trap releases intense bursts of heat on unwary explorers."
				+ " It sets fire to every tile around it in its basic form. Strong area of effect unit.", 
				GuiLibrary.tikiTrap, false);
		arrowTrap = new Page(location, size, "Arrow Trap", "Death, destruction, and devastation is the motto of this trap's creator."
				+ " This trap fires at long ranges dealing moderate amounts of damage. It cannot fire through other things though.",
				GuiLibrary.arrowTrap1, false);
		treasureTrap = new Page(location, size, "Treasure Chest", "This sealed vault of money will generate more over time. If an explorer" 
				+ " reaches it though, they will have more funding for their expiditions. Treasure finders are attracted to this trap.",
				GuiLibrary.treasureClosed, false);
		cursedIdolTrap = new Page(location, size, "Cursed Idol", "As greedy explorers pick up this trap, a curse fills their minds. This trap"
				+ " causes explorers to bend to your will in exchange for funding their expidition a small amount.",
				GuiLibrary.idolOnBlank, false);
		
		traps.add(tikiTrap);
		traps.add(arrowTrap);
		traps.add(treasureTrap);
		traps.add(cursedIdolTrap);
		
		trapMenu = createMenu(location, size, "Traps", traps);
	}
	
	private static void initializeInstructions(Vector2f location, Vector2f size) {
		goal = new Page(location, size, "Goals", "This is a tower defense game in which explorers try to infiltrate your temple. They enter from the top and work their way through to find treasure and glory. Defend yourself!");
		shop = new Page(location, size, "Shop", "Clicking on an area will select it and open the shop. Using your money in the bottom right, purchase traps to survive each night.");
		upgrades = new Page(location, size, "Upgrades", "By clicking on a trap, the UPGRADE Option will appear in the shop. This option will offer three random upgrades to choose from for that unit. Use this to fortify and enhance your defense.");
		floors = new Page(location, size, "Floors", "Purchasing floors is a great tactic for slowing down explorers. Clicking the plus sign in the top of the screen will buy a floor. Clicking the floor number will navigate your view. The arrows will allow you to scroll through your floor collection.");
		waves = new Page(location, size, "Waves", "Upon clicking the Start Wave button, the explorers will begin to enter your temple. Pay attention to how your traps interact with the intruders to better prepare for the next night. Remember that explorers grow stronger over time.");
	
		instructions.add(goal);
		instructions.add(shop);
		instructions.add(upgrades);
		instructions.add(floors);
		instructions.add(waves);
		
		instructMenu = createMenu(location, size, "Instructions", instructions);
	}
	
	private static void initializeCredits(Vector2f location, Vector2f size) {
		credits = new Page(location, size, "Credits", "Programmed by AJ Parker and Jackson Mills. Thanks to Glastellar for explorer art design.");
	}
	
	private static Menu createMenu(Vector2f location, Vector2f size, String title, ArrayList<Linkable> menuOptions) {
		String[] labels = new String[menuOptions.size()];
		for(int i = 0; i<menuOptions.size(); i++) {
			labels[i] = menuOptions.get(i).getTitle();
			allPages.put(labels[i], menuOptions.get(i));
		}
		Menu newMenu = new Menu(location, size, labels, title);
		allPages.put(title, newMenu);
		return newMenu;
	}
	
	

}

package entities;

import java.util.ArrayList;

public class Group {

	//This is the group of explorers traveling together on a tile
	private ArrayList<Explorer> group;
	
	//This is the room on a tile for members to exist (generally 2-4 explorers or 1 big thing like a drill)
	private final int MAX_SIZE;
	
	public Group() {
		group = new ArrayList<Explorer>();
		MAX_SIZE = 4;
	}
	
	public int getMaxSize() {
		return MAX_SIZE;
	}
	
	public ArrayList<Explorer> getGroup() {
		return group;
	}
	
	public void add(Explorer e) {
		group.add(e);
	}
	
}

package grid;

import org.lwjgl.util.vector.Vector2f;
import java.util.ArrayList;

public class PitfallTrap extends Tile{

	public PitfallTrap(Vector2f loc) {
		super(loc);
		this.passable = true;
		this.hp = 0;
		init();
	}
	
	public void update(){
		if(unitsContained.size() >= 3)
			trigger();
	}
	
	public void trigger(){
		
		
	}
	
}

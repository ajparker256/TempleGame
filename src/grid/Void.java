package grid;

import org.lwjgl.util.vector.Vector2f;


//This is the most basic tile, it's empty and completely passable. This tile replaces other tiles when
//they get removed

public class Void extends Tile{
	
	
	public Void(Vector2f loc, Grid gr) {
		super(loc, gr);
		this.passable = true;
		this.hp = 0;
		init();
	}

}

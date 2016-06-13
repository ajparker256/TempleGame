package grid;

import org.lwjgl.util.vector.Vector2f;

//this is dirt
public class Dirt extends Tile{

	public Dirt(Vector2f loc) {
		super(loc);
		this.passable = false;
		this.hp = 100;
		init();
	}
	

}

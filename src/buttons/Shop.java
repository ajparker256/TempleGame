package buttons;

import org.lwjgl.util.vector.Vector2f;

import grid.Tile;

public class Shop {
	
	//This is the bottom left corner of the shop
	Vector2f location;
	
	//This is the size of the shop from top left corner to bottom right
	Vector2f size;
	
	//Button array to determine which spot is being interacted with.
	Button[][] buttons;
	
	//Tile array containing the available traps for sale
	Tile[][] traps;
	
	public Shop(int numberOfRows, int numberOfColumns, Vector2f location, Vector2f size, Tile[][] traps) {
		this.location = location;
		this.size = size;
		buttons = new Button[numberOfRows][numberOfColumns];
		for(int i = 0; i<numberOfRows; i++) {
			for(int j = 0; j<numberOfColumns; j++) {
				//Y position then X position
				buttons[i][j] = new Button(new Vector2f(location.x+i*location.x/size.x, location.y+j*location.y/size.y), 
						new Vector2f(location.x*(i+1)*location.x/size.x, location.y*(j+1)*location.y/size.y));
			}
		}
	}
	
	public Tile purchase(int row, int column) {
		Ti
	}

}

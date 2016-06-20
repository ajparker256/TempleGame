package entities;

import java.util.ArrayList;

public class Squad {
ArrayList<Explorer> units;
	
	public Squad(){
		units = new ArrayList<Explorer>();
	}
	public void addUnit(Explorer unit){
		units.add(unit);
	}
	public ArrayList<Explorer> getUnits(){
		return units;
	}
}

package zUITests;

import java.util.ArrayList;

import grid.FloorCollection;
import grid.Grid;

public class FloorCollectionTest {
	
	public static void testFloorCollectionMethods() {
		System.out.println("getFloor test and getFloorToBeRendered : " + testGetFloor());
		System.out.println("addFloor test : " + testAddFloor());
		System.out.println("switchEditState test : " + testSwitchingStates());
	}
	
	public static boolean testGetFloor() {
		FloorCollection floors = new FloorCollection();

		if(floors.getFloor(0) == null) {
			System.out.println("There was no initial floor");
			return false;
		}
		
		if(floors.getFloorToBeRendered().getFloor() != 0) {
			System.out.println("The first floor was not initialized to zero");
			return false;
		}
	
		//Negative case
		if(floors.getFloor(1) != null) {
			System.out.println("The first position was populated before add was called");
			return false;
		}

		return true;
	}
	
	public static boolean testAddFloor() {
		FloorCollection floors = new FloorCollection();
		ArrayList<Grid> toBeTested = new ArrayList<Grid>();
		
		floors.addFloor();
		floors.addFloor();
		
		toBeTested.add(floors.getFloor(1));
		toBeTested.add(floors.getFloor(2));
		toBeTested.add(floors.getFloor(0));
		
		for(Grid testee : toBeTested) {
			if(testee == null) {
				System.out.println("A floor within the added floors was null");
				return false;
			}
		}
		if(floors.getFloor(3) == null) {
			return true;
		} else {
			System.out.println("A floor outside of the added floors was populated");
			return false;
		}
	}
	
	public static boolean testSwitchingStates() {
		int didPass = 0;
		FloorCollection floors = new FloorCollection();
		
		Grid readOnlyFloor = floors.getFloorToBeRendered();
		if(readOnlyFloor.equals(floors.getFloorToBeRendered())) {
			didPass++;
		} else {
			System.out.println("The floorToBeRendered was not the same as before without switching states");
		}
		
		floors.switchEditState();
		if(!readOnlyFloor.equals(floors.getFloorToBeRendered())) {
			didPass++;
		} else {
			System.out.println("The floorToBeRendered was the same after switching states");
		}
		return didPass == 2;
	}
	
	

}

package grid;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;

import entities.Projectile;
import explorerTypes.*;
import gui.GuiTexture;
import pathing.Group;
import pathing.Squad;

public class SandTemple extends Temple{
	
	public SandTemple() {
		Group group1 = new Group(0, allFloors.getFloorToBeRendered().getLoc());
		group1.add(new Exploder (group1));
		group1.add(new Miner (group1));
		group1.add(new Athlete (group1));
		group1.add(new Miner (group1));
		
		Group group2 = new Group(0, allFloors.getFloorToBeRendered().getLoc());
		group2.add(new Explorer (group2));
		group2.add(new Miner (group2));
		group2.add(new Explorer (group2));
		group2.add(new Miner (group2));
		
		Group group3 = new Group(0, allFloors.getFloorToBeRendered().getLoc());
		group3.add(new Explorer (group3));
		group3.add(new Miner (group3));
		group3.add(new Explorer (group3));
		group3.add(new Miner (group3));
		
		Group group4 = new Group(0, allFloors.getFloorToBeRendered().getLoc());
		group4.add(new Explorer (group4));
		group4.add(new Miner (group4));
		group4.add(new Explorer (group4));
		group4.add(new Miner (group4));
		
		ArrayList<Group> squad2List = new ArrayList<Group>();
		squad2List.add(group1);
		squad2List.add(group2);
		squad2List.add(group3);
		squad2List.add(group4);
		
		Squad squad2= new Squad(squad2List, 0);
		squads.add(squad2);
		assignSquadStartPosition(allFloors.getFloor(0).getLoc());
	}

	@Override
	protected void assignSquadStartPosition(Vector2f locationOnScreen) {
		for(Squad sq : squads) {
			for(Group gr : sq.getGroups()) {
				gr.setLoc(locationOnScreen);
			}
		}
		
	}

	@Override
	protected void tickSquads(long milli) {
		for(Squad currentSquad : squads) {
			currentSquad.tick((int)milli, allFloors.getFloor(currentSquad.getFurthestFloor()));
		}
	}

	@Override
	protected void tickProjectiles(long milli) {
		for(Projectile currentProjectile : projectiles) {
			currentProjectile.tick(milli);
			if(currentProjectile.isKill())
				projectiles.remove(currentProjectile);
		}
	}

	@Override
	protected void tickFloors(long milli) {
		allFloors.tick(milli);
	}

	@Override
	protected void renderSquads(ArrayList<GuiTexture> dynamicGuis) {
		for(Squad squad : squads) {
			dynamicGuis.addAll(squad.render(allFloors.getFloorToBeRendered().getFloor()));
		}
	}

	@Override
	protected void renderProjectiles(ArrayList<GuiTexture> dynamicGuis) {
		for(Projectile projectile : projectiles){
			if(projectile.canRender(allFloors.getFloorToBeRendered().getFloor()))
				dynamicGuis.add(projectile.render());
		}
		
	}

}

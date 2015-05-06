package com.tint.hospital.ai;

import com.tint.hospital.Human;
import com.tint.hospital.Transform;
import com.tint.hospital.ai.BuildingPathfinder.Path;
import com.tint.hospital.rooms.RoomType;

public class WalkHomeState extends AiState {

	public WalkHomeState(FiniteStateMachine fsm) {
		super(fsm);
	}

	@Override
	public void enter() {
		if(fsm.getOwner().getCurrentRoom().type != RoomType.RECEPTION) {
			Path path = WalkingState.pathfinder.findClosest(fsm.getOwner().getCurrentRoom(), RoomType.RECEPTION);
			fsm.pushState(new WalkingState(path, fsm));
			return;
		}
	}

	@Override
	public void update() {
		Transform transform = fsm.getOwner().transform;
		transform.addX(-Human.SPEED);
		if(transform.getX() < -1000) {
			//TODO Patient call home (remove)
		}
		
	}
}

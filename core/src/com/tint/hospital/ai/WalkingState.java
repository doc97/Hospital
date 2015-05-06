package com.tint.hospital.ai;

import com.badlogic.gdx.ai.msg.Telegram;
import com.tint.hospital.Human;
import com.tint.hospital.ai.BuildingPathfinder.Path;
import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.rooms.Room;
import com.tint.hospital.utils.Assets;

public class WalkingState extends AiState {

	public static BuildingPathfinder pathfinder = new BuildingPathfinder();
	
	private int stepIndex;
	private Path path;
	private Room destination;
	
	public WalkingState(Room destination, FiniteStateMachine fsm) {
		super(fsm);
		this.destination = destination;
	}
	
	public WalkingState(Path path, FiniteStateMachine fsm) {
		super(fsm);
		this.path = path;
		this.destination = path.get(path.size() - 1);
	}
	
	@Override
	public void update() {
		
		Room nextRoom = path.get(stepIndex);
		float tx = nextRoom.x * RenderSystem.TILE_SIZE + 64;
		float ty = nextRoom.y * RenderSystem.TILE_SIZE + 10;
		
		float dx = tx - fsm.getOwner().transform.getX();
		float dy = ty - fsm.getOwner().transform.getY();
		
		float length = 1;
		if(dx != 0 || dy != 0)
			length = (float) (1 / Math.sqrt(dx * dx + dy * dy));
		
		if(length > 0.25f) {
			stepIndex++;
			if(stepIndex >= path.size())
				fsm.popState();
		}
			
		dx *= length;
		dy *= length;
		
		fsm.getOwner().transform.addX(dx * Human.SPEED); 
		fsm.getOwner().transform.addY(dy * Human.SPEED);
	}

	@Override
	public void enter() {
		
		if(destination == null)
			fsm.popState();
		
		if(path != null) {
			
					
			path = pathfinder.getPath(fsm.getOwner().getCurrentRoom(), destination);
		}

		if(path == null)
			fsm.pushState(new StuckState(fsm));
		else
			fsm.getOwner().animationObject.playAnimation(Assets.getAnimation("male_walkcycle"));
		
	}

	@Override
	public void exit() {
		fsm.getOwner().animationObject.playAnimation(null);
	}
	
	@Override
	public void handleMessage(Telegram msg) {
		//Forward to underlying state
		AiState state = fsm.getState(fsm.getStateAmount() - 2);
		if(state != null)
			state.handleMessage(msg);
	}

	private class StuckState extends AiState {

		private int ticks = 0;
		
		public StuckState(FiniteStateMachine fsm) {
			super(fsm);
		}
		
		@Override
		public void update() {
			ticks++;
			
			if(ticks > 60)
				fsm.popState();
		}

		@Override
		public void handleMessage(Telegram msg) {
			//Forward to underlying state
			AiState state = fsm.getState(fsm.getStateAmount() - 3);
			if(state != null)
				state.handleMessage(msg);
		}
	}

}

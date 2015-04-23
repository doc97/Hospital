package com.tint.hospital.ai;

public abstract class AiState {

	protected FiniteStateMachine fsm;
	
	public AiState(FiniteStateMachine fsm) {
		this.fsm = fsm;
	}

	public void update() {}
	public void enter() {}
	public void exit() {}

}

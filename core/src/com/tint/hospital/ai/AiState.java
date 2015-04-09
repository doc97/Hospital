package com.tint.hospital.ai;

public interface AiState {

	public void update();
	public void enter();
	public void exit();
	public void setFSM(FiniteStateMachine fsm);

}

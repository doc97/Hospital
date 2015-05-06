package com.tint.hospital.ai;

import com.badlogic.gdx.ai.msg.Telegram;

public abstract class AiState {

	protected FiniteStateMachine fsm;
	
	public AiState(FiniteStateMachine fsm) {
		this.fsm = fsm;
	}

	public void update() {}
	public void enter() {}
	public void exit() {}

	public void handleMessage(Telegram msg) {}

}

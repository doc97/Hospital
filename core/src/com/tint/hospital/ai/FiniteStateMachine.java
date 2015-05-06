package com.tint.hospital.ai;

import java.util.Stack;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.tint.hospital.Human;

public class FiniteStateMachine implements Telegraph {
	private Stack<AiState> stateStack;
	private Human owner;
	
	public FiniteStateMachine(Human owner) {
		this.owner = owner;
		stateStack = new Stack<AiState>();
	}
	
	public void update() {
		AiState currentState = getCurrentState();
		if(currentState != null)
			currentState.update();
	}
	
	public void pushState(AiState state) {
		stateStack.push(state);
		state.enter();
	}
	
	public void pushNoEnter(AiState state) {
		stateStack.push(state);
	}
	
	public void popState() {
		AiState poppedState = stateStack.pop();
		poppedState.exit();
		
		AiState currentState = getCurrentState();
		if(currentState != null)
			currentState.enter();
	}
	
	public AiState getCurrentState() {
		return stateStack.size() > 0 ? stateStack.peek() : null;
	}
	
	public AiState getState(int index) {
		return index < stateStack.size() ? stateStack.get(index) : null;
	}
	
	public int getStateAmount() {
		return stateStack.size();
	}
	
	public Human getOwner() {
		return owner;
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		AiState currentState = getCurrentState();
		if(currentState != null) {
			currentState.handleMessage(msg);
			return true;
		}
		
		return false;
	}
}

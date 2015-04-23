package com.tint.hospital.ai;

import java.util.Stack;

import com.tint.hospital.Human;

public class FiniteStateMachine {
	private Stack<AiState> stateStack;
	private Human owner;
	
	public FiniteStateMachine(Human owner, AiState startingState) {
		this.owner = owner;
		stateStack = new Stack<AiState>();
		if(startingState != null)
			pushState(startingState);
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
	
	public Human getOwner() {
		return owner;
	}
}

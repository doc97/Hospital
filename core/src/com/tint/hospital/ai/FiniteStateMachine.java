package com.tint.hospital.ai;

import java.util.Stack;

public class FiniteStateMachine {
	private Stack<AiState> stateStack;
	
	public FiniteStateMachine(AiState startingState) {
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
		state.setFSM(this);
	}
	
	public void popState() {
		stateStack.pop();
	}
	
	public AiState getCurrentState() {
		return stateStack.size() > 0 ? stateStack.peek() : null;
	}
}

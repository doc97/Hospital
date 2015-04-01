package com.tint.hospital;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.tint.hospital.states.StateSystem.States;

public class Launcher extends Game {

	@Override
	public void create() {
		Root.INSTANCE.createSystems(this);
		Root.INSTANCE.stateSystem.enterState(States.GAMESTATE);
	}
	
	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}
}

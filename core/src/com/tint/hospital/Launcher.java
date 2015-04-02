package com.tint.hospital;

import com.badlogic.gdx.Game;
import com.tint.hospital.states.StateSystem.States;

public class Launcher extends Game {

	@Override
	public void create() {
		Root.INSTANCE.createSystems(this);
		Root.INSTANCE.stateSystem.enterState(States.GAMESTATE);
	}
}

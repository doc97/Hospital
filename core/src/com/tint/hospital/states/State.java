package com.tint.hospital.states;

import com.badlogic.gdx.Screen;

/**
 * 
 * @author Daniel Riissanen
 *
 */
public abstract class State implements Screen {

	protected StateSystem stateSystem;
	
	public State(StateSystem stateSystem) {
		this.stateSystem = stateSystem;
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}
}

package com.tint.hospital;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Launcher extends ApplicationAdapter {

	@Override
	public void create() {
		super.create();
		Root.INSTANCE.createSystems();
	}

	@Override
	public void render() {
		super.render();
		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Root.INSTANCE.renderSystem.renderObjects(Gdx.graphics.getDeltaTime());
	}

}

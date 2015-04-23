package com.tint.hospital.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.tint.hospital.Root;
import com.tint.hospital.ui.MainMenuUi;

public class MainMenuState extends ScreenAdapter{
	
	private MainMenuUi ui = new MainMenuUi();
	
	@Override
	public void render(float delta) {
		Root.INSTANCE.renderSystem.renderObjects(Gdx.graphics.getDeltaTime());
		ui.draw();
	}

	@Override
	public void show() {
		Gdx.gl20.glClearColor(0.42f, 0.71f, 1f, 1f);
	}
	
}

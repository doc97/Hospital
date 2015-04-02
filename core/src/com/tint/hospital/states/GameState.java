package com.tint.hospital.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.tint.hospital.Camera;
import com.tint.hospital.Root;
import com.tint.hospital.input.GameInput;
import com.tint.hospital.input.GeneralInput.GameKeys;
import com.tint.hospital.rooms.ExaminationRoom;


public class GameState extends ScreenAdapter {
	
	public final void loadGame() {
		// TODO dynamic game data loading required
		Root.INSTANCE.building.addRoom(new ExaminationRoom(0, 0, 2, 1));
		
		// TODO temporary loading code
		Root.INSTANCE.input.initialize();
		Root.INSTANCE.inputProcessor.addProcessor(new GameInput());
		Gdx.input.setInputProcessor(Root.INSTANCE.inputProcessor);
	}
	
	@Override
	public void render(float delta) {
		// Moving camera with keyboard
		if(Root.INSTANCE.input.isPressed(GameKeys.MOVE_UP)) {
			Camera.addPosition(0, 8);
		}
		if(Root.INSTANCE.input.isPressed(GameKeys.MOVE_LEFT)) {
			Camera.addPosition(-8, 0);
		}
		if(Root.INSTANCE.input.isPressed(GameKeys.MOVE_DOWN)) {
			Camera.addPosition(0, -8);
		}
		if(Root.INSTANCE.input.isPressed(GameKeys.MOVE_RIGHT)) {
			Camera.addPosition(8, 0);
		}
		
		Camera.update();
		
		Root.INSTANCE.renderSystem.renderObjects(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void show() {
		Gdx.gl20.glClearColor(0.42f, 0.71f, 1f, 1f);
		loadGame();
	}
}

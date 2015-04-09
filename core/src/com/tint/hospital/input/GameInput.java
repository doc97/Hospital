package com.tint.hospital.input;

import java.util.EnumMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import com.tint.hospital.Camera;
import com.tint.hospital.ConstructionMode;

public class GameInput extends CustomInputAdapter {

	public enum GameKeys {
		MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN, CONSTRUCTION_MODE;
	}
	private Map<GameKeys, Integer> keys = new EnumMap<GameKeys, Integer>(GameKeys.class);
	private int touchBtn;
	private float touchX, touchY;
	private float camX, camY;
	private ConstructionMode constructionMode;
	
	public GameInput(ConstructionMode constructionMode) {
		this.constructionMode = constructionMode;
		
		// TODO Hardcoded values
		keys.put(GameKeys.MOVE_LEFT, Keys.A);
		keys.put(GameKeys.MOVE_RIGHT, Keys.D);
		keys.put(GameKeys.MOVE_UP, Keys.W);
		keys.put(GameKeys.MOVE_DOWN, Keys.S);
		keys.put(GameKeys.CONSTRUCTION_MODE, Keys.C);
	}
	
	@Override
	public void update() {
		// Moving camera with keyboard
		if(Gdx.input.isKeyPressed(keys.get(GameKeys.MOVE_UP))) {
			Camera.addPosition(0, 8);
		}
		if(Gdx.input.isKeyPressed(keys.get(GameKeys.MOVE_LEFT))) {
			Camera.addPosition(-8, 0);
		}
		if(Gdx.input.isKeyPressed(keys.get(GameKeys.MOVE_DOWN))) {
			Camera.addPosition(0, -8);
		}
		if(Gdx.input.isKeyPressed(keys.get(GameKeys.MOVE_RIGHT))) {
			Camera.addPosition(8, 0);
		}
		constructionMode.calculateCurrentObjectPosition(Gdx.input.getX(), Gdx.input.getY());
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keys.get(GameKeys.CONSTRUCTION_MODE) == keycode) {
			if(constructionMode.isActive())
				constructionMode.exit();
			else
				constructionMode.enter();
		}
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 pos = Camera.getCamera().unproject(new Vector3(screenX, screenY, 0));
		touchX = pos.x;
		touchY = pos.y;
		touchBtn = button;
		camX = Camera.getCamera().position.x;
		camY = Camera.getCamera().position.y;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		camX = Camera.getCamera().position.x;
		camY = Camera.getCamera().position.y;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(touchBtn == Buttons.MIDDLE) {
			Vector3 pos = Camera.getCamera().unproject(new Vector3(screenX, screenY, 0));
			Camera.setPosition(camX + (touchX - pos.x), camY - (touchY - pos.y));
			return true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return true;
	}
}

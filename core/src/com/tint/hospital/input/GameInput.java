package com.tint.hospital.input;

import java.util.EnumMap;
import java.util.Map;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.tint.hospital.Camera;
import com.tint.hospital.ConstructionMode;

public class GameInput extends InputAdapter {

	public enum GameKeys {
		CONSTRUCTION_MODE;
	}
	private Map<GameKeys, Integer> keys = new EnumMap<GameKeys, Integer>(GameKeys.class);
	private int touchBtn;
	private float touchX, touchY;
	private ConstructionMode constructionMode;
	
	public GameInput(ConstructionMode constructionMode) {
		this.constructionMode = constructionMode;
		
		// TODO Hardcoded values
		keys.put(GameKeys.CONSTRUCTION_MODE, Keys.C);
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
		return true;
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(touchBtn == Buttons.MIDDLE) {
			Vector3 pos = Camera.getCamera().unproject(new Vector3(screenX, screenY, 0));
			Camera.addPosition(touchX - pos.x, touchY - pos.y);
			return true;
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		float zoom = Camera.getCamera().zoom + amount * 0.1f;
		
		if(zoom <= 0.5f) {
			Camera.setZoom(0.5f);
		} else if(zoom >= 1.5f) {
			Camera.setZoom(1.5f);
		} else {
			Camera.setZoom(zoom);
		}
		
		constructionMode.background.setSize((int) (Camera.getCamera().zoom * Camera.getCamera().viewportWidth), (int) (Camera.getCamera().zoom * Camera.getCamera().viewportHeight));
		Camera.addPosition(0, (int) (-zoom * Camera.getCamera().viewportHeight / 2));
		
		return true;
	}
}

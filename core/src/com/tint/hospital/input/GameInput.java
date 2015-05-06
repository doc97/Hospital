package com.tint.hospital.input;

import java.util.EnumMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.tint.hospital.Camera;
import com.tint.hospital.ConstructionMode;
import com.tint.hospital.Human;
import com.tint.hospital.Root;

public class GameInput extends InputAdapter {

	public enum GameKeys {
		CONSTRUCTION_MODE, CREATE_PATIENT, HIRE_DOCTOR;
	}
	private Map<GameKeys, Integer> keys = new EnumMap<GameKeys, Integer>(GameKeys.class);
	private int touchBtn;
	private float touchX, touchY;
	private ConstructionMode constructionMode;
	
	public GameInput(ConstructionMode constructionMode) {
		this.constructionMode = constructionMode;
		
		// TODO Hardcoded values
		keys.put(GameKeys.CONSTRUCTION_MODE, Keys.C);
		keys.put(GameKeys.CREATE_PATIENT, Keys.A);
		keys.put(GameKeys.HIRE_DOCTOR, Keys.S);
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keys.get(GameKeys.CONSTRUCTION_MODE) == keycode) {
			if(constructionMode.isActive())
				constructionMode.exit();
			else
				constructionMode.enter();
		} else if(keys.get(GameKeys.CREATE_PATIENT) == keycode) {
			Vector3 pos = Camera.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			Root.INSTANCE.humanSystem.retrievePatient(new Human((int) pos.x, (int) pos.y));
		} else if(keys.get(GameKeys.HIRE_DOCTOR) == keycode) {
			Vector3 pos = Camera.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			Root.INSTANCE.humanSystem.hireDoctor(new Human((int) pos.x, (int) pos.y));
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
		if(touchBtn == Buttons.LEFT) {
			Vector3 pos = Camera.getCamera().unproject(new Vector3(screenX, screenY, 0));
			Camera.addPosition(touchX - pos.x, touchY - pos.y);
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

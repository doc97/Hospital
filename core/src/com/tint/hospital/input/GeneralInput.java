package com.tint.hospital.input;

import java.util.EnumMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.tint.hospital.Camera;


public class GeneralInput {

	public enum GameKeys {
		MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN, CONSTRUCTION_SLOT_1, CONSTRUCTION_SLOT_2, CONSTRUCTION_SLOT_3, CONSTRUCTION_SLOT_4;
	}
	
	public Map<GameKeys, Integer> gameKeys = new EnumMap<GameKeys, Integer>(GameKeys.class);
	public boolean[] keys = new boolean[GameKeys.values().length];
	
	public GeneralInput() {}
	
	public void initialize() {
		gameKeys.put(GameKeys.MOVE_UP, Keys.W);
		gameKeys.put(GameKeys.MOVE_LEFT, Keys.A);
		gameKeys.put(GameKeys.MOVE_DOWN, Keys.S);
		gameKeys.put(GameKeys.MOVE_RIGHT, Keys.D);
		gameKeys.put(GameKeys.CONSTRUCTION_SLOT_1, Keys.NUM_1);
		gameKeys.put(GameKeys.CONSTRUCTION_SLOT_2, Keys.NUM_2);
		gameKeys.put(GameKeys.CONSTRUCTION_SLOT_3, Keys.NUM_3);
		gameKeys.put(GameKeys.CONSTRUCTION_SLOT_4, Keys.NUM_4);
	}
	
	public void assignKey(GameKeys key, int keycode) {
		// Only if there's no key with same keycode already
		if(getIndexByKeycode(keycode) == -1)
			gameKeys.replace(key, keycode);
	}
	
	public boolean pressKey(int keycode) {
		int index = getIndexByKeycode(keycode);
		if(index > -1) {
			keys[index] = true;
			return true;
		}
		return false;
	}
	
	public boolean releaseKey(int keycode) {
		int index = getIndexByKeycode(keycode);
		if(index > -1) {
			keys[index] = false;
			return true;
		}
		return false;
	}
	
	public boolean isPressed(GameKeys key) {
		return keys[getIndexByKeycode(gameKeys.get(key))];
	}
	
	private int getIndexByKeycode(int keycode) {
		GameKeys[] keys = GameKeys.values();
		for(int i = 0; i < keys.length; i++)
			if(gameKeys.get(keys[i]) == keycode)
				return i;
				
		return -1;
	}
	
	public int getKeyCode(GameKeys key) {
		return gameKeys.get(key);
	}
	
	public static float getMouseX(float screenX) {
		return (float) screenX / Gdx.graphics.getWidth() * Camera.getCamera().viewportWidth - Camera.getCamera().viewportWidth / 2;
	}
	
	public static float getMouseY(float screenY) {
		return (float) screenY / Gdx.graphics.getHeight() * Camera.getCamera().viewportHeight - Camera.getCamera().viewportHeight / 2;
	}
}

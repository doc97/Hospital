package com.tint.hospital.input;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.tint.hospital.Camera;
import com.tint.hospital.Root;

public class GameInput extends InputAdapter {

	private int touchBtn;
	private float touchX, touchY;
	private float camX, camY;
	
	public GameInput() {
	}
	
	@Override
	public boolean keyDown(int keycode) {
		return Root.INSTANCE.input.pressKey(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		return Root.INSTANCE.input.releaseKey(keycode);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchX = GeneralInput.getMouseX(screenX);
		touchY = GeneralInput.getMouseY(screenY);
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
			Camera.setPosition(camX + (touchX - GeneralInput.getMouseX(screenX)), camY - (touchY - GeneralInput.getMouseY(screenY)));
			return true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return true;
	}
}

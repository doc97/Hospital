package com.tint.hospital.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.tint.hospital.Camera;
import com.tint.hospital.Root;
import com.tint.hospital.input.GeneralInput.GameKeys;

public class ConstructionInput extends InputAdapter {

	public ConstructionInput() {
	}

	@Override
	public boolean keyUp(int keycode) {
		if(Root.INSTANCE.constructionSystem.isActive()) {
			if(keycode == Root.INSTANCE.input.getKeyCode(GameKeys.CONSTRUCTION_SLOT_1)) {
				Root.INSTANCE.constructionSystem.selectBuilding(0);
				return true;
			} else if(keycode == Root.INSTANCE.input.getKeyCode(GameKeys.CONSTRUCTION_SLOT_2)) {
				Root.INSTANCE.constructionSystem.selectBuilding(1);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(Root.INSTANCE.constructionSystem.isActive()) {
			if(button == Buttons.RIGHT) {
				Root.INSTANCE.constructionSystem.selectBuilding(-1);
				return true;
			} else if(button == Buttons.LEFT) {
				Root.INSTANCE.constructionSystem.build();
				Root.INSTANCE.constructionSystem.getCurrentObject().setCenterPosition(GeneralInput.getMouseX(screenX + Camera.getCamera().position.x),
						GeneralInput.getMouseY(Gdx.graphics.getHeight() - screenY + Camera.getCamera().position.y));
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(Root.INSTANCE.constructionSystem.isActive()) {
			Root.INSTANCE.constructionSystem.getCurrentObject().setCenterPosition((GeneralInput.getMouseX(screenX) + Camera.getCamera().position.x),
					(GeneralInput.getMouseY(Gdx.graphics.getHeight() - screenY) + Camera.getCamera().position.y));
			
			if(Root.INSTANCE.constructionSystem.isAvailable(Root.INSTANCE.constructionSystem.getCurrentObject()))
				Root.INSTANCE.constructionSystem.getCurrentObject().renderObject.setColor(Color.GREEN);
			else
				Root.INSTANCE.constructionSystem.getCurrentObject().renderObject.setColor(Color.RED);
		}
		return false;
	}
}

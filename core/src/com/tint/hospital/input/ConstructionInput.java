package com.tint.hospital.input;

import java.util.EnumMap;
import java.util.Map;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.tint.hospital.ConstructionMode;
import com.tint.hospital.rooms.RoomType;

public class ConstructionInput extends InputAdapter {

	public enum ConstructionKeys {
		CONSTRUCTION_SLOT1, CONSTRUCTION_SLOT2, CONSTRUCTION_SLOT3;
	}
	private Map<ConstructionKeys, Integer> keys = new EnumMap<ConstructionKeys, Integer>(ConstructionKeys.class);
	private ConstructionMode mode;
	
	public ConstructionInput(ConstructionMode mode) {
		this.mode = mode;
		keys.put(ConstructionKeys.CONSTRUCTION_SLOT1, Keys.NUM_1);
		keys.put(ConstructionKeys.CONSTRUCTION_SLOT2, Keys.NUM_2);
		keys.put(ConstructionKeys.CONSTRUCTION_SLOT3, Keys.NUM_3);
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == keys.get(ConstructionKeys.CONSTRUCTION_SLOT1)) {
			mode.selectBuilding(RoomType.EXAMINATION_ROOM);
			return true;
		} else if(keycode == keys.get(ConstructionKeys.CONSTRUCTION_SLOT2)) {
			mode.selectBuilding(RoomType.WAITING_ROOM);
			return true;
		} else if(keycode == keys.get(ConstructionKeys.CONSTRUCTION_SLOT3)) {
			mode.selectBuilding(RoomType.ENTRANCE);
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(button == Buttons.RIGHT) {
			mode.exit();
			return true;
		} else if(button == Buttons.LEFT) {
			mode.build();
			return true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(mode.constructionIsValid())
			mode.getCurrentObject().setColor(Color.GREEN);
		else
			mode.getCurrentObject().setColor(Color.RED);
		return false;
	}
}

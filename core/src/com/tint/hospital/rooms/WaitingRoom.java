package com.tint.hospital.rooms;

import com.tint.hospital.Assets;
import com.tint.hospital.TextureObject;


public class WaitingRoom extends Room {

	public WaitingRoom(int x, int y, int width, int height) {
		super(x, y, width, height);
		renderObject = new TextureObject(Assets.getTexture("waiting room.png"), x, y);
	}
}

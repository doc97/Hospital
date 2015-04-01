package com.tint.hospital.rooms;

import com.tint.hospital.utils.Assets;
import com.tint.hospital.render.TextureObject;


public class WaitingRoom extends Room {

	public WaitingRoom(int x, int y, int width, int height) {
		super(x, y, width, height);
		renderObject = new TextureObject(Assets.getTexture("waiting room"), x, y, width, height);
	}
}

package com.tint.hospital.rooms;

import com.tint.hospital.Assets;

public class WaitingRoom extends Room {

	public WaitingRoom(int x, int y, int width, int height) {
		super(x, y, width, height);
		region = Assets.getTexture("Waiting Room");
	}
}

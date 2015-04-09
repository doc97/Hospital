package com.tint.hospital.rooms;

import com.tint.hospital.utils.Assets;
import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.render.TextureObject;


public class WaitingRoom extends Room {

	public WaitingRoom() {
		super(0, 0, 0, 0);
		renderObject = new TextureObject(Assets.getTexture("waiting room"), x * RenderSystem.TILE_SIZE, y * RenderSystem.TILE_SIZE,
				width * RenderSystem.TILE_SIZE, height * RenderSystem.TILE_SIZE);
	}
	
	public WaitingRoom(int x, int y, int width, int height) {
		super(x, y, width, height);
		renderObject = new TextureObject(Assets.getTexture("waiting room"), x * RenderSystem.TILE_SIZE, y * RenderSystem.TILE_SIZE,
				width * RenderSystem.TILE_SIZE, height * RenderSystem.TILE_SIZE);
	}

	@Override
	public String toString() {
		return "Waiting Room";
	}
}

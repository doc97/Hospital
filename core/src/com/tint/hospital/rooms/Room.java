package com.tint.hospital.rooms;

import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.render.TextureObject;
import com.tint.hospital.utils.Assets;


public class Room {

	public int x, y;
	public RoomType type;
	public TextureObject renderObject;
	
	public Room(RoomType type, int x, int y) {
		this.x = x;
		this.y = y;
		changeType(type);
		renderObject.setPosition(x * RenderSystem.TILE_SIZE, y * RenderSystem.TILE_SIZE);
	}
	
	public void changeType(RoomType type) {
		this.type = type;
		if(renderObject == null) {
			renderObject = new TextureObject(Assets.getTexture(type.name),
					type.width * RenderSystem.TILE_SIZE, type.height * RenderSystem.TILE_SIZE);
		} else {
			renderObject.setTexture(Assets.getTexture(type.name));
			renderObject.setSize(type.width * RenderSystem.TILE_SIZE, type.height * RenderSystem.TILE_SIZE);
		}
	}
}

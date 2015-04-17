package com.tint.hospital.rooms;

import com.tint.hospital.render.RenderObject;
import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.render.TextureObject;
import com.tint.hospital.utils.Assets;


public class Room {

	public int x, y;
	public RoomType type;
	public RenderObject renderObject;
	
	public Room(RoomType type, int x, int y) {
		this.x = x;
		this.y = y;
		this.type = type;
		renderObject = new TextureObject(Assets.getTexture(type.name),
				type.width * RenderSystem.TILE_SIZE, type.height * RenderSystem.TILE_SIZE);
		renderObject.setPosition(x * RenderSystem.TILE_SIZE, y * RenderSystem.TILE_SIZE);
	}
}

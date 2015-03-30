package com.tint.hospital.rooms;

import com.tint.hospital.render.RenderObject;

public abstract class Room {

	public int x, y;
	public int width, height;
	public RenderObject renderObject;
	
	public Room(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}

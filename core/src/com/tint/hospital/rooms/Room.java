package com.tint.hospital.rooms;

import com.tint.hospital.RenderObject;

public abstract class Room {

	public int x, y;
	public int width, height;
	protected RenderObject renderObject;
	
	public Room(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public RenderObject getRenderObject() { return renderObject; }
}

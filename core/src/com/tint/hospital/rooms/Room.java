package com.tint.hospital.rooms;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Room {

	public int x, y;
	public int width, height;
	protected TextureRegion region;
	
	public Room(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void setTexture(TextureRegion region) { this.region = region; }
	public TextureRegion getTexture() { return region; }
}

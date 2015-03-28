package com.tint.hospital.rooms;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Room {

	protected int x, y;
	protected int width, height;
	protected TextureRegion region;
	
	public Room(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
	}
	
	public void setTexture(TextureRegion region) { this.region = region; }
	public TextureRegion getTexture() { return region; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}

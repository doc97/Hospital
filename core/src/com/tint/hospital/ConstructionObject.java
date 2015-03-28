package com.tint.hospital;

public class ConstructionObject {
	
	private int id;
	private int x, y;
	private int width, height;
	
	public ConstructionObject() {
		set(-1, 0, 0);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(int id, int width, int height) {
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	public int getID() { return id; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
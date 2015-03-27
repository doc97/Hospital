package com.tint.hospital;

public class Transform {
	private float x, y;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void addX(float x) {
		this.x += x;
	}
	
	public void addY(float y) {
		this.y += y;
	}
}

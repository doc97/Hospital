package com.tint.hospital;

import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.render.TintedRenderObject;

public class ConstructionObject {
	
	public int id;
	public int x, y;
	public int width, height;
	public TintedRenderObject renderObject;
	
	public ConstructionObject(TintedRenderObject renderObject) {
		this.renderObject = renderObject;
		this.id = -1;
		setPosition(0, 0);
		setSize(0, 0);
	}
	
	public void setPosition(float x, float y) {
		this.x = (int) x / RenderSystem.TILE_SIZE;
		this.y = (int) y / RenderSystem.TILE_SIZE;
		renderObject.setPosition(this.x, this.y);
	}
	
	public void setCenterPosition(float x, float y) {
		float xpos = x + RenderSystem.TILE_SIZE / 2.0f;
		float ypos = y + RenderSystem.TILE_SIZE / 2.0f;
		this.x = (int) (xpos / RenderSystem.TILE_SIZE - width / 2.0f);
		this.y = (int) (ypos / RenderSystem.TILE_SIZE - height / 2.0f);
		renderObject.setPosition(this.x, this.y);
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		renderObject.setSize(width, height);
	}
}

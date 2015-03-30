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
	
	public void setPosition(int x, int y) {
		this.x = x / RenderSystem.TILE_SIZE;
		this.y = y / RenderSystem.TILE_SIZE;
		renderObject.setPosition(this.x, this.y);
	}
	
	public void setCenterPosition(int x, int y) {
		setPosition(x - RenderSystem.TILE_SIZE * width / 2, y - RenderSystem.TILE_SIZE * height / 2);
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		renderObject.setSize(width, height);
	}
}
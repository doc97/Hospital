package com.tint.hospital.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * 
 * Wrapper around a render object which tints it with a color
 *
 */
public class TintedRenderObject implements RenderObject {

	public int x, y;
	public int width, height;
	private RenderObject renderObject;
	private Color color;
	
	public TintedRenderObject(RenderObject renderObject, Color color) {
		this.renderObject = renderObject;
		this.color = color;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setColor(color);
		renderObject.render(batch);
		batch.setColor(Color.WHITE);
	}

	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		renderObject.setPosition(x, y);
	}
	
	public void setCenterPosition(float x, float y) {
		this.x = (int) ((x + (RenderSystem.TILE_SIZE - width) / 2.0f) / RenderSystem.TILE_SIZE) * RenderSystem.TILE_SIZE;
		this.y = (int) ((y + (RenderSystem.TILE_SIZE - height) / 2.0f) / RenderSystem.TILE_SIZE) * RenderSystem.TILE_SIZE;
		renderObject.setPosition(this.x, this.y);
	}
	
	@Override
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		renderObject.setSize(width, height);
	}

	public void setColor(Color color) {
		this.color = color;
	}
}

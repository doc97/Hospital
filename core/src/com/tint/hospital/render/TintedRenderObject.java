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
	public RenderObject renderObject;
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
	
	public void setCenterPosition(int x, int y) {
		setPosition(x - width / 2, y - height / 2);
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

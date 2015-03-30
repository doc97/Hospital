package com.tint.hospital.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * 
 * Wrapper around a render object which tints it with a color
 *
 */
public class TintedRenderObject implements RenderObject {

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
		renderObject.setPosition(x, y);
	}
	
	@Override
	public void setSize(float width, float height) {
		renderObject.setSize(width, height);
	}

	public void setColor(Color color) {
		this.color = color;
	}

}

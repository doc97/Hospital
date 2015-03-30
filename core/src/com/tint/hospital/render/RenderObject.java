package com.tint.hospital.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface RenderObject {

	public void render(SpriteBatch batch);
	public void setPosition(int x, int y);
	public void setSize(float width, float height);
}

package com.tint.hospital.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tint.hospital.Transform;

public class TextureObject implements RenderObject {

	private TextureRegion textureRegion;
	private Transform transform;
	private int width, height;
	
	public TextureObject(TextureRegion textureRegion, float x, float y, int width, int height) {
		this.textureRegion = textureRegion;
		transform = new Transform();
		transform.setX(x);
		transform.setY(y);
		this.width = width;
		this.height = height;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(textureRegion, transform.getX(), transform.getY(), width, height);
	}
	
	@Override
	public void setPosition(int x, int y) {
		transform.setX(x);
		transform.setY(y);
	}
	
	@Override
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setTexture(TextureRegion region) {
		textureRegion = region;
	}
}

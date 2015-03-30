package com.tint.hospital.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tint.hospital.Transform;

public class TextureObject implements RenderObject {

	private TextureRegion textureRegion;
	private Transform transform;
	private float width, height;
	
	public TextureObject(TextureRegion textureRegion, float x, float y, float width, float height) {
		this.textureRegion = textureRegion;
		transform = new Transform();
		transform.setX(x * RenderSystem.TILE_SIZE);
		transform.setY(y * RenderSystem.TILE_SIZE);
		this.width = width * RenderSystem.TILE_SIZE;
		this.height = height * RenderSystem.TILE_SIZE;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(textureRegion, transform.getX(), transform.getY(), width, height);
	}
	
	@Override
	public void setPosition(int x, int y) {
		transform.setX(x * RenderSystem.TILE_SIZE);
		transform.setY(y * RenderSystem.TILE_SIZE);
	}
	
	@Override
	public void setSize(float width, float height) {
		this.width = width * RenderSystem.TILE_SIZE;
		this.height = height * RenderSystem.TILE_SIZE;
	}

	public void setTexture(TextureRegion region) {
		textureRegion = region;
	}
}

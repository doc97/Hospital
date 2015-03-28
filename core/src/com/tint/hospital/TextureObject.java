package com.tint.hospital;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureObject implements RenderObject {

	private TextureRegion textureRegion;
	private Transform transform;
	
	public TextureObject(TextureRegion textureRegion, float x, float y) {
		this.textureRegion = textureRegion;
		transform = new Transform();
		transform.setX(x);
		transform.setY(y);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(textureRegion, transform.getX(), transform.getY());
	}
}

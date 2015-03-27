package com.tint.hospital;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureObject implements RenderObject {

	private TextureRegion textureRegion;
	private Transform transform;
	
	public TextureObject(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(textureRegion, transform.getX(), transform.getY());
	}

}

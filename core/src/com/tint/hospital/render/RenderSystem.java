package com.tint.hospital.render;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tint.hospital.Camera;
import com.tint.hospital.utils.LoggingSystem;

public class RenderSystem {
	private static int LAYER_AMOUNT = 5;
	public static int TILE_SIZE = 128;
	
	private class RenderLayer {
		private List<RenderObject> renderObjects = new ArrayList<RenderObject>();
	}

	private RenderLayer[] renderLayers;
	private SpriteBatch spriteBatch;
	private float frameDelta;
	
	public void renderObjects(float delta) {
		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		frameDelta = delta;
		
		Camera.update();
		spriteBatch.setProjectionMatrix(Camera.getCamera().combined);
		spriteBatch.begin();
		for(RenderLayer rl : renderLayers) {
			for(RenderObject ro : rl.renderObjects) {
				ro.render(spriteBatch);
			}
		}
		spriteBatch.end();
	}
	
	public void addObject(RenderObject renderObject, int layerIndex) {
		if(layerIndex < 0 || layerIndex >= LAYER_AMOUNT)
			throw new IllegalArgumentException("Layer Index is out of range");
		
		renderLayers[layerIndex].renderObjects.add(renderObject);
	}
	
	public void removeObject(RenderObject renderObject, int layerIndex) {
		if(layerIndex < 0 || layerIndex >= LAYER_AMOUNT)
			throw new IllegalArgumentException("Layer Index is out of range");
			
		renderLayers[layerIndex].renderObjects.remove(renderObject);
	}

	public float getFrameDelta() {
		return frameDelta;
	}

	public void create() {
		spriteBatch = new SpriteBatch();
		
		renderLayers = new RenderLayer[LAYER_AMOUNT];
		
		for(int i = 0; i < LAYER_AMOUNT; i++) {
			renderLayers[i] = new RenderLayer();
		}
		
		LoggingSystem.log("Render System", "Initialized");
	}
}

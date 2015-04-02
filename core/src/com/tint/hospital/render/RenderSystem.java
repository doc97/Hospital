package com.tint.hospital.render;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tint.hospital.Camera;
import com.tint.hospital.utils.LoggingSystem;

public class RenderSystem {
	private static int LAYER_AMOUNT = 5;
	public static int TILE_SIZE = 128;
	
	private class RenderLayer {
		private ArrayList<RenderObject> renderObjects;
		
		private RenderLayer() {
			renderObjects = new ArrayList<RenderObject>();
		}

		private void add(RenderObject renderObject) {
			renderObjects.add(renderObject);
		}
		
		private void remove(RenderObject renderObject) {
			for(RenderObject ro : renderObjects) {
				if(ro.equals(renderObject)) {
					renderObjects.remove(ro);
					break;
				}
			}
		}
	}

	private RenderLayer[] renderLayers;
	private RenderLayer[] stillRenderLayers;
	private SpriteBatch spriteBatch;
	private float frameDelta;
	
	public void renderObjects(float delta) {
		
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		frameDelta = delta;
		
		Camera.resetPosition();
		Camera.update();
		spriteBatch.setProjectionMatrix(Camera.getCamera().combined);
		spriteBatch.begin();
		
		for(RenderLayer rl : renderLayers) {
			for(RenderObject ro : rl.renderObjects) {
				ro.render(spriteBatch);
			}
		}
		spriteBatch.flush();
		
		Camera.getCamera().position.set(0, 0, 0);
		Camera.update();
		spriteBatch.setProjectionMatrix(Camera.getCamera().combined);
		
		for(RenderLayer rl : stillRenderLayers)
			for(RenderObject ro : rl.renderObjects)
				ro.render(spriteBatch);
		
		Camera.resetPosition();
		Camera.update();
		spriteBatch.end();
	}
	
	public void addObject(RenderObject renderObject, int layerIndex, boolean still) {
		if(layerIndex < 0 || layerIndex >= LAYER_AMOUNT)
			throw new IllegalArgumentException("Layer Index is out of range");
		
		if(still)
			stillRenderLayers[layerIndex].add(renderObject);
		else
			renderLayers[layerIndex].add(renderObject);
	}
	
	public void removeObject(RenderObject renderObject, int layerIndex, boolean still) {
		if(layerIndex < 0 || layerIndex >= LAYER_AMOUNT)
			throw new IllegalArgumentException("Layer Index is out of range");
			
		if(still)
			stillRenderLayers[layerIndex].remove(renderObject);
		else
			renderLayers[layerIndex].remove(renderObject);
	}

	public float getFrameDelta() {
		return frameDelta;
	}

	public void create() {
		spriteBatch = new SpriteBatch();
		
		renderLayers = new RenderLayer[LAYER_AMOUNT];
		stillRenderLayers = new RenderLayer[LAYER_AMOUNT];
		
		for(int i = 0; i < LAYER_AMOUNT; i++) {
			renderLayers[i] = new RenderLayer();
			stillRenderLayers[i] = new RenderLayer();
		}
		
		LoggingSystem.log("Render System", "Initialized");
	}
}

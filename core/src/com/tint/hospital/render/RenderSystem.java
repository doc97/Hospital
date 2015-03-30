package com.tint.hospital.render;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tint.hospital.LoggingSystem;

public class RenderSystem {
	private static int LAYER_AMOUNT = 5;
	public static int TILE_SIZE = 64;
	
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
	private SpriteBatch spriteBatch;
	private float frameDelta;
	
	public void renderObjects(float delta) {
		frameDelta = delta;
		
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
		
		renderLayers[layerIndex].add(renderObject);
	}
	
	public void removeObject(RenderObject renderObject, int layerIndex) {
		if(layerIndex < 0 || layerIndex >= LAYER_AMOUNT)
			throw new IllegalArgumentException("Layer Index is out of range");
			
		renderLayers[layerIndex].remove(renderObject);
	}

	public float getFrameDelta() {
		return frameDelta;
	}

	public void create() {
		spriteBatch = new SpriteBatch();
		
		renderLayers = new RenderLayer[LAYER_AMOUNT];
		
		for(int i = 0; i < LAYER_AMOUNT; i++)
			renderLayers[i] = new RenderLayer();
		
		LoggingSystem.log("Render System", "Initialized");
	}
}

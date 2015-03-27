package com.tint.hospital;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderSystem {
	private static int LAYER_AMOUNT = 5;
	
	private class RenderLayer {
		private ArrayList<RenderObject> renderObjects;
		
		private RenderLayer() {
			renderObjects = new ArrayList<RenderObject>();
		}

		private void add(RenderObject renderObject) {
			renderObjects.add(renderObject);
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

	public float getFrameDelta() {
		return frameDelta;
	}

	public void create() {
		spriteBatch = new SpriteBatch();
		
		renderLayers = new RenderLayer[LAYER_AMOUNT];
		
		for(int i = 0; i < LAYER_AMOUNT; i++)
			renderLayers[i] = new RenderLayer();
	}
}

package com.tint.hospital;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.utils.LoggingSystem;

public class Camera {

	private static OrthographicCamera camera;
	
	public static void setup() {
		int renderWidth = 1920;
		int renderHeight = 1080;
		float cameraAspectRatio = (float) renderWidth / renderHeight;
		float displayAspectRatio = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
		
		if(displayAspectRatio > cameraAspectRatio) {
			camera = new OrthographicCamera(renderHeight * displayAspectRatio, renderHeight);
		} else {
			camera = new OrthographicCamera(renderWidth, renderWidth * (1.0f / displayAspectRatio));
		}
		
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update(true);
		
		LoggingSystem.log("Camera", "Initialized");
	}
	
	public static void update() {
		camera.update(true);
	}	
	
	public static void addPosition(float x, float y) {
		setPosition(camera.position.x + x, camera.position.y + y);
	}

	public static void setPosition(float x, float y) {
		boolean middle = Root.INSTANCE.building.getRoomAt((int) (x / RenderSystem.TILE_SIZE), 0) != null;;
		
		if(middle)
			camera.position.x = x;
		
		// Camera has a lower limit
		if(y >= camera.zoom * camera.viewportHeight / 2) {
			// +1 because when moving camera upwards there must be atleast one row of rooms visible
			int yPos = (int) ((camera.position.y - camera.viewportHeight / 2) / RenderSystem.TILE_SIZE) + (y > camera.position.y ? 1 : 0);
			for(int i = 0, n = (int) (camera.viewportWidth / RenderSystem.TILE_SIZE); i < n; i++) {
				if(Root.INSTANCE.building.getRoomAt(i, yPos) != null) {
					camera.position.y = y;
					return;
				}
			}
		} else {
			camera.position.y = camera.zoom * camera.viewportHeight / 2;
		}
	}
	
	public static void setZoom(float zoom) {
		camera.zoom = Math.max(zoom, 0);
	}
	
	public static OrthographicCamera getCamera() { return camera; }
}

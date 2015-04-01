package com.tint.hospital;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tint.hospital.utils.LoggingSystem;

public class Camera {

	private static OrthographicCamera camera;
	private static float camX, camY;
	private static float speed = 2f;
	
	private Camera() {}
	
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
		camX = camera.viewportWidth / 2;
		camY = camera.viewportHeight / 2;
		camera.update(true);
		
		LoggingSystem.log("Camera", "Initialized");
	}
	
	public static void update() {
		camera.update(true);
	}	
	
	public static void resetPosition() {
		camera.position.x = camX;
		camera.position.y = camY;
	}
	
	public static void addPosition(float x, float y) {
		camera.position.x += x;
		camX = camera.position.x;
		
		// Camera has a lower limit
		if(camera.position.y + y >= camera.viewportHeight / 2) {
			camera.position.y += y;
			camY = camera.position.y;
		} else {
			camera.position.y = camera.viewportHeight / 2;
			camY = camera.viewportHeight / 2;
		}
	}

	public static void setPosition(float x, float y) {
		camera.position.x = x;
		camX = x;
		
		// Camera has a lower limit
		if(y >= camera.viewportHeight / 2) {
			camera.position.y = y;
			camY = y;
		} else {
			camera.position.y = camera.viewportHeight / 2;
			camY = camera.viewportHeight / 2;
		}
	}
	
	public static void setZoom(float zoom) {
		camera.zoom = zoom;
	}
	
	public static OrthographicCamera getCamera() { return camera; }
	public static float getSpeed() { return speed; }
}

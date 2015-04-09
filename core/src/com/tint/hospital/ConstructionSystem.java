package com.tint.hospital;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.tint.hospital.input.ConstructionInput;
import com.tint.hospital.input.GeneralInput;
import com.tint.hospital.render.RenderObject;
import com.tint.hospital.render.TextureObject;
import com.tint.hospital.render.TintedRenderObject;
import com.tint.hospital.rooms.ExaminationRoom;
import com.tint.hospital.rooms.Room;
import com.tint.hospital.rooms.WaitingRoom;
import com.tint.hospital.utils.Assets;
import com.tint.hospital.utils.FileUtils;
import com.tint.hospital.utils.LoggingSystem;

public class ConstructionSystem {
	
	private ConstructionObject[] objects;
	private ConstructionObject currentObject;
	private RenderObject background;
	private boolean active;
	
	public void create() {
		LoggingSystem.log("Construction System", "Loading...");
		currentObject = new ConstructionObject(new TintedRenderObject(new TextureObject(Assets.getTexture("construction object"), 0, 0, 0, 0), Color.GREEN));
		background = new TextureObject(Assets.getTexture("construction object"), -Camera.getCamera().viewportWidth / 2, -Camera.getCamera().viewportHeight / 2,
				Camera.getCamera().viewportWidth, Camera.getCamera().viewportHeight);
		
		// Loading templates from file
		String file;
		try {
			file = FileUtils.readFromFile("data/construction_objects.txt");
			String[] obj = file.split("/n");
			objects = new ConstructionObject[obj.length];
			int comments = 0;
			for(int i = 0; i < obj.length - comments; i++) {
				// Comments
				if(i + comments < obj.length && obj[i + comments].startsWith("//")) {
					comments++;
					i--;
					continue;
				}
				
				String[] data = obj[i + comments].split(",");
				objects[i] = new ConstructionObject(new TintedRenderObject(new TextureObject(null, 0, 0, 0, 0), Color.GREEN));
				objects[i].id = Integer.valueOf(data[0]);
				objects[i].width = Integer.valueOf(data[1]);
				objects[i].height = Integer.valueOf(data[2]);
			}
		} catch (IOException e) {
			LoggingSystem.error("Construction System", "Couldn't load construction objects");
			e.printStackTrace();
			Gdx.app.exit();
		}
		
		// Creating input handle
		Root.INSTANCE.inputProcessor.addProcessor(new ConstructionInput());
		
		LoggingSystem.log("Construction System", "Done!");
	}
	
	public void build() {
		if(isActive()) {
			// Checking if intersecting with any other rooms
			if(isAvailable(currentObject)) {
				// Remove 'ghost room' and add a real room
				Root.INSTANCE.renderSystem.removeObject(currentObject.renderObject, 4);
				Root.INSTANCE.renderSystem.removeObject(background, 3);
				Root.INSTANCE.building.addRoom(getRoom(currentObject));
				
				if(getRoom(currentObject) != null)
					LoggingSystem.log("Construction System", "Constructed room with id: " + currentObject.id);
				
				// Reset object
				currentObject.id = -1;
			} else {
				LoggingSystem.log("Construction System", "Cannot construct room with id: " + currentObject.id);
			}
		}
	}
	
	public void enter() {
		active = true;
		Root.INSTANCE.renderSystem.addObject(background, 3);
	}
	
	public void exit() {
		active = false;
		Root.INSTANCE.renderSystem.removeObject(background, 3);
		Root.INSTANCE.renderSystem.removeObject(currentObject.renderObject, 4);
	}
	
	public void selectBuilding(int index) {
		// Removes earlier ghost
		Root.INSTANCE.renderSystem.removeObject(currentObject.renderObject, 4);
		
		
		if(index < objects.length && index >= 0) {
			// Sets current object attributes to match those of the object at specific index
			currentObject.id = objects[index].id;
			currentObject.setSize(objects[index].width, objects[index].height);
			
			// Create a ghost and set its position
			currentObject.setPosition(GeneralInput.getMouseX(Gdx.input.getX()) + Camera.getCamera().position.x,
					GeneralInput.getMouseY(Gdx.graphics.getHeight() - Gdx.input.getY()) + Camera.getCamera().position.y);
			
			// Create render object
			currentObject.renderObject = new TintedRenderObject(getRoom(currentObject).renderObject, Color.GREEN);

			// Set initial color on render object
			if(isAvailable(Root.INSTANCE.constructionSystem.getCurrentObject()))
				currentObject.renderObject.setColor(Color.GREEN);
			else
				currentObject.renderObject.setColor(Color.RED);
			
			// Add render objects
			Root.INSTANCE.renderSystem.addObject(currentObject.renderObject, 4);
			
		}
	}
	
	/**
	* Returns a new instance of a building identified by a unique id
	* @param id - the id for the building
	* @return the building
	*/
	public Room getRoom(ConstructionObject object) {
		switch(object.id) {
			case 0:
				return new ExaminationRoom(object.x, object.y, object.width, object.height);
			
			case 1:
				return new WaitingRoom(object.x, object.y, object.width, object.height);
			
			default:
				System.err.println("[Construction System]: No room with id: " + object.id);
				return null;
		}
	}
	
	public ConstructionObject getCurrentObject() { return currentObject; }
	
	public boolean isAvailable(ConstructionObject object) {
		// Building on ground
		if(object.y == 0) {
			if(Root.INSTANCE.building.getRoomAt(object.x - 1, object.y) == null &&
					Root.INSTANCE.building.getRoomAt(object.x + object.width, object.y) == null) {
				return false;
			}
		} 
		
		int emptyBlocks = 0;
		for(int i = object.x; i < object.x + object.width; i++) {
			for(int j = object.y; j < object.y + object.height; j++) {
				// Must not be blocked
				if(Root.INSTANCE.building.getRoomAt(i, j) != null)
					return false;
				
				// Must have full support under room
				if(object.y > 0 && Root.INSTANCE.building.getRoomAt(i, object.y-1) == null) {
					if(++emptyBlocks > object.width / 2f)
						return false;
				}
			}
		}
		return true;
	}
	
	public boolean isActive() { return active; }
}

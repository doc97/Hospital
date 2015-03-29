package com.tint.hospital;

import java.io.IOException;

import com.tint.hospital.rooms.ExaminationRoom;
import com.tint.hospital.rooms.Room;
import com.tint.hospital.rooms.WaitingRoom;

public class ConstructionSystem {
	
	private ConstructionObject[] objects;
	private ConstructionObject currentObject;
	private RenderObject renderObject;
	
	public void create() {
		LoggingSystem.log("Construction System", "loading...");
		currentObject = new ConstructionObject();
		renderObject = new TextureObject(null, 0, 0);
		
		// Loading templates from file
		String file;
		try {
			file = FileUtils.readFromFile("data/construction_objects.txt");
			String[] obj = file.split("/n");
			objects = new ConstructionObject[obj.length];
			for(int i = 0; i < obj.length; i++) {
				// Comments
				if(obj[i].startsWith("//"))
					continue;
				
				String[] data = obj[i].split(",");
				objects[i] = new ConstructionObject();
				objects[i].set(Integer.valueOf(data[0]), Integer.valueOf(data[1]), Integer.valueOf(data[2]));
			}
			LoggingSystem.log("Construction System", "Done!");
		} catch (IOException e) {
			LoggingSystem.error("Construction System", "Couldn't load construction objects");
			e.printStackTrace();
		}
		
		
	}
	
	public void build() {
		if(currentObject.getID() > -1) {
			// Checking if intersecting with any other rooms
			if(isAvailable(currentObject.getX(), currentObject.getY(), currentObject.getWidth(), currentObject.getHeight())) {
				// Remove 'ghost room' and add a real room
				Root.INSTANCE.renderSystem.removeObject(renderObject, 4);
				Root.INSTANCE.building.addRoom(getRoom(currentObject));
				LoggingSystem.log("Construction System", "Constructed room with id: " + currentObject.getID());
				
				// Reset objects
				renderObject = new TextureObject(null, 0, 0);
				currentObject = new ConstructionObject();
			}
		}
	}
	
	public void selectBuilding(int index) {
		if(index < objects.length && index >= 0) {
			// Removes earlier ghost
			Root.INSTANCE.renderSystem.removeObject(renderObject, 4);
			
			// Sets current object attributes to match those of the object at specific index
			currentObject.set(objects[index].getID(), objects[index].getWidth(), objects[index].getHeight());
			currentObject.setPosition(currentObject.getX() + 100 * index, currentObject.getY() + index * 100);
			
			// Create a ghost and add it to render system
			// TODO Change to custom render object
			renderObject = getRoom(currentObject).getRenderObject();
			Root.INSTANCE.renderSystem.addObject(renderObject, 4);
		}
	}
	
	/**
	* Returns a new instance of a building identified by a unique id
	* @param id - the id for the building
	* @return the building
	*/
	public Room getRoom(ConstructionObject object) {
		switch(object.getID()) {
			case 0:
				return new ExaminationRoom(object.getX(), object.getY(), object.getWidth(), object.getHeight());
			
			case 1:
				return new WaitingRoom(object.getX(), object.getY(), object.getWidth(), object.getHeight());
			
			default:
				System.err.println("[Construction System]: cannot build room with id: " + object.getID());
				return null;
		}
	}
	
	public boolean isAvailable(int x, int y, int width, int height) {
		for(int i = x; i < width; i++) {
			for(int j = y; j < height; j++) {
				if(Root.INSTANCE.building.getRoomAt(i, j) != null) {
					return false;
				}
			}
		}
		return true;
	}
}

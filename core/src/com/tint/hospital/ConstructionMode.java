package com.tint.hospital;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.tint.hospital.data.ConstructionData;
import com.tint.hospital.input.ConstructionInput;
import com.tint.hospital.render.RenderObject;
import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.render.TextureObject;
import com.tint.hospital.render.TintedRenderObject;
import com.tint.hospital.rooms.Room;
import com.tint.hospital.states.GameState;
import com.tint.hospital.utils.Assets;
import com.tint.hospital.utils.LoggingSystem;

public class ConstructionMode {
	
	private Room[] templates;
	public ConstructionInput input;
	private TintedRenderObject currentRenderObject;
	private RenderObject background;
	private final Vector3 translationVector = new Vector3();
	public int currentX, currentY;
	private int currentIndex;
	private boolean active;
	
	public void create() {
		currentRenderObject = new TintedRenderObject(new TextureObject(Assets.getTexture("construction object"), 0, 0, 0, 0), Color.GREEN);
		background = new TextureObject(Assets.getTexture("construction object"),
				-Camera.getCamera().viewportWidth / 2, -Camera.getCamera().viewportHeight / 2,
				(int) Camera.getCamera().viewportWidth, (int) Camera.getCamera().viewportHeight);
		
		input = new ConstructionInput(this);
		
		// Loading templates from file
		templates = loadRoomsFromJson("data/game_data.json");
	}
	
	public void update() {
		if(!active)
			Root.INSTANCE.input.removeProcessor(input);
		else
			translateModeToWorldPos(Gdx.input.getX(), Gdx.input.getY());
	}
	
	private Room[] loadRoomsFromJson(String filename) {
		List<Room> objects = new ArrayList<Room>();
		ConstructionData data = GameState.gameData.constructionData;
		
		for(int i = 0; i < data.rooms.size; i++) {
			Room room = data.rooms.get(i);
			room.renderObject.setSize(room.width, room.height);
			objects.add(room);
		}
		
		return objects.toArray(new Room[objects.size()]);
	}
	
	public void enter() {
		active = true;
		currentIndex = -1;
		Root.INSTANCE.renderSystem.addObject(background, 3);
		Root.INSTANCE.input.addProcessor(input);
	}
	
	public void exit() {
		active = false;
		Root.INSTANCE.renderSystem.removeObject(background, 3);
		Root.INSTANCE.renderSystem.removeObject(currentRenderObject, 4);
	}
	
	public void build() {
		if(active) {
			// Checking if intersecting with any other rooms
			if(constructionIsValid()) {
				try {
					Root.INSTANCE.building.addRoom(getRoom(currentIndex));
					selectBuilding(currentIndex);
				} catch (InstantiationException e) {
					e.printStackTrace();
					LoggingSystem.log("Construction", "Couldn't construct: " + templates[currentIndex].toString());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					LoggingSystem.log("Construction", "Couldn't construct: " + templates[currentIndex].toString());
				}
				
				LoggingSystem.log("Construction", "Constructed: " + templates[currentIndex].toString());
			} else {
				if(currentIndex < 0 || currentIndex >= templates.length)
					LoggingSystem.log("Construction", "Couldn't construct: No constructionobject selected");
				else
					LoggingSystem.log("Construction", "Couldn't construct: " + templates[currentIndex].toString() + " because inavailable");
			}
		}
	}
	
	public void selectBuilding(int index) {
		// Removes earlier ghost
		Root.INSTANCE.renderSystem.removeObject(currentRenderObject, 4);
		
		if(index < templates.length && index >= 0) {
			currentIndex = index;
			
			// Create render object
			currentRenderObject = new TintedRenderObject(templates[index].renderObject, Color.GREEN);
			
			// Sets current object attributes to match those of the object at specific index
			currentRenderObject.setSize(templates[currentIndex].width * RenderSystem.TILE_SIZE, templates[currentIndex].height * RenderSystem.TILE_SIZE);
			
			// Create a ghost and set its position
			translateModeToWorldPos(Gdx.input.getX(), Gdx.input.getY());
			
			// Set initial color on render object
			if(constructionIsValid())
				currentRenderObject.setColor(Color.GREEN);
			else
				currentRenderObject.setColor(Color.RED);
			
			// Add render objects
			Root.INSTANCE.renderSystem.addObject(currentRenderObject, 4);
		}
	}
	
	public void translateModeToWorldPos(int screenX, int screenY) {
		translationVector.set(screenX, screenY, 0);
		Vector3 worldPos = Camera.getCamera().unproject(translationVector);
		currentRenderObject.setPosition(
				(int) (Math.floor(worldPos.x / RenderSystem.TILE_SIZE)) * RenderSystem.TILE_SIZE,
				(int) (Math.floor(worldPos.y / RenderSystem.TILE_SIZE)) * RenderSystem.TILE_SIZE);
		background.setPosition(
				(int) (-Camera.getCamera().viewportWidth / 2 + Camera.getCamera().position.x),
				(int) (-Camera.getCamera().viewportHeight / 2 + Camera.getCamera().position.y)
				);
		currentX = (int) (currentRenderObject.x / RenderSystem.TILE_SIZE);
		currentY = (int) (currentRenderObject.y / RenderSystem.TILE_SIZE);
	}
	
	public boolean constructionIsValid() {
		if(currentIndex < 0 || currentIndex >= templates.length)
			return false;
		
		// Building on ground
		if(currentY == 0) {
			if(Root.INSTANCE.building.getRoomAt(currentX - 1, currentY) == null &&
					Root.INSTANCE.building.getRoomAt(currentX + templates[currentIndex].width, currentY) == null) {
				return false;
			}
		} 
		
		for(int i = currentX; i < currentX + templates[currentIndex].width; i++) {
			for(int j = currentY; j < currentY + templates[currentIndex].height; j++) {
				// Must not be blocked
				if(Root.INSTANCE.building.getRoomAt(i, j) != null)
					return false;

				// Must have full support under room
				if(currentY > 0 && Root.INSTANCE.building.getRoomAt(i, currentY-1) == null)
					return false;
			}
		}
		return true;
	}
	
	public Room getRoom(int currIndex) throws InstantiationException, IllegalAccessException {
		Room newRoom = templates[currIndex].getClass().newInstance();
		newRoom.x = currentX;
		newRoom.y = currentY;
		newRoom.width = templates[currentIndex].width;
		newRoom.height = templates[currentIndex].height;
		newRoom.renderObject.setPosition(currentX * RenderSystem.TILE_SIZE, currentY * RenderSystem.TILE_SIZE);
		newRoom.renderObject.setSize(currentRenderObject.width, currentRenderObject.height);
		
		return newRoom;
	}
	
	public TintedRenderObject getCurrentObject() { return currentRenderObject; }
	public boolean isActive() { return active; }
}

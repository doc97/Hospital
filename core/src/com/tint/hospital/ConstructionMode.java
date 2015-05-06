package com.tint.hospital;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.tint.hospital.input.ConstructionInput;
import com.tint.hospital.render.RenderObject;
import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.render.TextureObject;
import com.tint.hospital.render.TintedRenderObject;
import com.tint.hospital.rooms.Room;
import com.tint.hospital.rooms.RoomType;
import com.tint.hospital.ui.ConstructionUi;
import com.tint.hospital.utils.Assets;
import com.tint.hospital.utils.LoggingSystem;

public class ConstructionMode {
	
	private RoomType currentType;
	public ConstructionInput input;
	private TintedRenderObject currentRenderObject;
	public RenderObject background;
	private final Vector3 translationVector = new Vector3();
	public int currentX, currentY;
	private boolean active;
	public ConstructionUi cui = new ConstructionUi(this);
	
	public void create() {
		currentRenderObject = new TintedRenderObject(new TextureObject(Assets.getTexture("construction object"), 0, 0), Color.GREEN);
		background = new TextureObject(Assets.getTexture("construction object"),
				(int) Camera.getCamera().viewportWidth, (int) Camera.getCamera().viewportHeight);
		
		input = new ConstructionInput(this);
	}
	
	public void update() {
		if(!active)
			Root.INSTANCE.input.removeProcessor(input);
		else {
			translateModeToWorldPos(Gdx.input.getX(), Gdx.input.getY());
			cui.draw();
		}
	}
	
	public void enter() {
		active = true;
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
				// Custom code for stairs
				Room under = Root.INSTANCE.building.getRoomAt(currentX, currentY - 1);
				if(currentType == RoomType.STAIRS_BOTTOM) {
					if(under != null) {
						if(under.type == RoomType.STAIRS_TOP || under.type == RoomType.STAIRS_BOTTOM) {
							Root.INSTANCE.economySystem.addMoney(-RoomType.STAIRS_TOP.cost);
							Root.INSTANCE.building.addRoom(getRoom(RoomType.STAIRS_TOP));
							LoggingSystem.log("Construction", "Constructed: Stair extension");
							
							if(under.type == RoomType.STAIRS_TOP)
								under.changeType(RoomType.STAIRS_MIDDLE);
							
							return;
						}
						
					}
				}
				
				Root.INSTANCE.economySystem.addMoney(-currentType.cost);
				Root.INSTANCE.building.addRoom(getRoom(currentType));
				selectBuilding(currentType);
				LoggingSystem.log("Construction", "Constructed: " + currentType.toString());
			} else {
				if(currentType == null)
					LoggingSystem.log("Construction", "No constructionobject selected");
				else if(Root.INSTANCE.economySystem.getMoney() < currentType.cost)
					LoggingSystem.log("Construction", "Insufficent funds");
				else
					LoggingSystem.log("Construction", "Cannot place " + currentType.toString() + " there");
			}
		}
	}
	
	public void selectBuilding(RoomType type) {
		// Removes earlier ghost
		Root.INSTANCE.renderSystem.removeObject(currentRenderObject, 4);
		
		if ((currentType == type && !Thread.currentThread().getStackTrace()[2].getClassName().equals(ConstructionMode.class.getName())) || type == null){
			currentType = null;
			return;
		}
		
		currentType = type;
		currentRenderObject = new TintedRenderObject(getRoom(currentType).renderObject, Color.GREEN);
		
		// Positions mode objects properly
		translateModeToWorldPos(Gdx.input.getX(), Gdx.input.getY());
		
		// Set initial color on render object
		if(constructionIsValid())
			currentRenderObject.setColor(Color.GREEN);
		else
			currentRenderObject.setColor(Color.RED);
		
		// Add render objects
		Root.INSTANCE.renderSystem.addObject(currentRenderObject, 4);
	}
	
	public void translateModeToWorldPos(int screenX, int screenY) {
		translationVector.set(screenX, screenY, 0);
		Vector3 worldPos = Camera.getCamera().unproject(translationVector);
		currentRenderObject.setPosition(
				(int) (Math.floor(worldPos.x / RenderSystem.TILE_SIZE)) * RenderSystem.TILE_SIZE,
				(int) (Math.floor(worldPos.y / RenderSystem.TILE_SIZE)) * RenderSystem.TILE_SIZE);
		background.setPosition(
				(int) (Camera.getCamera().zoom * (-Camera.getCamera().viewportWidth) / 2 + Camera.getCamera().position.x),
				(int) (Camera.getCamera().zoom * (-Camera.getCamera().viewportHeight) / 2 + Camera.getCamera().position.y)
				);
		currentX = (int) (currentRenderObject.x / RenderSystem.TILE_SIZE);
		currentY = (int) (currentRenderObject.y / RenderSystem.TILE_SIZE);
	}
	
	public boolean constructionIsValid() {
		if(currentType == null)
			return false;

		if(Root.INSTANCE.economySystem.getMoney() < currentType.cost)
			return false;
		
		// Building on ground
		if(currentY == 0) {
			if(Root.INSTANCE.building.getRoomAt(currentX - 1, currentY) == null &&
					Root.INSTANCE.building.getRoomAt(currentX + currentType.width, currentY) == null) {
				return false;
			}
		} 
		
		for(int i = currentX; i < currentX + currentType.width; i++) {
			for(int j = currentY; j < currentY + currentType.height; j++) {
				// Must not be blocked
				if(Root.INSTANCE.building.getRoomAt(i, j) != null)
					return false;

				// Must have full support under room
				if(currentY > 0 && Root.INSTANCE.building.getRoomAt(i, currentY-1) == null)
					return false;
			}
		}
		
		// Custom code
		switch(currentType) {
		case RECEPTION:
			if(currentY > 0)
				return false;
			break;
		default:
			break;
		}
		
		return true;
	}
	
	public Room getRoom(RoomType currentType) {
		return new Room(currentType, currentX, currentY);
	}
	
	public RoomType getCurrentType() { return currentType; }
	public TintedRenderObject getCurrentObject() { return currentRenderObject; }
	public boolean isActive() { return active; }
}

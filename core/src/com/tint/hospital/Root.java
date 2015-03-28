package com.tint.hospital;

import java.util.ArrayList;
import java.util.List;

import com.tint.hospital.rooms.Room;

public enum Root {
	INSTANCE;
	
	public final RenderSystem renderSystem = new RenderSystem();
	public final ConstructionSystem constructionSystem = new ConstructionSystem();
	public List<Room> rooms = new ArrayList<Room>();
	
	public final void createSystems() {
		Assets.loadGraphics();
		renderSystem.create();
		constructionSystem.create();
	}
	
	public final void addRoom(Room b) {
		rooms.add(b);
		renderSystem.addObject(new TextureObject(b.getTexture(), b.getX(), b.getY()), 2);
	}
}

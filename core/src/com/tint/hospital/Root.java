package com.tint.hospital;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.rooms.ExaminationRoom;

public enum Root {
	INSTANCE;
	
	public final RenderSystem renderSystem = new RenderSystem();
	public final ConstructionSystem constructionSystem = new ConstructionSystem();
	public final Building building = new Building();
	public final InputMultiplexer input = new InputMultiplexer();
	
	public final void createSystems() {
		Assets.loadGraphics();
		renderSystem.create();
		constructionSystem.create();
		Gdx.input.setInputProcessor(input);
	}
	
	public final void loadGame() {
		building.addRoom(new ExaminationRoom(0, 0, 4, 2));
	}
}

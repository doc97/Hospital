package com.tint.hospital.states;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.Json;
import com.tint.hospital.ConstructionMode;
import com.tint.hospital.Root;
import com.tint.hospital.data.GameData;
import com.tint.hospital.input.GameInput;
import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.rooms.Room;
import com.tint.hospital.utils.FileUtils;
import com.tint.hospital.utils.LoggingSystem;


public class GameState extends ScreenAdapter {
	
	private ConstructionMode constructionMode = new ConstructionMode();
	public static GameData gameData;
	
	public final void loadGame() {
		LoggingSystem.log("Game", "Loading game....");

		Json json = new Json();
		try {
			gameData = json.fromJson(GameData.class, FileUtils.readFromFile("data/game_data.json", false));
			constructionMode.create();
		} catch (IOException e) {
			e.printStackTrace();
			LoggingSystem.error("Game", "Error loading game data");
			Gdx.app.exit();
		}
		
		for(Room r : gameData.gameStartData.rooms) {
			r.renderObject.setPosition(r.x * RenderSystem.TILE_SIZE, r.y * RenderSystem.TILE_SIZE);
			r.renderObject.setSize(r.width * RenderSystem.TILE_SIZE, r.height * RenderSystem.TILE_SIZE);
			Root.INSTANCE.building.addRoom(r);
		}
		
		// TODO temporary loading code
		Root.INSTANCE.input.addProcessor(new GameInput(constructionMode));
		Gdx.input.setInputProcessor(Root.INSTANCE.input);
	}
	
	@Override
	public void render(float delta) {
		Root.INSTANCE.input.updateProcessors();
		
		Root.INSTANCE.renderSystem.renderObjects(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void show() {
		Gdx.gl20.glClearColor(0.42f, 0.71f, 1f, 1f);
		loadGame();
	}
}

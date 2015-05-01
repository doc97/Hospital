package com.tint.hospital.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.tint.hospital.ConstructionMode;
import com.tint.hospital.Human;
import com.tint.hospital.Root;
import com.tint.hospital.Building.RoomPathData;
import com.tint.hospital.data.GameData;
import com.tint.hospital.input.GameInput;
import com.tint.hospital.rooms.Room;
import com.tint.hospital.rooms.RoomType;
import com.tint.hospital.ui.GameUi;
import com.tint.hospital.utils.LoggingSystem;


public class GameState extends ScreenAdapter {
	
	private ConstructionMode constructionMode = new ConstructionMode();
	public static GameData gameData;
	private GameUi ui = new GameUi(constructionMode);
	
	public final void loadGame() {
		LoggingSystem.log("Game", "Loading game....");

		constructionMode.create();

		// TODO temporary loading code
		Root.INSTANCE.building.addRoom(new Room(RoomType.RECEPTION, 0, 0));
		Root.INSTANCE.building.addRoom(new Room(RoomType.WAITING_ROOM, 2, 0));
		Root.INSTANCE.building.addRoom(new Room(RoomType.EXAMINATION_ROOM, 5, 0));
		Root.INSTANCE.humanSystem.hireDoctor(new Human(0, 0));
		// Dev reasons
		Root.INSTANCE.economySystem.setMoney(10000);
		Root.INSTANCE.input.addProcessor(new GameInput(constructionMode));
	}
	
	@Override
	public void render(float delta) {
		constructionMode.update();
		Root.INSTANCE.humanSystem.update();
		Root.INSTANCE.renderSystem.renderObjects(Gdx.graphics.getDeltaTime());
		ui.draw();
		if (constructionMode.isActive())
			constructionMode.cui.draw();
	}

	@Override
	public void show() {
		Gdx.gl20.glClearColor(0.42f, 0.71f, 1f, 1f);
		loadGame();
		ui.enter();
	}
	
	public void hide(){
		for (RoomPathData rpd : Root.INSTANCE.building.getRooms()){
			Root.INSTANCE.renderSystem.removeObject(rpd.room.renderObject, 2);
		}
		ui.exit();
	}
}

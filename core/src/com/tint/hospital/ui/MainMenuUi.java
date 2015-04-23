package com.tint.hospital.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tint.hospital.Root;
import com.tint.hospital.states.StateSystem.States;

public class MainMenuUi extends UiBase{
	
	public MainMenuUi(){
		Root.INSTANCE.input.addProcessor(stage);
		stage.addActor(createTextButton("New Game", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() * 2 / 3 - 25, 100, 50, new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Root.INSTANCE.stateSystem.enterState(States.GAMESTATE);
				Root.INSTANCE.input.removeProcessor(stage);
			}
		}));
		stage.addActor(createTextButton("Load Game", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2 - 25, 100, 50, new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("load game pressed");
			}
		}));
		stage.addActor(createTextButton("Exit Game", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 3 - 25, 100, 50, new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		}));
	}
}

package com.tint.hospital.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tint.hospital.ConstructionMode;
import com.tint.hospital.Root;

public class GameUi extends UiBase{
	
	public GameUi(final ConstructionMode cm){
		Root.INSTANCE.input.addProcessor(stage);
		stage.addActor(createTextButton("Build", Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 100, 100, 50, new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(cm.isActive())
					cm.exit();
				else
					cm.enter();
			}
		}));
	}
	
}

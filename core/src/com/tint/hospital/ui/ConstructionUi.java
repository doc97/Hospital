package com.tint.hospital.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tint.hospital.ConstructionMode;
import com.tint.hospital.Root;
import com.tint.hospital.rooms.RoomType;

public class ConstructionUi extends UiBase{
	
	private ButtonGroup<TextButton> buttons;
	private ScrollPane sp;
	
	public ConstructionUi(final ConstructionMode cm){
		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		//Table table = new Table(new Skin(Gdx.files.internal("ui/uiskin.json")));
		
		VerticalGroup table = new VerticalGroup();
		table.space(10);
		table.padTop(10);
		table.padBottom(10);
		sp = new ScrollPane(table, new Skin(Gdx.files.internal("ui/uiskin.json")));
		sp.setBounds(width * 7 / 10, height / 8, width / 6, height / 2);
		sp.setFadeScrollBars(false);
		sp.setScrollBarPositions(false, true);
		
		sp.setColor(1, 1, 1, 0.7f);
		table.setColor(1, 1, 1, 1);
		Root.INSTANCE.input.addProcessor(stage);
		buttons = new ButtonGroup<TextButton>();
		
		buttons.setMaxCheckCount(1);
		buttons.setMinCheckCount(0);
		stage.addActor(sp);
		
		stage.addActor(createTextButton("Done", Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 100, 100, 50, new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(cm.isActive())
					cm.exit();
				else
					cm.enter();
			}
		}));
		
		TextButton butt = createTextButton("Entrance", width / 10, height * 8 / 10, width / 10, height / 12, new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				cm.selectBuilding(RoomType.ENTRANCE);
			}
		});
		
		buttons.add(butt);
		table.addActor(butt);
		//table.add(butt);
		
		butt = createTextButton("Examination room", width / 10 * 3, height * 8 / 10, width / 10, height / 12, new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				cm.selectBuilding(RoomType.EXAMINATION_ROOM);
			}
		});
		
		buttons.add(butt);
		//table.add(butt);
		table.addActor(butt);
		
		butt = createTextButton("Stairs", width / 10 * 5, height / 8 * 10, width / 10, height / 12, new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				cm.selectBuilding(RoomType.STAIRS);
			}
		});
		
		buttons.add(butt);
		//table.add(butt);
		table.addActor(butt);
		
		butt = createTextButton("Waiting Room", 0, 0, 0, 0, new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				cm.selectBuilding(RoomType.WAITING_ROOM);
			}
		});
		
		buttons.add(butt);
		//table.add(butt);
		table.addActor(butt);
		
		
		
		
	}
	
}

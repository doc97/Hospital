package com.tint.hospital.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tint.hospital.ConstructionMode;
import com.tint.hospital.rooms.RoomType;
import com.tint.hospital.utils.Assets;

public class ConstructionUi extends UiBase{
	
	private ButtonGroup<CustomCheckbox> buttons;
	private ScrollPane sp;
	
	public ConstructionUi(final ConstructionMode cm){

		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
	
		VerticalGroup table = new VerticalGroup();
		table.space(5);
		table.padTop(5);
		table.padBottom(5);
		sp = new ScrollPane(table, skin);
		sp.setBounds(width * 4 / 5, 0, width / 5, height * 9 / 10);
		sp.setFadeScrollBars(false);
		sp.setScrollBarPositions(false, true);
		
		sp.setColor(1, 1, 1, 1);
		table.setColor(1, 1, 1, 1);
		buttons = new ButtonGroup<>();
		
		buttons.setMaxCheckCount(1);
		buttons.setMinCheckCount(0);
		stage.addActor(sp);
		
		CustomCheckbox cc = new CustomCheckbox("Reception", getString(RoomType.RECEPTION), Assets.getTexture("reception"));
		cc.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				cm.selectBuilding(RoomType.RECEPTION);
			}
		});
		Table t = new Table();
		t.add().setActor(cc).maxWidth(width * 9 / 50).minWidth(width * 9 / 50);
		buttons.add(cc);
		table.addActor(t);
		
		cc = new CustomCheckbox("Examination Room", getString(RoomType.EXAMINATION_ROOM), Assets.getTexture("examination room"));
		cc.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				cm.selectBuilding(RoomType.EXAMINATION_ROOM);
			}
		});
		
		t = new Table();
		t.add().setActor(cc).maxWidth(width * 9 / 50).minWidth(width * 9 / 50);
		buttons.add(cc);
		table.addActor(t);
		
		cc = new CustomCheckbox("Stairs", getString(RoomType.STAIRS_BOTTOM), Assets.getTexture("stairsBottom"));
		cc.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				cm.selectBuilding(RoomType.STAIRS_BOTTOM);
			}
		});
		
		t = new Table();
		t.add().setActor(cc).maxWidth(width * 9 / 50).minWidth(width * 9 / 50);
		buttons.add(cc);
		table.addActor(t);
		
		cc = new CustomCheckbox("Waiting Room", getString(RoomType.WAITING_ROOM), Assets.getTexture("waiting room"));
		cc.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				cm.selectBuilding(RoomType.WAITING_ROOM);
			}
		});
		
		t = new Table();
		t.add().setActor(cc).maxWidth(width * 9 / 50).minWidth(width * 9 / 50);
		buttons.add(cc);
		table.addActor(t);
		
	}
	
	private String getString(RoomType room){
		String s = "";
		s += room.width + "x" + room.height + "       " + room.cost + '$';
		return s;
	}
	
}

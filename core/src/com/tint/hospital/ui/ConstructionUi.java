package com.tint.hospital.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
	private boolean skip;
	
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
		
		sp.setColor(1, 1, 1, 0.85f);
		table.setColor(1, 1, 1, 1);
		buttons = new ButtonGroup<>();
		
		buttons.setMaxCheckCount(1);
		buttons.setMinCheckCount(0);
		stage.addActor(sp);
		
		final CustomCheckbox c1 = new CustomCheckbox("Reception", getString(RoomType.RECEPTION), Assets.getTexture("reception"));
		c1.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (!skip)
				cm.selectBuilding(RoomType.RECEPTION);
			}
		});
		c1.addAction(new Action() {
			
			@Override
			public boolean act(float delta) {
				if (c1.isChecked() && cm.getCurrentType() != RoomType.RECEPTION){
					skip = true;
					c1.setChecked(false);
					skip = false;
				}
				return false;
			}
		});
		Table t = new Table();
		t.add().setActor(c1).maxWidth(width * 9 / 50).minWidth(width * 9 / 50);
		buttons.add(c1);
		table.addActor(t);
		
		
		
		final CustomCheckbox c2 = new CustomCheckbox("Examination Room", getString(RoomType.EXAMINATION_ROOM), Assets.getTexture("examination room"));
		c2.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (!skip)
				cm.selectBuilding(RoomType.EXAMINATION_ROOM);
			}
		});
		c2.addAction(new Action() {
			
			@Override
			public boolean act(float delta) {
				if (c2.isChecked() && cm.getCurrentType() != RoomType.EXAMINATION_ROOM){
					skip = true;
					c2.setChecked(false);
					skip = false;
				}
				return false;
			}
		});
		t = new Table();
		t.add().setActor(c2).maxWidth(width * 9 / 50).minWidth(width * 9 / 50);
		buttons.add(c2);
		table.addActor(t);
		
		
		
		final CustomCheckbox c3 = new CustomCheckbox("Stairs", getString(RoomType.STAIRS_BOTTOM), Assets.getTexture("stairsBottom"));
		c3.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (!skip)
				cm.selectBuilding(RoomType.STAIRS_BOTTOM);
			}
		});
		c3.addAction(new Action() {
			
			@Override
			public boolean act(float delta) {
				if (c3.isChecked() && cm.getCurrentType() != RoomType.STAIRS_BOTTOM){
					skip = true;
					c3.setChecked(false);
					skip = false;
				}
				return false;
			}
		});
		t = new Table();
		t.add().setActor(c3).maxWidth(width * 9 / 50).minWidth(width * 9 / 50);
		buttons.add(c3);
		table.addActor(t);
		
		
		
		final CustomCheckbox c4 = new CustomCheckbox("Waiting Room", getString(RoomType.WAITING_ROOM), Assets.getTexture("waiting room"));
		c4.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (!skip)
				cm.selectBuilding(RoomType.WAITING_ROOM);
			}
		});
		c4.addAction(new Action() {
			
			@Override
			public boolean act(float delta) {
				if (c4.isChecked() && cm.getCurrentType() != RoomType.WAITING_ROOM){
					skip = true;
					c4.setChecked(false);
					skip = false;
				}
				return false;
			}
		});
		t = new Table();
		t.add().setActor(c4).maxWidth(width * 9 / 50).minWidth(width * 9 / 50);
		buttons.add(c4);
		table.addActor(t);
		
		
		
		Label l = CreateLabel("Choose room type from list and left click to build.", width / 50, height * 7 / 8, 0, 0);
		l.setColor(Color.BLACK);
		stage.addActor(l);
		l = CreateLabel("Right click on a room to remove it.", width / 50, height * 33 / 40, 0, 0);
		l.setColor(Color.BLACK);
		stage.addActor(l);
		
	}
	
	private String getString(RoomType room){
		String s = "";
		s += room.width + "x" + room.height + "       " + room.cost + '$';
		return s;
	}
	
}

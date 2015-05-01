package com.tint.hospital.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tint.hospital.ConstructionMode;
import com.tint.hospital.Root;
import com.tint.hospital.states.StateSystem.States;

public class GameUi extends UiBase{
	
	private Table topBar;
	private StaffUi sui = new StaffUi();
	private Action updateAction;
	
	public GameUi(final ConstructionMode cm){
		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
		final ButtonGroup buttons = new ButtonGroup<>();
		buttons.setMinCheckCount(0);
		buttons.setMaxCheckCount(1);
		
		topBar = new Table(skin);
		topBar.setBounds(0, height / 10 * 9, width, height / 10);
		topBar.setBackground(skin.getDrawable("default-rect"));
		
		stage.addActor(topBar);
		
		final Label l = CreateLabel("Money: " + Root.INSTANCE.economySystem.getMoney(), width / 2 - width / 28, height * 147 / 160, width / 14, height / 16);
		l.addAction(new Action() {
			
			@Override
			public boolean act(float delta) {
				l.setText("Money: " + Root.INSTANCE.economySystem.getMoney());
				return false;
			}
		});
		stage.addActor(l);
		
		TextButton tb = createTextButton("Build", width * 25 / 28, height * 147 / 160, width  / 14, height / 16, new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(cm.isActive()){
					cm.exit();
					((TextButton)actor).setText("Build");
				}
				else {
					cm.enter();
					((TextButton)actor).setText("Done");
				}
			}
		});
		
		stage.addActor(tb);
		buttons.add(tb);
		
		tb = createTextButton("Staff", width * 11 / 14, height * 147 / 160, width / 14, height / 16, new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (((TextButton)actor).getText().charAt(0) == 'S'){
					((TextButton)actor).setText("Done");
					stage.addAction(updateAction);
					sui.enter();
				} else {
					((TextButton)actor).setText("Staff");
					stage.getRoot().removeAction(updateAction);
					sui.exit();
				}
			}
		});
		
		stage.addActor(tb);
		buttons.add(tb);
		
		tb = createTextButton("Main Menu", width / 28, height * 147 / 160, width / 14, height / 16, new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				buttons.uncheckAll();
				Root.INSTANCE.stateSystem.enterState(States.MAINMENUSTATE);
			}
		});
		
		stage.addActor(tb);
		buttons.add(tb);
		
		updateAction = new Action() {
			@Override
			public boolean act(float delta) {
				sui.draw();
				return false;
			}
		};
	}
	
}

package com.tint.hospital.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class UiBase {
	
	protected final Stage stage = new Stage(new ScreenViewport());
	
	protected ImageTextButton createImageTextButton(String text, float x, float y, float width, float height, ChangeListener listener){
		ImageTextButton b = new ImageTextButton(text, new Skin(Gdx.files.internal("ui/uiskin.json")));
		b.setX(x);
		b.setY(y);
		b.setWidth(width);
		b.addListener(listener);
		b.setHeight(height);
		return b;
	}
	
	protected TextButton createTextButton(String text, float x, float y, float width, float height, ChangeListener listener){
		TextButton b = new TextButton(text,new Skin(Gdx.files.internal("ui/uiskin.json")));
		b.setX(x);
		b.setY(y);
		b.setWidth(width);
		b.addListener(listener);
		b.setHeight(height);
		return b;
	}
	
	protected Label CreateLabel(String text, float x, float y, float width, float height){
		Label l = new Label(text, new Skin(Gdx.files.internal("ui/uiskin.json")));
		l.setX(x);
		l.setY(y);
		l.setWidth(width);
		l.setHeight(height);
		return l;
	}
	
	protected void addCheckbox(String text, float x, float y, float width, float height, ChangeListener changeListener){
		CheckBox c = new CheckBox(text, new Skin(Gdx.files.internal("ui/uiskin.json")));
		c.addListener(changeListener);
		c.setX(x);
		c.setY(y);
		c.setWidth(width);
		c.setHeight(height);
		stage.addActor(c);
	}
	
	public void draw(){
		stage.act();
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.draw();
	}
	
	public void dispose(){
		stage.dispose();
	}
	
	
	
}

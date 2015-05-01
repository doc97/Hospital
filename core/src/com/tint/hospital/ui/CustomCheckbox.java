package com.tint.hospital.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class CustomCheckbox extends Button{
	
	private Image picture;
	private Label label1, label2;
	
	private Cell imageCell;
	private Cell textCell;
	
	private CustomCheckbox(){}
	
	public CustomCheckbox(String text1, String text2, TextureRegion tr){
		
		align(Align.left);
		
		setFillParent(true);
		
		label1 = new Label(text1, UiBase.skin);
		label2 = new Label(text2, UiBase.skin);
		
		VerticalGroup vg = new VerticalGroup();
		vg.addActor(label1);
		vg.addActor(label2);
		vg.fill();
		
		float height = vg.getPrefHeight() * 1.33f;
		
		if (tr.getRegionWidth() != tr.getRegionHeight())
			picture = new Image(new TextureRegionDrawable(tr), Scaling.fit);
			//picture = new Image(new TextureRegionDrawable(new TextureRegion(tr, 0, 0, tr.getRegionHeight(), tr.getRegionHeight())));
		else
			picture = new Image(new TextureRegionDrawable(tr));
		imageCell = add(picture);
		imageCell.padLeft(5);
		imageCell.padTop(5);
		imageCell.padBottom(5);
		imageCell.minSize(height, height);
		imageCell.maxSize(height, height);
		
		textCell = add(vg);
		
		textCell.padLeft(5);
		textCell.padTop(5);
		textCell.padBottom(5);
		textCell.padRight(5);
		
		setStyle(UiBase.skin.get(ButtonStyle.class));
	
	}
	
	public void draw(Batch batch, float parentAlpha){
		validate();
		
		super.draw(batch, parentAlpha);
	}

}

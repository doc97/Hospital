package com.tint.hospital.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tint.hospital.Camera;
import com.tint.hospital.Human;
import com.tint.hospital.Root;
import com.tint.hospital.ai.DoctorAi;
import com.tint.hospital.ai.PatientAi;
import com.tint.hospital.utils.Assets;

public class StaffUi extends UiBase {
	
	private ScrollPane sp;
	private int docs;
	
	public StaffUi(){
		final float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
		final VerticalGroup table = new VerticalGroup();
		table.space(5);
		table.padTop(5);
		table.padBottom(5);
		sp = new ScrollPane(table, skin);
		sp.setBounds(width * 4 / 5, 0, width / 5, height * 9 / 10);
		sp.setFadeScrollBars(false);
		sp.setScrollBarPositions(false, true);
		sp.setColor(1, 1, 1, 0.85f);
		
		stage.addActor(sp);
		
		docs = Root.INSTANCE.humanSystem.doctors.size;
		
		stage.addAction(new Action() {
			@Override
			public boolean act(float delta) {
				if (Root.INSTANCE.humanSystem.doctors.size != docs){
					table.clear();
					docs = Root.INSTANCE.humanSystem.doctors.size;
					for (final Human h : Root.INSTANCE.humanSystem.doctors){
						CustomCheckbox cc = new CustomCheckbox("Doctor", "*Press to fire*", Assets.getTexture("male_standing"));
						cc.addListener(new ChangeListener() {
							
							@Override
							public void changed(ChangeEvent event, Actor actor) {
								Root.INSTANCE.humanSystem.fireDoctor(h);
							}
						});
						Table tt = new Table();
						tt.add().setActor(cc).maxWidth(width * 9 / 50).minWidth(width * 9 / 50);
						table.addActor(tt);
					}
					table.addActor(createTextButton("++ Hire ++", 0, 0, 0, 0, new ChangeListener() {
						
						@Override
						public void changed(ChangeEvent event, Actor actor) {
							Human human = new Human(0, 0);
							human.getFSM().pushState(new DoctorAi(human.getFSM()));
							Root.INSTANCE.humanSystem.hireDoctor(human);
						}
					}));
				}
				return false;
			}
		});
		
	}
	
}

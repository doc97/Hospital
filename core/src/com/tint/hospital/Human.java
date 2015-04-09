package com.tint.hospital;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.tint.hospital.ai.FiniteStateMachine;
import com.tint.hospital.render.MultipleAnimationObject;
import com.tint.hospital.utils.Assets;

public class Human {
	private Transform transform = new Transform();
	private FiniteStateMachine fsm;
	public MultipleAnimationObject animationObject;
	
	public Human() {
		Animation anim = Assets.getAnimation("male_standing");
		animationObject = new MultipleAnimationObject(new OffsetTransform(transform, -32, 0), anim);
		
		Root.INSTANCE.renderSystem.addObject(animationObject, 3);
	}
	
	public void update() {
		fsm.update();
	}
}

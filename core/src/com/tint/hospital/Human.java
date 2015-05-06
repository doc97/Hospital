package com.tint.hospital;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tint.hospital.ai.FiniteStateMachine;
import com.tint.hospital.render.MultipleAnimationObject;
import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.rooms.Room;
import com.tint.hospital.utils.Assets;

public class Human implements Telegraph {
	public static final int SPEED = 2;
	public Transform transform = new Transform();
	private FiniteStateMachine fsm;
	public MultipleAnimationObject animationObject;
	
	public Human(int x, int y) {
		Animation anim = Assets.getAnimation("male_standing");
		transform.setX(x);
		transform.setY(y);
		animationObject = new MultipleAnimationObject(new OffsetTransform(transform, -32, 0), anim);
		fsm = new FiniteStateMachine(this);
		Root.INSTANCE.renderSystem.addObject(animationObject, 3);
	}
	
	public FiniteStateMachine getFSM() {
		return fsm;
	}
	
	public void update() {
		fsm.update();
	}
	
	public Room getCurrentRoom() {
		return Root.INSTANCE.building.getRoomAt((int) Math.floor(transform.getX() / RenderSystem.TILE_SIZE), 
												(int) Math.floor(transform.getY() / RenderSystem.TILE_SIZE));
	}

	@Override
	public boolean handleMessage(Telegram msg) {
		return fsm.handleMessage(msg);
	}
}

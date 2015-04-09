package com.tint.hospital.render;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.tint.hospital.Root;
import com.tint.hospital.Transform;

public class MultipleAnimationObject implements RenderObject {

	private Transform transform;
	private Animation baseAnimation;
	private Animation currentAnimation;
	private float animationTime;

	/**
	 * Lets you play animations and make the object return to
	 * its base animation when done.
	 * @param baseAnimation
	 */
	public MultipleAnimationObject(Transform transform, Animation baseAnimation) {
		this.transform = transform;
		this.baseAnimation = baseAnimation;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		animationTime += Root.INSTANCE.renderSystem.getFrameDelta();
		
		if(currentAnimation != null && currentAnimation.getPlayMode() == PlayMode.NORMAL) {
			if(currentAnimation.isAnimationFinished(animationTime))
				currentAnimation = null;
		}
		
		TextureRegion frame = null;
		if(currentAnimation != null) 
			frame = currentAnimation.getKeyFrame(animationTime);
		else
			frame = baseAnimation.getKeyFrame(animationTime);
		
		
		batch.draw(frame, transform.getX(), transform.getY());
	}
	
	/**
	 * @param animation Can be null to stop animation
	 */
	public void playAnimation(Animation animation) {
		if(currentAnimation == null)
			animationTime = 0;
		currentAnimation = animation;
	}

	@Override
	public void setPosition(int x, int y) {
		transform.setX(x);
		transform.setY(y);
	}

	@Override
	public void setSize(int width, int height) {
		for(TextureRegion frame : currentAnimation.getKeyFrames()) {
			frame.setRegionWidth(width);
			frame.setRegionHeight(height);
		}
	}
}

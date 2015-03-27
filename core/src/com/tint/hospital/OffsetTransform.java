package com.tint.hospital;
/**
 * 
 * Wrapper around a transform which offsets get and set by given values.
 * Useful for rendering textures in correct positions
 *
 */
public class OffsetTransform extends Transform {
	private Transform wrappedTransform;

	public OffsetTransform(Transform transform, float offsetX, float offsetY) {
		super();
		this.wrappedTransform = transform;
		super.setX(offsetX);
		super.setY(offsetY);
	}

	@Override
	public float getX() {
		return wrappedTransform.getX() + super.getX();
	}

	@Override
	public float getY() {
		return wrappedTransform.getY() + super.getY();
	}

	@Override
	public void setX(float x) {
		wrappedTransform.setX(x);
	}

	@Override
	public void setY(float y) {
		wrappedTransform.setY(y);
	}

	@Override
	public void addX(float x) {
		wrappedTransform.addX(x);
	}

	@Override
	public void addY(float y) {
		wrappedTransform.addY(y);
	}
	
	
}

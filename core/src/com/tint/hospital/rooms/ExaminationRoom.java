package com.tint.hospital.rooms;

import com.tint.hospital.Assets;
import com.tint.hospital.render.TextureObject;

public class ExaminationRoom extends Room {

	public ExaminationRoom(int x, int y, int width, int height) {
		super(x, y, width, height);
		renderObject = new TextureObject(Assets.getTexture("examination room"), x, y, width, height);
	}

}

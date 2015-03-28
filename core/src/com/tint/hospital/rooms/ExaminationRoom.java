package com.tint.hospital.rooms;

import com.tint.hospital.Assets;


public class ExaminationRoom extends Room {

	public ExaminationRoom(int x, int y, int width, int height) {
		super(x, y, width, height);
		region = Assets.getTexture("Examination Room");
	}

}

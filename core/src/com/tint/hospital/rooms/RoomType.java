package com.tint.hospital.rooms;

public enum RoomType {
	WAITING_ROOM("waiting room", 4, 1),
	EXAMINATION_ROOM("examination room", 2, 1);
	
	public final int width;
	public final int height;
	public final String name;
	RoomType(String name, int width, int height) {
		this.width = width;
		this.height = height;
		this.name = name;
	}
}

package com.tint.hospital.rooms;

public enum RoomType {
	STAIRS_TOP("stairsTop", 50, 1, 1),
	STAIRS_MIDDLE("stairsMiddle", 50, 1, 1),
	STAIRS_BOTTOM("stairsBottom", 50, 1, 1),
	RECEPTION("reception", 75, 2, 1),
	WAITING_ROOM("waiting room", 100, 3, 1),
	EXAMINATION_ROOM("examination room", 150, 2, 1);
	
	public final int width;
	public final int height;
	public final int cost;
	public final String name;

	RoomType(String name, int cost, int width, int height) {
		this.width = width;
		this.height = height;
		this.cost = cost;
		this.name = name;
	}
}

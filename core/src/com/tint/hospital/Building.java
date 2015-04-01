package com.tint.hospital;

import java.util.ArrayList;
import java.util.List;

import com.tint.hospital.rooms.Room;

public class Building {
	public class RoomData {
		public Room room;
		public List<RoomData> connectedRooms;
		
		public RoomData(Room room) {
			this.room = room;
			connectedRooms = new ArrayList<RoomData>(4);
		}
	}

	private List<RoomData> rooms = new ArrayList<RoomData>();
	
	public void addRoom(Room room) {
		rooms.add(new RoomData(room));
		Root.INSTANCE.renderSystem.addObject(room.renderObject, 3, false);
	}
	
	public RoomData getRoomAt(int x, int y) {
		for(RoomData rd : rooms) {
			if(rd.room.x <= x && rd.room.y <= y && rd.room.x + rd.room.width > x && rd.room.y + rd.room.height > y)
				return rd;
		}
		
		return null;
	}

	public RoomData getRoomData(Room room) {
		for(RoomData rd : rooms) {
			if(rd.room == room)
				return rd;
		}
		return null;
	}
}

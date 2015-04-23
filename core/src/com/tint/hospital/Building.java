package com.tint.hospital;

import java.util.ArrayList;
import java.util.List;

import com.tint.hospital.rooms.Room;

public class Building {
	public class RoomPathData implements Comparable<RoomPathData> {
		public Room room;
		public List<RoomPathData> connectedRooms;
		public int totalCost;
		public int distanceCost;
		public RoomPathData parent;
		
		public RoomPathData(Room room) {
			this.room = room;
			connectedRooms = new ArrayList<RoomPathData>(4);
		}

		@Override
		public int compareTo(RoomPathData other) {
			return other.totalCost - totalCost;
		}
	}
	
	private List<RoomPathData> rooms = new ArrayList<RoomPathData>();
	
	public void addRoom(Room room) {
		RoomPathData rpd = new RoomPathData(room);
		
		RoomPathData adjecent = getRoomData(getRoomAt(room.x - 1, room.y));
		if(adjecent != null) {
			rpd.connectedRooms.add(adjecent);
			adjecent.connectedRooms.add(rpd);
		}
		
		adjecent = getRoomData(getRoomAt(room.x + room.type.width, room.y));
		if(adjecent != null) {
			rpd.connectedRooms.add(adjecent);
			adjecent.connectedRooms.add(rpd);
		}
		
		rooms.add(rpd);
		Root.INSTANCE.renderSystem.addObject(room.renderObject, 2);
	}
	
	public Room getRoomAt(int x, int y) {
		for(RoomPathData rd : rooms) {
			if(rd.room.x <= x && rd.room.y <= y && rd.room.x + rd.room.type.width > x && rd.room.y + rd.room.type.height > y)
				return rd.room;
		}
		
		return null;
	}

	public RoomPathData getRoomData(Room room) {
		for(RoomPathData rd : rooms) {
			if(rd.room == room)
				return rd;
		}
		return null;
	}

	public List<RoomPathData> getRooms() {
		return rooms;
	}
}

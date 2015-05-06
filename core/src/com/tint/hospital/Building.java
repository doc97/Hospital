package com.tint.hospital;

import java.util.ArrayList;
import java.util.List;

import com.tint.hospital.rooms.Room;
import com.tint.hospital.rooms.RoomType;
import com.tint.hospital.utils.LoggingSystem;

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
	
	public void removeRoom(Room room){
		if (room == null)
			return;
		if (rooms.size() > 1){
			Room under = getRoomAt(room.x, room.y - 1);
			Room top = null;
			for (int i = 0; i < room.type.width; i++){
				top = getRoomAt(room.x + i, room.y + 1);
				if (top != null)
					break;
			}
			
			if (top != null){
				LoggingSystem.log("Building", "Cannot remove " + room.type.toString());
				return;
			}	
			if (room.type == RoomType.STAIRS_TOP && under.type != RoomType.STAIRS_BOTTOM){
				under.changeType(RoomType.STAIRS_TOP);
			}
			if (room.y == 0){
				if (getRoomAt(room.x - 1, room.y) != null && getRoomAt(room.x + room.type.width, room.y) != null){
					LoggingSystem.log("Building", "Cannot remove " + room.type.toString());
					return;
				}
			}
			rooms.remove(getRoomData(room));
			Root.INSTANCE.economySystem.addMoney(room.type.cost / 2);
			Root.INSTANCE.renderSystem.removeObject(room.renderObject, 2);
		} else {
			LoggingSystem.log("Building", "Cannot remove " + room.type.toString());
		}
	}
	
	public void resetBuilding(){
		for (RoomPathData rpd : rooms){
			Root.INSTANCE.renderSystem.removeObject(rpd.room.renderObject, 2);
		}
		rooms.clear();
	}
	
}

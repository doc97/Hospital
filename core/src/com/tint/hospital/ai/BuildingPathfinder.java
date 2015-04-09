package com.tint.hospital.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.tint.hospital.Building.RoomPathData;
import com.tint.hospital.Root;
import com.tint.hospital.rooms.Room;

public class BuildingPathfinder {
	public class Path {
		private List<Room> rooms = new ArrayList<Room>();
		
		public Room get(int index) {
			return rooms.get(index);
		}

		public int size() {
			return rooms.size();
		}
	}

	private List<RoomPathData> closed = new ArrayList<RoomPathData>();
	private Stack<RoomPathData> open = new Stack<RoomPathData>();
	
	public Path getPath(Room start, Room end) {
		
		if(start == null || end == null) return null;
		
		if(start == end) {
			Path path = new Path();
			path.rooms.add(end);
			return path;
		}
		
		open.clear();
		closed.clear();
		
		open.add(Root.INSTANCE.building.getRoomData(start));
		
		while(!open.isEmpty()) {
			Collections.sort(open);
			//Take the room with the lowest cost
			RoomPathData current = open.pop();
			if(current.room == end)
				return constructPath(start, current);
			
			closed.add(current);
			
			//Check connected rooms
			for(RoomPathData child : current.connectedRooms) {
				if(closed.contains(child)) continue;
				
				int newDistanceCost = current.distanceCost + cost(current, child);
				
				boolean isInOpen = open.contains(child);
				
				if(newDistanceCost < child.distanceCost) {
					child.parent = current;
					child.distanceCost = newDistanceCost;
					child.totalCost = child.distanceCost + heuristic(child, end);
				}
				
				if(!isInOpen) {
					child.parent = current;
					child.distanceCost = newDistanceCost;
					child.totalCost = child.distanceCost + heuristic(child, end);
					
					open.add(child);
						
				}
			}
		}
		
		return null;
	}
	
	private int cost(RoomPathData from, RoomPathData to) {
		//TODO Change to something more sophisticated like different costs for elevators and stairs
		return Math.abs(from.room.x - to.room.x) + Math.abs(from.room.y - to.room.y);
	}

	private Path constructPath(Room start, RoomPathData end) {
		Path path = new Path();
		RoomPathData target = end;
		while(target.room != start) {
			path.rooms.add(target.room);
			target = target.parent;
		}
		path.rooms.add(start);
		Collections.reverse(path.rooms);
		return path;
	}

	private int heuristic(RoomPathData child, Room end) {
		return Math.abs(child.room.x - end.x) + Math.abs(child.room.y - end.y);
	}
}

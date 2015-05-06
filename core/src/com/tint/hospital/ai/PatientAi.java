package com.tint.hospital.ai;

import com.badlogic.gdx.ai.msg.Telegram;
import com.tint.hospital.Building.RoomPathData;
import com.tint.hospital.MessageTypes.AppointmentData;
import com.tint.hospital.Root;
import com.tint.hospital.ai.BuildingPathfinder.Path;
import com.tint.hospital.rooms.Room;
import com.tint.hospital.rooms.RoomType;

import static com.tint.hospital.MessageTypes.*;

public class PatientAi extends AiState {

	private Room assignedRoom;
	
	public PatientAi(FiniteStateMachine fsm) {
		super(fsm);
	}

	@Override
	public void enter() {
		
		fsm.pushNoEnter(new WaitingRoomState(fsm));
		
		Room entrance = null;
		for(RoomPathData room : Root.INSTANCE.building.getRooms()) {
			if(room.room.type == RoomType.RECEPTION) {
				entrance = room.room;
				break;
			}
		}
		fsm.pushState(new WalkingState(new Path(entrance), fsm));
	}
	

	private class WaitingRoomState extends AiState {

		public WaitingRoomState(FiniteStateMachine fsm) {
			super(fsm);
		}
		
		@Override
		public void enter() {
			//TODO Register to PatientManager
		}

		@Override
		public void handleMessage(Telegram msg) {
			if(msg.message == ROOM_ASSIGNED) {
				AppointmentData data = (AppointmentData) msg.extraInfo;
				assignedRoom = data.room;
				fsm.pushNoEnter(new AppointmentState(fsm));
				fsm.pushState(new WalkingState(assignedRoom, fsm));
				fsm.popState();
			}
		}
	}
	
	private class AppointmentState extends AiState {

		public AppointmentState(FiniteStateMachine fsm) {
			super(fsm);
		}

		@Override
		public void handleMessage(Telegram msg) {
			if(msg.message == TREATMENT_DONE) {
				Root.INSTANCE.economySystem.addMoney(100);
				fsm.pushState(new WalkHomeState(fsm));
				fsm.popState();
			}
		}
	}
}

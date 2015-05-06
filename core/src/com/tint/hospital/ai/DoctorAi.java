package com.tint.hospital.ai;

import static com.tint.hospital.MessageTypes.*;

import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.ai.msg.Telegram;
import com.tint.hospital.Building.RoomPathData;
import com.tint.hospital.MessageTypes.AppointmentData;
import com.tint.hospital.Root;
import com.tint.hospital.ai.BuildingPathfinder.Path;
import com.tint.hospital.rooms.Room;
import com.tint.hospital.rooms.RoomType;

public class DoctorAi extends AiState {

	private static final float TREATMENT_TIME = 5;
	
	private AppointmentData appointmentData;
	
	public DoctorAi(FiniteStateMachine fsm) {
		super(fsm);
	}

	@Override
	public void enter() {

		fsm.pushNoEnter(new IdleState(fsm));
		
		Room entrance = null;
		for(RoomPathData room : Root.INSTANCE.building.getRooms()) {
			if(room.room.type == RoomType.RECEPTION) {
				entrance = room.room;
				break;
			}
		}
		fsm.pushState(new WalkingState(new Path(entrance), fsm));
	}

	private class IdleState extends AiState {

		public IdleState(FiniteStateMachine fsm) {
			super(fsm);
		}
		
		@Override
		public void enter() {
			//TODO Add to idle list
		}

		@Override
		public void handleMessage(Telegram msg) {
			if(msg.message == ROOM_ASSIGNED) {
				AppointmentData data = (AppointmentData) msg.extraInfo;
				fsm.pushNoEnter(new AppointmentState(fsm));
				fsm.pushState(new WalkingState(data.room, fsm));
				fsm.popState();
			}
		}
	}

	public class AppointmentState extends AiState {
		
		private boolean patientArrived = false,
						timerActivated = false;

		public AppointmentState(FiniteStateMachine fsm) {
			super(fsm);
		}

		@Override
		public void handleMessage(Telegram msg) {
			switch(msg.message) {
			case PATIENT_ARRIVED:
				patientArrived = true;
				break;
			case TREATMENT_DONE:
				MessageManager.getInstance().dispatchMessage(fsm.getOwner(), appointmentData.patient, TREATMENT_DONE);
				fsm.popState();
				break;
			}
		}

		@Override
		public void update() {
			if(!timerActivated && patientArrived) {
				MessageManager.getInstance().dispatchMessage(TREATMENT_TIME, fsm.getOwner(), appointmentData.patient, TREATMENT_DONE);
				timerActivated = true;
			}
		}
		
	}

}

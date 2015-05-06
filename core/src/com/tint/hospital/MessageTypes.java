package com.tint.hospital;

import com.tint.hospital.rooms.Room;

public class MessageTypes {
	public static final int ROOM_ASSIGNED  = 0,
							TREATMENT_DONE = 1,
							PATIENT_ARRIVED = 2;
	
	public static class AppointmentData {
		public Room room;
		public Human patient, doctor;
	}
}

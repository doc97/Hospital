package com.tint.hospital;

import com.badlogic.gdx.utils.Array;
import com.tint.hospital.ai.PatientAi;
import com.tint.hospital.utils.LoggingSystem;

public class HumanSystem {
	
	public Array<Human> patients = new Array<Human>();
	public Array<Human> doctors = new Array<Human>();
	private int spawnTimer;
	private int spawnRate = 3600; // 60 = 1s
	
	public void update() {
		// Update spawning of new patients
		spawnTimer++;
		if(spawnTimer >= spawnRate) {
			Human human = new Human((int) (Math.random() * 40 - Camera.getCamera().viewportWidth / 2.0f), 0);
			human.getFSM().pushState(new PatientAi(human.getFSM()));
			retrievePatient(human);
			spawnTimer = 0;
		} 
		
		// Update both patients and doctors
		for(Human patient : patients)
			patient.update();
		
		for(Human doctor : doctors)
			doctor.update();
	}
	
	
	public void hireDoctor(Human doctor) {
		doctors.add(doctor);
		LoggingSystem.log("HumanSystem", "New doctor hired");
	}
	
	public void fireDoctor(Human doctor) {
		doctors.removeValue(doctor, false);
		Root.INSTANCE.renderSystem.removeObject(doctor.animationObject, 3);
		LoggingSystem.log("HumanSystem", "Doctor fired");
	}
	
	public void retrievePatient(Human patient) {
		patients.add(patient);
		LoggingSystem.log("HumanSystem", "New patient sent to hospital");
	}
	
	public void removePatient(Human patient) {
		patients.removeValue(patient, false);
		Root.INSTANCE.renderSystem.removeObject(patient.animationObject, 3);
		LoggingSystem.log("HumanSystem", "Patient going home");
	}

	public void resetHumans() {
		for (Human h : doctors){
			Root.INSTANCE.renderSystem.removeObject(h.animationObject, 3);
		}
		for (Human h : patients){
			Root.INSTANCE.renderSystem.removeObject(h.animationObject, 3);
		}
		patients.clear();
		doctors.clear();
	}
}

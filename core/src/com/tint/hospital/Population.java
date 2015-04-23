package com.tint.hospital;

import java.util.Random;

import com.badlogic.gdx.utils.Array;

public class Population {
	private Array<Human> sick = new Array<Human>();
	private Array<Human> well = new Array<Human>();
	private float illnessLevel = 0.1f;
	
	public void add(Human human) {
		well.add(human);
	}
	
	public void increaseIllness(float illness) {
		illnessLevel += illness;
		
		// Cap level between 0 and 1
		illnessLevel = Math.max(0, illnessLevel);
		illnessLevel = Math.min(1, illnessLevel);
		
		Random rand = new Random();
		int newSickPeople = (int) Math.floor(illnessLevel * (well.size + sick.size)) - sick.size;
		while(newSickPeople > 0) {
			int index = rand.nextInt(well.size);
			sick.add(well.get(index));
			well.removeIndex(index);
			newSickPeople--;
		}
	}
	
	public int getSize() {
		return well.size + sick.size;
	}
	
	public float getIllnessLevel() {
		return illnessLevel;
	}
}

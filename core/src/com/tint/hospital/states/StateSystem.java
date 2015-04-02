package com.tint.hospital.states;

import java.util.EnumMap;
import java.util.Map;

import com.badlogic.gdx.Screen;
import com.tint.hospital.Launcher;

public class StateSystem {
	public enum States { MAINMENUSTATE, GAMESTATE };
	
	private Map<States, Screen> states = new EnumMap<States, Screen>(States.class);
	private Launcher launcher;
	
	public void create(Launcher launcher) {
		this.launcher = launcher;
		states.put(States.GAMESTATE, new GameState());
	}
	
	public void enterState(States s)
	{
		Screen state = states.get(s);
		if(state != null)
			launcher.setScreen(state);
		else
			throw new RuntimeException("No state assigned to: " + s.toString());
	}
}

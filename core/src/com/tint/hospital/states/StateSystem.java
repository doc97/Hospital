package com.tint.hospital.states;

import java.util.EnumMap;
import java.util.Map;

import com.tint.hospital.Launcher;

public class StateSystem {
	public enum States { MAINMENUSTATE, GAMESTATE };
	
	private Map<States, State> states = new EnumMap<States, State>(States.class);
	private Launcher launcher;
	
	public StateSystem() {}
	
	public void create(Launcher launcher) {
		this.launcher = launcher;
		states.put(States.MAINMENUSTATE, new MainMenuState(this));
		states.put(States.GAMESTATE, new GameState(this));
	}
	
	public void enterState(States s)
	{
		State state = states.get(s);
		if(state != null)
			launcher.setScreen(state);
		else
			throw new RuntimeException("No state assigned to: " + s.toString());
	}
}

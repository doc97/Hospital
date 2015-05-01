package com.tint.hospital;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.states.StateSystem;
import com.tint.hospital.utils.Assets;

public enum Root {
	INSTANCE;
	
	public final RenderSystem renderSystem = new RenderSystem();
	public final StateSystem stateSystem = new StateSystem();
	public final Building building = new Building();
	public final EconomySystem economySystem = new EconomySystem();
	public final HumanSystem humanSystem = new HumanSystem();
	public final InputMultiplexer input = new InputMultiplexer();
	
	public final void createSystems(Launcher launcher) {
		Assets.loadGraphics();
		Camera.setup();
		stateSystem.create(launcher);
		renderSystem.create();
		Gdx.input.setInputProcessor(input);
	}
}

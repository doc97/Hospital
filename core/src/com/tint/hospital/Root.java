package com.tint.hospital;

import com.badlogic.gdx.InputMultiplexer;
import com.tint.hospital.input.GeneralInput;
import com.tint.hospital.render.RenderSystem;
import com.tint.hospital.states.StateSystem;
import com.tint.hospital.utils.Assets;

public enum Root {
	INSTANCE;
	
	public final RenderSystem renderSystem = new RenderSystem();
	public final ConstructionSystem constructionSystem = new ConstructionSystem();
	public final StateSystem stateSystem = new StateSystem();
	public final Building building = new Building();
	public final InputMultiplexer inputProcessor = new InputMultiplexer();
	public final GeneralInput input = new GeneralInput();
	
	public final void createSystems(Launcher launcher) {
		Assets.loadGraphics();
		Camera.setup();
		stateSystem.create(launcher);
		renderSystem.create();
		constructionSystem.create();
	}
}

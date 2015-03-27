package com.tint.hospital;

public enum Root {
	INSTANCE;
	
	public final RenderSystem renderSystem = new RenderSystem();
	
	public final void createSystems() {
		renderSystem.create();
	}
}

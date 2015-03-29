package com.tint.hospital;

public enum Root {
	INSTANCE;
	
	public final RenderSystem renderSystem = new RenderSystem();
	public final ConstructionSystem constructionSystem = new ConstructionSystem();
	public final Building building = new Building();
	
	public final void createSystems() {
		Assets.loadGraphics();
		renderSystem.create();
		constructionSystem.create();
	}
}

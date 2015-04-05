package com.tint.hospital.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.tint.hospital.Launcher;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Settings s = new Settings();
		s.paddingX = 0;
		s.paddingY = 0;
		TexturePacker.processIfModified(s, "graphics/unpacked", "graphics", "Hospital");
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new Launcher(), config);
	}
}

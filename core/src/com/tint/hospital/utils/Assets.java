package com.tint.hospital.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Assets {

	private static Map<String, Animation> animations = new HashMap<String, Animation>();
	private static Map<String, TextureRegion> textures = new HashMap<String, TextureRegion>();

	public static void loadGraphics() {
		TextureAtlas ta = new TextureAtlas(Gdx.files.internal("graphics/Hospital.atlas"));
		
		for(AtlasRegion region : ta.getRegions()) {
			textures.put(region.name, region);
		}
		LoggingSystem.log("Assets", "Textures loaded");
		
		int i = 0;
		// Load animations
		try {
			String[] animData = FileUtils.readFromFile("data/animations.txt", true).split("/n");
			for(String anim : animData) {
				if(anim.startsWith("//"))
					continue;
				
				String[] data = anim.split(",");
				String name = data[0];
				int frameWidth = Integer.valueOf(data[1].trim());
				int frameHeight = Integer.valueOf(data[2].trim());
				int firstX = Integer.valueOf(data[3].trim());
				int firstY = Integer.valueOf(data[4].trim());
				int lastX = Integer.valueOf(data[5].trim());
				int lastY = Integer.valueOf(data[6].trim());
				float frameTime = Float.valueOf(data[7].trim());
				PlayMode playMode = PlayMode.valueOf(data[8].trim());
				
				TextureRegion region = ta.findRegion(name);
				
				if(region == null) {
					LoggingSystem.error("Assets", "Error parsing animations on line " + (i + 1) + ". No such texture found");
					Gdx.app.exit();
					return;
				}
				
				Animation animation = loadAnimation(ta.findRegion(name), frameWidth, frameHeight, frameTime, firstX, firstY, lastX, lastY);
				animation.setPlayMode(playMode);
				
				animations.put(name, animation);
			}
			LoggingSystem.log("Assets", "Animations loaded");
		} catch (IOException e) {
			LoggingSystem.error("Assets", "Error loading animations");
			e.printStackTrace();
			Gdx.app.exit();
		} catch (IllegalArgumentException|ArrayIndexOutOfBoundsException e) {
			LoggingSystem.error("Assets", "Error parsing animations on line " + (i + 1));
			e.printStackTrace();
			Gdx.app.exit();
		}
	}

	/**
	 * 
	 * @param texture - The texture containing all frames
	 * @param spriteWidth - The width of the frame
	 * @param spriteHeight - The height of the frame
	 * @param frameTime - The duration every frame will be showed (in seconds)
	 * @param firstX - The first frames x coordianate
	 * @param firstY - The first frames y coordianate
	 * @param lastX - The last frames x coordianate
	 * @param lastY - The last frames y coordianate
	 * @return an animation
	 */
	private static Animation loadAnimation(TextureRegion texture, int spriteWidth, int spriteHeight, float frameTime, int firstX, int firstY, int lastX, int lastY) {
		TextureRegion[][] trArr = texture.split(spriteWidth, spriteHeight);
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int y = firstY; y <= lastY; y++) {
			int x = 0;
			if(y == firstY)
				x = firstX;
			
			for(; x < trArr[0].length; x++) {
				frames.add(trArr[y][x]);
				if(y == lastY && x == lastX)
					break;
			}
		}
		return new Animation(frameTime, frames);
	}

	public static Animation getAnimation(String id) {
		Animation anim = animations.get(id);
		if(anim != null)
			return anim;
		
		throw new RuntimeException("[Assets] No animation with id: " + id);
	}
	
	public static TextureRegion getTexture(String id) {
		TextureRegion tex = textures.get(id);
		if(tex != null)
			return tex;

		throw new RuntimeException("[Assets] No texture with id: " + id);
	}
}

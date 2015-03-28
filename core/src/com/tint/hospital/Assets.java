package com.tint.hospital;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
*
* @author Daniel Riissanen
*
*/

public class Assets {

	private static Map<String, Animation> animations = new HashMap<String, Animation>();
	private static Map<String, TextureRegion> textures = new HashMap<String, TextureRegion>();
	private static String[] keys = { "Examination Room", "Waiting Room" };

	public static void loadGraphics() {
		TextureAtlas ta = new TextureAtlas(Gdx.files.internal("graphics/packed/Hospital.atlas"));
		textures.put(keys[0], ta.findRegion("examination_room"));
		textures.put(keys[1], ta.findRegion("waiting_room"));
		
		for(int i = 0; i < keys.length; i++) {
			if(textures.get(keys[i]) == null)
				LoggingSystem.log("Assets", "Failed to load texture: " + keys[i], true);
			else if(i == keys.length - 1)
				LoggingSystem.log("Assets", "Graphics loaded", false);
		}
	}

	/**
	 * 
	 * @param texture - The texture containing all frames
	 * @param spriteWidth - The width of the frame
	 * @param spriteHeight - The height of the frame
	 * @param frameTime - The duration every frame will be showed
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
				if(y == lastY && x == lastX)
					break;
				frames.add(trArr[y][x]);
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

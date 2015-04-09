package com.tint.hospital.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

public class CustomInputMultiplexer implements InputProcessor {

	private Array<CustomInputAdapter> processors = new Array<CustomInputAdapter>(4);

	public CustomInputMultiplexer (CustomInputAdapter... processors) {
		for (int i = 0; i < processors.length; i++)
			this.processors.add(processors[i]);
	}
	
	public void updateProcessors() {
		for(CustomInputAdapter processor : processors)
			processor.update();
	}

	public void addProcessor (int index, CustomInputAdapter processor) {
		if (processor == null) throw new NullPointerException("processor cannot be null");
		processors.insert(index, processor);
	}

	public void removeProcessor (int index) {
		processors.removeIndex(index);
	}

	public void addProcessor (CustomInputAdapter processor) {
		if (processor == null) throw new NullPointerException("processor cannot be null");
		processors.add(processor);
	}

	public void removeProcessor (CustomInputAdapter processor) {
		processors.removeValue(processor, true);
	}

	/** @return the number of processors in this multiplexer */
	public int size () {
		return processors.size;
	}

	public void clear () {
		processors.clear();
	}

	public void setProcessors (Array<CustomInputAdapter> processors) {
		this.processors = processors;
	}

	public Array<CustomInputAdapter> getProcessors () {
		return processors;
	}

	public boolean keyDown (int keycode) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).keyDown(keycode)) return true;
		return false;
	}

	public boolean keyUp (int keycode) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).keyUp(keycode)) return true;
		return false;
	}

	public boolean keyTyped (char character) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).keyTyped(character)) return true;
		return false;
	}

	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).touchDown(screenX, screenY, pointer, button)) return true;
		return false;
	}

	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).touchUp(screenX, screenY, pointer, button)) return true;
		return false;
	}

	public boolean touchDragged (int screenX, int screenY, int pointer) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).touchDragged(screenX, screenY, pointer)) return true;
		return false;
	}

	@Override
	public boolean mouseMoved (int screenX, int screenY) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).mouseMoved(screenX, screenY)) return true;
		return false;
	}

	@Override
	public boolean scrolled (int amount) {
		for (int i = 0, n = processors.size; i < n; i++)
			if (processors.get(i).scrolled(amount)) return true;
		return false;
	}
	
}

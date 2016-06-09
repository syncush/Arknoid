package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
	private KeyboardSensor ks;
	private String endKey;
	private Animation ani;
	public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
		this.ks = sensor;
		this.endKey = key;
		this.ani = animation;
	}
	@Override
	public void doOneFrame(DrawSurface d, double dt) {
		ani.doOneFrame(d, dt);
	}

	@Override
	public boolean shouldStop() {
		if (this.ks.isPressed(endKey)) {
			return true;
		}
		return false;
	}

}

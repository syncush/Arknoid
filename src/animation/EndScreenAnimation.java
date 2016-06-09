package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import sprites.Sprite;

public class EndScreenAnimation implements Animation {
	private GUI ui;
	private Sprite frame;
	
	public EndScreenAnimation(Sprite endScreen, GUI graphicInterface) {
		this.frame = endScreen;
		this.ui = graphicInterface;
	}
	@Override
	public void doOneFrame(DrawSurface d, double dt) {
		this.frame.drawOn(d);
    }
	@Override
	public boolean shouldStop() {
		return false;
	}
}

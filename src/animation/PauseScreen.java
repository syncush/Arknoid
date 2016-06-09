package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The class describes a pause screen.
 * @author Daniel Hermon.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    /**
     * The method creates a new pause screen.
     * @param k -The keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    /**
     * The method draws the pause screen.
     * @param d - The draw surface which we draw on.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, "paused SpaceBar press space to continue", 20);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    /**
     * The method returns if animation should stop.
     * @return if animation should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}

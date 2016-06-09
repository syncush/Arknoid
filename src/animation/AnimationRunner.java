package animation;

import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;
/**
 * The class describes an animation runner.
 * @author Daniel Hermon.
 */
public class AnimationRunner {
    // Class member.
    private GUI gui;
    private int framesPerSecond;
    /**
     * The method creates a new Animation Runner.
     * @param g The GUI
     * @param fps the fps we run the game.
     */
    public AnimationRunner(GUI g, int fps) {
        this.gui = g;
        this.framesPerSecond = fps;
    }
    /**
     * The method runs the animation.
     * @param animation The animation we run.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, 1.0 / this.framesPerSecond);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * The method returns the GUI.
     * @return the GUI.
     */
    public GUI getGui() {
        return gui;
    }
}

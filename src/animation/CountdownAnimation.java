package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import sprites.SpriteCollection;
/**
 * The class describes a count down animation.
 * @author Daniel Hermon.
 *
 */
public class CountdownAnimation implements Animation {
    //Class members.
    private double numberOfSeconds;
    private int countfrom;
    private SpriteCollection gs;
    private GUI gui;
    private boolean stopCount;
    /**
     * The method creates a countdown animation.
     * @param numOfSeconds -The number of seconds we pause.
     * @param countFrom -From where to count.
     * @param gameScreen -The game screen.
     * @param graphicUI -The GUI.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, GUI graphicUI) {
        this.numberOfSeconds = numOfSeconds;
        this.gs = gameScreen;
        this.countfrom = countFrom;
        this.gui = graphicUI;
        this.stopCount = false;
    }
    /**
     * The method draws a frame of a countdown animation.
     * @param d -The draw surface which we draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        Sleeper sleepy = new Sleeper();
        for (int i = this.countfrom; i >= 1; i--) {
            DrawSurface c = this.gui.getDrawSurface();
            this.gs.drawAllOn(c);
            c.drawText(400, 300, Integer.toString(i), 65);
            gui.show(c);
            sleepy.sleepFor((long) (this.numberOfSeconds / this.countfrom) * 1000);
        }
        this.stopCount = true;
    }
    /**
     * The method returns if should stop.
     * @return if animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stopCount;
    }
}

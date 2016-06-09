package sprites;

import biuoop.DrawSurface;
import helpers.Counter;

import java.awt.*;

/**
 * The class describes a lives indicator.
 * @author Daniel Hermon.
 *
 */
public class LivesIndicator implements Sprite {
    // Class member.
    private Counter liveCounter;
    /**
     * The method creates a new lives indicator.
     */
    public LivesIndicator() {
        this.liveCounter = new Counter();
    }
    /**
     * The method draws on the lives on the screen.
     * @param d -The draw surface which we draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(100, 20, "Lives : " + Integer.toString(liveCounter.getValue()), 12);
    }
    /**
     * The method returns the lives counter.
     * @return the counter of lives.
     */
    public Counter getliveCounter() {
        return this.liveCounter;
    }
    /**
     * The method notifies that time passed.
     */
    @Override
    public void timePassed(double dt) {
    }
}

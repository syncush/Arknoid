package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Created by Daniel on 6/7/2016.
 */
public class ColorBackground implements Sprite {
    private Color backgroundColor;

    public ColorBackground(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * the method draws a sprite on the draw surface.
     *
     * @param d -The draw surface we are gonna draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.backgroundColor);
        d.fillRectangle(0, 40, 800, 600);
    }

    /**
     * the method executes a behavior of a sprite object when a time passed.
     *
     * @param dt - the time between frames.
     */
    @Override
    public void timePassed(double dt) {
    }
}

package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import sprites.Sprite;
/**
 * The class describes the background of Green3 Level.
 * @author Daniel Hermon.
 *
 */
public class Green3Background implements Sprite {
    /**
     * Creates the Green3 Background.
     */
    public Green3Background() {
    }
    /**
     * The method draws the background on the draw surface.
     * @param d The draw surface we draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.fillRectangle(0, 40, 800, 600);
        d.setColor(Color.gray);
        d.fillRectangle(60, 400, 100, 200);
        d.fillRectangle(95, 320, 30, 120);
        d.fillRectangle(105, 260, 10, 70);
        d.setColor(Color.black);
        d.fillRectangle(108, 140, 3, 120);
        d.setColor(Color.white);
        d.fillRectangle(111, 140, 120, 50);
        d.setColor(Color.red);
        d.fillCircle(165, 160, 20);
    }
    /**
     * The method notifies the background that time passed.
     */
    @Override
    public void timePassed(double dt) {
    }
}

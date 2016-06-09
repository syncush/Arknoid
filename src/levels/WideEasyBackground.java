package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import sprites.Sprite;
/**
 * The class describes the background of WideEasy Level.
 * @author Daniel Hermon
 */
public class WideEasyBackground implements Sprite {
    /**
     * The method creates a new WideEasy Background.
     */
    public WideEasyBackground() {
    }
    /**
     * The method draws the background on the draw surface.
     * @param d -The draw surface we drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Color cd = new Color(206, 177, 32);
        Color cs = new Color(246, 246, 246);
        Color ca = new Color(255, 255, 50);
        d.setColor(cs);
        d.fillRectangle(0, 40, 800, 600);
        d.setColor(cd);
        d.fillCircle(140, 140, 50);
        d.setColor(ca);
        d.fillCircle(140, 140, 40);
        for (int i = 0; i < 680; i = i + 15) {
            d.drawLine(140, 140, 20 + i , 280);
        }
    }
    /**
     * The method notifies the background time changed.
     */
    @Override
    public void timePassed(double dt) {
    }
}

package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import sprites.Sprite;
/**
 * The class describes the FinalFour level background.
 * @author Daniel Hermon.
 */
public class FinalFourBackground implements Sprite {
    /**
     * The method draws the level's background.
     * @param d The draw surface we draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 40, 800, 600);
        d.setColor(Color.BLUE);
        d.fillRectangle(20, 40, 750, 100);
        d.fillRectangle(20, 500, 750, 100);
        d.drawLine(200, 370, 550, 370);
        d.drawLine(200, 370, 375, 150);
        d.drawLine(550, 370, 375, 150);
        d.drawLine(200, 200, 550, 200);
        d.drawLine(550, 200, 375, 450);
        d.drawLine(375, 450, 200, 200);
    }
    /**
     * The method notifies the background that time passed.
     */
    @Override
    public void timePassed(double dt) {
        // TODO Auto-generated method stub
    }
}

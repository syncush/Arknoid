package levels;
import java.awt.Color;
import biuoop.DrawSurface;
import sprites.Sprite;
/**
 * The class creates a background sprite for direct hit level.
 * @author Daniel Hermon.
 */
public class DirectHitBackground implements Sprite {
    /**
     * The method draws on the level's background.
     * @param d - The draw surface we draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
            d.setColor(Color.black);
        d.fillRectangle(0, 40, 800, 600);
        d.setColor(Color.red);
        d.drawCircle(420, 200, 80);
        d.drawCircle(420, 200, 40);
        d.drawCircle(420, 200, 20);
        d.drawLine(340, 200, 500, 200);
        d.drawLine(420, 115, 420, 280);
    }
    /**
     * The method notifies the background time passed.
     */
    @Override
    public void timePassed(double dt) {
    }
}

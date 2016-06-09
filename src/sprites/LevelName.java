package sprites;

import biuoop.DrawSurface;

import java.awt.*;

/**
 * The class describes a level name.
 * @author Daniel Hermon.
 */
public class LevelName implements Sprite {
    // Class member.
    private String levelName;
    /**
     * The method creates the level name object.
     * @param str -The string of level name.
     */
    public LevelName(String str) {
        this.levelName = str;
    }
    /**
     * The method draws on the level name on screen.
     * @param d - The surface which we draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(580, 20, "Level Name: " + this.levelName, 12);
    }
    /**
     * The method notifies that time passed.
     */
    @Override
    public void timePassed(double dt) {

    }

}

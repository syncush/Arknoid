package sprites;

import biuoop.DrawSurface;
/**
 * Class describes a sprite behavior.
 * @author Daniel Hermon
 */
public interface Sprite {
       /**
        * the method draws a sprite on the draw surface.
        * @param d -The draw surface we are gonna draw on.
        */
       void drawOn(DrawSurface d);
       /**
        * the method executes a behavior of a sprite object when a time passed.
        */
       void timePassed(double dt);
}

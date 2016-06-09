package animation;

import biuoop.DrawSurface;
/**
 * The class describes an animation.
 * @author Daniel Hermon
 */
public interface Animation {
    /**
     * The method draws one frame of draw surface.
     * @param d The draw surface which we draw the frame on.
     */
    void doOneFrame(DrawSurface d, double dt);
    /**
     * The method returns if animation should stop.
     * @return if animation should stop.
     */
    boolean shouldStop();
}

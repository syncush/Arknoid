package listeners;

import sprites.Ball;
import sprites.Block;
/**
 * The class describes a listener, from observer design pattern.
 * @author Daniel Hermon.
 */
public interface HitListener {
    /**
     * The method notifies that hit happened.
     * @param beingHit The block that was hit.
     * @param hitter The ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

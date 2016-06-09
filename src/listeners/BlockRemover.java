package listeners;

import animation.GameLevel;
import helpers.Counter;
import sprites.Ball;
import sprites.Block;
/**
 * The class describes a block remover listener.
 * @author Daniel Hermon.
 */
public class BlockRemover implements HitListener {
    // Class member.
    private GameLevel game;
    private Counter removedBlocks;
    /**
     * The method creates a new block remover.
     * @param g The game.
     * @param rBlocks The block Counter.
     */
    public BlockRemover(GameLevel g, Counter rBlocks) {
        this.game = g;
        this.removedBlocks = rBlocks;
    }
    /**
     * The method notifies that hit occurred.
     * @param beingHit The block that was hit.
     * @param hitter The ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.isDeathblock() && beingHit.getHitCount() == 0) {
            beingHit.removeFromGame(this.game);
            this.removedBlocks.decrease(1);
        }
    }
    /**
     * The method returns the block counter.
     * @return the block counter.
     */
    public Counter getBlocksCounter() {
        return this.removedBlocks;
    }
}

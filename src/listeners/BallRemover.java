package listeners;
import animation.GameLevel;
import helpers.Counter;
import sprites.Ball;
import sprites.Block;
/**
 * The class describes ball remover listener.
 * @author Daniel Hermon.
 */
public class BallRemover implements HitListener {
    private GameLevel g;
    private Counter ballRemovedCounter;
    /**
     * The method creates ball remover.
     * @param gg The game.
     * @param c Counter of balls.
     */
    public BallRemover(GameLevel gg, Counter c) {
        this.g = gg;
        this.ballRemovedCounter = c;
    }
    /**
     * The method notifies that there is a hit event.
     * @param beingHit The block that got hit.
     * @param hitter The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.isDeathblock()) {
            this.ballRemovedCounter.decrease(1);
            this.g.removeSprite(hitter);
        }
    }
    /**
     * The method returns the counter of balls.
     * @return the counter of balls.
     */
    public Counter getBallCounter() {
        return this.ballRemovedCounter;
    }
}

package listeners;

import helpers.Counter;
import sprites.Ball;
import sprites.Block;

/**
 * The class describes a score tracking listener.
 * Created by Dell on 10/05/2016.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * The method creates a new score tracking listener.
     * @param scoreCounter The score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * The method notifies that hit happened.
     * @param beingHit -The block that was hit.
     * @param hitter -The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.isWorth()) {
            currentScore.increase(5);
        }
        if (beingHit.getHitCount() == 0) {
            currentScore.increase(10);
        }
    }
    /**
     * The method increases the counter by a given value.
     * @param score -The addition for our counter.
     */
    public void increaseCounter(int score) {
        this.currentScore.increase(score);
    }
}
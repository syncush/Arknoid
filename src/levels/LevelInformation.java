package levels;

import helpers.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;
/**
 * The interface represents a level information.
 * @author DANIEL
 */
public interface LevelInformation {
    /**
     * The method returns how many balls should be in the game.
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * The method returns the velocities of the balls.
     * @return the velociteis of each ball.
     */
    List<Velocity> initialBallVelocities();
    /**
     * The method returns the speed of the paddle.
     * @return the paddle's speed.
     */
    int paddleSpeed();
    /**
     * The method returns the paddle's width.
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * The method returns the name of the level.
     * @return the level's name.
     */
    String levelName();

    /**
     * The method returns the background of the level.
     * @return the level's background.
     */
    Sprite getBackground();

    /**
     * The method returns a list of all the blocks in the game.
     * @return a list of all the blocks in the game.
     */
    List<Block> blocks();

    /**
     * The method returns the amount of blocks that should be removes to move to the next level.
     * @return the number of blocks needed to be destroyed.
     */
    int numberOfBlocksToRemove();
}
package levels;

import helpers.Velocity;
import sprites.Block;
import sprites.Sprite;
import java.util.List;

/**
 * Created by Daniel on 6/5/2016.
 */
public class BaseLevel implements LevelInformation {
    private int paddleWidth;
    private int paddleSpeed;
    private int numOfBlocks;
    private String levelName;
    private List<Velocity> ballsVelo9;
    private List<Block> blockList;
    private Sprite backGround;
    public BaseLevel(int paddleWidth, List<Velocity> ballVelocities, List<Block> blockList,
                     int paddleSpeed, int numOfBlocks, String levelName, Sprite backGround) {
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.numOfBlocks = numOfBlocks;
        this.levelName = levelName;
        this.ballsVelo9 = ballVelocities;
        this.blockList = blockList;
        this.backGround = backGround;

    }
    /**
     * The method returns how many balls should be in the game.
     *
     * @return the number of balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return this.ballsVelo9.size();
    }

    /**
     * The method returns the velocities of the balls.
     *
     * @return the velociteis of each ball.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelo9;
    }

    /**
     * The method returns the speed of the paddle.
     *
     * @return the paddle's speed.
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * The method returns the paddle's width.
     *
     * @return the paddle's width.
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * The method returns the name of the level.
     *
     * @return the level's name.
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * The method returns the background of the level.
     *
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return this.backGround;
    }

    /**
     * The method returns a list of all the blocks in the game.
     *
     * @return a list of all the blocks in the game.
     */
    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    /**
     * The method returns the amount of blocks that should be removes to move to the next level.
     *
     * @return the number of blocks needed to be destroyed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }
}

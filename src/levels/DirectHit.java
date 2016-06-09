package levels;
import helpers.Velocity;
import sprites.Block;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import geometry.Point;
import geometry.Rectangle;
/**
 * The class describes the direct hit level.
 * @author Daniel Hermon.
 */
public class DirectHit implements LevelInformation {
    /**
     * Creates a DirectHit level.
     */
    public DirectHit() {
    }
    /**
     * The method returns how many balls should be in the level.
     * @return the numbers of the balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }
    /**
     * The method returns the velocities of all balls.
     * @return A list with all the balls' velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> veloList = new ArrayList<Velocity>();
        veloList.add(Velocity.fromAngleAndSpeed(90, -180));
        return veloList;
    }
    /**
     * The method returns the paddle's speed.
     * @return the paddle's speed.
     */
    @Override
    public int paddleSpeed() {
        return 180;
    }
    /**
     * The method returns the paddle's width in pixels.
     * @return Paddle's width
     */
    @Override
    public int paddleWidth() {
        // TODO Auto-generated method stub
        return 50;
    }
    /**
     * The method returns the name of the level.
     * @return the level's name.
     */
    @Override
    public String levelName() {
        // TODO Auto-generated method stub
        return "Direct Hit";
    }
    /**
     * The method returns the background of the level.
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return new DirectHitBackground();
    }
    /**
     * The method returns the list of all the blocks in the game.
     * @return A list of all the level's blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
           Block b =
                  new Block(new Rectangle(new Point(400, 180), 50, 40), Color.RED,  1, Color.BLACK);
           blockList.add(b);
           return blockList;
    }
    /**
     * The method returns the amount of blocks that should be removed.
     * @return number of blocks that needed to be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        // TODO Auto-generated method stub
        return 1;
    }
}

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
 * The class describes the level FinalFour.
 * @author Daniel Hermon.
 */
public class FinalFour implements LevelInformation {
     /**
     * Creates a FinalFour level.
     */
    public FinalFour() {
    }
    /**
     * The method returns how many balls should be in the level.
     * @return the numbers of the balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }
    /**
     * The method returns the velocities of all balls.
     * @return A list with all the balls' velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> veloList = new ArrayList<Velocity>();
        veloList.add(Velocity.fromAngleAndSpeed(40, 180));
        veloList.add(Velocity.fromAngleAndSpeed(180, -60));
        veloList.add(Velocity.fromAngleAndSpeed(60, -120));
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
        return 200;
    }
    /**
     * The method returns the name of the level.
     * @return the level's name.
     */
    @Override
    public String levelName() {
        return "Final Four";
    }
    /**
     * The method returns the background of the level.
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return (Sprite) new FinalFourBackground();
    }
    /**
     * The method returns the list of all the blocks in the game.
     * @return A list of all the level's blocks.
     */

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Color[] colorArray = {Color.gray, Color.red, Color.yellow, Color.green, Color.white, Color.pink, Color.cyan};
        for (int i = 0; i < 7; i++) {
            createLineOfBlocks(colorArray[i], blockList, 150 + (i * 20));
        }
        return blockList;
    }
    /**
     * The method creates lines of blocks.
     * @param c -Color of the blocks.
     * @param blockList - List of blocks.
     * @param height -Height of each block.
     */
    private void createLineOfBlocks(Color c, List<Block> blockList, int height) {
        for (int i = 50; i < 750; i = i + 50) {
            Block b = new Block(new Rectangle(new Point(i, height), 50, 20), c,  1, Color.BLACK);
            blockList.add(b);
        }
    }
    /**
     * The method returns the amount of blocks that should be removed.
     * @return number of blocks that needed to be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 98;
    }
}

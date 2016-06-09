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
 * THe class describes the Green3 Level.
 * @author Daniel Hermon.
 *
 */
public class Green3 implements LevelInformation {
    /**
     * Creates a Green3 level.
     */
    public Green3() {
    }
    /**
     * The method returns how many balls should be in the level.
     * @return the numbers of the balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }
    /**
     * The method returns the velocities of all balls.
     * @return A list with all the balls' velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> veloList = new ArrayList<Velocity>();
        veloList.add(Velocity.fromAngleAndSpeed(40, -180));
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
        return 150;
    }
    /**
     * The method returns the name of the level.
     * @return the level's name.
     */
    @Override
    public String levelName() {
        return "Green 3";
    }
    /**
     * The method returns the background of the level.
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return new Green3Background();
    }
      /**
     * The method returns the list of all the blocks in the game.
     * @return A list of all the level's blocks.
     */
    @Override
    public List<Block> blocks() {
        ArrayList<Block> blockList = new ArrayList<Block>();
        for (int i = 700; i >= 200; i = i - 50) {
            Block b = new Block(new Rectangle(new Point(i, 170), 50, 30), Color.gray, 1, Color.BLACK);
            blockList.add(b);
        }
        for (int i = 700; i >= 250; i = i - 50) {
            Block b = new Block(new Rectangle(new Point(i, 200), 50, 30), Color.RED, 1, Color.BLACK);
            blockList.add(b);
        }
        for (int i = 700; i >= 300; i = i - 50) {
            Block b = new Block(new Rectangle(new Point(i, 230), 50, 30), Color.YELLOW, 1, Color.BLACK);
            blockList.add(b);
        }
        for (int i = 700; i >= 350; i = i - 50) {
            Block b = new Block(new Rectangle(new Point(i, 260), 50, 30), Color.BLUE, 1, Color.BLACK);
            blockList.add(b);
        }
        for (int i = 700; i >= 350; i = i - 50) {
            Block b = new Block(new Rectangle(new Point(i, 290), 50, 30), Color.WHITE ,1, Color.BLACK);
            blockList.add(b);
        }
        return blockList;
    }
    /**
     * The method returns the amount of blocks that should be removed.
     * @return number of blocks that needed to be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

}

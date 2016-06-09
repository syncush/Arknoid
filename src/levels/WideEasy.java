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
 * The class describes the WideEasy level.
 * @author Daniel Hermon.
 */
public class WideEasy implements LevelInformation {
    /**
     * Creates a WideEasy level.
     */
    public WideEasy() {
    }
     /**
     * The method returns how many balls should be in the level.
     * @return the numbers of the balls in the level.
     */
    @Override
    public int numberOfBalls() {
        return 4;
    }
    /**
     * The method returns the velocities of all balls.
     * @return A list with all the balls' velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        for (int i = 0; i < 2; i++) {
            //Velocites creation.
            Velocity velocity9 = Velocity.fromAngleAndSpeed(50, (i - 5) * 60);
            list.add(velocity9);
        }
        for (int i = 0; i < 2; i++) {
            Velocity newVelo = Velocity.fromAngleAndSpeed(30, (i + 4) * 60);
            list.add(newVelo);
        }
        return list;
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
        return 300;
    }
    /**
     * The method returns the name of the level.
     * @return the level's name.
     */
    @Override
    public String levelName() {
        return "WideEasy";
    }
    /**
     * The method returns the background of the level.
     * @return the level's background.
     */
    @Override
    public Sprite getBackground() {
        return new WideEasyBackground();
    }
     /**
     * The method returns the list of all the blocks in the game.
     * @return A list of all the level's blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Color[] colorArray = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink, Color.cyan};
        int j = 0;
        for (int i = 0; i < 50 * 13; i = i + 50) {
            Block b =
                    new Block(new Rectangle(new Point(50 + i, 280), 50, 10),
                            colorArray[j / 2], 1, Color.BLACK);
            blockList.add(b);
            j++;
        }
        return blockList;
    }
     /**
     * The method returns the amount of blocks that should be removed.
     * @return number of blocks that needed to be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        // TODO Auto-generated method stub
        return 13;
    }
}

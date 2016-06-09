package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import helpers.Counter;

import java.awt.*;

/**
 * Created by Dell on 10/05/2016.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreToShow;
    private GameLevel game;
    /**
     * Creates new Score indicator.
     * @param game The game.
     */
    public ScoreIndicator(GameLevel game) {
        this.scoreToShow = new Counter();
        this.game = game;
    }
    /**
     * The method draws the score on the screen.
     * @param d -The surface which we drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        int xTextLocation = d.getWidth() / 2;
        int yTextLocation = 20;
        //Draws the text.
        d.drawText(xTextLocation, yTextLocation + 5, "Score: " + Integer.toString(scoreToShow.getValue()), 12);
    }
    /**
     * The method notifies that time passed.
     */
    @Override
    public void timePassed(double dt) {
        this.scoreToShow = game.getScore();
    }
}

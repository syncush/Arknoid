package levels;
import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;
/**
 * The class creates the end Screen.
 * Created by Dell on 22/05/2016.
 */
public class EndScreen implements Sprite {
    // Class Members.
    private String message1;
    private String message2;
    private int score;
    /**
     * The method creates an End Screen object.
     * @param isWon boolean if player won the game.
     * @param score -The players' score.
     */
    public EndScreen(boolean isWon, int score) {
        if (isWon) {
            this.message1 = "You Win!";
            this.message2 = "Winner!!";
        } else {
            this.message1 = "Game Over!";
            this.message2 = "Loser!";
        }
        this.score = score;
    }
    /**
     * The method draws on the end game screen.
     * @param d The draw surface we draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.yellow);
        d.fillRectangle(90, 80, 600, 90);
        d.setColor(Color.black);
        d.drawText(290, 140, this.message2, 60);
        d.setColor(Color.yellow);
        d.fillCircle(400, 500, 70);
        d.setColor(Color.white);
        d.fillCircle(375, 480, 10);
        d.fillCircle(425, 480, 10);
        d.setColor(Color.black);
        d.fillCircle(375, 480, 4);
        d.fillCircle(425, 480, 4);
        if (this.message2 == "Winner!!") {
            d.drawLine(370, 465, 385, 465);
            d.drawLine(420, 465, 435, 465);
            d.drawLine(375, 537, 400, 549);
            d.drawLine(427, 537, 400, 549);
            d.setColor(new Color(255, 204, 204));
            d.fillCircle(350, 510, 10);
            d.fillCircle(440, 510, 10);
        } else {
            d.drawLine(368, 460, 387, 470);
            d.drawLine(440, 460, 420, 468);
            d.drawLine(375, 537, 425, 537);
        }
        d.drawText(300, 300, this.message1, 50);
        d.drawText(300, 400, "Your Score Is: " + Integer.toString(this.score), 50);
    }
    /**
     * The method notifies the background time passed.
     */
    @Override
    public void timePassed(double dt) {
    }
}

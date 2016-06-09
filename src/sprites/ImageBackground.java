package sprites;

import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Daniel on 6/7/2016.
 */
public class ImageBackground implements Sprite {
    private Image backgroundImage;
    public ImageBackground(String imagePath) {
        try {
            this.backgroundImage = ImageIO.read(new File(imagePath));

        } catch (IOException ex) {
            throw new RuntimeException("COULD NOT FIND BACKGROUND IMAGE");
        }
    }
    /**
     * the method draws a sprite on the draw surface.
     *
     * @param d -The draw surface we are gonna draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0,40, this.backgroundImage);
    }

    /**
     * the method executes a behavior of a sprite object when a time passed.
     *
     * @param dt -The time diffrence between frames.
     */
    @Override
    public void timePassed(double dt) {

    }
}

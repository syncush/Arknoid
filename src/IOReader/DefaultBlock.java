package IOReader;

import java.awt.Color;

/**
 * Created by Daniel on 6/4/2016.
 */
public class DefaultBlock {
    private int height;
    private int width;
    private Color stroke;
    private int hitPoints;
    public DefaultBlock() {
        this.height = -1;
        this.width = -1;
        this.hitPoints = -1;
        this.stroke = null;
    }
    public DefaultBlock(int height, int width, int hitPoints, Color stroke) {
        this();
        this.height = height;
        this.width = width;
        this.hitPoints = hitPoints;
        this.stroke = stroke;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public int getHeight() {
        return this.height;
    }

    public Color getStroke() {
        return this.stroke;
    }
}

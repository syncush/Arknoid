package IOReader;

import java.awt.Color;
import java.util.List;

/**
 * Created by Daniel on 6/2/2016.
 */
public class BlockSymbol {
    private String symbol;
    private int width;
    private int height;
    private int hitPoints;
    private Color stroke;
    private List<FillK> fillKList;
    public BlockSymbol(String symbol, int width, int height, int hitPoints, List<FillK> fillKList, Color stro) {
        this.symbol = symbol;
        this.width = width;
        this.height = height;
        this.hitPoints = hitPoints;
        this.stroke = stro;
        this.fillKList = fillKList;
    }
    public String getSymbol() {
        return this.symbol;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public List<FillK> getFillKList() {
        return this.fillKList;
    }

    public Color getStroke() {
        return this.stroke;
    }
}

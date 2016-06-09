package IOReader;

/**
 * Created by Daniel on 6/2/2016.
 */
public class SpacerSymbol {
    private String symbol;
    private int width;
    public SpacerSymbol(String symbol, int width) {
        this.symbol = symbol;
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public String getSymbol() {
        return this.symbol;
    }
}

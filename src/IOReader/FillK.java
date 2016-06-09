package IOReader;

import java.awt.Color;

/**
 * Created by Daniel on 6/3/2016.
 */
public class FillK {
    private int kHits;
    private String imagePath;
    private Color c;
    public FillK(int kHits, String imagePath, Color c) {
        this.kHits = kHits;
        this.imagePath = imagePath;
        this.c = c;
    }
    public boolean isImageFillK() {
        if (!imagePath.equals("")) {
            return true;

        }
        return false;
    }
    public boolean isColorFillK() {
        if (c != null) {
            return true;
        }
        return false;
    }

    public Color getColor() {
        return c;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getkHits() {
        return kHits;
    }
}

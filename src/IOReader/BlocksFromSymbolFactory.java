package IOReader;

import sprites.Block;
import geometry.Point;
import geometry.Rectangle;

import java.util.List;

/**
 *
 */
public class BlocksFromSymbolFactory {
    private List<BlockSymbol> blockSymbolList;
    private List<SpacerSymbol> spacerSymbolList;
    public BlocksFromSymbolFactory(List<BlockSymbol> b, List<SpacerSymbol> s) {
        this.blockSymbolList = b;
        this.spacerSymbolList = s;
    }
    // returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        for (SpacerSymbol item : this.spacerSymbolList) {
            if (item.getSymbol().equals(s)) {
                return true;
            }
        }
        return false;
    }
    // returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        for (BlockSymbol item : this.blockSymbolList) {
            if (item.getSymbol().equals(s)) {
                return true;
            }
        }
        return false;
    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {
        BlockSymbol temp = null;
        for (BlockSymbol item : this.blockSymbolList) {
            if (item.getSymbol().equals(s)) {
                temp = item;
                break;
            }
        }
        int width = temp.getWidth();
        int height = temp.getHeight();
        Rectangle rect = new Rectangle(new Point(xpos, ypos), width, height);
        //TODO FIX THIS SHIT
        return new Block(temp.getHitPoints(), temp.getFillKList(), temp.getStroke(), rect);
    }

    // Returns the width in pixels associated with the given spacer-symbol.
    public int getSpaceWidth(String s) {
        if (this.isSpaceSymbol(s)) {
            for (SpacerSymbol item : this.spacerSymbolList) {
                if (item.getSymbol().equals(s)) {
                    return item.getWidth();
                }
            }
            return -1;
        } else {
            return -1;
        }
    }
}
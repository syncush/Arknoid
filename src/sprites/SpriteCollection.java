package sprites;

import java.util.ArrayList;
import biuoop.DrawSurface;
/**
 * The class represents a collection of sprites.
 * @author Daniel Hermon
 */
public class SpriteCollection {
    // Class member.
    private ArrayList<Sprite> spriteAList;
    /**
     * Creates a new sprite collection.
     */
    public SpriteCollection() {
        spriteAList = new ArrayList<Sprite>();
    }
    /**
     * Creates a new sprite collection.
     * @param aList -ArrayList of sprite objects.
     */
    public SpriteCollection(ArrayList<Sprite> aList) {
        this.spriteAList = aList;
    }
    /**
     * The method adds a sprite to the collection.
     * @param s - The sprite we want to add.
     */
    public void addSprite(Sprite s) {
        this.spriteAList.add(s);
    }
    /**
     * The function notify every object in the collection that time passed.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.spriteAList.size(); i++) {
            this.spriteAList.get(i).timePassed(dt);
        }
    }
    /**
     * The function draw each sprite on the draw surface.
     * @param d -The draw surface which we will draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite item : this.spriteAList) {
            item.drawOn(d);
        }
    }
    /**
     * The method returns the list of sprites.
     * @return The sprites list.
     */
    public ArrayList<Sprite> getArray() {
        return this.spriteAList;
    }
}

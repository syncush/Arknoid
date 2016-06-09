package sprites;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import animation.GameLevel;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import helpers.Velocity;
import IOReader.FillK;
import listeners.HitListener;
import listeners.HitNotifier;
import javax.imageio.ImageIO;

/**
 * @author Yossi Mandil on 27/03/2016.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // Class member
    private Rectangle blockRectangle;
    private Color blockColor;
    private Color stroke;
    private int hitCounts;
    //private boolean isPaddle;
    private ArrayList<HitListener> hListenerList;
    private boolean isDeathBlock;
    private boolean isWorth;
    private List<FillK> fillKList;
    /**
     * Creates new block.
     * @param block - The rectangle that define the block.
     */
    public Block(Rectangle block) {
        this.blockRectangle = block;
        this.blockColor = Color.CYAN;
        this.hitCounts = -1;
        this.stroke = null;
        this.hListenerList = new ArrayList<>();
        this.isWorth = true;
        this.isDeathBlock = false;
        this.fillKList = new ArrayList<>();
    }
    /**
     * Creates new block.
     * @param block - The rectangle that define the block.
     * @param c - The color of the block.
     */
    public Block(Rectangle block, Color c) {
        this(block);
        this.blockColor = c;
    }
    /**
     * Creates new block.
     * @param block - The rectangle that define the block
     * @param c - The color of the block.
     * @param hitCounts - the number of hits required to destroy the block.
     */
    public Block(Rectangle block, Color c, int hitCounts) {
        this(block, c);
        this.hitCounts = hitCounts;
    }
    /**
     * Creates new block.
     * @param block - The rectangle that define the block
     * @param c - The color of the block.
     * @param hitCounts - the number of hits required to destroy the block.
     */
    public Block(Rectangle block, Color c, int hitCounts, Color stroke) {
        this(block, c, hitCounts);
        this.stroke = stroke;
    }
    /**
     * Creates new block.
     * //TODO documante
     */
    public Block(int hitPoints, List<FillK> fillK, Color stroke, Rectangle rect) {
        this(rect);
        this.fillKList = fillK;
        this.stroke = stroke;
        this.hitCounts = hitPoints;
    }
    /**
     * returns The rectangle that define the block.
     * @return - The rectangle that define the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.blockRectangle;
    }
    /**
     * The method sets the array of listeners.
     * @param l -The listener list.
     */
    public void setListenerList(ArrayList<HitListener> l) {
        this.hListenerList = l;
    }

    /**
     * Change the velocity according the hitting point and the current velocity.
     * @param collisionPoint   - the collision point.
     * @param currentVelocity -the previous velocity.
     * @param hitter -The ball that hit the block.
     * @return New velocity that change according the hitting point and the previous velocity.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        Velocity newVelocity;
        int whichSideFlag = this.getBlock().whichSideByPoint(collisionPoint);
        if (whichSideFlag == 1) {
            newVelocity = new Velocity(-currentVelocity.getDX(), currentVelocity.getDY());
        } else {
            if (whichSideFlag == 0) {
            newVelocity = new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
            } else {
            if (whichSideFlag == -1) {
                 newVelocity = new Velocity(-currentVelocity.getDX(), -currentVelocity.getDY());
            } else {
            return null;
                }
            }
        }
        this.hitCounts--;
        notifyHit(hitter);
        return newVelocity;
    }
    @Override
    /**
     * The method notifies the block that time passed.
     * @param dt - the differinate time.
     */
    public void timePassed(double dt) {
    }
    @Override
    /**
     * The method draws the block on the draw surface.
     * @param d -The draw surface which we draw on.
     */
    public void drawOn(DrawSurface d) {
        //Calculates the parameter required for the draw function.
        int x = (int) this.getCollisionRectangle().upperLeft().getX();
        int y = (int) this.getCollisionRectangle().upperLeft().getY();
        int height = (int) this.getCollisionRectangle().getHeight();
        int width = (int) this.getCollisionRectangle().getWidth();
        if (fillKList.size() > 0) {
            FillK temp = getFillK();
            if (temp.isColorFillK()) {
                d.setColor(temp.getColor());
                d.fillRectangle(x, y, width, height);
            } else {
                try {
                    Image blockImage = ImageIO.read(new File(temp.getImagePath()));
                    d.drawImage(x, y, blockImage);
                } catch (IOException e) {
                    throw new RuntimeException("MISSING PICTURE FOR BLOCKS PLEASE CHECK IMAGE PATH");
                }
            }
            if (this.stroke != null) {
                d.setColor(this.stroke);
                d.drawRectangle(x, y, width, height);
            }
            return;
        } else {
            if (this.stroke != null) {
                d.setColor(this.stroke);
                d.drawRectangle(x, y, width, height);
            }
        }
        d.setColor(this.blockColor);
        d.fillRectangle(x, y, width, height);
    }

    private FillK getFillK() {
        for (FillK item : this.fillKList) {
            if (item.getkHits() == this.hitCounts) {
                return item;
            }
        }
        throw new RuntimeException("MISSING ARGUMENTS FOR BLOCK PLEASE CHECK BLOCKS FILES");
    }

    /**
     * The method add the paddle to the game.
     * @param g -The game object.
     */
    public void addToGame(GameLevel g) {
       g.addCollidable(this);
       g.addSprite(this);
    }
    /**
     * The method removes the block from the game.
     * @param g -The game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeColliadable(this);
        g.removeSprite(this);
    }
    /**
     * The method returns the hit counts of a block.
     * @return the hit counts of the block.
     */
    public int getHitCount() {
        return this.hitCounts;
    }
    /**
     * The method notifies the listeners that hit occurred.
     * @param hitter -The ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
          // Make a copy of the hitListeners before iterating over them.
          ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hListenerList);
          // Notify all listeners about a hit event:
          for (HitListener hl : listeners) {
             hl.hitEvent(this, hitter);
          }
     }
    /**
     * The method checks if blocks are equal.
     * @param b -THe block we compare to.
     * @return true if equals , false if not.
     */
    public boolean equals(Block b) {
        if (b.getBlock().equals(this.getBlock())) {
            return true;
        }
        return false;
    }
    /**
     * The method returns if block is a death block.
     * @return if block is death block.
     */
    public boolean isDeathblock() {
        return this.isDeathBlock;
    }
    /**
     * The method returns if block is worth points.
     * @return if block worth points.
     */
    public boolean isWorth() {
        return this.isWorth;
    }
    /**
     * The method returns the hash code.
     * @return hash value.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns the rectangle of the block.
     * @return the rectangle of the block.
     */
    public Rectangle getBlock() {
        return this.blockRectangle;
    }

    /**
     * The method adds the listener from the hit notifier.
     *
     * @param hl -The listener that we should add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hListenerList.add(hl);
    }

    /**
     * The method removes the listener from the hit notifier.
     *
     * @param hl -The listener that we should remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hListenerList.remove(hl);

    }
    public void turnBlockToDeathBlock() {
        this.isDeathBlock = true;
    }
    public void turnOffBlockFromDeathBlock() {
        this.isDeathBlock = false;
    }

    public int getWidth() {
        return (int) this.blockRectangle.getWidth();
    }
}
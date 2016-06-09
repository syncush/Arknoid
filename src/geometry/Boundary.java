package geometry;

/**
 * The class represents a frame boundaries.
 * @author Daniel Hermon
 */
public class Boundary {
    // Class members.
    private int upperBound;
    private int lowerBound;
    private int leftBound;
    private int rightBound;
    /**
     * Constructor for the class.
     * @param up -the upper boundary.
     * @param down - The lower boundary.
     * @param left -The left boundary.
     * @param right - The right boundary.
     */
    public Boundary(int up, int down, int left, int right) {
        this.upperBound = up;
        this.lowerBound = down;
        this.leftBound = left;
        this.rightBound = right;
    }
    /**
     * The method returns the value of the upper boundary.
     * @return The upper bound value.
     */
    public int getUpperBound() {
        return this.upperBound;
    }
    /**
     * The method returns the value of the upper boundary.
     * @return The lower bound value.
     */
    public int getLowerBound() {
        return this.lowerBound;
    }
    /**
     * The method returns the value of the upper boundary.
     * @return The left bound value.
     */
    public int getLeftBound() {
        return this.leftBound;
    }
    /**
     * The method returns the value of the upper boundary.
     * @return The right bound value.
     */
    public int getRightBound() {
        return this.rightBound;
    }
}

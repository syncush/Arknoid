package geometry;

/**
 * Point class represented by two coordinates x and y.
 * @author Daniel Hermon
 * @version 1.0 13 March 2016
 */
public class Point {
    // Class Variables
    private double x;
    private double y;
    /**
     * Constructor of the point class.
     * @param x -The X coordinate to initialize point.
     * @param y -The Y coordinate to initialize point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Overriding basic method.
     * @return Value.
     */
    public int hashCode() {
        return this.hashCode();
    }
    /**
     * The method calculates the distance of the current point with another point.
     * using the math equation SquareRoot((X1-X2)^2+(Y1-Y2)^2)
     * @param other -Point which we calculate the distance from.
     * @return the distance between the two points.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    /**
     * The method compare two points , return true if they are the same other return false.
     * @param other -The point we compare it with.
     * @return True-if they are the same point , false for other result.
     */
    public boolean equals(Point other) {
        if (this.getX() == other.getX() && this.getY() == other.getY()) {
            return true;
        }
        return false;
    }
    /**
     * Property , returns the x coordinate of the class.
     * @return the x coordinate value of the point
     */
    public double getX() {
        return this.x;
    }
    /**
     * Property , returns the y coordinate of the class.
     * @return the y coordinate value of the point
     */
    public double getY() {
        return this.y;
    }
    /**
     * The method applies a change on the x value of the point.
     * @param dx - The change on the x axis.
     */
    public void addXaxis(double dx) {
        this.x = this.x + dx;
    }
}
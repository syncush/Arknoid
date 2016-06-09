package geometry;


import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a rectangle.
 * @author Daniel Hermon
 */
public class Rectangle {
    // Class members.
    private ArrayList<Line> lineSidesArray;
    private double width;
    private double height;
    private Point upperLeft;
    /**
     * Constructor for the class.
     * @param upperLeft -The upper left corner in the rectangle.
     * @param width -The width of the rectangle.
     * @param height -The height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        // Calculates all the corners of the rectangle.
        Point upperRight = new Point(Math.abs(upperLeft.getX() + width), upperLeft.getY());
        Point lowerLeft = new Point(Math.abs(upperLeft.getX()), upperLeft.getY() + height);
        Point lowerRight = new Point(Math.abs(upperLeft.getX() + width), upperLeft.getY() + height);
        this.lineSidesArray = new ArrayList<Line>();
        // sets the class members.
        this.setLinesRectangle(upperLeft, upperRight, lowerLeft, lowerRight);
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
    }
    /**\
     * Sets the parameters in the right place in the array.
     * @param upperLeftCorner -The upper left corner in the rectangle.
     * @param upperRight -The upper right corner in the rectangle.
     * @param lowerLeft -The lower left corner in the rectangle.
     * @param lowerRight -The lower right corner in the rectangle.
     */
    public void setLinesRectangle(Point upperLeftCorner, Point upperRight, Point lowerLeft, Point lowerRight) {
        this.lineSidesArray.add(new Line(upperLeftCorner, upperRight));
        this.lineSidesArray.add(new Line(upperLeftCorner, lowerLeft));
        this.lineSidesArray.add(new Line(upperRight, lowerRight));
        this.lineSidesArray.add(new Line(lowerLeft, lowerRight));
    }
    /**
     * The method returns the left side which is represented as a line segment.
     * @return the left side which is represented as a line segment.
     */
    public Line getLeftSide() {
        return this.lineSidesArray.get(1);
    }
    /**
     * The method sets the rectangle line segments of the sides.
     * @param upperLeftCorner -The upper left corner point value.
     */
    public void setNewLinesRectangle(Point upperLeftCorner) {
         //Empty the list.
         this.clearList();
         //calcualte all the corners.
         //Point upperRight = new Point(Math.abs(upperLeftCorner.getX() + this.getHeight()), upperLeftCorner.getY());
         Point upperRight = new Point(Math.abs(upperLeftCorner.getX() + this.width), upperLeftCorner.getY());
         Point lowerLeft = new Point(Math.abs(upperLeftCorner.getX()), upperLeftCorner.getY() + height);
         Point lowerRight = new Point(Math.abs(upperLeftCorner.getX() + width), upperLeftCorner.getY() + height);
         this.setLinesRectangle(upperLeftCorner, upperRight, lowerLeft, lowerRight);
    }
    /**
     * The method returns the right side which is represented as a line segment.
     * @return the right side which is represented as a line segment.
     */
    public Line getRightSide() {
        return this.lineSidesArray.get(2);
    }
    /**
     * The method returns the upper side which is represented as a line segment.
     * @return the upper side which is represented as a line segment.
     */
    public Line getUpperSide() {
        return this.lineSidesArray.get(0);
    }
    /**
     * The method returns the lower side which is represented as a line segment.
     * @return the lower side which is represented as a line segment.
     */
    public Line getLowerSide() {
        return this.lineSidesArray.get(3);
    }
    /**
     * The method returns width of the rectangle.
     * @return width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * The method returns height of the rectangle.
     * @return height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * The method returns the upper left corner of the rectangle.
     * @return the upper left corner of the rectangle.
     */
    public Point upperLeft() {
        return this.upperLeft;
    }
    /**
     * The method returns all the intersections points of a line segment with the rectangle.
     * <p>
     * for each side of the rectangle the method checks if the line intersect with the side,if so
     * add him to the list if not , continue checking for intersections.
     * <p>
     * @param line -The line parameter which we check if intersect with the rectangle.
     * @return all the intersections points of a line segment with the rectangle.
     */
    public List intersectionPoints(Line line) {
        List intersectionList = new ArrayList();
        for (Line side : this.lineSidesArray) {
            if (line.isIntersecting(side)) {
                intersectionList.add(line.intersectionWith(side));
            }
        }
        return intersectionList;
    }
    /**
     * The method checks if a line segment intersect with the rectangle.
     * <p>
     * The method checks the line using the isintersection function.
     * if so updates the boolean , final stage is to return the values according
     * to the intersections
     * <p>
     * @param collisonLine -The line which we check if it intersect with the rectangle.
     * @return 1 - if intersecting only with the sides.
     *         0 - if intersecting only with the upper or loser sides.
     *         -1 if intersecting with the corner of the rectangle.
     *         -2 if not intersecting at all.
     */
    public int doesHitSide(Line collisonLine) {
        boolean flagSides = false;
        boolean flagUpperLower = false;
        if (this.getLeftSide().isIntersecting(collisonLine) || this.getRightSide().isIntersecting(collisonLine)) {
            flagSides = true;
        }
        if (this.getUpperSide().isIntersecting(collisonLine) || this.getLowerSide().isIntersecting(collisonLine)) {
            flagUpperLower = true;
        }
        if (!flagUpperLower && !flagSides) {
            return -2;
        }
        if (flagUpperLower && flagSides) {
            return -1;
        } else {
            if (flagUpperLower && !flagSides) {
                return 0;
            } else {
                return 1;
            }
        }
    }
    /**
     * The function checks on which side the collision point is on.
     * @param collisionPoint The point of impact of the ball and the block.
     * @return an integer represents which side did it hit.
     *             -2 for no hits.
     *             -1 for corners.
     *             0 for upper or lower.
     *             1 for sides.
     */
    public int whichSideByPoint(Point collisionPoint) {
        //intialize variables.
        boolean flagSides = false;
        boolean flagUpperLower = false;
        // checks for the sides.
        if (this.getLeftSide().isOnLineSegement(collisionPoint)
                || this.getRightSide().isOnLineSegement(collisionPoint)) {
            flagSides = true;
        }
        //checks for the upper and lower sides.
        if (this.getUpperSide().isOnLineSegement(collisionPoint)
                || this.getLowerSide().isOnLineSegement(collisionPoint)) {
            flagUpperLower = true;
        }
        //if there is no intersection on the line segments.
        if (!flagUpperLower && !flagSides) {
            return -2;
        }
        // if it is on one of the corners.
        if (flagUpperLower && flagSides) {
            return -1;
        } else {
            //if it is only on top or bottom
            if (flagUpperLower && !flagSides) {
                return 0;
            } else {
                return 1;
            }
        }
    }
    /**
     * The method applies a change on the X axis of the rectangle value.
     * @param dx - The change the point made from his previous location.
     */
    public void addUpperLeftX(double dx) {
        this.upperLeft.addXaxis(dx);
        this.setNewLinesRectangle(this.upperLeft);
    }
    /**
     * The method clears the sidesarray class member.
     */
    private void clearList() {
        this.lineSidesArray = new ArrayList();
    }
    /**
     * The method cheks if a point in inside of a rectangle.
     * @param p -The point we check if it is the rectangle.
     * @return True - point is inside.false if doesn't.
     */
    public boolean isBallInsideRect(Point p) {
        //if the point x value is in range of the x values of the rectangle.
        if (this.upperLeft.getX() <= p.getX() && this.upperLeft.getX() + this.getWidth() >= p.getX()) {
            // if the point y value is in range of the y values of the rectangle.
            if (this.upperLeft.getY() <= p.getY() && this.upperLeft.getY() + this.getHeight() >= p.getY()) {
                //Point inside of the rectangle so return True.
                return true;
            }
        }
        //point is not in the rectangle.
        return false;
    }
    /**
     * The method sets upper left point.
     * @param p -The point we set as the new upper left point.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
        this.setNewLinesRectangle(this.upperLeft);
    }
    /**
     * The method returns the upper left.
     * @return the upper left corner.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
package geometry;

import java.util.List;
/**
 * The class represents a line segment by two points.
 * @author Daniel Hermon
 */
public class Line {
    // Class variable.
    private Point startPoint;
    private Point endPoint;
    /**
     * creates a line with two points coordinates.
     *
     * @param x1 -First point x coordinate.
     * @param y1 -First point y coordinate.
     * @param x2 -Second point x coordinate.
     * @param y2 -Second point y coordinate.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.startPoint = new Point(x1, y1);
        this.endPoint = new Point(x2, y2);
    }
    /**
     * Creates a line from two points.
     *
     * @param p1 -First point.
     * @param p2 -Second point.
     */
    public Line(Point p1, Point p2) {
        this.startPoint = p1;
        this.endPoint = p2;
    }
    /**
     * Calculates the length of the line.
     * @return The length of the line.
     */
    public double length() {
        return this.start().distance(this.end());
    }
    /**
     * The method returns the middle point in the line.
     *
     * @return Point with the coordinates of the middle of the line.
     */
    public Point middle() {
        double pointX = (start().getX() + end().getX()) / 2;
        double pointY = (start().getY() + end().getY()) / 2;
        Point center = new Point(pointX, pointY);
        return center;
    }
    /**
     * The method returns true if the line is vertical to x axis, else returns false.
     * @return True if the line is vertical , false if not.
     */
    public boolean isVertical() {
        return (this.end().getX() - this.start().getX() == 0) ? true : false;
    }
    /**
     * Method calculates the swap by dy and dx of the points.
     *
     * @return the value of the slope.
     */
    public double getSlope() {
        double dy = this.end().getY() - this.start().getY();
        double dx = this.end().getX() - this.start().getX();
        // when we call the function we already check dx not equals to 0**/
        return dy / dx;
    }
    /**
     * Method calculates the independent variable.
     * @param other -The point which we help to calculate the variable.
     * @return the value of the independent variable.
     */
    public double getindependentVariable(Point other) {
        return other.getY() - (this.getSlope() * other.getX());
    }
    /**
     * The method returns the line start point.
     *
     * @return start point of the line.
     */
    public Point start() {
        return this.startPoint;
    }
    /**
     * The method returns the line end point.
     *
     * @return end point of the line.
     */
    public Point end() {
        return this.endPoint;
    }
    /**
     * The method checks if two lines are intersecting.
     *
     * @param other -A line to check if intersect with current line.
     * @return True if lines are intersecting . false for not intersecting
     */
    public boolean isIntersecting(Line other) {
        Point p = intersectionWith(other);
        if (p == null) {
            return false;
        }
        if (!this.isOnLineSegement(p) || !other.isOnLineSegement(p)) {
            return false;
        }
        return true;
    }
    /**
     * The method check if a point is on the line segment.
     * @param point The point to check
     * @return True if the point is on the line segment, false if not.
     */
    public boolean isOnLineSegement(Point point) {
        double epsilon = 3;
        double minX = Math.min(this.startPoint.getX(), this.end().getX()) - epsilon;
        double maxX = Math.max(this.startPoint.getX(), this.end().getX()) + epsilon;
        double minY = Math.min(this.startPoint.getY(), this.end().getY()) - epsilon;
        double maxY = Math.max(this.startPoint.getY(), this.end().getY()) + epsilon;
        if (point.getX() <= maxX && point.getX() >= minX) {
            if (point.getY() <= maxY && point.getY() >= minY) {
                return true;
            }
        }
        return false;
    }
    /**
     * The method returns the point where the two lines intersect.
     *
     * @param other -A line to check if intersect with current line.
     * @return the point where they intersect.
     */
    public Point intersectionWith(Line other) {
        /** we checked if there is a slope**/
        if (this.isVertical() && other.isVertical()) {
            return null;
            } else {
                if (this.isVertical()) {
                    return intersectionPointWithVertical(other, this);
            } else {
                if (other.isVertical()) {
                    return intersectionPointWithVertical(this, other);
            } else {
                if (this.getSlope() == other.getSlope()) {
                    return null;
            }
          }
          }
        }
        double a1 = this.getSlope();
        double a2 = other.getSlope();
        // get the b from
        double b1 = this.getindependentVariable(this.start());
        double b2 = other.getindependentVariable(other.start());
        // solve the equation
        double pointX = (b2 - b1) / (a1 - a2);
        double pointY = a1 * pointX + b1;
        return new Point(pointX, pointY);
    }
    /**
     * The method calculates the intersection point of a two lines ,which one is vertical.
     * @param regular A line which has a slope.
     * @param vertical The vertical line
     * @return the intersection point of the two lines.
     */
    public Point intersectionPointWithVertical(Line regular, Line vertical) {
        double pointX = vertical.start().getX();
        double pointY = (regular.getSlope() * pointX)
                + regular.getindependentVariable(regular.start());
        return new Point(pointX, pointY);
    }
    /**
     * The method compares two lines.
     *
     * @param other -A line to check if is equal to the current line.
     * @return true - lines are equal , false -lines are not equal.
     */
    public boolean equals(Line other) {
        return this.start().equals(other.start()) && this.end().equals(other.end())
                || this.start().equals(other.end()) && this.end().equals(other.start());
    }
    /**
     * The method returns the closest intersection point.
     * <p/>
     * The function runs all over the list of points and looks for the minimum
     * distance between the intersection point to the start of the line
     * <p/>
     *
     * @param rect -A rectangle that we check on it the closest intersection point.
     * @return the closest intersection point, if it doesn't find one returns null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionList = rect.intersectionPoints(this);
        if (intersectionList.size() == 0) {
            return null;
        }
        Point closestPoint = intersectionList.get(0);
        double minDistance = intersectionList.get(0).distance(this.startPoint);
        for (int i = 1; i < intersectionList.size(); i++) {
            if (intersectionList.get(i).distance(this.startPoint) < minDistance) {
                closestPoint = intersectionList.get(i);
                minDistance = (intersectionList.get(i).distance(this.startPoint));
            }
        }
        return closestPoint;
    }
}

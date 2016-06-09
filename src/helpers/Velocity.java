package helpers;

import geometry.Point;

/**
 * The class "describes" a velocity , meaning the change on the x and y axis.
 * @author Daniel Hermon
 */
public class Velocity {
    // Class members.
    private double dx;
    private double dy;
    /**
     * Override default constructor for the velocity class.
     */
    public Velocity() {
        this.dx = 0;
        this.dy = 0;
    }
    /**
     * A constructor for the velocity class.
     * @param dx the change on the dx axis.
     * @param dy the change on the dy axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * The method applies the change of the point coordinates by the given points plus the change.
     * @param p the point which we want to apply the change of the velocity.
     * @return the point after the change of the velocity.
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + dx * dt, p.getY() + dy * dt);
    }
    /**
     * The method returns the change on the x axis.
     * @return the dx class member AKA the change on the x axis.
     */
    public double getDX() {
        return this.dx;
    }
    /**
     * The method returns the change on the y axis.
     * @return the dy class member AKA the change on the y axis.
     */
    public double getDY() {
        return this.dy;
    }
    /**
     * The method transform an angular velocity to a velocity object for the x and y axis movement.
     * @param angle -The angle of the movement.
     * @param speed -The speed the balls move in the angle direction.
     * @return Velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Converts the angle to radiant.
       // double angleInRadians = Math.PI * ((angle - 90) / 180);
        // Calculates the velocity on the x and y axis.
       // double dx = speed * Math.sin(angleInRadians);
       // double dy = speed * Math.cos(angleInRadians);
        // returns the Velocity object.
       // return new Velocity(-dx , dy);
        double dx = Math.cos((angle - 90.0) / 180.0 * 3.141592653589793) * speed;
        double dy = Math.sin((angle - 90.0) / 180.0 * 3.141592653589793) * speed;
        return new Velocity(dx, dy);
    }
}

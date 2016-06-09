package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import geometry.Boundary;
import geometry.Line;
import geometry.Point;
import helpers.GameEnvironment;
import helpers.Velocity;

/**
 * The class represents a ball and his motion and boundaries.
 * @author Daniel Hermon
 */
public class Ball implements Sprite {
    // Class members
    private int radius;
    private Point center;
    private java.awt.Color color;
    private Velocity speed;
    private Boundary bound;
    private GameEnvironment gEnviornemnt;

    /**
     * Constructor of the class.
     * @param center
     *            - A point which is the center of the ball.
     * @param r
     *            - integer , value of the ball's radius.
     * @param color
     *            - The color of the ball.
     *  @param g - The game Environment of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment g) {
        this.radius = r;
        this.center = center;
        this.color = color;
        this.speed = new Velocity(0, 0);
        this.gEnviornemnt = g;
    }
    /**
     * Constructor of the class.
     * @param x
     *            - integer , the x coordinate of the ball's center.
     * @param y
     *            - integer , the y coordinate of the ball's center.
     * @param r
     *            -integer , the ball's radius.
     * @param color
     *            - The color of the ball.
     * @param g - The game Environment of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment g) {
        this(new Point(x, y), r, color, g);
    }

    /**
     * The method returns the x coordinate of the ball's center.
     * @return the x coordinate of the ball's center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * The method returns the y coordinate of the ball's center.
     * @return the y coordinate of the ball's center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * The method returns the radius of the ball .
     * @return the radius coordinate of the ball.
     */
    public int getSize() {
        return (int) this.radius;
    }
    /**
     * The method returns the boundary of the ball's movement.
     * @return boundary of the ball's movement.
     */
    public Boundary getBound() {
        return this.bound;
    }

    /**
     * The method returns the color of the ball.
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * The method sets a new velocity for the ball.
     * @param v
     *            -The new velocity we need to set.
     */
    public void setVelocity(Velocity v) {
        this.speed = v;
    }

    /**
     * The method sets a new boundary for the ball.
     * @param boundary
     *            -The new boundary we need to set.
     */
    public void setBoundary(Boundary boundary) {
        this.bound = boundary;
    }

    /**
     * The method sets a new velocity for the ball.
     * @param dx
     *            - a parameter for the velocity object.
     * @param dy
     *            - a parameter for the velocity object.
     */
    public void setVelocity(double dx, double dy) {
        this.setVelocity(new Velocity(dx, dy));
    }

    /**
     * The method returns the velocity of the ball.
     * @return Velocity - The ball's Velocity object.
     */
    public Velocity getVelocity() {
        return this.speed;
    }

    /**
     * The method moves the ball by "one step" by the following algorithm :
     * if the ball next move is extending over the boundary ,
     * change his direction in 180 degrees or by the reflected angle.
     */
    public void moveOneStep(double dt) {
        //Calculates the end of the trajectory by applying the Velociy to the center
        Point endTrajectoryP = new Point(this.center.getX() + (this.speed.getDX() * dt),
                                                              this.center.getY() + (this.speed.getDY() * dt));
        // Calculates the same point as the endTrajectory, which we will process on this point.
        Point tempMovedCenterPoint = new Point(this.center.getX() + (this.speed.getDX() * dt),
                                                                    this.center.getY() + (this.speed.getDY() * dt));
        //Creates the Line using the center and the future position of the ball.
        Line trajectory = new Line(this.center, endTrajectoryP);
        //Checks for any collision withe the objects in the game.
        CollisionInfo collinformation = this.gEnviornemnt.getClosestCollision(trajectory);
        //Cheks if there is an intersection with one of the colliadables.
        if (collinformation == null) {
            // there is no intersection , now check if the ball will get out from the frame boundary.
            if (((tempMovedCenterPoint.getX() + this.radius) - this.bound.getRightBound() > 0)
                    || ((tempMovedCenterPoint.getX() - this.radius) < 0)) {
               //The ball is out of the frame in his x axis value,so change his x axis movement.
                this.setVelocity(-this.speed.getDX() * dt, this.speed.getDY() * dt);
            }
            //Checks if the ball next move will get out of the y axis boundary.
            if (((tempMovedCenterPoint.getY() - this.radius) < 0)
                    || ((tempMovedCenterPoint.getY() + this.radius) - this.bound.getUpperBound() > 0)) {
                // The ball exceeded from his height boundary so change his direction on the y axis.
                this.setVelocity(this.speed.getDX() * dt, -this.speed.getDY() * dt);
            }
            // Apply the new velocity to the center point.
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        } else {
            //There is a collision , now check if the ball intersection is right on the block.
            if (((int) collinformation.collisionPoint().getX() == (int) tempMovedCenterPoint.getX())
                    && ((int) collinformation.collisionPoint().getY() == (int) tempMovedCenterPoint.getY())) {
                //Get the new speed from the hit function and apply it to the center.
                this.speed = collinformation.collisionObject().hit(collinformation.collisionPoint(), this.speed, this);
                this.center = this.getVelocity().applyToPoint(this.center, dt);
        } else {
            // check is the ball end tracjectory point is inside the rectangle.
            if (collinformation.collisionObject().getCollisionRectangle().isBallInsideRect(tempMovedCenterPoint)) {
                //get the new velocity from the hit function
                this.speed = collinformation.collisionObject().hit(collinformation.collisionPoint(), this.speed, this);
                //Apply the new velocity for the ball.
                this.center = this.getVelocity().applyToPoint(this.center, dt);
            } else {
               /* Othermwise there is a collision exactly on the line segment of the rectangle.
                * so apply the new velocity given by the hit function
                */
                this.center = this.getVelocity().applyToPoint(this.center, dt);
            }
        }
      }
    }
    /**
     * The method draws the circle on the given surface.
     * @param surface
     *            -The surface we want to draw the circle on.
     */
    public void drawOn(DrawSurface surface) {
        // Setting the color of the ball to the surface.
        surface.setColor(this.getColor());
        // Draws the circle.
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }
    @Override
    /**
     * The method moves the ball one step further.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }
    /**
     * The method add the paddle to the game.
     * @param g -The game object.
     */
    public void addToGame(GameLevel g) {
       g.addSprite(this);
    }
}

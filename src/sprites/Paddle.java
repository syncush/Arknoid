package sprites;

import animation.GameLevel;
import biuoop.KeyboardSensor;
import geometry.Boundary;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import helpers.Velocity;

/**
 * The class represents a paddle of the arknoid game.
 * @author Daniel Hermon.
 */
public class Paddle implements Sprite, Collidable {
    // Class members
    private KeyboardSensor keyBoard;
    private Block theblock;
    private Boundary bound;
    private int speed;
    /**
     * Constructor for the class.
     * @param kb -The keyboard sensor object.
     * @param b -The block for the paddle.
     * @param velocity -The ball velocity.
     */
    public Paddle(KeyboardSensor kb, Block b, int velocity) {
        this.keyBoard = kb;
        this.theblock = b;
        this.speed = velocity;
    }
    /**
     * The method moves the paddle to the left.
     */
    public void moveLeft(double dt) {
        if (this.theblock.getBlock().getLeftSide().start().getX() - this.speed * dt > bound.getLeftBound()) {
            this.theblock.getBlock().addUpperLeftX((-this.speed - 1) * dt);
        }
    }
    /**
     * The method moves the paddle to the right.
     */
    public void moveRight(double dt) {
        if (this.theblock.getBlock().getRightSide().start().getX() + this.speed * dt <= bound.getRightBound()) {
            this.theblock.getBlock().addUpperLeftX(dt * this.speed);
        }
    }
    /**
     * The method sets boundaries for the paddle.
     * @param boundary - The boundary of the paddle.
     */
    public void setBoundary(Boundary boundary) {
        this.bound = boundary;
    }
    /**
     * The method returns the paddle the ball colliaded with.
     * @return The object the ball colliaded with.
     */
    public Rectangle getCollisionRectangle() {
        return this.theblock.getCollisionRectangle();
    }
    /**
     * The method calculates a new velocity after the impact of the ball in the paddle.
     * @param collisionPoint -The collision point of the ball with the paddle.
     * @param currentVelocity -The velocity of the ball before the impact.
     * @param hitter -The ball that hit the paddle.
     * @return the new velocity after hit.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        // Checks on which from the 5 parts of the paddle it hit.
        int numOfSide = whichNumOfSideHits(this.getCollisionRectangle().getUpperSide(), collisionPoint);
        //Calculates the speed vector.
        double paddleSpeed = Math.sqrt(Math.pow(currentVelocity.getDX(), 2) + Math.pow(currentVelocity.getDY(), 2));
        Velocity newVelocity = currentVelocity;
        //According to the instruction given by yoav each part has it's on angle which the ball will go.
        switch (numOfSide) {
            case 1:
                newVelocity = Velocity.fromAngleAndSpeed(300, paddleSpeed);
                break;
            case 2:
                newVelocity = Velocity.fromAngleAndSpeed(330, paddleSpeed);
                break;
            case 3:
                newVelocity = new Velocity(currentVelocity.getDX(), -currentVelocity.getDY());
                break;
            case 4:
                newVelocity = Velocity.fromAngleAndSpeed(-30, paddleSpeed);
                break;
            case 5:
                newVelocity = Velocity.fromAngleAndSpeed(-60, paddleSpeed);
                break;
            default:
                break;
        }
        //if it doesnt hit the upper part , check for intersection with the sides.
        if (numOfSide == -1) {
            /**
             * If the intersection point is on the side or inside the rectangle,
             * without coming from the upper side change his direction.
             */
            if (this.getCollisionRectangle().getLeftSide().isOnLineSegement(collisionPoint)
                    || this.getCollisionRectangle().getRightSide().isOnLineSegement(collisionPoint)
                    || this.getCollisionRectangle().isBallInsideRect(collisionPoint)) {
                return new Velocity(-currentVelocity.getDX(), currentVelocity.getDY());
            }
        }
        return newVelocity;
    }
    /**
     * The method draws the paddle on the screen.
     * @param d -The draw surface we will draw the ball on.
     */
    public void drawOn(DrawSurface d) {
        this.theblock.drawOn(d);
    }
    /**
     * The method returns on which parts out of the 5 equally parts of the upper side of the paddle
     * the ball hits.
     * @param lsegment -The upper side of the paddle.
     * @param pImpact -The collision point of the ball with the paddle.
     * @return The number of part the collision point is on the paddle.
     */
    public int whichNumOfSideHits(Line lsegment, Point pImpact) {
        //Calculate length of each part.
        double lengthOfEachPart = lsegment.start().distance(lsegment.end()) / 5;
        //Check on which side.
        for (int i = 1; i <= 5; i++) {
            Line temp = new Line(lsegment.start(), new Point((i * lengthOfEachPart) + lsegment.start().getX(),
                                                                                    lsegment.start().getY()));
            if (temp.isOnLineSegement(pImpact)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * The method checks for any left or right arrow keys pressed,
     * so the paddle will move.
     */
    public void timePassed(double dt) {
        if (this.keyBoard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        } else {
            if (this.keyBoard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
            }
        }
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
     * The method returns the upper left corner.
     * @return the upper left corner.
     */
    public Point getUpperLeft() {
        return this.theblock.getBlock().getUpperLeft();
    }
    /**
     * The method returns the width of the block.
     * @return the width of the block.
     */
    public double getWidth() {
        return this.theblock.getBlock().getWidth();
     }

}
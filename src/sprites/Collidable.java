package sprites;

import geometry.Point;
import geometry.Rectangle;
import helpers.Velocity;

/**
 * The interface represents a colliadable object.
 * @author Daniel Hermon
 */
public interface Collidable {
    /**
     * the method returns the object the ball clashed with.
     * @return the object the ball colliaded with.
     */
    Rectangle getCollisionRectangle();
    /**
     * The method returns a new velocity after the hit.
     * @param collisionPoint -The collision point.
     * @param currentVelocity -The velocity before the ball hit the object.
     * @param hitter -THe ball that hit the block.
     * @return the new velocity after the collision.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}

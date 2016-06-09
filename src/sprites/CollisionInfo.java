package sprites;

import geometry.Point;

/**
 * The class represents information about a collision.
 * @author Daniel Hermon
 */
public class CollisionInfo {
    // Class memebers.
    private Point collisionPoint;
    private Collidable colid;
    /**
     * Creates a new CollisionInfo object.
     * @param collisionPoint - The point of collision.
     * @param coli -The collision object the ball hit.
     */
    public CollisionInfo(Point collisionPoint, Collidable coli) {
        this.collisionPoint = collisionPoint;
        this.colid = coli;
    }
    /**
     * The method returns the collision point.
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * The method returns the collision point.
     * @return the object the ball hit.
     */
    public Collidable collisionObject() {
        return this.colid;
    }

}

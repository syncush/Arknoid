package helpers;

import sprites.Block;
import sprites.Collidable;
import sprites.CollisionInfo;
import sprites.Paddle;

import java.util.ArrayList;
import java.util.List;

import geometry.Line;
import geometry.Point;

/**
 * Class describes the game Environment of the ball.
 *
 * @author Daniel Hermon 4/4/16
 */
public class GameEnvironment {
    // Class members.
    private List<Collidable> collidableList;

    /**
     * Constructor for the class.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }

    /**
     * The method add a colliadable to the colliadable list.
     *
     * @param c -A colliadable we would like to add to the list.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * The method runs on all the colliadables and checks with which.
     * colliadable the ball has the closest collision
     *
     * @param trajectory -The movement of the balls represented in a line.
     * @return A collision info object with the collision information.
     * null for no collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //initalize variables.
        Point minDistCollision = null;
        Collidable minCollidCollision = null;
        Point tempDistCollision;
        //for every object check their intersection with the trajectory.
        for (Collidable item : this.collidableList) {
            if (item instanceof Block) {
                tempDistCollision = trajectory.closestIntersectionToStartOfLine(((Block) item).getCollisionRectangle());
                //checks if there is a collision
                if (tempDistCollision != null) {
                    //check their distance against the minimum distant
                    if (minDistCollision == null || trajectory.start().distance(tempDistCollision)
                        <= trajectory.start().distance(minDistCollision)) {
                        //We found a closer intersection , so update the minumum.
                        minDistCollision = tempDistCollision;
                        minCollidCollision = item;
                    }
                }
            }
            if (item instanceof Paddle) {
                tempDistCollision = trajectory.closestIntersectionToStartOfLine(
                                                    ((Paddle) item).getCollisionRectangle());
                //checks if there is a collision
                if (tempDistCollision != null) {
                    //checks if it is the first intersection or a "better" one meaning a closer point.
                    if (minDistCollision == null || trajectory.start().distance(tempDistCollision)
                        <= trajectory.start().distance(minDistCollision)) {
                        //update the new closer point.
                        minDistCollision = tempDistCollision;
                        minCollidCollision = item;
                    }
                }
            }
        }
        //if there are no collisions return null
        if (minDistCollision == null) {
            return null;
        } else {
            //return the collision.
            return new CollisionInfo(minDistCollision, minCollidCollision);
        }
    }
    /**
     * The method returns the colliadble list.
     * @return The data structure of our collection.
     */
    public List<Collidable> getColliadList() {
        return this.collidableList;
    }
}

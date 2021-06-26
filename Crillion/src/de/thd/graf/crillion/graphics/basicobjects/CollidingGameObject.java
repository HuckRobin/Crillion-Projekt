package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.gameview.GameView;

import java.util.ArrayList;

/**
 * An object that can actively collide with other objects, e.g. a shot.
 */
public abstract class CollidingGameObject extends BlockObject{
    protected final ArrayList<CollidableGameObject> objectsToCollideWith;

    protected CollidingGameObject(GameView gameView,
                                  ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView);
        this.objectsToCollideWith = objectsToCollideWith;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        super.update();
        checkCollisions();
    }

    /**
     * Used to check possible collisions that are actively caused by this game object.
     * Both parties are notified about the collision.
     */
    private void checkCollisions() {
        for (CollidableGameObject collidableGameObject : objectsToCollideWith) {
            if (collidesWith(collidableGameObject)) {
                reactToCollision(collidableGameObject);
                collidableGameObject.reactToCollision(this);
            }
        }
    }

    /**
     * Determines if this game object is collided with the other game object.
     *
     * @param other The other game object.
     * @return <code>true</code> if the there was a collision.
     */
    protected final boolean collidesWith(CollidableGameObject other) {
        return this.hitBox.intersects(other.hitBox);
    }

    /**
     * Get CollidingGameObjects
     * @return the objects to collide with
     */
    public ArrayList<CollidableGameObject> getObjectsToCollideWith() {
        return objectsToCollideWith;
    }
}

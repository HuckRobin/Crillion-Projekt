package de.thd.graf.crillion.graphics.basicobjects;
import de.thd.graf.crillion.gameview.GameView;
import java.awt.*;
import java.util.Objects;

/** Represents all game objects that are able to collide with something. */
public abstract class CollidableGameObject extends GameObject implements Cloneable {

    protected Rectangle hitBox;

    protected CollidableGameObject(GameView gameView) {
        super(gameView);
        this.hitBox = new Rectangle(-100_000, -100_000, 0, 0);
    }

    @Override
    public void update() {
        super.update();
        updateHitBoxPosition();
    }

    /**
     * Used to update the hitBox position of the game object.
     */
    protected abstract void updateHitBoxPosition();

    /**
     * If a GameObject has collided with something, it is able to react to the collision.
     *
     * @param otherObject The other GameObject that is involved in the collision.
     */
    public abstract void reactToCollision(CollidableGameObject otherObject);

    /**
     * Compares the objects hitboxes.
     * @param o object.
     * @return true or false if equal or not equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CollidableGameObject that = (CollidableGameObject) o;
        return Objects.equals(hitBox, that.hitBox);
    }

    /**
     * Creates hashcode.
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hitBox);
    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
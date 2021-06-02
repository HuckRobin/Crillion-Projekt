package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.gameview.GameView;

import java.awt.*;
import java.util.Objects;

/**
 * Superclass for the block objects
 */
public abstract class BlockObjects extends CollidableGameObject implements Cloneable {

    protected String blockImage;

    /**
     * Create a block
     *
     * @param gameView get important code from GameView
     */
    protected BlockObjects(GameView gameView) {
        super(gameView);
        this.size = 2;
        this.rotation = 0;
        this.width = 11 * (int) size;
        this.height = 11 * (int) size;
        this.hitBox.width = this.width;
        this.hitBox.height = this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y;
    }

    /**
     * Adds the hit box frame to the objects
     */
    protected void addHitBoxToCanvas(){
        gameView.addRectangleToCanvas(this.hitBox.x, this.hitBox.y, this.hitBox.width, this.hitBox.height, 1, false, Color.RED);
    }

    /**
     * Clones the object.
     * @return
     */
    @Override
    public BlockObjects clone() {
        return (BlockObjects) super.clone();
    }

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
        BlockObjects that = (BlockObjects) o;
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
}

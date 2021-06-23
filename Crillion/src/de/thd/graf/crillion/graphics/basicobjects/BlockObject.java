package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.gameview.GameView;

import java.awt.*;

/**
 * Superclass for the block objects
 */
public abstract class BlockObject extends CollidableGameObject implements Cloneable {

    protected String blockImage;

    /**
     * Create a block
     *
     * @param gameView get important code from GameView
     */
    protected BlockObject(GameView gameView) {
        super(gameView);
        this.size = 3;
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
}

package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.gameview.GameView;

import java.awt.*;

/**
 * Superclass for the block objects
 */
public abstract class BlockObjects extends CollidableGameObject {

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
        this.hitBox = new Rectangle((int) this.position.x, (int) this.position.y, this.width, this.height);
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

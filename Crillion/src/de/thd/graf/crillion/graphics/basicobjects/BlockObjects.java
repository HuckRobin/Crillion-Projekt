package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.gameview.GameView;

/**
 * Superclass for the block objects
 */
public abstract class BlockObjects extends GameObject {

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
        this.width = 10 * (int) size;
        this.height = 10 * (int) size;
    }
}

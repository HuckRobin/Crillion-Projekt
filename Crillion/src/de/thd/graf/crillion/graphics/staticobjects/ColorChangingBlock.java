package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

/**
 * Block which changes the Ball color.
 */
public class ColorChangingBlock extends GameObject {

    private String blockImage;

    /**
     * Create a ColorChangingBlock
     * @param gameView get important Code from GameView
     */
    public ColorChangingBlock(GameView gameView) {
        super(gameView);
        this.position = new Position(50, 100);
        this.color = null;
        this.blockImage = "WWWWWWWWWBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBWBBBBB" +
                "\nWBBBBWBBBBB" +
                "\nWBBBBBBBBBB" +
                "\nWBWWBWBWWBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBWBBBBB" +
                "\nWBBBBWBBBBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBBBBBBB";

        this.size = 2;
        this.rotation = 0;
        this.width = 10 * (int) size;
        this.height = 10 * (int) size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(this.blockImage, this.position.x, this.position.y, this.size, this.rotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
    }


    /**
     * Change the color of the Ball when it get hit by it.
     */
    private void changeColorOfTheBall() {
    }

    /**
     * @return the block image
     */
    public String getBlockImage() {
        return blockImage;
    }
}

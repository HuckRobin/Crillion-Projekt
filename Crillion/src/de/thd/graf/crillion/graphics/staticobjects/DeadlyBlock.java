package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

/**
 * Block which remove 1 Life when it get hit by the {@link Ball}
 */
public class DeadlyBlock extends GameObject {

    private String blockImage;

    /**
     * Creates a DeadlyBlock
     *
     * @param gameView
     */
    public DeadlyBlock(GameView gameView) {
        super(gameView);
        this.position = new Position(50, 250);
        this.color = null;
        this.blockImage = "WWWWWWWWWBB" +
                "\nWBBBBBBBBBB" +
                "\nWBWBBBBBBWB" +
                "\nWBBWBBBBWBB" +
                "\nWBBBWBBWBBB" +
                "\nWBBBBWWBBBB" +
                "\nWBBBBWWBBBB" +
                "\nWBBBWBBWBBB" +
                "\nWBBWBBBBWBB" +
                "\nWBWBBBBBBWB" +
                "\nWBBBBBBBBBB";

        this.size = 2;
        this.rotation = 0;
        this.width = 10 * (int) size;
        this.height = 10 * (int) size;
    }

    private void losesOneLifeWhenHit() {
    }

    private void kill() {
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
     * @return the block image
     */
    public String getBlockImage() {
        return blockImage;
    }

}

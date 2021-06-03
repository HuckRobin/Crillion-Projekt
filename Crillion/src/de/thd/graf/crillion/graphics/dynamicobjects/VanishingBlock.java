package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObjects;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;

import java.awt.*;
import java.util.Random;

/**
 * A block which vanish when it get hit by the {@link Ball}
 */
public class VanishingBlock extends BlockObjects {

    private Random random;

    /**
     * Create a VanishingBlock
     * @param gameView get important Code from GameView
     */
    public VanishingBlock(GameView gameView) {
        super(gameView);
        this.random = new Random();
        this.position = new Position(10 + 1 + random.nextInt(GameView.WIDTH - this.width - 10 - 10 - 1), 60 + 1 + random.nextInt(GameView.HEIGHT - 60 - this.height - 10 - 1));
        this.blockImage = "WWWWWWWWWBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBBBBBBB" +
                "\nWBBBBBBBBBB";

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateHitBoxPosition() {
        super.updateHitBoxPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        System.out.println("Hit_Vanishingblock");
        this.gamePlayManager.destroy(this);
    }

    private void vanishTheBlockWhenHit() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(this.blockImage, this.position.x, this.position.y, this.size, this.rotation);
        addHitBoxToCanvas();
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

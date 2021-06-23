package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

/**
 * Block which changes the Ball color.
 */
public class ColorChangingBlock extends BlockObject implements Cloneable {

    /**
     * Create a ColorChangingBlock
     * @param gameView get important Code from GameView
     */
    public ColorChangingBlock(GameView gameView) {
        super(gameView);
        this.position = new Position(50, 100);
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
        System.out.println("Hit_ColorChangingBlock");
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
}

package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.dynamicobjects.Ball;

/**
 * Block which remove 1 Life when it get hit by the {@link Ball}
 */
public class DeadlyBlock extends BlockObject {

    /**
     * Creates a DeadlyBlock
     *
     * @param gameView wWindow to show the GameObject on.
     */
    public DeadlyBlock(GameView gameView) {
        super(gameView);
        this.position = new Position(50, 250);
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
        //gamePlayManager.destroy(this);
        System.out.println("Hit_Deadlyblock");
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

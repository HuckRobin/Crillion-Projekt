package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.*;

/**
 * Block which moves when it get hit by the {@link Ball} with the same Color
 */
public class MovableBlock extends BlockObject implements MovingGameObject {

    private boolean flyFromRightToLeft;

    /**
     * Create a MoveableBlock
     *
     * @param gameView get important Code from GameView
     */
    public MovableBlock(GameView gameView) {
        super(gameView);
        this.position = new Position(50, 60 + 1);
        this.speedInPixel = 1;
        this.blockImage = "WWWWWWWWWBB\n" +
                "WBBBBBBBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBWWWBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBBBBBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBBBBBBBB\n";
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
     * @param otherObject The other GameObject that is involved in the collision.
     */
    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        System.out.println("Hit_MovableBlock");
        this.gamePlayManager.bounceBallBack(this);
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
     * Update the Position when the Block get hits by the ball
     */
    @Override
    public void updatePosition() {
        if (position.x == GameView.WIDTH - width - 10) {
            flyFromRightToLeft = false;
        } else if (position.x == 10) {
            flyFromRightToLeft = true;
        }

        if (flyFromRightToLeft) {
            position.right(this.speedInPixel);
        } else {
            position.left(this.speedInPixel);
        }
    }

}
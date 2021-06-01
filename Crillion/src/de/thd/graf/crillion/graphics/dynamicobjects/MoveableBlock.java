package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.*;
import de.thd.graf.crillion.graphics.staticobjects.Background;

/**
 * Block which moves when it get hit by the {@link Ball} with the same Color
 */
public class MoveableBlock extends BlockObjects implements MovingGameObject {

    private String blockImage;

    private boolean flyFromRightToLeft;

    /**
     * Create a MoveableBlock
     * @param gameView get important Code from GameView
     */
    public MoveableBlock(GameView gameView) {
        super(gameView);
        this.position = new Position(50, 60);
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
                "WBBBBBBBBBB\n" +
                "WBBBBBBBBBB\n";
    }

    @Override
    protected void updateHitBoxPosition() {

    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

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

    private void move() {
    }

    /**
     * Update the Position when the Block get hits by the ball
     */
    @Override
    public void updatePosition() {
        if (position.x == GameView.WIDTH - width - 10) {
            flyFromRightToLeft = false;
        } else if (position.x == GameView.WIDTH - GameView.WIDTH + 10) {
            flyFromRightToLeft = true;
        }

        if (flyFromRightToLeft == true) {
            position.right(this.speedInPixel);
        } else {
            position.left(this.speedInPixel);
        }

    }

    /**
     * @return the block image.
     */
    public String getBlockImage() {
        return blockImage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "MoveableBlock: " + position;
    }
}
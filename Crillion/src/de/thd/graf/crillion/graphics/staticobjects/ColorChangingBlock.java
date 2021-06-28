package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.awt.*;

/**
 * Block which changes the Ball color.
 */
public class ColorChangingBlock extends BlockObject implements Cloneable {

    public enum StatusColor{RED, BLUE, GREEN, YELLOW, PURPLE}
    private StatusColor statusColor;

    /**
     * Create a ColorChangingBlock
     * @param gameView get important Code from GameView
     */
    public ColorChangingBlock(GameView gameView, StatusColor statusColor) {
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
        switch (statusColor) {
            case BLUE:
                this.blockImage = this.blockImage;
                break;
            case RED:
                this.blockImage = this.blockImage.replace('B','R');
                break;
            case GREEN:
                this.blockImage = this.blockImage.replace('B','G');
                break;
            case PURPLE:
                this.blockImage = this.blockImage.replace('B', 'P');
                break;
            case YELLOW:
                this.blockImage = this.blockImage.replace('B', 'Y');
                break;
        }
        this.statusColor = statusColor;


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
        if(this.gamePlayManager.getGameObjectManager().getBall().getHitBox().intersects(hitBox)){
            this.gamePlayManager.bounceBallBack(this);
            this.gamePlayManager.changeBallColor(this);
        }
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
     * Get the Color of the Block
     * @return
     */
    public String getStatusColor() {
        return String.valueOf(statusColor);
    }
}

package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.dynamicobjects.Ball;


/**
 * A block which vanish when it get hit by the {@link Ball}
 */
public class VanishingBlock extends BlockObject {
    public enum StatusColor{RED, BLUE, GREEN, YELLOW, PURPLE}
    private StatusColor statusColor;

    private boolean createExplosion;
    private boolean timerSet;

    /**
     * Create a VanishingBlock
     * @param gameView get important Code from GameView
     */
    public VanishingBlock(GameView gameView, StatusColor statusColor) {
        super(gameView);
        this.position = new Position(0,0);
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
        this.createExplosion = false;
        this.timerSet = false;
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
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        if(this.gamePlayManager.getGameObjectManager().getBall().getHitBox().intersects(hitBox)) {
            this.gamePlayManager.bounceBallBack(this);
            this.gamePlayManager.destroyVanishingBlock(this);
            System.out.println("Hit_Vanishingblock");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        if(createExplosion) {
            this.gameView.addImageToCanvas("Explosion64.png", this.position.x, this.position.y, 0.75, 0);
            if(time()){
                this.gamePlayManager.getGameObjectManager().getBlockObjects().remove(this);
            }
        }
        else {
            gameView.addBlockImageToCanvas(this.blockImage, this.position.x, this.position.y, this.size, this.rotation);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
    }

    public void setCreateExplosion(boolean createExplosion) {
        this.createExplosion = createExplosion;
    }

    /**
     * Check the if the time is Expired
     * @return boolean
     */
    public boolean time() {
        if(!timerSet) {
            this.gameView.setTimer("blockExplosion", "VanishingBlock", 200);
            timerSet = true;
            return false;
        } else {
            return this.gameView.timerExpired("blockExplosion", "VanishingBlock");
        }
    }

    /**
     * Get the color of the block
     * @return
     */
    public String getStatusColor() {
        return String.valueOf(statusColor);
    }
}

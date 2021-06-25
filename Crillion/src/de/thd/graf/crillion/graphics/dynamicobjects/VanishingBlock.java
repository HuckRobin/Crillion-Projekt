package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;


/**
 * A block which vanish when it get hit by the {@link Ball}
 */
public class VanishingBlock extends BlockObject {

    private boolean createExplosion;

    /**
     * Create a VanishingBlock
     * @param gameView get important Code from GameView
     */
    public VanishingBlock(GameView gameView) {
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
        this.gamePlayManager.bounceBallBack(this);
        this.gamePlayManager.destroyVanishingBlock(this);
        addPointsToScore();
        System.out.println("Hit_Vanishingblock");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        if(createExplosion) {
            if (gameView.timerExpired("blockExplosion", "VanishingBlock")) {
                gameView.setTimer("blockExplosion", "VanishingBLock", 500);
                this.gameView.addImageToCanvas("Explosion.png", this.position.x, this.position.y, 1, 0);
            }
        }
        else {
            gameView.addBlockImageToCanvas(this.blockImage, this.position.x, this.position.y, this.size, this.rotation);
            addHitBoxToCanvas();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
    }

    private void addPointsToScore(){
        this.gamePlayManager.getPlayer().score += 100;
    }

    public void setCreateExplosion(boolean createExplosion) {
        this.createExplosion = createExplosion;
    }
}

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

    private boolean createExplosion;
    private boolean timerSet;

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
        this.timerSet = false;

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
            addPointsToScore();
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
}

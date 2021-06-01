package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.MovingGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

/**
 * Animation of a Block when it vanished
 */
public class BlockExplosion extends GameObject implements MovingGameObject {

    private String image;


    /**
     * Create a block explosion
     *
     * @param gameView get important Code from GameView
     */
    public BlockExplosion(GameView gameView) {
        super(gameView);
        this.position = new Position(100, 100);
        this.image = "Explosion64.png";

        this.size = 0.5;
        this.rotation = 0;
        this.width = 10 * (int) size;
        this.height = 10 * (int) size;
        this.speedInPixel = 1;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(this.image, this.position.x, this.position.y, this.size, this.rotation);
    }

    @Override
    public void updateStatus() {
        destroyShotIfItHasLeftTheScreen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePosition() {
        position.down(speedInPixel);
    }

    /**
     * @return the block image
     */
    public String getImage() {
        return image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BlockExplosion: " + position;
    }

    private void destroyShotIfItHasLeftTheScreen() {
        if (this.position.y > GameView.HEIGHT - this.height - 30) {
            gamePlayManager.destroy(this);
        }
    }
}

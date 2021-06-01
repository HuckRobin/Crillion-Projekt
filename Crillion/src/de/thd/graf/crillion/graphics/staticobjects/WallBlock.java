package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObjects;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

/**
 * A block which is fixed
 */
public class WallBlock extends BlockObjects {


    /**
     * Create a WallBlock
     * @param gameView get important Code from GameView
     */
    public WallBlock(GameView gameView) {
        super(gameView);
        this.position = new Position(50, 150);
        this.blockImage = "WWWWWWWLWWW\n"
                + "GGGGGGGLGGG\n"
                + "LLLLLLLLLLL\n"
                + "WWWLWWWWLWW\n"
                + "GGGLGGGGLGG\n"
                + "LLLLLLLLLLL\n"
                + "WWWWWLWWWWW\n"
                + "GGGGGLGGGGG\n"
                + "LLLLLLLLLLL\n"
                + "WWWLWWWLWWW\n"
                + "GGGLGGGLGGG\n";
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

    private void changeImage() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FixedBlock: " + position;
    }
}

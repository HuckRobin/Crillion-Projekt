package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObjects;
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
        this.blockImage = "WWWWWWWSWWW\n"
                + "GGGGGGGSGGG\n"
                + "SSSSSSSSSSS\n"
                + "WWWSWWWWSWW\n"
                + "GGGSGGGGSGG\n"
                + "SSSSSSSSSSS\n"
                + "WWWWWSWWWWW\n"
                + "GGGGGSGGGGG\n"
                + "SSSSSSSSSSS\n"
                + "WWWSWWWSWWW\n"
                + "GGGSGGGSGGG\n";
        this.size = 2;
        this.rotation = 0;
        this.width = 10 * (int) size;
        this.height = 10 * (int) size;
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

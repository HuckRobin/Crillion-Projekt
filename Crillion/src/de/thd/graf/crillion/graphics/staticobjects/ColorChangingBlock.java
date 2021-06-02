package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObjects;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.util.Objects;

/**
 * Block which changes the Ball color.
 */
public class ColorChangingBlock extends BlockObjects implements Cloneable {

    private String blockImage;

    /**
     * Create a ColorChangingBlock
     * @param gameView get important Code from GameView
     */
    public ColorChangingBlock(GameView gameView) {
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
        System.out.println("Hit_ColorChangingBlock");
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
     * Change the color of the Ball when it get hit by it.
     */
    private void changeColorOfTheBall() {
    }

    /**
     * @return the block image
     */
    public String getBlockImage() {
        return blockImage;
    }

    /**
     * Clones the colorChangingBlock.
     * @return clone.
     */
    @Override
    public ColorChangingBlock clone(){
        return (ColorChangingBlock) super.clone();
    }

    /**
     *Compares color of color-changing-blocks.
     * @param o object.
     * @return true of false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColorChangingBlock colorBlock = (ColorChangingBlock) o;
        return color == colorBlock.color;
    }

    /**
     * Creates hashcode for the color-changing-block.
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }
}

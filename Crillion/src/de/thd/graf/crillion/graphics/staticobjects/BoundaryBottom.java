package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BoundaryObjects;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.awt.*;

/**
 * Bottom limitation of the playing field
 */
public class BoundaryBottom extends BoundaryObjects {

    /**
     * Creat the boundary
     * @param gameView get the gameview
     */
    public BoundaryBottom(GameView gameView){
        super(gameView);
        this.position = new Position(0 ,GameView.HEIGHT - 10);
        this.width = GameView.WIDTH;
        this.height = 10;

        this.hitBox.width = this.width;
        this.hitBox.height = this.height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHitBoxPosition(){
        super.updateHitBoxPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(this.position.x, this.position.y, this.width, this.height, this.lineWeight, this.filled, this.color);
        gameView.addRectangleToCanvas(this.hitBox.x, this.hitBox.y, this.hitBox.width, this.hitBox.height, 1, false, Color.RED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateStatus() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        System.out.println("Hit_Bottom");
    }
}
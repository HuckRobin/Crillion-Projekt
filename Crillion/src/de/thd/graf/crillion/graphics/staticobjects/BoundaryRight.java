package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BoundaryObjects;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.awt.*;

/**
 * Playing field limitation for the right side
 */
public class BoundaryRight extends BoundaryObjects {

    /**
     * Create the limitation for the right
     * @param gameView Get gameview
     */
    public BoundaryRight(GameView gameView){
        super(gameView);
        this.position = new Position(GameView.WIDTH - 10 ,50);
        this.width = 10;
        this.height = GameView.HEIGHT;

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
        System.out.println("Hit_Right");
    }
}

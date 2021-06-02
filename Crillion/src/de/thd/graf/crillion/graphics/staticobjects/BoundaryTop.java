package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BoundaryObjects;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

public class BoundaryTop extends BoundaryObjects {

    public BoundaryTop(GameView gameView) {
        super(gameView);
        this.position = new Position(0, 50);
        this.width = GameView.WIDTH;
        this.height = 10;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHitBoxPosition() {
        super.updateHitBoxPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(this.position.x, this.position.y, this.width, this.height, this.lineWeight, this.filled, this.color);
        addHitBoxToCanvas();
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

    }
}

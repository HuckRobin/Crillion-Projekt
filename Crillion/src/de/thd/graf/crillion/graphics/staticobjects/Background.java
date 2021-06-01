package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.awt.*;

/**
 * Background and boundary for the game
 */
public class Background extends CollidableGameObject {

    private double lineWeight;
    private final BoundaryTop boundaryTop;
    private final BoundaryLeft boundaryLeft;
    private final BoundaryBottom boundaryBottom;
    private final BoundaryRight boundaryRight;

    /**
     * Creat the background with the boundary
     */
    public Background(GameView gameView) {
        super(gameView);
        this.position = new Position(0,50);
        this.color = new Color(7, 85, 8);
        this.size = 1;
        this.width = GameView.WIDTH * (int)size;
        this.height = (GameView.HEIGHT - 50) * (int)size;
        this.lineWeight = 1;

        this.boundaryTop = new BoundaryTop(gameView);
        this.boundaryLeft = new BoundaryLeft(gameView);
        this.boundaryBottom = new BoundaryBottom(gameView);
        this.boundaryRight = new BoundaryRight(gameView);
    }

    @Override
    protected void updateHitBoxPosition() {
        this.boundaryTop.updateHitBoxPosition();
        this.boundaryLeft.updateHitBoxPosition();
        this.boundaryBottom.updateHitBoxPosition();
        this.boundaryRight.updateHitBoxPosition();
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas(){
        this.gameView.addRectangleToCanvas(this.position.x, this.position.y,this.width, this.height,this.lineWeight,
                true, this.color);
        this.boundaryTop.addToCanvas();
        this.boundaryLeft.addToCanvas();
        this.boundaryBottom.addToCanvas();
        this.boundaryRight.addToCanvas();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
    }
}

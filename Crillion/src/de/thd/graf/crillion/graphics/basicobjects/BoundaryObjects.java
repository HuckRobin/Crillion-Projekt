package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.gameview.GameView;

import java.awt.*;

/**
 * Upper class for the playing field limitation
 */
public abstract class BoundaryObjects extends CollidableGameObject {

    protected Rectangle frame;
    protected int lineWeight;
    protected boolean filled;

    /**
     * Creat the frame for the limitation
     * @param gameView get the gamneview
     */
    public BoundaryObjects (GameView gameView){
        super(gameView);
        this.lineWeight = 1;
        this.filled = true;
        this.color = Color.WHITE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected  void updateHitBoxPosition(){
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y;
    }

    /**
     * Adds the hit box frame to the objects
     */
    protected void addHitBoxToCanvas(){
    }
}

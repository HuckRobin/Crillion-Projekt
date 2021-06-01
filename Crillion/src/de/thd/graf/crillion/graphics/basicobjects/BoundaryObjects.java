package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.gameview.GameView;

import java.awt.*;

public abstract class BoundaryObjects extends CollidableGameObject {

    protected Rectangle frame;
    protected int lineWeight;
    protected boolean filled;

    public BoundaryObjects (GameView gameView){
        super(gameView);
        this.frame = new Rectangle((int) this.position.x,(int) this.position.y,this.width,this.height);
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
        this.hitBox.width = this.width;
        this.hitBox.height = this.height;
    }

    /**
     * Adds the hit box frame to the objects
     */
    protected void addHitBoxToCanvas(){
        gameView.addRectangleToCanvas(this.hitBox.x, this.hitBox.y, this.hitBox.width, this.hitBox.height, 1, false, Color.RED);
    }
}

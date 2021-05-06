package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

/**
 * Boundary for the map, where the ball bounces from
 */
public class Boundary extends GameObject {

    /**
     * Create a boundary
     * @param gameView Get Gameview
     */
    public Boundary (GameView gameView){
        super(gameView);
        this.position = new Position(0,0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {

    }

    // /**
   //  * {@inheritDoc}
   //  */
   // @Override
   // public void addToCanvas() {
   //     this.gameView.addLineToCanvas();
   // }
}

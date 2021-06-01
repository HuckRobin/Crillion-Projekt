package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.awt.*;

/**
 * Background and boundary for the game
 */
public class Background extends GameObject {

    private double lineWeight;

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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas(){
        this.gameView.addRectangleToCanvas(this.position.x, this.position.y,this.width, this.height,this.lineWeight,
                true, this.color);
        this.addBoundary();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
    }

    private void addBoundary(){
        //Top
        gameView.addRectangleToCanvas(0, 50,GameView.WIDTH,10,1,true, Color.red);
        //Left
        gameView.addRectangleToCanvas(0,50,10,GameView.HEIGHT,1,true,Color.red);
        //Bottom
        gameView.addRectangleToCanvas(0,GameView.HEIGHT - 10,GameView.WIDTH,10,1,true,Color.red);
        //Right
        gameView.addRectangleToCanvas(GameView.WIDTH - 10,50,10,GameView.HEIGHT,1,true,Color.red);
    }
}

package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.game.managers.GamePlayManager;
import de.thd.graf.crillion.gameview.GameView;

import java.awt.*;

/**
 * Super class for the game objects
 */
public abstract class GameObject {

    protected GameView gameView;
    protected Position position;
    protected Color color;
    protected double size;
    protected double speedInPixel;
    protected double rotation;
    protected int width;
    protected int height;
    protected GamePlayManager gamePlayManager;

    /**
     * Create a "game object"
     *
     * @param gameView get important code from GameView
     */
    protected GameObject(GameView gameView) {
        this.gameView = gameView;
        this.position = new Position(0, 0);
    }

    /**
     * Add the object or a image to the canvas
     */
    public abstract void addToCanvas();

    /**
     * Return the {@link Position} of the Object
     *
     * @return Position(x, y)
     * @see Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set Gameplay Manager
     * @param gamePlayManager Gameplay Manager
     */
    public void setGamePlayManager(GamePlayManager gamePlayManager) {
        this.gamePlayManager = gamePlayManager;
    }

    /**
     * Change the status of the object
     */
    protected abstract void updateStatus();

    /**
     * Update the game Objects. Update Position and status
     */
    public void update(){
        if (this instanceof MovingGameObject) {
            ((MovingGameObject)this).updatePosition();
        }
        updateStatus();
    }
}

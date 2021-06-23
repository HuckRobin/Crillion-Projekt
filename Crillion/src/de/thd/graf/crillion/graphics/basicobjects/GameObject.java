package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.game.managers.GamePlayManager;
import de.thd.graf.crillion.gameview.GameView;

import java.awt.*;
import java.util.Objects;

/**
 * Super class for the game objects
 */
public abstract class GameObject implements Cloneable {

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


    @Override
    public GameObject clone() {
        GameObject gameObject = null;
        try {
            gameObject = (GameObject) super.clone();
            gameObject.position = position.clone();
        } catch (CloneNotSupportedException ignored) {
        }
        return gameObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return Double.compare(that.speedInPixel, speedInPixel) == 0
                && Double.compare(that.rotation, rotation) == 0
                && Double.compare(that.size, size) == 0 && width == that.width
                && height == that.height && position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, speedInPixel, rotation, size, width, height);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + position;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

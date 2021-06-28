package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.dynamicobjects.Ball;
import de.thd.graf.crillion.graphics.scoreobjects.Scoreboard;
import de.thd.graf.crillion.graphics.staticobjects.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class manages the objects
 */
public class GameObjectManager {

    private final Background background;
    private final Ball ball;
    private final BoundaryTop boundaryTop;
    private final BoundaryLeft boundaryLeft;
    private final BoundaryBottom boundaryBottom;
    private final BoundaryRight boundaryRight;
    private final Scoreboard scoreboard;
    private final Overlay overlay;

    private final LinkedList<BlockObject> blockObjects;
    private final LinkedList<GameObject> gameObjects;

    private final ArrayList<CollidableGameObject> collidableGameObjectsBall;
    private final ArrayList<CollidableGameObject> collidableGameObjectsMovableBlock;


    GameObjectManager(GameView gameView) {
        this.background = new Background(gameView);
        this.scoreboard = new Scoreboard(gameView);
        this.boundaryTop = new BoundaryTop(gameView);
        this.boundaryLeft = new BoundaryLeft(gameView);
        this.boundaryBottom = new BoundaryBottom(gameView);
        this.boundaryRight = new BoundaryRight(gameView);
        this.overlay = new Overlay(gameView);


        this.blockObjects = new LinkedList<>();
        this.gameObjects = new LinkedList<>();

        this.collidableGameObjectsMovableBlock = new ArrayList<>();
        this.collidableGameObjectsMovableBlock.addAll(List.of(boundaryTop, boundaryLeft, boundaryBottom, boundaryRight));

        this.collidableGameObjectsBall = new ArrayList<>();
        this.collidableGameObjectsBall.addAll(List.of(boundaryTop, boundaryLeft, boundaryBottom, boundaryRight));
        this.collidableGameObjectsBall.addAll(blockObjects);

        this.ball = new Ball(gameView, Ball.StatusColor.RED, collidableGameObjectsBall);
    }

    /**
     * Add and Update the Position from the GameObjects at the Canvas
     */
    public void updateGameObjects() {

        gameObjects.clear();
        gameObjects.add(background);
        gameObjects.add(scoreboard);
        gameObjects.addAll(List.of(boundaryBottom, boundaryTop, boundaryRight, boundaryLeft));
        gameObjects.addAll(blockObjects);
        gameObjects.add(ball);
        gameObjects.add(overlay);

        for (GameObject gameObject : gameObjects) {
            gameObject.update();
            gameObject.addToCanvas();
        }
    }

    /**
     * Get the Player Object
     *
     * @return The Playerobject
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * Get the blockObjects
     *
     * @return return the block objects
     */
    public LinkedList<BlockObject> getBlockObjects() {
        return blockObjects;
    }

    /**
     * Get the scoreboard
     *
     * @return scoreboard
     */
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    /**
     * Get the Gameobjcts
     *
     * @return List of gameobjects
     */
    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Get the collidable game objcts for the ball
     *
     * @return collidable game objects
     */
    public ArrayList<CollidableGameObject> getCollidableGameObjectsBall() {
        return collidableGameObjectsBall;
    }

    /**
     * Get the collidable game objects for the moveable block
     *
     * @return List ofgame objects which can collide with movable block
     */
    public ArrayList<CollidableGameObject> getCollidableGameObjectsMovableBlock() {
        return collidableGameObjectsMovableBlock;
    }

    /**
     * Get the overlay
     *
     * @return the overlay
     */
    public Overlay getOverlay() {
        return overlay;
    }

    BoundaryTop getBoundaryTop() {
        return boundaryTop;
    }

    BoundaryLeft getBoundaryLeft() {
        return boundaryLeft;
    }

    BoundaryBottom getBoundaryBottom() {
        return boundaryBottom;
    }

    BoundaryRight getBoundaryRight() {
        return boundaryRight;
    }
}

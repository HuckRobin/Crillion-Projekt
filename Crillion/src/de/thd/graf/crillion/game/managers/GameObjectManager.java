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

class GameObjectManager {

    private final Background background;
    private final Ball ball;
    private final BoundaryTop boundaryTop;
    private final BoundaryLeft boundaryLeft;
    private final BoundaryBottom boundaryBottom;
    private final BoundaryRight boundaryRight;
    private final Scoreboard scoreboard;

    private final LinkedList<BlockObject> blockObjects;

    LinkedList<GameObject> gameObjects;
    ArrayList<CollidableGameObject> collidableGameObjects;


    GameObjectManager(GameView gameView) {
        this.background = new Background(gameView);
        this.scoreboard = new Scoreboard(gameView);
        this.boundaryTop = new BoundaryTop(gameView);
        this.boundaryLeft = new BoundaryLeft(gameView);
        this.boundaryBottom = new BoundaryBottom(gameView);
        this.boundaryRight = new BoundaryRight(gameView);


        this.blockObjects = new LinkedList<>();

        this.gameObjects = new LinkedList<>();

        this.collidableGameObjects = new ArrayList<>();
        this.collidableGameObjects.addAll(List.of(boundaryTop, boundaryLeft, boundaryBottom, boundaryRight));
        this.collidableGameObjects.addAll(blockObjects);

        this.ball = new Ball(gameView, collidableGameObjects);
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
     * @return
     */
    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}

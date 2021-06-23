package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.dynamicobjects.Ball;
import de.thd.graf.crillion.graphics.dynamicobjects.VanishingBlock;
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

    private final LinkedList<Scoreboard> scoreboards;
    private final LinkedList<BlockObject> blockObjects;

    LinkedList<GameObject> gameObjects;
    ArrayList<CollidableGameObject> collidableGameObjects;


    GameObjectManager(GameView gameView) {
        this.background = new Background(gameView);
        Scoreboard scoreboard = new Scoreboard(gameView);
        this.boundaryTop = new BoundaryTop(gameView);
        this.boundaryLeft = new BoundaryLeft(gameView);
        this.boundaryBottom = new BoundaryBottom(gameView);
        this.boundaryRight = new BoundaryRight(gameView);


        LinkedList<VanishingBlock> vanishingBlocks = new LinkedList<>();
        this.blockObjects = new LinkedList<>();
        this.blockObjects.addAll(vanishingBlocks);

        this.gameObjects = new LinkedList<>();
        this.scoreboards = new LinkedList<>(List.of(scoreboard));

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
        gameObjects.addAll(scoreboards);
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
}

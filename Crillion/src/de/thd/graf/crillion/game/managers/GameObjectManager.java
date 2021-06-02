package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.dynamicobjects.Ball;
import de.thd.graf.crillion.graphics.dynamicobjects.MoveableBlock;
import de.thd.graf.crillion.graphics.dynamicobjects.VanishingBlock;
import de.thd.graf.crillion.graphics.staticobjects.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class manages the objects
 */

class GameObjectManager {

    private final GameView gameView;

    private final Background background;
    private final Ball ball;
    private final ColorChangingBlock colorChangingBlock;
    private final DeadlyBlock deadlyBlock;
    private final MoveableBlock moveableBlock;
    private final WallBlock wallBlock;
    private final Scoreboard scoreboard;
   private final BoundaryTop boundaryTop;
   private final BoundaryLeft boundaryLeft;
   private final BoundaryBottom boundaryBottom;
   private final BoundaryRight boundaryRight;

    private final LinkedList<VanishingBlock> vanishingBlocks;
    private final LinkedList<GameObject> gameObjects;
    private final LinkedList<Scoreboard> scoreboards;

    ArrayList<CollidableGameObject> collidableGameObjects;

    /**
     * Create the GameObjectManager
     *
     * @param gameView gameView get important Code from GameView
     */
    public GameObjectManager(GameView gameView) {
        this.gameView = gameView;
        this.background = new Background(gameView);
        this.colorChangingBlock = new ColorChangingBlock(gameView);
        this.deadlyBlock = new DeadlyBlock(gameView);
        this.moveableBlock = new MoveableBlock(gameView);
        this.wallBlock = new WallBlock(gameView);
        this.scoreboard = new Scoreboard(gameView);
        this.boundaryTop = new BoundaryTop(gameView);
        this.boundaryLeft = new BoundaryLeft(gameView);
        this.boundaryBottom = new BoundaryBottom(gameView);
        this.boundaryRight = new BoundaryRight(gameView);


        this.vanishingBlocks = new LinkedList<>();

        this.gameObjects = new LinkedList<>();
        this.scoreboards = new LinkedList<>(List.of(scoreboard));
        this.collidableGameObjects = new ArrayList<>();


        this.collidableGameObjects.addAll(List.of(boundaryTop, boundaryLeft, boundaryBottom,boundaryRight, colorChangingBlock, deadlyBlock, moveableBlock, wallBlock));
        this.ball = new Ball(gameView, collidableGameObjects);
    }

    /**
     * Add and Update the Position from the GameObjects at the Canvas
     */
    public void updateGameObjects() {

        gameObjects.clear();
        gameObjects.add(background);
        gameObjects.addAll(scoreboards);
        gameObjects.addAll(vanishingBlocks);
        gameObjects.addAll(List.of(boundaryBottom, boundaryTop, boundaryRight, boundaryLeft));
        gameObjects.addAll(List.of(colorChangingBlock, deadlyBlock, moveableBlock, wallBlock, ball));

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
     * Return the Linked List vanishingBlocks
     *
     * @return LinkedList<VanishingBlock>
     */
    public LinkedList<VanishingBlock> getVanishingBlocks() {
        return vanishingBlocks;
    }

    /**
     * Return the Linked List of scoreboard
     *
     * @return LinkedList<Scoreboard>
     */
    public LinkedList<Scoreboard> getScoreboards() {
        return scoreboards;
    }

    /**
     * Return the Linked List of gameobject
     *
     * @return LinkedList<GameObject>
     */
    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Get the ColliadbleGameObjects
     * @return
     */
    public ArrayList<CollidableGameObject> getCollidableGameObjects() {
        return collidableGameObjects;
    }
}

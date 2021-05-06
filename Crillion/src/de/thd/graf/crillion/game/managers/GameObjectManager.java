package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObjects;
import de.thd.graf.crillion.graphics.basicobjects.MovingGameObject;
import de.thd.graf.crillion.graphics.dynamicobjects.BlockExplosion;
import de.thd.graf.crillion.graphics.staticobjects.Background;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.staticobjects.*;
//import de.thd.graf.crillion.graphics.staticObjects.Score;
import de.thd.graf.crillion.graphics.dynamicobjects.VanishingBlock;

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

    private final LinkedList<VanishingBlock> vanishingBlocks;
    private final LinkedList<GameObject> gameObjects;
    private final LinkedList<Scoreboard> scoreboards;
    private final LinkedList<BlockExplosion> blockExplosions;
    //private final Score score;

    /**
     * Create the GameObjectManager
     *
     * @param gameView gameView get important Code from GameView
     */
    public GameObjectManager(GameView gameView) {
        this.gameView = gameView;
        this.ball = new Ball(gameView);
        this.background = new Background(gameView);
        this.colorChangingBlock = new ColorChangingBlock(gameView);
        this.deadlyBlock = new DeadlyBlock(gameView);
        this.moveableBlock = new MoveableBlock(gameView);
        this.wallBlock = new WallBlock(gameView);

        this.vanishingBlocks = new LinkedList<>();
        this.blockExplosions = new LinkedList<>();

        this.gameObjects = new LinkedList<>();
        this.scoreboards = new LinkedList<>();
    }

    /**
     * Add and Update the Position from the GameObjects at the Canvas
     */
    public void updateGameObjects() {

        gameObjects.clear();
        gameObjects.add(background);
        gameObjects.addAll(vanishingBlocks);
        gameObjects.addAll(List.of(colorChangingBlock, deadlyBlock, moveableBlock, wallBlock, ball));
        gameObjects.addAll(blockExplosions);

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
     * Return the Linked List blockExplosions
     *
     * @return LinkedList<BlockExplosion>
     */
    public LinkedList<BlockExplosion> getBlockExplosions() {
        return blockExplosions;
    }
}

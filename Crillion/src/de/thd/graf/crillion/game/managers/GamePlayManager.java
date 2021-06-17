package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.game.utilities.Level;
import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObjects;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.dynamicobjects.VanishingBlock;
import de.thd.graf.crillion.graphics.staticobjects.DeadlyBlock;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Gameplay Manager controlls the actions in the game
 */
public class GamePlayManager {

    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private final Random random;
    private boolean listHasBeenDeleted;
    private final Level levelEins;
    private boolean nextLevel;
    ArrayList<GameObject> deletObjects;

    /**
     * Create the gameplay manager
     *
     * @param gameView
     * @param gameObjectManager
     */
    public GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {

        this.gameView = gameView;
        this.gameObjectManager = gameObjectManager;
        this.random = new Random();
        this.listHasBeenDeleted = false;
        this.levelEins = new Level(1);
        this.nextLevel = true;
        this.deletObjects = new ArrayList<>();
    }

    /**
     * Updates the action of the game, new objects spawn
     */
    public void updateGamePlay() {
        if (nextLevel) {
            createLevel();
        }
        this.gameObjectManager.collidableGameObjects.removeAll(deletObjects);
        //spawnAndDestroyVanishingblock();
    }

    /**
     * Spawn and destroys the vanishing block after a certain time
     */
    public void spawnAndDestroyVanishingblock() {
        LinkedList<VanishingBlock> vanishingBlocks = gameObjectManager.getVanishingBlocks();

        if (gameView.timerExpired("Spawn", "vanishingBlock")) {
            gameView.setTimer("Spawn", "vanishingBlock", 1000);
            VanishingBlock vanishingBlock = new VanishingBlock(gameView);
            vanishingBlock.setGamePlayManager(this);
            this.gameObjectManager.getVanishingBlocks().add(vanishingBlock);
            this.gameObjectManager.getCollidableGameObjects().addAll(vanishingBlocks);
        }
        if (!this.gameObjectManager.getVanishingBlocks().isEmpty() && gameView.timerExpired("Destroy", "vanishingBlock")) {
            gameView.setTimer("Destroy", "vanishingBlock", 5000);
            this.gameObjectManager.getVanishingBlocks().remove(random.nextInt(this.gameObjectManager.getVanishingBlocks().size()));
        }

        if (!listHasBeenDeleted && gameView.getGameTimeInMilliseconds() > 10_000) {
            this.gameObjectManager.getVanishingBlocks().clear();
            this.listHasBeenDeleted = true;
        }

    }

    /**
     * Removes an vanishingBlock from the list of game objects, so it will be not be displayed on the window anymore.
     *
     * @param vanishingBlock Object to be removed from the window.
     */
    public void destroy(VanishingBlock vanishingBlock) {
        this.gameObjectManager.getVanishingBlocks().remove(vanishingBlock);
        this.deletObjects.add(vanishingBlock);
        //this.gameObjectManager.getBall().getObjectsToCollideWith().remove(vanishingBlock);
    }

    /**
     * Creates the levels of the game
     */
    public void createLevel(){
        System.out.println(this.levelEins.level1("VanishingBlock").size());
        for (Position position : this.levelEins.level1("VanishingBlock")){
            VanishingBlock vanishingBlock = new VanishingBlock(gameView);
            vanishingBlock.getPosition().x = position.x;
            vanishingBlock.getPosition().y = position.y;
            vanishingBlock.setGamePlayManager(this);
            this.gameObjectManager.getVanishingBlocks().add(vanishingBlock);
            this.gameObjectManager.getBall().getObjectsToCollideWith().add(vanishingBlock);
        }
        this.nextLevel = false;
    }
}


package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.game.utilities.Level;
import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObjects;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.dynamicobjects.VanishingBlock;
import de.thd.graf.crillion.graphics.staticobjects.DeadlyBlock;

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
    private final VanishingBlock vanishingBlock;

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
        this.vanishingBlock = new VanishingBlock(gameView);
    }

    /**
     * Updates the action of the game, new objects spawn
     */
    public void updateGamePlay() {
        //createLevel();
        spawnAndDestroyVanishingblock();
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
        gameObjectManager.getVanishingBlocks().remove(vanishingBlock);
    }

    public void createLevel(){
        for (Position position : this.levelEins.level1("VanishingBlock")){
            this.vanishingBlock.getPosition().x = position.x;
            this.vanishingBlock.getPosition().y = position.y;
            this.gameObjectManager.getVanishingBlocks().add(vanishingBlock);
        }
    }
}


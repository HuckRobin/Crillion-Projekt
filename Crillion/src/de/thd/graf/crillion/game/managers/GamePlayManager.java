package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.game.utilities.Level;
import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObject;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.dynamicobjects.MovableBlock;
import de.thd.graf.crillion.graphics.dynamicobjects.VanishingBlock;
import de.thd.graf.crillion.graphics.staticobjects.ColorChangingBlock;
import de.thd.graf.crillion.graphics.staticobjects.DeadlyBlock;
import de.thd.graf.crillion.graphics.staticobjects.WallBlock;

import java.util.ArrayList;

/**
 * Gameplay Manager controlls the actions in the game
 */
public class GamePlayManager {

    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private final InputManager inputManager;
    private final Level level;
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
        this.level = new Level(1);
        this.nextLevel = true;
        this.deletObjects = new ArrayList<>();
        this.inputManager = new InputManager(gameView, gameObjectManager.getBall());
    }

    /**
     * Updates the action of the game, new objects spawn
     */
    public void updateGamePlay() {
        if (nextLevel) {
            createLevel();
        }
        this.gameObjectManager.collidableGameObjects.removeAll(deletObjects);
    }

    /**
     * Removes an vanishingBlock from the list of game objects, so it will be not be displayed on the window anymore.
     *
     * @param vanishingBlock Object to be removed from the window.
     */
    public void destroyVanishingBlock(VanishingBlock vanishingBlock) {
        this.gameObjectManager.getBlockObjects().remove(vanishingBlock);
        this.deletObjects.add(vanishingBlock);
    }

    public void bounceBallBack(BlockObject blockObject){
        if(this.gameObjectManager.getBall().getHitBox().intersectsLine(blockObject.getPosition().x, blockObject.getPosition().y, blockObject.getPosition().x, blockObject.getPosition().y + blockObject.getHeight())) {
            this.gameObjectManager.getBall().setLeftSideHit(true);
            this.gameObjectManager.getBall().setPauseUserInput(true);
        }
        else if(this.gameObjectManager.getBall().getHitBox().intersectsLine(blockObject.getPosition().x + blockObject.getWidth(), blockObject.getPosition().y, blockObject.getPosition().x + blockObject.getWidth(), blockObject.getPosition().y + blockObject.getHeight())) {
            this.gameObjectManager.getBall().setRightSideHit(true);
            this.gameObjectManager.getBall().setPauseUserInput(true);
        }

        if(this.gameObjectManager.getBall().isChangeDirectionTopToBottom()) {
            this.gameObjectManager.getBall().setChangeDirectionTopToBottom(false);
        }
        else {
            this.gameObjectManager.getBall().setChangeDirectionTopToBottom(true);
        }
    }

    /**
     * Creates the levels of the game
     */
    public void createLevel(){
        System.out.println(this.level.level1("VanishingBlock").size());
        for (Position position : this.level.level1("VanishingBlock")){
            VanishingBlock vanishingBlock = new VanishingBlock(gameView);
            vanishingBlock.getPosition().x = position.x;
            vanishingBlock.getPosition().y = position.y;
            vanishingBlock.setGamePlayManager(this);
            this.gameObjectManager.getBlockObjects().add(vanishingBlock);
            this.gameObjectManager.getBall().getObjectsToCollideWith().add(vanishingBlock);
        }
        for (Position position : this.level.level1("WallBlock")){
            WallBlock wallBlock = new WallBlock(gameView);
            wallBlock.getPosition().x = position.x;
            wallBlock.getPosition().y = position.y;
            wallBlock.setGamePlayManager(this);
            this.gameObjectManager.getBlockObjects().add(wallBlock);
            this.gameObjectManager.getBall().getObjectsToCollideWith().add(wallBlock);
        }
        for (Position position : this.level.level1("ColorChangingBlock")){
            ColorChangingBlock colorChangingBlock = new ColorChangingBlock(gameView);
           colorChangingBlock.getPosition().x = position.x;
           colorChangingBlock.getPosition().y = position.y;
           colorChangingBlock.setGamePlayManager(this);
            this.gameObjectManager.getBlockObjects().add(colorChangingBlock);
            this.gameObjectManager.getBall().getObjectsToCollideWith().add(colorChangingBlock);
        }
        for (Position position : this.level.level1("DeadlyBlock")){
            DeadlyBlock deadlyBlock = new DeadlyBlock(gameView);
            deadlyBlock.getPosition().x = position.x;
            deadlyBlock.getPosition().y = position.y;
            deadlyBlock.setGamePlayManager(this);
            this.gameObjectManager.getBlockObjects().add(deadlyBlock);
            this.gameObjectManager.getBall().getObjectsToCollideWith().add(deadlyBlock);
        }
        for (Position position : this.level.level1("MovableBlock")){
            MovableBlock movableBlock = new MovableBlock(gameView);
            movableBlock.getPosition().x = position.x;
            movableBlock.getPosition().y = position.y;
            movableBlock.setGamePlayManager(this);
            this.gameObjectManager.getBlockObjects().add(movableBlock);
            this.gameObjectManager.getBall().getObjectsToCollideWith().add(movableBlock);
        }
        this.nextLevel = false;
    }
}


package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.game.utilities.Level;
import de.thd.graf.crillion.game.utilities.Player;
import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
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
    private final Player player;
    private boolean nextLevel;
    ArrayList<CollidableGameObject> deletObjects;


    GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {

        this.gameView = gameView;
        this.gameObjectManager = gameObjectManager;
        this.player = new Player();
        this.player.level = new Level(1);
        this.nextLevel = true;
        this.deletObjects = new ArrayList<>();
    }

    /**
     * Updates the action of the game, new objects spawn
     */
    public void updateGamePlay() {
        updatePlayerLives();
        updateScore();
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

    /**
     * Let the ball bounce back from the object that gets hit
     * @param collidableGameObject block object
     */
    public void bounceBallBack(CollidableGameObject collidableGameObject) {

        if (this.gameObjectManager.getBall().getHitBox().intersectsLine(collidableGameObject.getPosition().x, collidableGameObject.getPosition().y, collidableGameObject.getPosition().x, collidableGameObject.getPosition().y + collidableGameObject.getHeight())) {
            this.gameObjectManager.getBall().setLeftSideHit(true);
            this.gameObjectManager.getBall().setPauseUserInput(true);
        }
        else if (this.gameObjectManager.getBall().getHitBox().intersectsLine(collidableGameObject.getPosition().x + collidableGameObject.getWidth(), collidableGameObject.getPosition().y, collidableGameObject.getPosition().x + collidableGameObject.getWidth(), collidableGameObject.getPosition().y + collidableGameObject.getHeight())) {
            this.gameObjectManager.getBall().setRightSideHit(true);
            this.gameObjectManager.getBall().setPauseUserInput(true);
        }

        if (this.gameObjectManager.getBall().getHitBox().intersectsLine(collidableGameObject.getPosition().x, collidableGameObject.getPosition().y, collidableGameObject.getPosition().x + collidableGameObject.getWidth(), collidableGameObject.getPosition().y)) {
            this.gameObjectManager.getBall().setChangeDirectionTopToBottom(false);
        }

        if (this.gameObjectManager.getBall().getHitBox().intersectsLine(collidableGameObject.getPosition().x, collidableGameObject.getPosition().y + collidableGameObject.getHeight(), collidableGameObject.getPosition().x + collidableGameObject.getWidth(), collidableGameObject.getPosition().y + collidableGameObject.getHeight()))
            this.gameObjectManager.getBall().setChangeDirectionTopToBottom(true);
    }

    /**
     * Creates the levels of the game
     */
    public void createLevel(){
        this.gameObjectManager.getScoreboard().getCurrentLevel().setScoreNum(this.player.level.name);
        for (Position position : this.player.level.level1("VanishingBlock")){
            VanishingBlock vanishingBlock = new VanishingBlock(gameView);
            vanishingBlock.getPosition().x = position.x;
            vanishingBlock.getPosition().y = position.y;
            vanishingBlock.setGamePlayManager(this);
            this.gameObjectManager.getBlockObjects().add(vanishingBlock);
            this.gameObjectManager.getBall().getObjectsToCollideWith().add(vanishingBlock);
        }
        for (Position position : this.player.level.level1("WallBlock")){
            WallBlock wallBlock = new WallBlock(gameView);
            wallBlock.getPosition().x = position.x;
            wallBlock.getPosition().y = position.y;
            wallBlock.setGamePlayManager(this);
            this.gameObjectManager.getBlockObjects().add(wallBlock);
            this.gameObjectManager.getBall().getObjectsToCollideWith().add(wallBlock);
        }
        for (Position position : this.player.level.level1("ColorChangingBlock")){
            ColorChangingBlock colorChangingBlock = new ColorChangingBlock(gameView);
           colorChangingBlock.getPosition().x = position.x;
           colorChangingBlock.getPosition().y = position.y;
           colorChangingBlock.setGamePlayManager(this);
            this.gameObjectManager.getBlockObjects().add(colorChangingBlock);
            this.gameObjectManager.getBall().getObjectsToCollideWith().add(colorChangingBlock);
        }
        for (Position position : this.player.level.level1("DeadlyBlock")){
            DeadlyBlock deadlyBlock = new DeadlyBlock(gameView);
            deadlyBlock.getPosition().x = position.x;
            deadlyBlock.getPosition().y = position.y;
            deadlyBlock.setGamePlayManager(this);
            this.gameObjectManager.getBlockObjects().add(deadlyBlock);
            this.gameObjectManager.getBall().getObjectsToCollideWith().add(deadlyBlock);
        }
        for (Position position : this.player.level.level1("MovableBlock")){
            MovableBlock movableBlock = new MovableBlock(gameView);
            movableBlock.getPosition().x = position.x;
            movableBlock.getPosition().y = position.y;
            movableBlock.setGamePlayManager(this);
            this.gameObjectManager.getBlockObjects().add(movableBlock);
            this.gameObjectManager.getBall().getObjectsToCollideWith().add(movableBlock);
        }
        this.nextLevel = false;
    }

    private void updatePlayerLives(){
        this.gameObjectManager.getScoreboard().getLives().setScoreNum(player.lives);
    }

    private void updateScore(){

        this.gameObjectManager.getScoreboard().getScore().setScoreNum(player.score);
        if(this.player.score > this.player.highscore){
            this.player.highscore = this.player.score;
            this.gameObjectManager.getScoreboard().getHighscore().setScoreNum(player.highscore);
        }
    }

    /**
     * Get the player
     * @return
     */
    public Player getPlayer() {
        return player;
    }
}


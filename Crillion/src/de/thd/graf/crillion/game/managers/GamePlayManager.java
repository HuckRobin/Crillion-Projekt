package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.game.utilities.Level;
import de.thd.graf.crillion.game.utilities.Player;
import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.dynamicobjects.MovableBlock;
import de.thd.graf.crillion.graphics.staticobjects.VanishingBlock;
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
        this.gameObjectManager.getCollidableGameObjectsBall().removeAll(deletObjects);
        this.gameObjectManager.getCollidableGameObjectsMovableBlock().removeAll(deletObjects);
    }

    /**
     * Removes an vanishingBlock from the list of game objects, so it will be not be displayed on the window anymore.
     *
     * @param vanishingBlock Object to be removed from the window.
     */
    public void destroyVanishingBlock(VanishingBlock vanishingBlock) {
      if(this.gameObjectManager.getBall().getHitBox().intersects(vanishingBlock.getHitBox())) {
          //this.gameObjectManager.getBlockObjects().remove(vanishingBlock);
          this.deletObjects.add(vanishingBlock);
      }
        vanishingBlock.setCreateExplosion(true);
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
     * MOve the movable block by hit with the ball and stop moving by hit with a other object
     * @param movableBlock Movableblock
     * @param collidableGameObject collidableGameobject
     */
    public void moveBlock(MovableBlock movableBlock, CollidableGameObject collidableGameObject) {

        //Links
        if (this.gameObjectManager.getBall().getHitBox().intersectsLine(movableBlock.getPosition().x, movableBlock.getPosition().y, movableBlock.getPosition().x, movableBlock.getPosition().y + movableBlock.getHeight())
                && !collidableGameObject.getHitBox().intersectsLine(movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y + 2, movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y + movableBlock.getHeight() - 2)) {
            movableBlock.setDirection(MovableBlock.Direction.RIGHT);
        }
        //rechts
        else if (this.gameObjectManager.getBall().getHitBox().intersectsLine(movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y, movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y + movableBlock.getHeight())
                && !collidableGameObject.getHitBox().intersectsLine(movableBlock.getPosition().x, movableBlock.getPosition().y + 2, movableBlock.getPosition().x, movableBlock.getPosition().y + movableBlock.getHeight() - 2)) {
            movableBlock.direction = MovableBlock.Direction.LEFT;
        }

        //oben
        if (this.gameObjectManager.getBall().getHitBox().intersectsLine(movableBlock.getPosition().x, movableBlock.getPosition().y, movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y)
                && !collidableGameObject.getHitBox().intersectsLine(movableBlock.getPosition().x + 2, movableBlock.getPosition().y + movableBlock.getHeight(), movableBlock.getPosition().x + movableBlock.getWidth() - 2, movableBlock.getPosition().y + movableBlock.getHeight())) {
            movableBlock.direction = MovableBlock.Direction.DOWN;
        }

        //unten
        else if (this.gameObjectManager.getBall().getHitBox().intersectsLine(movableBlock.getPosition().x, movableBlock.getPosition().y + movableBlock.getHeight(), movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y + movableBlock.getHeight())
                && !collidableGameObject.getHitBox().intersectsLine(movableBlock.getPosition().x + 2, movableBlock.getPosition().y, movableBlock.getPosition().x + movableBlock.getWidth() - 2, movableBlock.getPosition().y)) {
            movableBlock.direction = MovableBlock.Direction.UP;
        }
    }
    /**
     * Creates the levels of the game
     */
    public void createLevel(){
        this.gameObjectManager.getScoreboard().getCurrentLevel().setScoreNum(this.player.level.name);
        for (Position position : this.player.level.level1("VanishingBlock")){
            VanishingBlock vanishingBlock = new VanishingBlock(gameView);
            helpFunctionCreateLevel(vanishingBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(vanishingBlock);
        }
        for (Position position : this.player.level.level1("WallBlock")){
            WallBlock wallBlock = new WallBlock(gameView);
            helpFunctionCreateLevel(wallBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(wallBlock);
        }
        for (Position position : this.player.level.level1("ColorChangingBlock")){
            ColorChangingBlock colorChangingBlock = new ColorChangingBlock(gameView);
            helpFunctionCreateLevel(colorChangingBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(colorChangingBlock);
        }
        for (Position position : this.player.level.level1("DeadlyBlock")){
            DeadlyBlock deadlyBlock = new DeadlyBlock(gameView);
            helpFunctionCreateLevel(deadlyBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(deadlyBlock);
        }
        for (Position position : this.player.level.level1("MovableBlock")){
            MovableBlock movableBlock = new MovableBlock(gameView, this.gameObjectManager.getCollidableGameObjectsMovableBlock());
            helpFunctionCreateLevel(movableBlock, position);
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

    private void helpFunctionCreateLevel(BlockObject blockObject, Position position){
        blockObject.getPosition().x = position.x;
        blockObject.getPosition().y = position.y;
        blockObject.setGamePlayManager(this);
        this.gameObjectManager.getBlockObjects().add(blockObject);
        this.gameObjectManager.getBall().getObjectsToCollideWith().add(blockObject);
    }

    /**
     * Get the gameobject Manager
     * @return
     */
    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }
}


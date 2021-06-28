package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.game.utilities.Level;
import de.thd.graf.crillion.game.utilities.Player;
import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.BlockObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.dynamicobjects.MovableBlock;
import de.thd.graf.crillion.graphics.staticobjects.ColorChangingBlock;
import de.thd.graf.crillion.graphics.staticobjects.DeadlyBlock;
import de.thd.graf.crillion.graphics.staticobjects.VanishingBlock;
import de.thd.graf.crillion.graphics.staticobjects.WallBlock;
import de.thd.graf.crillion.screen.EndScreen;
import de.thd.graf.crillion.screen.StartScreen;

import java.util.ArrayList;

/**
 * Gameplay Manager controlls the actions in the game
 */
public class GamePlayManager {

    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private LevelManager levelManager;
    private final Player player;
    private boolean nextLevel;
    private boolean gameover;
    private ArrayList<CollidableGameObject> deletObjects;


    GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {

        this.gameView = gameView;
        this.gameObjectManager = gameObjectManager;
        this.player = new Player();
        this.player.level = new Level(1);
        this.nextLevel = true;
        this.gameover = false;
        this.deletObjects = new ArrayList<>();
        this.levelManager = new LevelManager();
        StartScreen startScreen = new StartScreen(gameView);
        startScreen.showStartScreen();
    }

    /**
     * Updates the action of the game, new objects spawn
     */
    public void updateGamePlay() {
        updatePlayerLives();
        updateScore();
        if (nextLevel) {
            if (!gameover) {
                createLevel();
            }
            if (gameover) {
                EndScreen endScreen = new EndScreen(gameView);
                endScreen.showEndScreen(player.score);
                StartScreen startScreen = new StartScreen(gameView);
                startScreen.showStartScreen();
                levelManager = new LevelManager();
                this.player.lives = Player.MAXIMUM_NUMBER_OF_LIVES;
                this.player.score = 0;
                this.gameObjectManager.getBlockObjects().clear();
                this.gameObjectManager.getCollidableGameObjectsMovableBlock().clear();
                this.gameObjectManager.getCollidableGameObjectsBall().clear();
                this.gameObjectManager.getGameObjects().clear();
                createLevel();
            }
        }
        this.gameObjectManager.getCollidableGameObjectsBall().removeAll(deletObjects);
        this.gameObjectManager.getCollidableGameObjectsMovableBlock().removeAll(deletObjects);

        if (player.lives == 0 || this.player.level.blocks == 0) {
            this.gameover = true;
            this.nextLevel = true;
        }

    }

    /**
     * Removes an vanishingBlock from the list of game objects, so it will be not be displayed on the window anymore.
     *
     * @param vanishingBlock Object to be removed from the window.
     */
    public void destroyVanishingBlock(VanishingBlock vanishingBlock) {
        if (this.gameObjectManager.getBall().getBallColor() == vanishingBlock.getStatusColor()) {
            if (this.gameObjectManager.getBall().getHitBox().intersects(vanishingBlock.getHitBox())) {
                this.deletObjects.add(vanishingBlock);
            }
            vanishingBlock.setCreateExplosion(true);
            this.gameView.playSound("explosion.wav", false);
            this.player.score += 100;
            this.player.level.blocks--;
        }
    }

    /**
     * Let the ball bounce back from the object that gets hit
     *
     * @param collidableGameObject block object
     */
    public void bounceBallBack(CollidableGameObject collidableGameObject) {

        if (this.gameObjectManager.getBall().getHitBox().intersectsLine(collidableGameObject.getPosition().x, collidableGameObject.getPosition().y, collidableGameObject.getPosition().x, collidableGameObject.getPosition().y + collidableGameObject.getHeight())) {
            this.gameObjectManager.getBall().setLeftSideHit(true);
            this.gameObjectManager.getBall().setPauseUserInput(true);
        } else if (this.gameObjectManager.getBall().getHitBox().intersectsLine(collidableGameObject.getPosition().x + collidableGameObject.getWidth(), collidableGameObject.getPosition().y, collidableGameObject.getPosition().x + collidableGameObject.getWidth(), collidableGameObject.getPosition().y + collidableGameObject.getHeight())) {
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
     *
     * @param movableBlock         Movableblock
     * @param collidableGameObject collidableGameobject
     */
    public void moveBlock(MovableBlock movableBlock, CollidableGameObject collidableGameObject) {

        if (this.gameObjectManager.getBall().getBallColor() == movableBlock.getStatusColor()) {
            //Links
            if (this.gameObjectManager.getBall().getHitBox().intersectsLine(movableBlock.getPosition().x, movableBlock.getPosition().y, movableBlock.getPosition().x, movableBlock.getPosition().y + movableBlock.getHeight())
                    && !collidableGameObject.getHitBox().intersectsLine(movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y + 2, movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y + movableBlock.getHeight() - 2)) {
                movableBlock.setDirection(MovableBlock.Direction.RIGHT);
            }
            //rechts
            if (this.gameObjectManager.getBall().getHitBox().intersectsLine(movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y, movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y + movableBlock.getHeight())
                    && !collidableGameObject.getHitBox().intersectsLine(movableBlock.getPosition().x, movableBlock.getPosition().y + 2, movableBlock.getPosition().x, movableBlock.getPosition().y + movableBlock.getHeight() - 2)) {
                movableBlock.direction = MovableBlock.Direction.LEFT;
            }

            //oben
            if (this.gameObjectManager.getBall().getHitBox().intersectsLine(movableBlock.getPosition().x, movableBlock.getPosition().y, movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y)
                    && !collidableGameObject.getHitBox().intersectsLine(movableBlock.getPosition().x + 2, movableBlock.getPosition().y + movableBlock.getHeight(), movableBlock.getPosition().x + movableBlock.getWidth() - 2, movableBlock.getPosition().y + movableBlock.getHeight())) {
                movableBlock.direction = MovableBlock.Direction.DOWN;
            }

            //unten
            if (this.gameObjectManager.getBall().getHitBox().intersectsLine(movableBlock.getPosition().x, movableBlock.getPosition().y + movableBlock.getHeight(), movableBlock.getPosition().x + movableBlock.getWidth(), movableBlock.getPosition().y + movableBlock.getHeight())
                    && !collidableGameObject.getHitBox().intersectsLine(movableBlock.getPosition().x + 2, movableBlock.getPosition().y, movableBlock.getPosition().x + movableBlock.getWidth() - 2, movableBlock.getPosition().y)) {
                movableBlock.direction = MovableBlock.Direction.UP;
            }
        }
    }

    private void createLevel() {
        this.gameObjectManager.getScoreboard().getCurrentLevel().setScoreNum(this.player.level.name);
        this.gameObjectManager.getBall().getPosition().x = 50;
        this.gameObjectManager.getBall().getPosition().y = 100;
        for (Position position : this.player.level.getBlockPositionForTheLevel("VanishingBlock_Blue", this.player.level.name)) {
            VanishingBlock vanishingBlock = new VanishingBlock(gameView, VanishingBlock.StatusColor.BLUE);
            helpFunctionCreateLevel(vanishingBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(vanishingBlock);
        }
        for (Position position : this.player.level.getBlockPositionForTheLevel("VanishingBlock_Red", this.player.level.name)) {
            VanishingBlock vanishingBlock = new VanishingBlock(gameView, VanishingBlock.StatusColor.RED);
            helpFunctionCreateLevel(vanishingBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(vanishingBlock);
        }
        for (Position position : this.player.level.getBlockPositionForTheLevel("VanishingBlock_Yellow", this.player.level.name)) {
            VanishingBlock vanishingBlock = new VanishingBlock(gameView, VanishingBlock.StatusColor.YELLOW);
            helpFunctionCreateLevel(vanishingBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(vanishingBlock);
        }
        for (Position position : this.player.level.getBlockPositionForTheLevel("WallBlock", this.player.level.name)) {
            WallBlock wallBlock = new WallBlock(gameView);
            helpFunctionCreateLevel(wallBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(wallBlock);
        }
        for (Position position : this.player.level.getBlockPositionForTheLevel("ColorChangingBlock_Blue", this.player.level.name)) {
            ColorChangingBlock colorChangingBlock = new ColorChangingBlock(gameView, ColorChangingBlock.StatusColor.BLUE);
            helpFunctionCreateLevel(colorChangingBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(colorChangingBlock);
        }
        for (Position position : this.player.level.getBlockPositionForTheLevel("ColorChangingBlock_Red", this.player.level.name)) {
            ColorChangingBlock colorChangingBlock = new ColorChangingBlock(gameView, ColorChangingBlock.StatusColor.RED);
            helpFunctionCreateLevel(colorChangingBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(colorChangingBlock);
        }
        for (Position position : this.player.level.getBlockPositionForTheLevel("ColorChangingBlock_Yellow", this.player.level.name)) {
            ColorChangingBlock colorChangingBlock = new ColorChangingBlock(gameView, ColorChangingBlock.StatusColor.YELLOW);
            helpFunctionCreateLevel(colorChangingBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(colorChangingBlock);
        }
        for (Position position : this.player.level.getBlockPositionForTheLevel("DeadlyBlock", this.player.level.name)) {
            DeadlyBlock deadlyBlock = new DeadlyBlock(gameView);
            helpFunctionCreateLevel(deadlyBlock, position);
            this.gameObjectManager.getCollidableGameObjectsMovableBlock().add(deadlyBlock);
        }
        for (Position position : this.player.level.getBlockPositionForTheLevel("MovableBlock_Blue", this.player.level.name)) {
            MovableBlock movableBlock = new MovableBlock(gameView, MovableBlock.StatusColor.BLUE, this.gameObjectManager.getCollidableGameObjectsMovableBlock());
            helpFunctionCreateLevel(movableBlock, position);
        }
        for (Position position : this.player.level.getBlockPositionForTheLevel("MovableBlock_Red", this.player.level.name)) {
            MovableBlock movableBlock = new MovableBlock(gameView, MovableBlock.StatusColor.RED, this.gameObjectManager.getCollidableGameObjectsMovableBlock());
            helpFunctionCreateLevel(movableBlock, position);
        }
        this.nextLevel = false;
    }

    /**
     * Change the color of the ball
     *
     * @param colorChangingBlock
     */
    public void changeBallColor(ColorChangingBlock colorChangingBlock) {
        this.gameObjectManager.getBall().setStatusColor(colorChangingBlock.getStatusColor());
    }

    private void updatePlayerLives() {
        this.gameObjectManager.getScoreboard().getLives().setScoreNum(player.lives);
    }

    private void updateScore() {

        this.gameObjectManager.getScoreboard().getScore().setScoreNum(player.score);
        if (this.player.score > this.player.highscore) {
            this.player.highscore = this.player.score;
            this.gameObjectManager.getScoreboard().getHighscore().setScoreNum(player.highscore);
        }
    }

    /**
     * Get the player
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    private void helpFunctionCreateLevel(BlockObject blockObject, Position position) {
        blockObject.getPosition().x = position.x;
        blockObject.getPosition().y = position.y;
        blockObject.setGamePlayManager(this);
        this.gameObjectManager.getBlockObjects().add(blockObject);
        this.gameObjectManager.getBall().getObjectsToCollideWith().add(blockObject);
    }

    /**
     * Get the gameobject Manager
     *
     * @return
     */
    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }
}


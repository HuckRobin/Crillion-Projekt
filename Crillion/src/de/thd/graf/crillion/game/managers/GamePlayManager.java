package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.game.managers.GameObjectManager;
import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.dynamicobjects.BlockExplosion;
import de.thd.graf.crillion.graphics.dynamicobjects.VanishingBlock;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.staticobjects.Level;
import de.thd.graf.crillion.graphics.staticobjects.Scoreboard;

import java.util.Random;

/**
 * Gameplay Manager controlls the actions in the game
 */
public class GamePlayManager {

    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private final Random random;
    private boolean listHasBeenDeleted;

    /**
     *Create the gameplay manager
     * @param gameView
     * @param gameObjectManager
     */
    public GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {

        this.gameView = gameView;
        this.gameObjectManager = gameObjectManager;
        this.random = new Random();
        this.listHasBeenDeleted = false;
        gameObjectManager.getBall().setGamePlayManager(this);
    }

    /**
     * Updates the action of the game, new objects spawn
     */
    public void updateGamePlay() {
        spawnAndDestroyVanishingblock();
    }

    /**
     * Spawn and destroys the vanishing block after a certain time
     */
    public void spawnAndDestroyVanishingblock(){
        VanishingBlock vanishingBlock = new VanishingBlock(gameView);

        if(gameView.timerExpired("Spawn", "vanishingBlock")){
            gameView.setTimer("Spawn","vanishingBlock", 1000);
            this.gameObjectManager.getVanishingBlocks().add(vanishingBlock);
        }

        if(!this.gameObjectManager.getVanishingBlocks().isEmpty() && gameView.timerExpired("Destroy", "vanishingBlock")){
            gameView.setTimer("Destroy", "vanishingBlock", 5000);
            this.gameObjectManager.getVanishingBlocks().remove(random.nextInt(this.gameObjectManager.getVanishingBlocks().size()));
        }

        if(!listHasBeenDeleted && gameView.getGameTimeInMilliseconds()  > 10_000){
           this.gameObjectManager.getVanishingBlocks().clear();
           this.listHasBeenDeleted = true;
        }
    }

    private void updateLevel(){
    }

    /**
     * Adds a shot in form of a explosion
     * @param startPosition The position to spawn the shot from
     */
    public void shootBallExplosion(Position startPosition){
        BlockExplosion blockExplosion = new BlockExplosion(gameView);
        blockExplosion.getPosition().x = startPosition.x;
        blockExplosion.getPosition().y = startPosition.y;
        blockExplosion.setGamePlayManager(this);
        this.gameObjectManager.getBlockExplosions().add(blockExplosion);
    }

    /**
     * Destroy the block explosion
     * @param blockExplosion
     */
    public void destroy(BlockExplosion blockExplosion){
        this.gameObjectManager.getBlockExplosions().remove(blockExplosion);
    }
}


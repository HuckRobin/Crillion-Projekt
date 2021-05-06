package de.thd.graf.crillion.game.managers;
import de.thd.graf.crillion.gameview.GameView;

/**
 * This class manages the main game loop of the game.
 */
public class GameLoopManager {
    private final GameView gameView;
    private final InputManager inputManager;
    private final GameObjectManager gameObjectManager;
    private final GamePlayManager gamePlayManager;

    /**
     * Creates the main loop.
     */
    public GameLoopManager() {
        this.gameView = new GameView();
        this.gameView.setWindowTitle("Crillion");
        this.gameView.setStatusText("Phillip Graf - Java Programmierung SS 2021");
        this.gameView.setWindowIcon("Purple-Ball.png");
        this.gameObjectManager = new GameObjectManager(gameView);
        this.inputManager = new InputManager(gameView, gameObjectManager.getBall());
        this.gamePlayManager = new GamePlayManager(gameView, gameObjectManager);
    }

    /**
     * Starts the main loop of the game.
     */
    public void startGame() {
        while (true) { // The "Game-Loop"
            gamePlayManager.updateGamePlay();
            inputManager.updateUserInputs();
            gameObjectManager.updateGameObjects();
            gameView.printCanvas();
        }
    }
}

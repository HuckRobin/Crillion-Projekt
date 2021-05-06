package de.thd.graf.crillion.game.bin;

import de.thd.graf.crillion.game.managers.GameLoopManager;

/**
 * start the game
 */
public class Start {
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        var gameLoopManager = new GameLoopManager();
        gameLoopManager.startGame();
    }
}

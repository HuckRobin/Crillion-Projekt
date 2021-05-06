package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.ScoreObjects;

/**
 * Scoreboard for the game
 */
public class Scoreboard extends ScoreObjects {

    private int highscore;
    private int level;
    private int lives;
    private int leftBlocks;
    private int bonus;

    /**
     * Create the Scoreboard
     * @param gameView get important Code from GameView
     */
    public Scoreboard(GameView gameView) {
        super(gameView);
        highscore = 0;
        level = 1;
        lives = 5;
        leftBlocks = 100;
        bonus = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {

    }
}

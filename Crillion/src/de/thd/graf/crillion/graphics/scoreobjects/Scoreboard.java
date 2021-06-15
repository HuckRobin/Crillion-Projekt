package de.thd.graf.crillion.graphics.scoreobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.ScoreObjects;

/**
 * Scoreboard for the game
 */
public class Scoreboard extends ScoreObjects {

    private final Highscore highscore;
    private final Score score;
    private final CurrentLevel currentLevel;
    private final Lives lives;


    /**
     * Create the Scoreboard
     *
     * @param gameView get important Code from GameView
     */
    public Scoreboard(GameView gameView) {
        super(gameView);
        this.highscore = new Highscore(gameView);
        this.score = new Score(gameView);
        this.currentLevel = new CurrentLevel(gameView);
        this.lives = new Lives(gameView);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        this.currentLevel.addToCanvas();
        this.lives.addToCanvas();
        this.score.addToCanvas();
        this.highscore.addToCanvas();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
    }
}

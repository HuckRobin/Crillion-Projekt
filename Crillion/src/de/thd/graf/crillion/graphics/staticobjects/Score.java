package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.basicobjects.ScoreObjects;

/**
 * Create the score to add to the scoreboard
 */
public class Score extends ScoreObjects {

    /**
     * Create the Score object
     * @param gameView get important Code from GameView
     */
    public Score(GameView gameView){
        super(gameView);
        this.position = new Position(GameView.WIDTH, 0);
        this.scoreNum = 0;
        this.name = "Score:" + this.scoreNum;
    }

    private void addPointsToScore(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas(this.name, this.position.x - this.name.length() * this.size, this.position.y,
                this.size, this.color, this.rotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
    }
}

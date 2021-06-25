package de.thd.graf.crillion.graphics.scoreobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.basicobjects.ScoreObjects;

public class Highscore extends ScoreObjects {
    /**
     * Create the Level object
     * @param gameView get important Code from GameView
     */
    public Highscore(GameView gameView){
        super(gameView);
        this.position = new Position(GameView.WIDTH, 30);
        this.scoreNum = 5;
        this.name = "Highscore:" + this.scoreNum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        this.name = "Highscore:" + this.scoreNum;
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

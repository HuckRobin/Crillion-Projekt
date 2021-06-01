package de.thd.graf.crillion.graphics.staticobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.basicobjects.ScoreObjects;

import java.awt.*;

class LeftBlock extends ScoreObjects {

    /**
     * Create the Level object
     *
     * @param gameView get important Code from GameView
     */
    public LeftBlock(GameView gameView) {
        super(gameView);
        this.position = new Position(300, 30);
        this.score = 9;
        this.name = "LeftBlocks:" + this.score;
    }

    private void addPointsToScore() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas(this.name, this.position.x, this.position.y, this.size,
                this.color, this.rotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
    }
}

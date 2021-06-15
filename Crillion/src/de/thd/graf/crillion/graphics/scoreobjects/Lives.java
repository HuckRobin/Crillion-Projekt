package de.thd.graf.crillion.graphics.scoreobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.basicobjects.ScoreObjects;

class Lives extends ScoreObjects {

    /**
     * Create the Lives object
     * @param gameView get important Code from GameView
     */
    public Lives(GameView gameView){
        super(gameView);
        this.position = new Position(300, 0);
        this.scoreNum = 5;
        this.name = "Lives:";
    }

    private void addPointsToScore(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas(this.name, this.position.x, this.position.y,this.size, this.color, this.rotation);
        for (int i = 0; i < scoreNum; i++) {
            gameView.addImageToCanvas("Heart.png", (position.x + this.name.length() * this.size)
                    + i * 35, this.position.y, 0.4, rotation);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {

    }
}

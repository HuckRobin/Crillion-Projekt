package de.thd.graf.crillion.graphics.basicobjects;

import de.thd.graf.crillion.gameview.GameView;

/**
 * Subclass of GameObject to initial score variables
 */
public abstract class ScoreObjects extends GameObject{

    protected String name;
    protected int score;

    /**
     * Create the Scoreobject
     * @param gameView Get the Gameview
     */
    public ScoreObjects(GameView gameView){
        super(gameView);
    }

}

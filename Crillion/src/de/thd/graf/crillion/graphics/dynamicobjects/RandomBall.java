package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.MovingGameObject;
import de.thd.graf.crillion.graphics.basicobjects.MovementPatterns;
import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * creates the randomball
 *
 */
public class RandomBall extends MovementPatterns implements MovingGameObject {
    protected Position endPosition;
    private int movementPosition;
    private ArrayList<Position> movementClusterSquare;
    private ArrayList<Position> movemnetClusterZigZag;
    MovementPatterns movementPatterns;

    /**
     * creates Randomball
     * for moving the position {@link Position} will be called     *
     * @param gameView
     * @see Position
     */
    public RandomBall(GameView gameView) {
        super(gameView);
        this.size = 50;
        this.speedInPixel = 4;
        this.position = new Position(0, 0);
        this.endPosition = new Position(800, 200);
        this.movementPosition = 0;
        this.movementClusterSquare = new ArrayList<Position>(java.util.List.of(new Position(30, 30), new Position(930, 30), new Position(930, 510), new Position(30, 510)));
        this.movemnetClusterZigZag = new ArrayList<Position>(List.of(new Position(300, 200), new Position(400, 340), new Position(500, 200), new Position(600, 340), new Position(700, 200), new Position(800, 340)));
        gameView.setColorForBlockImage('.', Color.YELLOW);
        gameView.setColorForBlockImage('G', Color.GREEN);
    }



    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.x, position.y, size, size, 2, true, Color.YELLOW);
        gameView.addOvalToCanvas(endPosition.x, endPosition.y, size, size, 2, false, Color.WHITE);
    }

    @Override
    public void updateStatus() {
        double distance = position.distance(endPosition);
        if (distance >= speedInPixel) {
            position.right((endPosition.x - position.x) / distance * speedInPixel);
            position.down((endPosition.y - position.y) / distance * speedInPixel);
        } else {
            setRandomEndPosition();
        }

    }

    /**
     * Set position to aim at
     */
    public void setRandomEndPosition() {
        try {
            endPosition = (Position) getPattern("Zero").get(movementPosition);
            movementPosition++;
        } catch (Exception e) {
            movementPosition = 0;
        }

    }
    @Override
    public void updatePosition() {
    }
}

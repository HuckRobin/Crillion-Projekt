package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.MovingGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.awt.*;
import java.util.Random;

/** Ball with random movement. */
public class RandomBall extends GameObject implements MovingGameObject {
    private final Position targetPosition;
    private final Random random;

    /**
     * Creates the GameObject with the GameView to be displayed on.
     *
     * @param gameView Window to show the GameObject on.
     */
    public RandomBall(GameView gameView) {
        super(gameView);
        this.targetPosition = new Position(800, 200);
        this.random = new Random();
        this.size = 50;
        this.speedInPixel = 4;
    }

    @Override
    public void updatePosition() {
        double distance = position.distance(targetPosition);
        if (distance >= speedInPixel) {
            position.right((targetPosition.x - position.x) / distance * speedInPixel);
            position.down((targetPosition.y - position.y) / distance * speedInPixel);
        } else {
            setRandomTargetPosition();
        }
    }


    @Override
    protected void updateStatus() {

    }

   /** Set position to aim at */
    public void setRandomTargetPosition() {
        targetPosition.x = random.nextInt(GameView.WIDTH);
        targetPosition.y = random.nextInt(GameView.HEIGHT);
    }

    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.x, position.y, size, size, 2, true, Color.YELLOW);
        gameView.addOvalToCanvas(targetPosition.x, targetPosition.y, size, size, 2, false, Color.WHITE);
    }
}

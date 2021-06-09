package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.MovingGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.awt.*;

/** This ball follows another ball. */
public class FollowerBall extends GameObject implements MovingGameObject {

    private enum Status {FOLLOW, CENTER, HIDE}

    private final RandomBall followMe;
    private Position targetPosition;
    private Status status;

    /**
     * Creates the GameObject with the GameView to be displayed on.
     *
     * @param gameView Window to show the GameObject on.
     * @param followMe The RandomBall to follow.
     */
    public FollowerBall(GameView gameView, RandomBall followMe) {
        super(gameView);
        this.status = Status.CENTER;
        this.followMe = followMe;
        this.position = new Position(GameView.WIDTH, GameView.HEIGHT);
        this.size = 50;
        this.speedInPixel = 2;

    }

    @Override
    public void updatePosition() {
        if (gameView.timerExpired("Status", "FollowerBall")) {
            gameView.setTimer("Status", "FollowerBall", 3000);
            switch (status) {
                case HIDE:
                    status = Status.CENTER;
                    break;
                case CENTER:
                    status = Status.FOLLOW;
                    break;
                case FOLLOW:
                    status = Status.HIDE;
                    break;
            }
        }
        if (status == Status.FOLLOW) {
            targetPosition = followMe.getPosition().clone();
            targetPosition.y += 25;
        } else if (status == Status.CENTER) {
            targetPosition = new Position(GameView.WIDTH / 2d, GameView.HEIGHT / 2d);
        }
        if (status != Status.HIDE) {
            double distance = position.distance(targetPosition);
            if (distance >= speedInPixel) {
                position.right((targetPosition.x - position.x) / distance * speedInPixel);
                position.down((targetPosition.y - position.y) / distance * speedInPixel);
            }
        }
    }

    @Override
    protected void updateStatus() {

    }

    @Override
    public void addToCanvas() {
        if (status == Status.HIDE) {
            gameView.addOvalToCanvas(position.x, position.y, size, size, 2, true, Color.WHITE);
        } else {
            gameView.addOvalToCanvas(position.x, position.y, size, size, 2, true, Color.GREEN);
        }
    }
}

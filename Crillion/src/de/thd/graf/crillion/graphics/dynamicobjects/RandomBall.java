package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.basicobjects.MovingGameObject;
import de.thd.graf.crillion.graphics.basicobjects.MovementPatterns;
import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/** Ball with random movement. */
public class RandomBall extends GameObject implements MovingGameObject {
    private final Position targetPosition;
    private final Random random;
    private final MovementPatterns movementPatterns;
    private final ArrayList<Position> patternList;

    /**
     * Creates the GameObject with the GameView to be displayed on.
     *
     * @param gameView Window to show the GameObject on.
     */
    public RandomBall(GameView gameView) {
        super(gameView);
        this.targetPosition = new Position(800, 200);
        this.random = new Random();
        this.movementPatterns = new MovementPatterns();
        this.patternList = new ArrayList<>();
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
            setPatternTargetPosition();
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

    /**
     * Set position to aim at
     */
    public void setPatternTargetPosition(){
        if (this.patternList.size() == 0){
            this.patternList.addAll(this.movementPatterns.getPattern(this.movementPatterns.getRandomPattern()));
        }

        this.targetPosition.x = this.patternList.get(0).x;
        this.targetPosition.y = this.patternList.get(0).y;
        this.patternList.remove(0);
    }
}

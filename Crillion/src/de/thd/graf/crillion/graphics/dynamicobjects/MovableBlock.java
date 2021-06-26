package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.*;

import java.util.ArrayList;

/**
 * Block which moves when it get hit by the {@link Ball} with the same Color
 */
public class MovableBlock extends CollidingGameObject implements MovingGameObject {

     public enum Direction {STOP, UP, DOWN, LEFT, RIGHT}
     public Direction direction;

     private enum StopMoving{STOPUP, STOPDOWN, STOPLEFT, STOPRIGHT}
     private StopMoving stopMoving;
     private boolean stopUp;
     private boolean stopDown;
     private boolean stopLeft;
     private boolean stopRight;

    /**
     * Create a MoveableBlock
     *
     * @param gameView get important Code from GameView
     */
    public MovableBlock(GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, objectsToCollideWith);
        this.position = new Position(50, 60 + 1);
        this.speedInPixel = 1;
        this.blockImage = "WWWWWWWWWBB\n" +
                "WBBBBBBBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBWWWBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBBBBBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBBWBBBBB\n" +
                "WBBBBBBBBBB\n";
        this.direction = Direction.STOP;
        this.stopMoving = null;
        this.stopUp = false;
        this.stopDown = false;
        this.stopLeft = false;
        this.stopRight = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateHitBoxPosition() {
        super.updateHitBoxPosition();
    }

    /**
     * {@inheritDoc}
     * @param otherObject The other GameObject that is involved in the collision.
     */
    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        System.out.println("Hit_MovableBlock");
        this.gamePlayManager.bounceBallBack(this);
        hitAObject(otherObject);
        this.gamePlayManager.moveBlock(this, otherObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(this.blockImage, this.position.x, this.position.y, this.size, this.rotation);
        addHitBoxToCanvas();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
    }

    /**
     * Update the Position when the Block get hits by the ball
     */
    @Override
    public void updatePosition() {
        switch (direction) {
            case UP:
               if(!stopUp) {
                   this.position.up(1);
               }
                break;
            case DOWN:
                if(!stopDown) {
                    this.position.down(1);
                }
                break;
            case LEFT:
                if(!stopLeft) {
                    this.position.left(1);
                }
                break;
            case RIGHT:
                if(!stopRight) {
                    this.position.right(1);
                }
                break;
            case STOP:
                break;
        }
        this.stopUp = false;
        this.stopDown = false;
        this.stopRight = false;
        this.stopLeft = false;
    }

    private void hitAObject(CollidableGameObject collidableGameObject) {

        if (collidableGameObject.getHitBox().intersectsLine(position.x + 2, position.y, position.x + width - 2, position.y)) {
            this.stopUp = true;
        }
        if (collidableGameObject.getHitBox().intersectsLine(position.x + 2, position.y + height, position.x + width - 2, position.y + height)){
            this.stopDown = true;
        }
        if (collidableGameObject.getHitBox().intersectsLine(position.x, position.y + 2, position.x, position.y + height - 2)){
            this.stopLeft = true;
        }
        if (collidableGameObject.getHitBox().intersectsLine(position.x + width, position.y + 2, position.x + width, position.y + height - 2)){
            this.stopRight = true;
        }

    }

    /**
     * Get the direction of the block
     * @return direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Set the direction of the block
     * @param direction direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidingGameObject;
import de.thd.graf.crillion.graphics.basicobjects.MovingGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.util.ArrayList;

/**
 * Block which moves when it get hit by the {@link Ball} with the same Color
 */
public class MovableBlock extends CollidingGameObject implements MovingGameObject {

     public enum Direction {STOP, UP, DOWN, LEFT, RIGHT}

    /**
     * Direction in which the block should move
     */
     public Direction direction;

    public enum StatusColor{RED, BLUE, GREEN, YELLOW, PURPLE}
    private final StatusColor statusColor;

     private boolean stopUp;
     private boolean stopDown;
     private boolean stopLeft;
     private boolean stopRight;
     private int moveIndex;

    /**
     * Create a MoveableBlock
     *
     * @param gameView get important Code from GameView
     * @param objectsToCollideWith List of objects where the block can collide with
     * @param statusColor in which color the block should be
     */
    public MovableBlock(GameView gameView, StatusColor statusColor, ArrayList<CollidableGameObject> objectsToCollideWith) {
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
        this.stopUp = false;
        this.stopDown = false;
        this.stopLeft = false;
        this.stopRight = false;
        this.moveIndex = 0;

        switch (statusColor) {
            case BLUE:
                break;
            case RED:
                this.blockImage = this.blockImage.replace('B','R');
                break;
            case GREEN:
                this.blockImage = this.blockImage.replace('B','G');
                break;
            case PURPLE:
                this.blockImage = this.blockImage.replace('B', 'P');
                break;
            case YELLOW:
                this.blockImage = this.blockImage.replace('B', 'Y');
                break;
        }
        this.statusColor = statusColor;
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
        if(otherObject.getClass() != this.getClass()){
            this.gamePlayManager.moveBlock(this, otherObject);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(this.blockImage, this.position.x, this.position.y, this.size, this.rotation);
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
               if(!stopUp && this.moveIndex <= 20){
                   this.position.up(1);
                   this.moveIndex ++;
               }
               else {
                   this.moveIndex = 0;
                   this.direction = Direction.STOP;
               }
               break;
            case DOWN:
                if(!stopDown && this.moveIndex <= 20) {
                    this.position.down(1);
                    this.moveIndex ++;
                }
                else {
                    this.moveIndex = 0;
                    this.direction = Direction.STOP;
                }
                break;
            case LEFT:
                if(!stopLeft && this.moveIndex <= 20) {
                    this.position.left(1);
                    this.moveIndex ++;
                }
                else {
                    this.moveIndex = 0;
                    this.direction = Direction.STOP;
                }
                break;
            case RIGHT:
                if(!stopRight && this.moveIndex <= 20) {
                    this.position.right(1);
                    this.moveIndex ++;
                }
                else {
                    this.moveIndex = 0;
                    this.direction = Direction.STOP;
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
     * Set the direction of the block
     * @param direction direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Get the color of the block as a String
     * @return the color of the moveable block
     */
    public String getStatusColor() {
        return String.valueOf(statusColor);
    }
}
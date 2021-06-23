package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidingGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.staticobjects.BoundaryBottom;
import de.thd.graf.crillion.graphics.staticobjects.BoundaryLeft;
import de.thd.graf.crillion.graphics.staticobjects.BoundaryRight;
import de.thd.graf.crillion.graphics.staticobjects.BoundaryTop;

import java.awt.*;
import java.util.ArrayList;

/**
 * The player object. A (color changing) ball which is controlled by the Player.
 */
public class Ball extends CollidingGameObject {
    private enum Status {RED, BLUE, GREEN, YELLOW, PURPLE}

    private final String blueBall;
    private final String greenBall;
    private final String redBall;
    private final String yellowBall;
    private final String purpleBall;
    private boolean shooting;
    private boolean changeDirectionTopToBottom;
    private final BoundaryLeft boundaryLeft;
    private final BoundaryRight boundaryRight;
    private final BoundaryTop boundaryTop;
    private final BoundaryBottom boundaryBottom;
    private boolean pauseUserInput;
    private boolean leftSideHit;
    private boolean rightSideHit;
    private int bounceIndex;

    /**
     * Constant to activate diagonal movement
     */
    public final static boolean DIAGONAL_MOVEMENT = true;
    /**
     * Constant to change player object
     */
    public final static boolean PLAYER_GRAPHIC = true;

    /**
     * Create the PlayerObject
     *
     * @param gameView             get important Code from GameView
     * @param objectsToCollideWith Game objects this game object can collide with.
     */
    public Ball(GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, objectsToCollideWith);
        this.position = new Position(GameView.WIDTH / 2, GameView.HEIGHT / 2);
        Status status = Status.RED;
        this.blueBall = "Blue-Ball.png";
        this.greenBall = "Green-Ball.png";
        this.redBall = "Red-Ball.png";
        this.yellowBall = "Yellow-Ball.png";
        this.purpleBall = "Purple-Ball.png";
        this.shooting = false;
        this.speedInPixel = 2;
        this.size = 1.25;
        this.width = 11 * (int) size;
        this.height = 11 * (int) size;
        this.hitBox.width = (int) (this.width - 1 * size);
        this.hitBox.height = (int) (this.height - 1 * size);
        this.changeDirectionTopToBottom = false;
        this.pauseUserInput = false;
        this.leftSideHit = false;
        this.rightSideHit = false;
        this.bounceIndex = 0;

        this.boundaryTop = (BoundaryTop) objectsToCollideWith.get(0);
        this.boundaryLeft = (BoundaryLeft) objectsToCollideWith.get(1);
        this.boundaryBottom = (BoundaryBottom) objectsToCollideWith.get(2);
        this.boundaryRight = (BoundaryRight) objectsToCollideWith.get(3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) (this.position.x + 3 * size);
        this.hitBox.y = (int) (this.position.y + 3 * size);
    }

    /**
     * {@inheritDoc}
     *
     * @param otherObject The other GameObject that is involved in the collision.
     */
    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

    }

    /**
     * Move PlayerObject to the left
     */
    public void left() {
        if (collidesWith(boundaryLeft)) {
            this.position.left(0);
        } else {
            this.position.left(this.speedInPixel);
        }
    }

    /**
     * Move PlayerObject to the right
     */
    public void right() {
        if (collidesWith(boundaryRight)) {
            this.position.right(0);
        } else {
            this.position.right(this.speedInPixel);
        }
    }

    /**
     * Move PlayerObject up
     */
    public void up() {
        this.position.up(this.speedInPixel);
    }

    /**
     * Move PlayerObject down
     */
    public void down() {
        this.position.down(this.speedInPixel);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        if (!PLAYER_GRAPHIC) {
            if (shooting) {
                gameView.addTextToCanvas("O", this.position.x, this.position.y, this.size, Color.WHITE, this.rotation);
                shooting = false;
            } else
                gameView.addTextToCanvas("X", this.position.x, this.position.y, this.size, Color.WHITE, this.rotation);
        } else
            gameView.addImageToCanvas(this.redBall, this.position.x, this.position.y, this.size, this.rotation);
        gameView.addRectangleToCanvas(this.hitBox.x, this.hitBox.y, this.hitBox.width, this.hitBox.height, 1, false, Color.RED);
    }


    private void updatePositition() {
        if (leftSideHit && this.bounceIndex <= 10) {
            left();
            moveTopToBottom();
            this.bounceIndex++;
        } else if (rightSideHit && this.bounceIndex <= 10) {
            right();
            moveTopToBottom();
            this.bounceIndex++;
        } else {
            this.leftSideHit = false;
            this.rightSideHit = false;
            this.bounceIndex = 0;
            this.pauseUserInput = false;
            moveTopToBottom();
        }


        if (collidesWith(boundaryTop)) {
            this.changeDirectionTopToBottom = true;
        } else if (collidesWith(boundaryBottom)) {
            this.changeDirectionTopToBottom = false;
        }
    }

    private void moveTopToBottom() {
        if (changeDirectionTopToBottom) {
            this.down();
        } else {
            this.up();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStatus() {
        updatePositition();
    }

    /**
     * @return file name
     */
    public String getBlueBall() {
        return blueBall;
    }

    /**
     * @return file name
     */
    public String getGreenBall() {
        return greenBall;
    }

    /**
     * @return file name
     */
    public String getRedBall() {
        return redBall;
    }

    /**
     * @return file name
     */
    public String getYellowBall() {
        return yellowBall;
    }

    /**
     * @return file name
     */
    public String getPurpleBall() {
        return purpleBall;
    }


    /**
     * @param changeDirectionTopToBottom boolean changeDirectionTopToBottom
     */
    public void setChangeDirectionTopToBottom(boolean changeDirectionTopToBottom) {
        this.changeDirectionTopToBottom = changeDirectionTopToBottom;
    }

    /**
     * Pause the user Input
     *
     * @param pauseUserInput blooean pauseUserInput
     */
    public void setPauseUserInput(boolean pauseUserInput) {
        this.pauseUserInput = pauseUserInput;
    }

    /**
     * Get the PauseUserInput
     *
     * @return the boolean pauseUserInput
     */
    public boolean isPauseUserInput() {
        return pauseUserInput;
    }

    /**
     * Set the boolean leftSideHit
     * @param leftSideHit boolean leftSideHit
     */
    public void setLeftSideHit(boolean leftSideHit) {
        this.leftSideHit = leftSideHit;
    }

    /**
     * Set value of rightSideHit
     * @param rightSideHit boolean rightSideHit
     */
    public void setRightSideHit(boolean rightSideHit) {
        this.rightSideHit = rightSideHit;
    }
}

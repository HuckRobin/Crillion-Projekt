package de.thd.graf.crillion.graphics.dynamicobjects;

import de.thd.graf.crillion.game.managers.GamePlayManager;
import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.basicobjects.CollidableGameObject;
import de.thd.graf.crillion.graphics.basicobjects.CollidingGameObject;
import de.thd.graf.crillion.graphics.basicobjects.Position;
import de.thd.graf.crillion.graphics.basicobjects.GameObject;
import de.thd.graf.crillion.graphics.staticobjects.BoundaryLeft;
import de.thd.graf.crillion.graphics.staticobjects.BoundaryRight;

import java.awt.*;
import java.util.ArrayList;

/**
 * The player object. A (color changing) ball which is controlled by the Player.
 */
public class Ball extends CollidingGameObject {

    private final String blueBall;
    private final String greenBall;
    private final String redBall;
    private final String yellowBall;
    private final String purpleBall;
    private boolean shooting;
    private boolean changeDirection;
    private BoundaryLeft boundaryLeft;
    private BoundaryRight boundaryRight;

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
     * @param gameView get important Code from GameView
     */
    public Ball(GameView gameView, ArrayList<CollidableGameObject> objectsToCollideWith) {
        super(gameView, objectsToCollideWith);
        this.position = new Position(GameView.WIDTH / 2, GameView.HEIGHT / 2);
        this.blueBall = "Blue-Ball.png";
        this.greenBall = "Green-Ball.png";
        this.redBall = "Red-Ball.png";
        this.yellowBall = "Yellow-Ball.png";
        this.purpleBall = "Purple-Ball.png";
        this.shooting = false;
        this.speedInPixel = 2;
        this.size = 1;
        this.width = 11 * (int) size;
        this.height = 11 * (int) size;
        this.hitBox = new Rectangle((int) this.position.x, (int) this.position.y, this.width - 1, this.height - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x + 3;
        this.hitBox.y = (int) this.position.y + 3;
    }

    /**
     * {@inheritDoc}
     * @param otherObject The other GameObject that is involved in the collision.
     */
    @Override
    public void reactToCollision(CollidableGameObject otherObject){    }

    /**
     * Move PlayerObject to the left
     */
    public void left() {
        this.position.left(this.speedInPixel);
    }

    /**
     * Move PlayerObject to the right
     */
    public void right() {
        this.position.right(this.speedInPixel);
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
     * The PlayerObjects shoots
     */
    public void shoot() {
        shooting = true;
        if(gameView.timerExpired("Shoot","blockExplosion")) {
            gameView.setTimer("Shoot", "blockExplosion", 300);
            gamePlayManager.shootBallExplosion(this.position);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void addToCanvas() {
        if (PLAYER_GRAPHIC == false) {
            if (shooting) {
                gameView.addTextToCanvas("O", this.position.x, this.position.y, this.size, Color.WHITE, this.rotation);
                shooting = false;
            }
            else
                gameView.addTextToCanvas("X", this.position.x, this.position.y, this.size, Color.WHITE, this.rotation);
        } else
            gameView.addImageToCanvas(this.redBall, this.position.x, this.position.y, this.size, this.rotation);
        gameView.addRectangleToCanvas(this.hitBox.x, this.hitBox.y, this.hitBox.width, this.hitBox.height, 1, false, Color.RED);
    }

    /**
     * Updates the position of the ball so that the ball is constantly moving
     */
    public void updatePositition(){
        if (this.position.x == GameView.WIDTH - this.hitBox.width - 10) {
            changeDirection = false;
        } else if (this.position.x == GameView.WIDTH - GameView.WIDTH + 10) {
            changeDirection = true;
        }

        if (changeDirection == true) {
            this.position.right(this.speedInPixel);
        } else {
            this.position.left(this.speedInPixel);
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
     * Return the boolean ChangeDirection
     * @return
     */
    public boolean isChangeDirection() {
        return changeDirection;
    }
}

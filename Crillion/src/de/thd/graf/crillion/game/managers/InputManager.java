package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.gameview.GameView;
import de.thd.graf.crillion.graphics.dynamicobjects.Ball;

import java.awt.event.KeyEvent;

class InputManager {

    private final GameView gameView;
    private final Ball ball;

    /**
     * Create the Input Manager
     * @param gameView get important code from GameView
     * @param ball
     */
    public InputManager(GameView gameView, Ball ball) {
        this.gameView = gameView;
        this.ball = ball;
    }

    /**
     * Get the user input and take action. Move the player object with the arrow keys.
     */
    public void updateUserInputs() {
        Integer[] gedruekteTasten = gameView.getKeyCodesOfCurrentlyPressedKeys();

        for (int keyCode : gedruekteTasten) {

            switch (keyCode) {
                case KeyEvent.VK_RIGHT:
                    if(this.ball.getDirection() != Ball.Direction.LEFT && this.ball.getDirection() != Ball.Direction.RIGHT) {
                        this.ball.right();
                    }
                    else {
                        continue;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(this.ball.getDirection() != Ball.Direction.LEFT && this.ball.getDirection() != Ball.Direction.RIGHT) {
                        this.ball.left();
                    }
                    else {
                        continue;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(this.ball.getDirection() != Ball.Direction.DOWN && this.ball.getDirection() != Ball.Direction.UP) {
                        this.ball.up();
                    }
                    else {
                        continue;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(this.ball.getDirection() != Ball.Direction.DOWN && this.ball.getDirection() != Ball.Direction.UP) {
                        this.ball.down();
                    }
                    else {
                        continue;
                    }
                    break;
            }

            if (Ball.DIAGONAL_MOVEMENT == false) {
                break;
            }
        }
    }

}

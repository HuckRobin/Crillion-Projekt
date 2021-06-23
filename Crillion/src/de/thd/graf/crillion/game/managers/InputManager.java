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

            if (this.ball.isPauseUserInput()) {
                break;
            } else {
                switch (keyCode) {
                    case KeyEvent.VK_RIGHT:
                        this.ball.right();
                        break;
                    case KeyEvent.VK_LEFT:
                        this.ball.left();
                        break;
                }

                if (Ball.DIAGONAL_MOVEMENT == false) {
                    break;
                }
            }
        }
    }

    public void pauseUserInput(){
        this.ball.setPauseUserInput(true);
      // if (gameView.timerExpired("pauseUserInput", "UserInput")){
      //     gameView.setTimer("pauseUserInput", "UserInput", 100);
      //     this.setBreakInput(false);
      // }
    }
}

package de.thd.graf.crillion.screen;

import de.thd.graf.crillion.gameview.GameView;

/**
 * Displays a start screen that enables the player chose between "Easy" and "Standard" difficulty. The player is also
 * able to end the game.
 */
public class StartScreen {
    private final GameView gameView;
    private boolean isDifficultySetToEasy;

    /**
     * Creates the screen;
     *
     * @param gameView GameView to show the screen on.
     */
    public StartScreen(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Shows the screen.
     */
    public void showStartScreen() {
        //@formatter:off
        String title =
                  ",ad8888ba,            \"\" 88 88 \"\"\n"+
                 "d8\"'  `\"8b            \"\" 88 88 \"\"\n"+
                 "d8'                      88 88 \n"+
                 "88         8b,dPPYba, 88 88 88 88  ,adPPYba,  8b,dPPYba,\n"+
                 "88         88P'   \"Y8 88 88 88 88 a8\"     \"8a 88P'   `\"8a\n"+
                  "Y8,        88         88 88 88 88 8b       d8 88       88\n" +
                   "Y8a.  .a8P 88         88 88 88 88 \"8a,   ,a8\" 88       88\n"+
                    "`\"Y8888Y\"' 88         88 88 88 88  `\"YbbdP\"'  88       88\n";

        String description = "\n\n\n"
                + "            Destroy all the the blocks!           \n\n"
                + "       But avoid the black ones, they are deadly!                 \n\n\n\n\n"
                + "         Use left and right to move the ball!   ";
        //@formatter:on
        isDifficultySetToEasy = gameView.showSimpleStartScreen(title, description);
    }

    /**
     * Returns the choice of the player.
     *
     * @return <code>true</code>, if the player has chosen "Easy", <code>false</code> otherwise.
     */
    public boolean isDifficultySetToEasy() {
        return isDifficultySetToEasy;
    }
}


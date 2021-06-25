package de.thd.graf.crillion.game.utilities;

/**
 * Represents the player of the game
 */
public class Player {
    /**
     * The number of lives, the player starts with.
     */
    public static final int MAXIMUM_NUMBER_OF_LIVES = 5;
    /**
     * Current number of lives of the player.
     */
    public int lives;
    /**
     * Current score f the player.
     */
    public int score;
    /**
     * Current level of the player.
     */
    public Level level;

    /**
     * Creates a player with lives set to maximum an a score of 5.
     */
    public Player() {
        this.lives = MAXIMUM_NUMBER_OF_LIVES;
        this.score = 0;
    }

}

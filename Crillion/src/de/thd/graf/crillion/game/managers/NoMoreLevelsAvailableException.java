package de.thd.graf.crillion.game.managers;

/**
 * Exepiton is thrown, if no further levels are available in the set of levels.
 */
public class NoMoreLevelsAvailableException extends RuntimeException {

    /**
     * Creates exception without parameters.
     */
    public NoMoreLevelsAvailableException() {
        super();
    }

    /**
     * Creates exception with message.
     */
    public NoMoreLevelsAvailableException(String message) {
        super(message);
    }
}

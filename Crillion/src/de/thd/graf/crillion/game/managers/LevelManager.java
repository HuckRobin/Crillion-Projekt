package de.thd.graf.crillion.game.managers;

import de.thd.graf.crillion.game.utilities.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the levels of the game.
 */
class LevelManager {
    private ArrayList<Level> levels;
    private int nextLevel;

    LevelManager() {
        Level level1 = new Level(1);
        levels = new ArrayList<>(List.of(level1));
        this.nextLevel = 0;
    }

    /**
     * Determines, if a next level exists.
     *
     * @return <code>true</code> if there is a next level.
     */
    public boolean hasNextLevel() {
        return nextLevel < levels.size();
    }

    /**
     * Returns the next level.
     *
     * @return Next level
     * @throws NoMoreLevelsAvailableException if no next level is available.
     */
    public Level getNextLevel() {
        Level level = levels.get(nextLevel);
        if (level == null) {
            throw new NoMoreLevelsAvailableException();
        }
        nextLevel++;
        return level;
    }
}

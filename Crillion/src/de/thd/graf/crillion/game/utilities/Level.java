package de.thd.graf.crillion.game.utilities;

import de.thd.graf.crillion.graphics.basicobjects.Position;

import java.util.LinkedList;
import java.util.List;

/**
 * A level of the game.
 */
public class Level {

    /**
     * Name of the level.
     */
    public final int name;

    /**
     * The amount of blocks are in the level
     */
    public int blocks;

    /**
     * Creates a level
     *
     * @param name Name of the level.
     */
    public Level(int name) {
        this.blocks = 0;
        this.name = name;
    }

    /**
     * return the Positions of the blocks
     * @param blockType the block object for which the position should return
     * @return Return the Position of the block object
     */
    public LinkedList<Position> getBlockPositionForTheLevel(String blockType, int level) {
        LinkedList<Position> positions = new LinkedList<>();

        switch (level) {
            case 1:
                switch (blockType) {
                    case "VanishingBlock_Blue":
                        positions.add(new Position(100, 100));
                        positions.addAll(List.of(
                                new Position(250, 250),
                                new Position(300, 150),
                                new Position(800, 200)));
                        this.blocks = this.blocks + positions.size();
                        return positions;
                    case "VanishingBlock_Red":
                        positions.addAll(List.of(
                                new Position(300, 250),
                                new Position(360, 150),
                                new Position(600, 200)));
                        this.blocks = this.blocks + positions.size();
                        return positions;
                    case "VanishingBlock_Yellow":
                        positions.addAll(List.of(
                                new Position(400, 400),
                                new Position(360, 250),
                                new Position(600, 400)));
                        this.blocks = this.blocks + positions.size();
                        return positions;
                    case "WallBlock":
                        positions.addAll(List.of(
                                new Position(300, 400)));
                        return positions;
                    case "ColorChangingBlock_Blue":
                        positions.addAll(List.of(
                                new Position(200, 500)));
                    case "ColorChangingBlock_Red":
                        positions.addAll(List.of(
                                new Position(700, 500)));
                        return positions;
                    case "ColorChangingBlock_Yellow":
                        positions.addAll(List.of(
                                new Position(750, 500)));
                        return positions;
                    case "DeadlyBlock":
                        positions.addAll(List.of(
                                new Position(600, 300)));
                        return positions;
                    case "MovableBlock_Blue":
                        positions.addAll(List.of(
                                new Position(800, 150)));
                        return positions;
                    case "MovableBlock_Red":
                        positions.addAll(List.of(
                                new Position(800, 100)));
                        return positions;
                    default:
                        return null;
                }
            default:
                return null;
        }
    }

}
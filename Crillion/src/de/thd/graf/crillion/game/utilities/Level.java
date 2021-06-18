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
     * Creates a level
     *
     * @param name Name of the level.
     */
    public Level(int name) {
        this.name = name;
    }

    /**
     * return the Positions of the blocks
     * @param blockType the block object for which the position should return
     * @return
     */
    public LinkedList<Position> level1(String blockType){
        LinkedList<Position> positions = new LinkedList<Position>();

        switch(blockType){
            case "VanishingBlock":
                positions.add(new Position(100,100));
                positions.addAll(List.of(
                        new Position(250,250),
                        new Position(300,300)));
                return positions;
            case "WallBlock":
                positions.addAll(List.of(
                        new Position(300, 400)));
                return positions;
            case "ColorChangingBlock":
                positions.addAll(List.of(
                        new Position(200, 500)));
                return positions;
            case "DeadlyBlock":
                positions.addAll(List.of(
                        new Position(600, 300)));
                return positions;
            case "MovableBlock":
                positions.addAll(List.of(
                        new Position(800, 150)));
                return positions;
            default:
                return null;
        }
    }

}
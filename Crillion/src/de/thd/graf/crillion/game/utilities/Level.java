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
     * @param levelname get the level
     * @return Return the Position of the block object
     */
    public LinkedList<Position> getBlockPositionForTheLevel(String blockType, int levelname){
        LinkedList<Position> positions = new LinkedList<>();

        switch (levelname) {
            case 1:
                switch (blockType) {
                    case "VanishingBlock_Blue":
                        positions.addAll(List.of(
                                new Position(150, 300),
                                new Position(150, 330),
                                new Position(150, 360),
                                new Position(150, 390),
                                new Position(150, 420),
                                new Position(150, 450),

                                new Position(450, 300),
                                new Position(450, 330),
                                new Position(450, 360),
                                new Position(450, 390),
                                new Position(450, 420),
                                new Position(450, 450),

                                new Position(750, 300),
                                new Position(750, 330),
                                new Position(750, 360),
                                new Position(750, 390),
                                new Position(750, 420),
                                new Position(750, 450),

                                new Position(880, 495),
                                new Position(880, 460),
                                new Position(915, 460)));
                        this.blocks += positions.size();
                        return positions;
                    case "VanishingBlock_Red":
                        positions.addAll(List.of(
                                new Position(340, 165),
                                new Position(370, 165),
                                new Position(400, 165),
                                new Position(430, 165),
                                new Position(460, 165),
                                new Position(490, 165),
                                new Position(520, 165),
                                new Position(550, 165),
                                new Position(580, 165),
                                new Position(610, 165),
                                new Position(845, 75),
                                new Position(845, 110),
                                new Position(845, 145),
                                new Position(880, 75),
                                new Position(880, 110),
                                new Position(880, 145),
                                new Position(915, 145),

                                new Position(300, 300),
                                new Position(300, 330),
                                new Position(300, 360),
                                new Position(300, 390),
                                new Position(300, 420),
                                new Position(300, 450),

                                new Position(600, 300),
                                new Position(600, 330),
                                new Position(600, 360),
                                new Position(600, 390),
                                new Position(600, 420),
                                new Position(600, 450),

                                new Position(915, 75)));
                        this.blocks += positions.size();
                        return positions;
                    case "WallBlock":
                        positions.addAll(List.of(
                                new Position(10, 200),
                                new Position(40, 200),
                                new Position(70, 200),
                                new Position(100, 200),
                                new Position(130, 200),
                                new Position(160, 200),
                                new Position(220, 200),
                                new Position(250, 200),
                                new Position(280, 200),
                                new Position(280, 100),
                                new Position(280, 125),
                                new Position(280, 150),
                                new Position(280, 175),
                                new Position(310, 200),
                                new Position(340, 200),
                                new Position(370, 200),
                                new Position(400, 200),
                                new Position(430, 200),
                                new Position(460, 200),
                                new Position(490, 200),
                                new Position(520, 200),
                                new Position(550, 200),
                                new Position(580, 200),
                                new Position(610, 200),
                                new Position(640, 200),
                                new Position(670, 200),
                                new Position(700, 200),
                                new Position(730, 200),
                                new Position(760, 200),
                                new Position(790, 200),
                                new Position(820, 200),
                                new Position(850, 200),
                                new Position(880, 200),
                                new Position(915, 200)));
                        return positions;
                    case "ColorChangingBlock_Blue":

                        positions.addAll(List.of(
                                new Position(915, 110)));
                        return positions;
                    case "ColorChangingBlock_Red":
                        positions.addAll(List.of(
                                new Position(915, 495)));
                        return positions;
                    case "DeadlyBlock":
                        positions.addAll(List.of(
                                new Position(65, 370),
                                new Position(230, 370),
                                new Position(375, 370),
                                new Position(525, 370),
                                new Position(675, 370),
                                new Position(850, 370),
                                new Position(450, 60),
                                new Position(700, 60),
                                new Position(575, 60),
                                new Position(720, 165)));
                        return positions;
                    case "MovableBlock_Blue":
                        positions.addAll(List.of(
                        ));
                        return positions;
                    case "MovableBlock_Red":
                        positions.addAll(List.of(
                                new Position(280, 65),
                                new Position(190, 165)));
                        return positions;
                    default:
                        return null;
                }
            default:
                return null;
        }
    }

}
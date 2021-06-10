package de.thd.graf.crillion.graphics.basicobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Create the movement Patterns for  the RandomBall
 */
public class MovementPatterns {
    private final HashMap<String, ArrayList<Position>> hashMap;

    private final ArrayList<Position> square;
    private final ArrayList<Position> zigZag;

    private final Random random;

    /**
     * Create movement Pattern
     */
    public MovementPatterns() {
        this.hashMap = new HashMap<>();
        this.square =
                new ArrayList<>(List.of(
                        new Position(30, 30),
                        new Position(930, 30),
                        new Position(930, 510),
                        new Position(30, 510)));
        this.zigZag =
                new ArrayList<>(List.of(
                        new Position(300, 200),
                        new Position(400, 340),
                        new Position(500, 200),
                        new Position(600, 340),
                        new Position(700, 200),
                        new Position(800, 340)));
        this.hashMap.put("Square", square);
        this.hashMap.put("ZigZag", zigZag);
        this.random = new Random();
    }

    /**
     * Return the RandomPattern Square or ZigZag
     * @return "Square" or "ZigZag" its a random choice
     */
    public String getRandomPattern(){
        if (random.nextBoolean()) {
            return "Square";
        } else {
            return "ZigZag";
        }
    }

    /**
     * Get the Arraylist of the key
     * @param pattern the keys variable
     * @return the arraylist which is linked to the key
     */
    public ArrayList getPattern(String pattern){
        return hashMap.get(pattern);
    }
}
